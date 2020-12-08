package com.kinghao.dian.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.UpdateOptions;
import com.mongodb.client.result.UpdateResult;


/**
 * @Author Kinghao
 * @Date 2020/11/21 18:56
 * @Version 1.0
 */
public class Demo {

    public static void main(String[] args) {
        MongoClientOptions option=MongoClientOptions.builder().connectTimeout(60000).build();

        MongoClient monGoClient=new MongoClient(new ServerAddress("101.37.78.56",27017),option);
        //获取操作数据库
        MongoDatabase db=monGoClient.getDatabase("test");
        //获取集合。后面的操作，大部分都是基于集合操作
        MongoCollection<Document> contections=db.getCollection("testfq");
        //插入
        contections.insertOne(new Document("name","test123").append("sex", "男"));


        //批量插入
        List<Document> documents=new ArrayList<>();
        documents.add(new Document("name","text111").append("sex", "女"));
        documents.add(new Document("name","chaoba4565").append("sex", "男"));
        contections.insertMany(documents);
//            更新一个
        UpdateResult update1=contections.updateOne(Filters.eq("name", "test123"), new Document("$set",new Document("name","暮雪超霸")));
        //批量更新
        UpdateResult update=contections.updateMany(Filters.eq("name", "test123"), new Document("$set",new Document("name","暮雪超霸")));
        //UpdateResult这个里面包含AcknowledgedUpdateResult{matchedCount=1, modifiedCount=1, upsertedId=null}
        //matchedCount不知道什么意思，看着好像是修改的个数。。modifiedCount这个也是

        //更新，如果key不存在。则新插入一个文档
        contections.updateOne(Filters.eq("name","mumu"), new Document("$set",new Document("age", 20)),new UpdateOptions().upsert(true));


//        System.out.println(update);
        //查询所有一
        FindIterable<Document> result=contections.find();
        for (Document document : result) {
            System.out.println(document);
        }
        //通过自带的迭代方法查询全部数据
        MongoCursor result1=    contections.find().iterator();
        while(result1.hasNext()) {
            System.out.println(result1.next());
        }

        //查询单个
        Document result3=contections.find().first();
        System.out.println(result3);

        //根据条件查询
        MongoCursor result4=contections.find().filter(Filters.eq("name","mumu")).iterator();
        while(result4.hasNext()) {
            System.out.println(result4.next());
        }
        //多条件查询
        MongoCursor result5=    contections.find().filter(Filters.and(Filters.eq("name","mumu"),Filters.eq("age",20))).iterator();
        while(result5.hasNext()) {
            System.out.println(result5.next());
        }


        //删除
        //contections.deleteOne(Filters.eq("chaoba",1));

    }

}