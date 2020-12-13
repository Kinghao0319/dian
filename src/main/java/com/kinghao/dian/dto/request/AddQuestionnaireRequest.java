package com.kinghao.dian.dto.request;

import com.kinghao.dian.dto.BaseRequest;
import com.kinghao.dian.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.NameStyle;
import tk.mybatis.mapper.code.Style;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @Author Esther
 * @Date 2020/12/12 14:56
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddQuestionnaireRequest implements BaseRequest {
    @ApiModelProperty(value = "问卷标题",required = true)
    @NotNull
    private String title;

    @ApiModelProperty(value = "问卷描述")
    private String description;

    @ApiModelProperty(value = "问卷内容")
    private List<Question> content;
    @ApiModelProperty(value = "问卷回答")
    private String answer;
    @ApiModelProperty(value = "面向人群")
    private String targetGroup;

    @ApiModelProperty(value = "问卷类型")
    private String type;

    @ApiModelProperty(value = "起始时间\"yyyy-MM-dd HH:mm:ss\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    @ApiModelProperty(value = "结束时间\"yyyy-MM-dd HH:mm:ss\"")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    @ApiModelProperty(value = "难度")
    private String difficulty;
    @ApiModelProperty(value = "创建消耗点数")
    private Integer foundPoint;
    @ApiModelProperty(value = "回答奖励点数")
    private Integer answerPoint;
    @ApiModelProperty(value = "发布者id")
    private Integer founder_id;
    @ApiModelProperty(value = "是否来自模板")
    private Integer is_from_template;

}
