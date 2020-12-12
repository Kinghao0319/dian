package com.kinghao.dian.controller;

import com.kinghao.dian.dto.request.AddQuestionnaireRequest;
import com.kinghao.dian.service.FqService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @Author Kinghao
 * @Date 2020/11/6 14:55
 * @Version 1.0
 */

@Api(tags = "自定义问卷")
@RestController
@RequestMapping("/fq")
@Slf4j
@Validated
public class FqController {

    @Autowired
    private FqService fqService;

    @ApiOperation(value = "创建问卷")
    @PostMapping("/addFq")
    public Object addFq(@Valid AddQuestionnaireRequest addFqRequest){
        //System.out.println(addFqRequest.getTitle());
        //String content= JSON.toJSONString(addFqRequest.getContent());
        //System.out.println("controller:"+content);
        fqService.addFq(addFqRequest);
        return "Add successfully!";
    }

    @ApiOperation(value = "根据id查找问卷")
    @PostMapping("/queryFqById")
    public Object queryFqById(Integer fqId){
        return fqService.queryFqById(fqId);
    }
}
