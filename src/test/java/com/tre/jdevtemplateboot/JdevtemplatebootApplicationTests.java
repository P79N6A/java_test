package com.tre.jdevtemplateboot;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tre.jdevtemplateboot.domain.po.ProductOrder;
import com.tre.jdevtemplateboot.mapper.ProductOrderMapper;
import com.tre.jdevtemplateboot.service.IProductOrderService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JdevtemplatebootApplicationTests {

    @Autowired
    private IProductOrderService iProductOrderService;

    @Test
    @Ignore
    public void contextLoads() {

        Wrapper wrapper = new QueryWrapper();
        List<ProductOrder> list = iProductOrderService.list(null);
        list.forEach(System.out::println);
    }

    @Test
    @Ignore
    public void insertProductOrderData(){

        Collection<ProductOrder> collection = new ArrayList<>();

        ProductOrder productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10001"));
        productOrder.setDetailid(Long.parseLong("20001"));
        productOrder.setUserid(Long.parseLong("1000429901"));
        productOrder.setOrdername("10001 容器技术");
        productOrder.setDetailname("20001 双十一Docker容器");
        productOrder.setUsername("1000429901 yuanmingyin");
        collection.add(productOrder);

        productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10002"));
        productOrder.setDetailid(Long.parseLong("20002"));
        productOrder.setUserid(Long.parseLong("1000429902"));
        productOrder.setOrdername("10002 容器技术");
        productOrder.setDetailname("20002 双十一Docker容器");
        productOrder.setUsername("1000429902 yuanmingyin");
        collection.add(productOrder);

        productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10003"));
        productOrder.setDetailid(Long.parseLong("20003"));
        productOrder.setUserid(Long.parseLong("1000429903"));
        productOrder.setOrdername("10003 1111 容器技术");
        productOrder.setDetailname("20003 1111 双十一Docker容器");
        productOrder.setUsername("1000429903 1111 yuanmingyin");
        collection.add(productOrder);

        productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10004"));
        productOrder.setDetailid(Long.parseLong("20004"));
        productOrder.setUserid(Long.parseLong("1000429904"));
        productOrder.setOrdername("10004 容器技术");
        productOrder.setDetailname("20004 双十一Docker容器");
        productOrder.setUsername("1000429904 yuanmingyin");
        collection.add(productOrder);

        productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10007"));
        productOrder.setDetailid(Long.parseLong("20007"));
        productOrder.setUserid(Long.parseLong("2000429907"));
        productOrder.setOrdername("210007 容器技术");
        productOrder.setDetailname("20007 双十一Docker容器");
        productOrder.setUsername("2000429907 yuanmingyin");
        collection.add(productOrder);

        //iProductOrderService.saveBatch(list);
        iProductOrderService.saveOrUpdateBatch(collection);

    }

    @Test
    @Ignore
    public void insertProductOrderData1(){

        Collection<ProductOrder> collection = new ArrayList<>();

        //要更新的数据
        ProductOrder productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10001"));
        productOrder.setDetailid(Long.parseLong("30001"));
        productOrder.setUserid(Long.parseLong("1000429901"));
        productOrder.setOrdername("10001 222222 容器技术");
        productOrder.setDetailname("20001  222222 双十一Docker容器");
        productOrder.setUsername("10001   222222 aaaaa");
        collection.add(productOrder);

        //要插入的新数据
        productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10008"));
        productOrder.setDetailid(Long.parseLong("20008"));
        productOrder.setUserid(Long.parseLong("1000429908"));
        productOrder.setOrdername("10008 容器技术");
        productOrder.setDetailname("20008 双十一Docker容器");
        productOrder.setUsername("10008  fffffffff");
        collection.add(productOrder);

        iProductOrderService.saveOrUpdateBatch(collection,2);
    }

    @Test
    @Ignore
    public void insertCustomProductOrderData1() {

        List<ProductOrder> list = new ArrayList<>();

        //要插入的新数据
        ProductOrder productOrder =new ProductOrder();
        productOrder.setOrderid(Long.parseLong("10012"));
        productOrder.setDetailid(Long.parseLong("20012"));
        productOrder.setUserid(Long.parseLong("1000429912"));
        productOrder.setOrdername("100112 容器技术");
        productOrder.setDetailname("20012 双十一Docker容器");
        productOrder.setUsername("10012  fffffffff");
        list.add(productOrder);

        iProductOrderService.insertCustomBatch(list);

    }


}
