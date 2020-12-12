package com.kinghao.dian.controller;


import com.kinghao.dian.dto.request.AddQuestionnaireRequest;

import com.kinghao.dian.dto.request.DeleteQuestionnaireByIdRequest;
import com.kinghao.dian.dto.request.DeleteQuestionnaireByTitleRequest;
import com.kinghao.dian.entity.Question;
import com.kinghao.dian.util.BsonUtil;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.DeleteResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
public class QuestionnaireController {
    @ApiOperation(value = "添加问卷")
    @PostMapping("/addQuestionnaire")
    public Object addQuestionnaire(@Valid AddQuestionnaireRequest addQuestionnaireRequest){
        MongoDatabase db = MongoDBUtil.getConnect();
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        collection.insertOne(BsonUtil.toDocument(addQuestionnaireRequest));
        System.out.println("019");
        return "Add successfully!";
    }

    @ApiOperation(value = "根据标题删除问卷")
    @PostMapping("/deleteQuestionnaireByitle")
    public Object deleteQuestionnaireBytitle(@Valid DeleteQuestionnaireByTitleRequest deleteQuestionnaireByTitleRequest){
        MongoDatabase db = MongoDBUtil.getConnect();
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");
        Bson filter = Filters.eq("title", deleteQuestionnaireByTitleRequest.getTitle());
        DeleteResult deleteResult = collection.deleteOne(filter);
        int count = (int) deleteResult.getDeletedCount();
        collection.deleteOne(BsonUtil.toDocument(deleteQuestionnaireByTitleRequest));
        System.out.println("019");
        return "Delete"+count+"records successfully!";
    }

    @ApiOperation(value = "根据问卷id删除问卷")
    @PostMapping("/deleteQuestionnaireById")
    public Object deleteQuestionnaireById(@Valid DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest){
        MongoDatabase db = MongoDBUtil.getConnect();
        MongoCollection<Document> collection=db.getCollection("found_questionnaire");

        Bson filter = Filters.eq("id", deleteQuestionnaireByIdRequest.getId());
        DeleteResult deleteResult = collection.deleteOne(filter);
        int count = (int) deleteResult.getDeletedCount();
        collection.deleteOne(BsonUtil.toDocument(deleteQuestionnaireByIdRequest));
        System.out.println("019");
        return "Delete"+count+"records successfully!";
    }



}

