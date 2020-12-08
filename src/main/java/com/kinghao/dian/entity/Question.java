package com.kinghao.dian.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
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
    @ApiModelProperty(value = "所属问卷id",required = true)
    @NotNull
    private Integer fq_id;

    @ApiModelProperty(value = "所属问卷标题")
    private String fq_title;

    @ApiModelProperty(value = "题号")
    private Integer question_id;

    @ApiModelProperty(value = "选项",example = "")
    private List<String> choices;

    @ApiModelProperty(value = "是否为单选",example = "")
    private Boolean isSingle;

    @ApiModelProperty(value = "矩阵多选")
    private String[][] matrix;

    @ApiModelProperty(value = "问题字数")
    private Integer words;

    @ApiModelProperty(value = "问题难度")
    private String difficulty;

    @ApiModelProperty(value = "标准答案")
    private String standard_answer;

    @ApiModelProperty(value = "是否必做",example = "")
    private Boolean must_answered;

    @ApiModelProperty(value = "是否fool",example = "")
    private Boolean is_fool;

    @ApiModelProperty(value = "有没有派生问题",example = "")
    private Boolean deriving;

    @ApiModelProperty(value = "是否是派生问题",example = "")
    private Boolean derived;

}
