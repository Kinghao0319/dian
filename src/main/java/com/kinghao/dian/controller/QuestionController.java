package com.kinghao.dian.controller;

import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.request.RegisterRequest;
import com.kinghao.dian.entity.Question;
import com.kinghao.dian.enums.UserType;
import com.kinghao.dian.util.AssertUtil;
import com.kinghao.dian.util.BsonUtil;
import com.kinghao.dian.util.PhoneNumUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/12/8 22:51
 * @Version 1.0
 */
@Api(tags = "Question Operation")
@RestController
@RequestMapping("/question")
@Slf4j
@Validated
public class QuestionController {
    @ApiOperation(value = "添加问题")
    @PostMapping("/add")
    public Object register(@Valid Question question){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("test");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("question");

        collection.insertOne(BsonUtil.toDocument(question));
        //System.out.println("019");
        return "Add successfully!";
    }
}
