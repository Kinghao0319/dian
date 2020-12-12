package com.kinghao.dian.dto.request;

import com.kinghao.dian.dto.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeleteQuestionnaireByIdRequest implements BaseRequest {
    @ApiModelProperty(value = "问卷id",required = true)
    @NotNull
    private Integer id;

}
