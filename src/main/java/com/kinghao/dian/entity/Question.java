package com.kinghao.dian.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Kinghao
 * @Date 2020/11/6 15:56
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    @ApiModelProperty(value = "题号")
    private Integer question_number;
    @ApiModelProperty(value = "问题描述")
    private String question_content;
    @ApiModelProperty(value = "问题类型")
    private String type;
    @ApiModelProperty(value = "选项",example = "")
    private List<String> choices;
}
