package com.kinghao.dian.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.request.AddFqRequest;
import com.kinghao.dian.dto.request.UpdateFqRequest;
import com.kinghao.dian.entity.FoundQuestion;
import com.kinghao.dian.entity.FoundQuestionnaire;
import com.kinghao.dian.service.FqService;
import com.kinghao.dian.util.AssertUtil;
import com.mongodb.WriteResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/11/6 14:55
 * @Version 1.0
 */

@Api(tags = "自定义问卷")
@RestController
@RequestMapping("/fq")
@Slf4j
@Validated
public class FqController {

    @Autowired
    private FqService fqService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @ApiOperation(value = "创建问卷")
    @PostMapping("/addFq")
    public Object addFq(@Valid AddFqRequest addFqRequest){
        //System.out.println(addFqRequest.getTitle());
        //String content= JSON.toJSONString(addFqRequest.getContent());
        //System.out.println("controller:"+content);
        fqService.addFq(addFqRequest);

        return "Add successfully!";
    }

    @ApiOperation(value = "根据id查找问卷")
    @PostMapping("/queryFqById")
    public Object queryFqById(String _id){
        Query query=new Query();
        query.addCriteria(Criteria.where("_id").is(_id));
        List<FoundQuestionnaire> rts=mongoTemplate.find(query, FoundQuestionnaire.class,"found_questionnaire");
        //FoundQuestionnaire rt=fqMapper.selectByPrimaryKey(fqId);
        //List<Question> content= JSONArray.parseArray(rt.getContent()).toJavaList(Question.class);
        //System.out.println(rt.getStartTime());
        AssertUtil.isTrue(rts.size()>0, CommonErrorCode.ILLEGAL_PARAMETER);
        FoundQuestionnaire rt=rts.get(0);
        return rt;
    }

    @ApiOperation(value = "根据id修改问卷")
    @PostMapping("/updateFqById")
    public Object updateFqById(UpdateFqRequest updateFqRequest){
        String _id=updateFqRequest.get_id();
        Criteria criteria = new Criteria();
        criteria.and("Id").is(_id);
        Query query = new Query(criteria);
        Update update=new Update();

        if(updateFqRequest.getContent()!=null){
            update.set("content",updateFqRequest.getContent());
        }
        if(updateFqRequest.getDescription()!=null){
            update.set("description",updateFqRequest.getDescription());
        }
        if(updateFqRequest.getDifficulty()!=null){
            update.set("difficulty",updateFqRequest.getDifficulty());
        }
        if(updateFqRequest.getAnswerPoint()!=null){
            update.set("answerPoint",updateFqRequest.getAnswerPoint());
        }
        if(updateFqRequest.getFounder_id()!=null){
            update.set("founder_id",updateFqRequest.getFounder_id());
        }
        if(updateFqRequest.getTargetGroup()!=null){
            update.set("targetGroup",updateFqRequest.getTargetGroup());
        }
        if(updateFqRequest.getTitle()!=null){
            update.set("title",updateFqRequest.getTitle());
        }
        if(updateFqRequest.getType()!=null){
            update.set("type",updateFqRequest.getType());
        }
        if(updateFqRequest.getEndTime()!=null){
            update.set("endTime",updateFqRequest.getEndTime());
        }
        if(updateFqRequest.getStartTime()!=null){
            update.set("startTime",updateFqRequest.getStartTime());
        }
        mongoTemplate.upsert(query, update, "found_questionnaire");


        return "Update Successfully";
    }
}
