package com.kinghao.dian.controller;

import com.github.pagehelper.PageInfo;
import com.kinghao.dian.annotation.Auth;
import com.kinghao.dian.annotation.ReSubmit;
import com.kinghao.dian.dto.request.ExampleRequest;
import com.kinghao.dian.enums.UserType;
import com.kinghao.dian.service.ExampleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author kinghao
 * @version 2020/10/26 13:34
 */
@RestController
@Validated
public class ExampleController {

    @Resource(name = "exampleService")
    ExampleService exampleService;

    @Auth(UserType.PERSONAL)
    @ReSubmit(1000)
    @PostMapping("/example")
    @ApiOperation(value = "示例接口", response = PageInfo.class)
    public Object list(@Valid @NotNull ExampleRequest exampleRequest) {

        return exampleService.doService(exampleRequest);
    }

}
