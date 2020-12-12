package com.kinghao.dian.service;

import com.kinghao.dian.dto.request.AddQuestionnaireRequest;

/**
 * @Author Kinghao
 * @Date 2020/11/6 17:35
 * @Version 1.0
 */
public interface FqService {
    void addFq(AddQuestionnaireRequest addFqRequest);

    AddQuestionnaireRequest queryFqById(Integer fqId);
}
