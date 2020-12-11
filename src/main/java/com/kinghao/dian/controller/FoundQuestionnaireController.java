package com.kinghao.dian.controller;


import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.request.RegisterRequest;
import com.kinghao.dian.entity.*;
import com.kinghao.dian.enums.UserType;
import com.kinghao.dian.util.AssertUtil;
import com.kinghao.dian.util.BsonUtil;
import com.kinghao.dian.util.PhoneNumUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/12/8 22:51
 * @Version 1.0
 */
@Api(tags = "QuestionNaire Operation")
@RestController
@RequestMapping("/questionnaire")
@Slf4j
@Validated
public class FoundQuestionnaireController {
    @ApiOperation(value = "显示创建的问卷")
    @GetMapping("/show")
    public Object show(@Valid FoundQuestionnaire questionnaire){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        MongoCursor result=collection.find().filter(Filters.eq("founder_id",questionnaire.getFounder_id());
//        while(result4.hasNext()) {
//            System.out.println(result4.next());
//        }
        System.out.println("019");
        return result;
    }

    @ApiOperation(value = "创建问卷")
    @PostMapping("/add")
    public Object answer(@Valid AnswerQuestionnaire questionnaire){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        collection.insertOne(BsonUtil.toDocument(questionnaire));
        System.out.println("019");
        return "Add successfully!";
    }

    @ApiOperation(value = "删除创建的问卷")
    @PostMapping("/delete")
    public Object delete(@Valid AnswerQuestionnaire questionnaire){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        collection.deleteOne(BsonUtil.toDocument(questionnaire));
        System.out.println("020");
        return "Delete successfully!";

    }
}
