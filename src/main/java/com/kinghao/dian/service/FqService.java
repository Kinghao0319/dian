package com.kinghao.dian.service;

import com.kinghao.dian.dto.request.AddFqRequest;
import com.kinghao.dian.entity.FoundQuestionnaire;

/**
 * @Author Kinghao
 * @Date 2020/11/6 17:35
 * @Version 1.0
 */
public interface FqService {
    void addFq(AddFqRequest addFqRequest);

    AddFqRequest queryFqById(Integer fqId);
}
