package com.kinghao.dian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kinghao.dian.controller.QuestionnaireController;
import com.kinghao.dian.dto.request.*;
import com.kinghao.dian.entity.Questionnaire;
import com.kinghao.dian.entity.Question;
import com.kinghao.dian.mapper.FqMapper;

import com.kinghao.dian.service.QuestionnaireService;
import com.kinghao.dian.util.MongoUtil;
import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/11/6 17:35
 * @Version 1.0
 */
@Service
@Slf4j
public class FqServiceImpl implements QuestionnaireService {

    @Resource(type = FqMapper.class)
    private FqMapper fqMapper;
    String dbName = "Project";
    String collName = "questionnaire";
    MongoDatabase mongoDatabase = MongoUtil.instance.getDB("Project");
//    private MongoCollection<Document> coll = MongoUtil.instance.getCollection(dbName, collName);;

    @Override
    public void addQuestionnaire(AddQuestionnaireRequest addQuestionnaireRequest) {


        //System.out.println(content);
        Questionnaire questionnaire= Questionnaire
                .builder()
                .title(addQuestionnaireRequest.getTitle())
                .description(addQuestionnaireRequest.getDescription())
                .content(addQuestionnaireRequest.getContent())
                .targetGroup(addQuestionnaireRequest.getTargetGroup())
                .type(addQuestionnaireRequest.getType())
                .startTime(addQuestionnaireRequest.getStartTime())
                .endTime(addQuestionnaireRequest.getEndTime())
                .difficulty(addQuestionnaireRequest.getDifficulty())
                .foundPoint(addQuestionnaireRequest.getFoundPoint())
                .answerPoint(addQuestionnaireRequest.getAnswerPoint())
                .founder_id(addQuestionnaireRequest.getFounder_id())
                .is_from_template(addQuestionnaireRequest.getIs_from_template())
                .build();
        MongoUtil.instance.insert(mongoDatabase,dbName, (DBObject) questionnaire);

    }

    @Override
    public void queryFqById(ObjectId Id) {

    }

    @Override
    public void deleteById(DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest) {
        MongoUtil.instance.deleteById(MongoUtil.instance.getCollection(dbName,"questionnaire"),deleteQuestionnaireByIdRequest.getId());
    }

    @Override
    public void deleteByTitle(DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest) {

    }

    @Override
    public SelectQuestionnaireByIdRequest selectById(ObjectId Id) {
        return null;
    }

    @Override
    public SelectQuestionnaireByTitleRequest selectByTitle(ObjectId Id) {
        return null;
    }



}
