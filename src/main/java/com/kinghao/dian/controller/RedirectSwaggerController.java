package com.kinghao.dian.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @Author Kinghao
 * @Date 2020/10/21 17:42
 * @Version 1.0
 */
@Controller
public class RedirectSwaggerController {

    //重定向
    @ApiIgnore
    @RequestMapping("/")
    String redirect(){
        return "redirect:/doc.html";
    }
}
