package com.kinghao.dian.service.impl;

import com.kinghao.dian.common.CommonErrorCode;
import com.kinghao.dian.dto.SessionData;
import com.kinghao.dian.dto.request.LoginRequest;
import com.kinghao.dian.dto.request.RegisterRequest;
import com.kinghao.dian.dto.request.UpdateUserInfoRequest;
import com.kinghao.dian.entity.FoundQuestionnaire;
import com.kinghao.dian.entity.User;
import com.kinghao.dian.mapper.UserMapper;
import com.kinghao.dian.service.UserService;
import com.kinghao.dian.util.AssertUtil;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/10/21 16:37
 * @Version 1.0
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Resource(type = UserMapper.class)
    private UserMapper usermapper;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void userRegister(RegisterRequest registerRequest) {
        //判断用户名是否已经注册过
        Example example=new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",registerRequest.getUsername());
        User rt=usermapper.selectOneByExample(example);
        AssertUtil.isTrue(rt==null, CommonErrorCode.USERNAME_USED_ERROR);

        //以上检验成功，添加用户
        User user=User
                .builder()
                .tel(registerRequest.getTel())
                .password(registerRequest.getPassword())
                .username(registerRequest.getUsername())
                .signature(registerRequest.getSignature())
                .type(registerRequest.getType())
                .create_time(new Timestamp(System.currentTimeMillis()))
                .age(registerRequest.getAge())
                .build();
        if(registerRequest.getGender()!=null){
            user.setGender(registerRequest.getGender().equals("男")?0:1);
        }
        //usermapper.insertSelective(user);//只给传进来的字段赋值
        mongoTemplate.insert(user,"user");
    }

    @Override
    public User userLogin(LoginRequest loginRequest) {
        String username=loginRequest.getUsername();
        String password=loginRequest.getPassword();
        //检验用户名和密码
//        Example example=new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("username",username);
//        criteria.andEqualTo("password",password);
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        query.addCriteria(Criteria.where("password").is(password));
        User rt=mongoTemplate.findOne(query, User.class,"user");

        //User rt=usermapper.selectOneByExample(example);
        AssertUtil.isTrue(rt!=null,CommonErrorCode.LOGIN_FAILED);
        return rt;
    }

    @Override
    public void updateInfo(UpdateUserInfoRequest updateUserInfoRequest, SessionData sessionData) {
        String _id=sessionData.getId();

        Criteria criteria = new Criteria();
        criteria.and("_id").is(_id);
        Query query = new Query(criteria);
        Update update=new Update();
        User user=User
                .builder()
                .tel(updateUserInfoRequest.getTel())
                .password(updateUserInfoRequest.getPassword())
                .username(updateUserInfoRequest.getUsername())
                .signature(updateUserInfoRequest.getSignature())
                .type(updateUserInfoRequest.getType())
                .age(updateUserInfoRequest.getAge())
                .build();
        if(updateUserInfoRequest.getGender()!=null){
            user.setGender(updateUserInfoRequest.getGender().equals("男")?0:1);
        }
        if(user.getType()!=null){
            update.set("type",user.getType());
        }
        if(user.getUsername()!=null){
            update.set("username",user.getUsername());
        }
        if(user.getPassword()!=null){
            update.set("password",user.getPassword());
        }
        if(user.getAge()!=null){
            update.set("age",user.getAge());
        }
        if(user.getSignature()!=null){
            update.set("signature",user.getSignature());
        }
        if(user.getGender()!=null){
            update.set("gender",user.getGender());
        }

        mongoTemplate.upsert(query,update,"user");
//        Example example=new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("id",id);
//        //根据手机号，修改不为null的字段
//        usermapper.updateByExampleSelective(user,example);
    }

    @Override
    public User queryInfo(String username) {
        Query query=new Query();
        query.addCriteria(Criteria.where("username").is(username));
        User rt=mongoTemplate.findOne(query, User.class,"user");
        if(rt==null){
            return null;
        }
//        Example example=new Example(User.class);
//        Example.Criteria criteria = example.createCriteria();
//        criteria.andEqualTo("username",username);
//        User rt=usermapper.selectOneByExample(example);
        rt.setPassword("");
        return rt;
    }
}
