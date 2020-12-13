package com.kinghao.dian.service;

import com.kinghao.dian.dto.request.*;
import org.bson.types.ObjectId;

/**
 * @Author Esther
 * @Date 2020/12/12 14:56
 * @Version 1.0
 */
public interface QuestionnaireService {
    void addQuestionnaire(AddQuestionnaireRequest addQuestionnaireRequest);
    void queryFqById(ObjectId Id);
    void deleteById(DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest);
    void deleteByTitle(DeleteQuestionnaireByIdRequest deleteQuestionnaireByIdRequest);
    SelectQuestionnaireByIdRequest selectById(ObjectId Id);
    SelectQuestionnaireByTitleRequest selectByTitle(ObjectId Id);

}
