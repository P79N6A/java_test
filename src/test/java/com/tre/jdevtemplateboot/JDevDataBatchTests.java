package com.tre.jdevtemplateboot;

import com.tre.jdevtemplateboot.domain.po.ProductOrder;
import com.tre.jdevtemplateboot.mapper.ProductOrderMapper;
import com.tre.jdevtemplateboot.service.IProductOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: 批处理测试
 * @author: JDev
 * @create: 2018-12-20 11:06
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class JDevDataBatchTests {

    @Autowired
    private IProductOrderService iProductOrderService;

    @Autowired
    private ProductOrderMapper productOrderMapper;


    @Test
    public  void  testInsertCustomBatch(){

        //清空数据
        productOrderMapper.deleteAllCustom();
        //批量插入(分批，一次1000条)
        List<ProductOrder> list = getMotoData();
        long start = System.currentTimeMillis();
        iProductOrderService.insertCustomBatch(list);
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (end - start) + "---------------");
    }

    @Test
    public  void  testInsertCustomBatch2(){

        //清空数据
        productOrderMapper.deleteAllCustom();
        //批量插入
        List<ProductOrder> list = getMotoData();
        long start = System.currentTimeMillis();
        iProductOrderService.insertCustomBatch2(list);
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (end - start) + "---------------");
    }

    //批数据(建议5000件以下时)插入后，一次提交事务
    @Test
    public  void  testInsertForEachBatch(){

        //清空数据
        productOrderMapper.deleteAllCustom();
        //批量插入
        List<ProductOrder> list = getMotoData();
        long start = System.currentTimeMillis();
        productOrderMapper.insertForEachBatch(list);
        long end = System.currentTimeMillis();
        System.out.println("---------------" + (end - start) + "---------------");
    }

    //生产数据
    private List<ProductOrder> getMotoData(){

        List<ProductOrder> list = new ArrayList<>();
        ProductOrder productOrder = null;
        for(int i = 0; i < 5000; i++){
            productOrder =new ProductOrder();
            productOrder.setOrderid(Long.parseLong("100"+i));
            productOrder.setDetailid(Long.parseLong("200"+i));
            productOrder.setUserid(Long.parseLong("4299"+i));
            productOrder.setOrdername("100"+ i + " 容器技术");
            productOrder.setDetailname("100"+ i + " 双十一Docker容器");
            productOrder.setUsername("100"+ i +"  fffffffff");
            list.add(productOrder);
        }
        return list;
    }

}
