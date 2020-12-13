package com.kinghao.dian.dto.request;

import com.kinghao.dian.dto.BaseRequest;
import com.kinghao.dian.entity.Question;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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

public class SelectQuestionnaireByTitleRequest implements BaseRequest {
    @ApiModelProperty(value = "问卷标题",required = true)
    @NotNull
    private String title;
}
