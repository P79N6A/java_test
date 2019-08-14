package com.tre.jdevtemplateboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tre.jdevtemplateboot.domain.po.ProductOrder;
import com.tre.jdevtemplateboot.mapper.ProductOrderMapper;
import com.tre.jdevtemplateboot.service.IProductOrderService;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JDev
 * @since 2018-11-20
 */
@Service
public class ProductOrderServiceImpl extends ServiceImpl<ProductOrderMapper, ProductOrder> implements IProductOrderService {


    @Autowired
    private SqlSessionTemplate sqlSessionTemplate;

    /** 
    * @Description:  批数据插入后，分配提交事务
     *
    * @Param: [list] 
    * @return: void 
    * @Author: JDev
    * @Date: 2018/12/24 
    **/
    @Override
    public void  insertCustomBatch(List<ProductOrder> list){
        SqlSession session =sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        ProductOrderMapper productOrderMapper = session.getMapper(ProductOrderMapper.class);
        try{
            if(null != list || list.size()>0) {
                ProductOrder productOrder = null;
                int lsize = list.size();
                for(int i = 0, n=list.size(); i < n; i++){
                    productOrder = list.get(i);
                    productOrderMapper.saveBatch(productOrder);
                    if((i>0 && i % 1000 == 0) || i == lsize - 1) {
                        // 手动每1000个一提交，提交后无法回滚
                        session.commit();
                        // 清理缓存，防止溢出
                        session.clearCache();
                    }
                }
            }
        }
        catch (Exception e){
            // 没有提交的数据可以回滚
            session.rollback();
        }
        finally {
            session.close();
        }
    }

    /** 
    * @Description: 批数据插入后，一次提交事务
    * @Param: [list] 
    * @return: void 
    * @Author: JDev
    * @Date: 2018/12/24 
    **/
    @Override
    public void insertCustomBatch2(List<ProductOrder> list) {

        SqlSession session =sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH,false);
        ProductOrderMapper productOrderMapper = session.getMapper(ProductOrderMapper.class);
        try{
            if(null != list || list.size()>0) {
                ProductOrder productOrder = null;
                for(int i = 0, n=list.size(); i < n; i++){
                    productOrder = list.get(i);
                    productOrderMapper.saveBatch(productOrder);
                }
                session.commit();
            }
        }
        catch (Exception e){
            // 没有提交的数据可以回滚
            session.rollback();
        }
        finally{
            session.close();
        }
    }

}
