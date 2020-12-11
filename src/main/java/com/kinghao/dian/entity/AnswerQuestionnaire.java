package com.kinghao.dian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NameStyle(Style.normal)
@Table(name = "answer_questionnaire")
public class AnswerQuestionnaire {
    @Id
    private Integer id;
    private String title;
    private String fq_id;
    private Date startTime;
    private Date endTime;
    private String sourceIP;
    private String area;
    private String state;
    private Integer founder_id;
    private Integer filler_id;
    private Integer candidate;
    private String content;
}
