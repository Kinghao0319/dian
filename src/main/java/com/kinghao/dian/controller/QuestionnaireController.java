package com.kinghao.dian.controller;


import com.kinghao.dian.dto.request.AddQuestionnaireRequest;

import com.kinghao.dian.dto.request.DeleteQuestionnaireByIdRequest;
import com.kinghao.dian.dto.request.DeleteQuestionnaireByTitleRequest;
import com.kinghao.dian.entity.Question;
import com.kinghao.dian.entity.Questionnaire;
import com.kinghao.dian.service.impl.FqServiceImpl;
import com.kinghao.dian.util.BsonUtil;
import com.kinghao.dian.util.MongoUtil;
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
    FqServiceImpl fqServiceImpl;
    String dbName = "Project";
    String collName = "questionnaire";

    @ApiOperation(value = "添加问卷")
    @PostMapping("/addQuestionnaire")
    public Object addQuestionnaire(@Valid AddQuestionnaireRequest addQuestionnaireRequest){
        MongoCollection<Document> collection = MongoUtil.instance.getCollection(dbName, collName);
        fqServiceImpl.addQuestionnaire(addQuestionnaireRequest);
        return "Add successfully!";
    }

//    @ApiOperation(value = "根据标题删除问卷")
//    @PostMapping("/deleteQuestionnaireByitle")
//    public Object deleteQuestionnaireBytitle(@Valid DeleteQuestionnaireByTitleRequest deleteQuestionnaireByTitleRequest){
//        MongoCollection<Document> collection = MongoUtil.instance.getCollection(dbName, collName);
//        fqServiceImpl.deleteQuestionnaire();
//        return "Delete successfully!";
//    }

    @ApiOperation(value = "根据问卷id删除问卷")
    @PostMapping("/deleteQuestionnaireById")
    public Object deleteQuestionnaireById(@Valid DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest){
        MongoCollection<Document> collection = MongoUtil.instance.getCollection(dbName, collName);
        fqServiceImpl.deleteById(deleteQuestionnaireByIdRequest);
        return "Delete records successfully!";
    }

//    @ApiOperation(value = "根据标题筛选问卷")
//    @PostMapping("/selectQuestionnaireByitle")
//    public Object deleteQuestionnaireBytitle(@Valid DeleteQuestionnaireByTitleRequest deleteQuestionnaireByTitleRequest){
//        MongoDatabase db = MongoDBUtil.getConnect();
//        MongoCollection<Document> collection=db.getCollection("found_questionnaire");
//        Bson filter = Filters.eq("title", deleteQuestionnaireByTitleRequest.getTitle());
//        DeleteResult deleteResult = collection.deleteOne(filter);
//        int count = (int) deleteResult.getDeletedCount();
//        collection.deleteOne(BsonUtil.toDocument(deleteQuestionnaireByTitleRequest));
//        System.out.println("019");
//        return "Delete"+count+"records successfully!";
//    }
//
//    @ApiOperation(value = "根据id提取问卷")
//    @PostMapping("/selectQuestionnaireByitle")
//    public Object deleteQuestionnaireBytitle(@Valid DeleteQuestionnaireByTitleRequest deleteQuestionnaireByTitleRequest){
//        MongoDatabase db = MongoDBUtil.getConnect();
//        MongoCollection<Document> collection=db.getCollection("found_questionnaire");
//        Bson filter = Filters.eq("title", deleteQuestionnaireByTitleRequest.getTitle());
//        DeleteResult deleteResult = collection.deleteOne(filter);
//        int count = (int) deleteResult.getDeletedCount();
//        collection.deleteOne(BsonUtil.toDocument(deleteQuestionnaireByTitleRequest));
//        System.out.println("019");
//        return "Delete"+count+"records successfully!";
//    }



}

