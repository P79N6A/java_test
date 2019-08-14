package com.tre.jdevtemplateboot.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tre.jdevtemplateboot.annotatiaon.Authentication;
import com.tre.jdevtemplateboot.annotatiaon.CurrentUser;
import com.tre.jdevtemplateboot.common.pojo.ResponseResult;
import com.tre.jdevtemplateboot.common.util.LFileDownloadUtils;
import com.tre.jdevtemplateboot.common.util.LLoggerUtils;
import com.tre.jdevtemplateboot.common.util.LPojoValidatorUtils;
import com.tre.jdevtemplateboot.domain.po.Category;
import com.tre.jdevtemplateboot.domain.po.User;
import com.tre.jdevtemplateboot.domain.vo.LoginVO;
import com.tre.jdevtemplateboot.exception.SysExceptionValidator;
import com.tre.jdevtemplateboot.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author JDev
 * @since 2018-10-12
 */
@Controller
@RequestMapping("/category")
@ConfigurationProperties
public class CategoryController {

    private Logger logger = LLoggerUtils.Logger(LLoggerUtils.LogFileName.Business);

    @Autowired
    private  CategoryMapper categoryMapper;

    //获取文件上传路径
    @Value("${spring.servlet.multipart.location}")
    private  String location;

    /**
    * @Description: 获取所有数据 
    * @Param: [request] 
    * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult 
    * @Author: JDev
    * @Date: 2018/12/20 
    **/
    @RequestMapping(value = "/get/data")
    public ResponseResult getNoPageData(HttpServletRequest request) throws Exception {

        QueryWrapper<Category> wrapper = new QueryWrapper<>();
        //wrapper.eq("CId","10001");
        List<Category> blogList =  categoryMapper.selectList(wrapper);
        return  ResponseResult.buildOK(blogList);
    }

    /** 
    * @Description: 分页数据获取
    * @Param: [] 
    * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult 
    * @Author: JDev
    * @Date: 2018/12/20 
    **/
    @RequestMapping("/page/data")
    public ResponseResult getPageData() throws Exception {

        PageHelper.startPage(1,10);
        List<Category> blogList =  categoryMapper.selectList(null);
        return  ResponseResult.buildOK(new PageInfo<Category>(blogList));
    }

    /** 
    * @Description: MVC index页面 
    * @Param: [] 
    * @return: java.lang.String 
    * @Author: JDev
    * @Date: 2018/12/20 
    **/
    @RequestMapping("/index")
    public String  index(){
        return "index";
    }


    /** 
    * @Description: 多文件上传 
    * @Param: [file] 
    * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult 
    * @Author: JDev
    * @Date: 2018/12/20 
    **/
    @PostMapping("/upload/multipart")
    public ResponseResult uploadRequest(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();
        File destFile = new File(fileName);

        file.transferTo(destFile);

        return  ResponseResult.buildOK();
    }

    /**
     * @Description: 文件下载
     * @Param: [request, response]
     * @return: void
     * @Author: JDev
     * @Date: 2018/12/20
     **/
    @RequestMapping(value = "fileDownload")
    public void fileDownload(@RequestParam(value="fileName",required=true) String fileName
            ,HttpServletRequest request, HttpServletResponse response) {

        LFileDownloadUtils.FileDownload(location,fileName,request,response);
    }

    /**
    * @Description: 数据对象校验
    * @Param: [loginVO, errors]
    * @return: com.tre.jdevtemplateboot.common.pojo.ResponseResult
    * @Author: JDev
    * @Date: 2018/12/03
    **/
    @PostMapping("/upload/validator")
    public ResponseResult loginValidator(@RequestBody  @Valid LoginVO loginVO , Errors errors) throws SysExceptionValidator {
        //校验字段

        LPojoValidatorUtils.pojoValidator(errors);
        return  ResponseResult.buildOK();
    }


    @PostMapping(value = "/checkAll")
    @Authentication
    public ResponseResult checkAll(@CurrentUser("hello") User user){

        System.out.println("checkAll：" + user.getUserName());

        return  ResponseResult.buildOK(user);
    }

}
