package com.tre.jdevtemplateboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tre.jdevtemplateboot.domain.po.Category;
import com.tre.jdevtemplateboot.mapper.CategoryMapper;
import com.tre.jdevtemplateboot.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author JDev
 * @since 2018-10-12
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>  implements ICategoryService {


}
