package com.kinghao.dian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/11/20 21:22
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document
public class Testfq {
    @Id
    private String id;

    private String title;

    private String description;
    private List<Question> content;
    private String targetGroup;
    private String type;
    private Date startTime;
    private Date endTime;
    private String difficulty;
    private Integer foundPoint;
    private Integer answerPoint;
    private Integer founder_id;
    private Integer is_from_template;
}
