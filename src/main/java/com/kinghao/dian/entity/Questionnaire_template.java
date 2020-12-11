package com.kinghao.dian.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NameStyle(Style.normal)
@Table(name = "questionnaire_template")
public class Questionnaire_template {
    @Id
    private String id;
    private String title;
    private String content;
    private String description;
    private String targetGroup;
    private String type;
    private Integer difficulty;
    private Integer upload_point;
    private Integer download_point;
    private Integer popularity;
    private String tag;
}
