package com.kinghao.dian.service.impl;

import com.kinghao.dian.dto.request.AddFqRequest;
import com.kinghao.dian.entity.Testfq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

/**
 * @Author Kinghao
 * @Date 2020/11/20 20:20
 * @Version 1.0
 */

@Service
public class TestMongodbService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void test(AddFqRequest addFqRequest){
        System.out.println("in test 111");
        Testfq testfq = Testfq
                .builder()
                .title(addFqRequest.getTitle())
                .description(addFqRequest.getDescription())
                .content(addFqRequest.getContent())
                .targetGroup(addFqRequest.getTargetGroup())
                .type(addFqRequest.getType())
                .startTime(addFqRequest.getStartTime())
                .endTime(addFqRequest.getEndTime())
                .difficulty(addFqRequest.getDifficulty())
                .foundPoint(addFqRequest.getFoundPoint())
                .answerPoint(addFqRequest.getAnswerPoint())
                .founder_id(addFqRequest.getFounder_id())
                .is_from_template(addFqRequest.getIs_from_template())
                .build();
        System.out.println("in test 222");
        System.out.println(mongoTemplate.findAll(Testfq.class,"testfq"));
        mongoTemplate.insert(testfq,"testfq");
        System.out.println("in test 333");
    }
}
