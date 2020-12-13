package com.kinghao.dian.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.request.AddFqRequest;
import com.kinghao.dian.entity.FoundQuestion;
import com.kinghao.dian.entity.FoundQuestionnaire;
import com.kinghao.dian.entity.Question;
import com.kinghao.dian.mapper.FqMapper;
import com.kinghao.dian.service.FqService;
import com.kinghao.dian.util.AssertUtil;
import com.mongodb.Mongo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/11/6 17:35
 * @Version 1.0
 */
@Service
@Slf4j
public class FqServiceImpl implements FqService {

    @Resource(type = FqMapper.class)
    private FqMapper fqMapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void addFq(AddFqRequest addFqRequest) {

        String content= JSON.toJSONString(addFqRequest.getContent());
        //System.out.println(content);
        FoundQuestionnaire foundQuestionnaire=FoundQuestionnaire
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
        mongoTemplate.insert(foundQuestionnaire,"found_questionnaire");
        //fqMapper.insertSelective(foundQuestionnaire);
    }

    @Override
    public AddFqRequest queryFqById(Integer fqId) {
//        Example example=new Example(FoundQuestionnaire.class);
//        Example.Criteria criteria=example.createCriteria();
//        criteria.andEqualTo("id",fqId);
        Query query=new Query();
        query.addCriteria(Criteria.where("id").is(fqId));
        List<FoundQuestionnaire> rts=mongoTemplate.find(query, FoundQuestionnaire.class,"found_questionnaire");
        //FoundQuestionnaire rt=fqMapper.selectByPrimaryKey(fqId);
        //List<Question> content= JSONArray.parseArray(rt.getContent()).toJavaList(Question.class);
        //System.out.println(rt.getStartTime());
        AssertUtil.isTrue(rts.size()>0, CommonErrorCode.ILLEGAL_PARAMETER);
        FoundQuestionnaire rt=rts.get(0);
        AddFqRequest res=AddFqRequest
                .builder()
                .title(rt.getTitle())
                .description(rt.getDescription())
                .answerPoint(rt.getAnswerPoint())
                .foundPoint(rt.getFoundPoint())
                .founder_id(rt.getFounder_id())
                //.content(rt.getContent())
                .difficulty(rt.getDifficulty())
                .startTime(rt.getStartTime())
                .endTime(rt.getEndTime())
                .is_from_template(rt.getIs_from_template())
                .type(rt.getType())
                .targetGroup(rt.getTargetGroup())
                .build();
        return res;
    }
}
