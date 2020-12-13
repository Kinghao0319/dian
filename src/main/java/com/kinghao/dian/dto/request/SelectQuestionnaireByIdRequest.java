package com.kinghao.dian.dto.request;

import com.kinghao.dian.dto.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
/**
 * @Author Esther
 * @Date 2020/12/12 14:56
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class SelectQuestionnaireByIdRequest {
    @ApiModelProperty(value = "问卷id",required = true)
    @NotNull
    private String id;
}
