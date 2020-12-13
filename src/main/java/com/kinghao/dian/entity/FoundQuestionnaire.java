package com.kinghao.dian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;


/**
 * @Author Kinghao
 * @Date 2020/11/6 14:54
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NameStyle(Style.normal)
@Table(name = "found_questionnaire")
public class FoundQuestionnaire {
//    @Id
//    private Integer id;
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
