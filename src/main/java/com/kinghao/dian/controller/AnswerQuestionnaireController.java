package com.kinghao.dian.controller;

import com.kinghao.dian.entity.*;
import com.kinghao.dian.util.BsonUtil;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
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

@Api(tags = "Answer Questionnaire Operation")
@RestController
@RequestMapping("/ansquestionnaire")
@Slf4j
@Validated
public class AnswerQuestionnaireController {
    @ApiOperation(value = "显示待答问卷")
    @GetMapping("/show")
    public Object show(@Valid User user){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        FindIterable<Document> result=collection.find().filter(Filters.eq("targetGroup",user.getType())).iterator();
//        while(result4.hasNext()) {
//            System.out.println(result4.next());
//        }
        System.out.println("019");
        return result;
    }

    @ApiOperation(value = "显示已作答答问卷")
    @GetMapping("/showans")
    public Object show(@Valid User user){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("answer_questionnaire");

        FindIterable<Document> result=collection.find();
//        while(result4.hasNext()) {
//            System.out.println(result4.next());
//        }
        System.out.println("019");
        return result;
    }

    @ApiOperation(value = "回答问卷")
    @PostMapping("/answer")
    public Object answer(@Valid AnswerQuestionnaire questionnaire){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("answer_questionnaire");

        collection.insertOne(BsonUtil.toDocument(questionnaire));
        System.out.println("019");
        return "Add successfully!";
    }

    @ApiOperation(value = "删除待答问卷")
    @PostMapping("/delete")
    public Object delete(@Valid AnswerQuestionnaire questionnaire){
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("Project");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> collection=db.getCollection("found_question");

        collection.deleteOne(BsonUtil.toDocument(questionnaire));
        System.out.println("020");
        return "Delete successfully!";

    }
    }
}
