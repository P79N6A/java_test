package com.tre.jdevtemplateboot.web.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * InnoDB free: 4096 kB 前端控制器
 * </p>
 *
 * @author JDev
 * @since 2019-08-14
 */
//注意：@controller不是@RestController，使用@RestController会返回“index”字符串
@Controller
@RequestMapping("userinfor")
public class UserinforController {
    @RequestMapping("/test")
    public String  test(){
        return "index";
    }
}
