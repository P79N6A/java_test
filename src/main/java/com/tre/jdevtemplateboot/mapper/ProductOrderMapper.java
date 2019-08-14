package com.tre.jdevtemplateboot.mapper;

import com.tre.jdevtemplateboot.domain.po.ProductOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author JDev
 * @since 2018-11-20
 */
public interface ProductOrderMapper extends BaseMapper<ProductOrder> {

    void saveBatch(ProductOrder productOrder);

    void insertForEachBatch(List<ProductOrder> list);

    void deleteAllCustom();
}
