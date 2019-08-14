package com.tre.jdevtemplateboot.service;

import com.tre.jdevtemplateboot.domain.po.ProductOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author JDev
 * @since 2018-11-20
 */
public interface IProductOrderService extends IService<ProductOrder> {

    public void  insertCustomBatch(List<ProductOrder> list);

    public void  insertCustomBatch2(List<ProductOrder> list);
}
