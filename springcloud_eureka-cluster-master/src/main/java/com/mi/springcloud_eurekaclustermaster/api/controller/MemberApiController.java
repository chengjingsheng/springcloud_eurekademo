package com.mi.springcloud_eurekaclustermaster.api.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Misc
 * @create 2020-11-13 15:10
 * @PACKAGE_NAME com.mi.springcloud_eurekaclustermaster.api.controller
 * @PROJECT_NAME springcloud_eurekademo
 */

@RestController
public class MemberApiController {

    @RequestMapping("/getMenber")
    public  String getMenber(){


        return "this is menber ,我是服务";
    }
}
