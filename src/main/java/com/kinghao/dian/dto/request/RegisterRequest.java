package com.kinghao.dian.dto.request;

import com.kinghao.dian.dto.BaseRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @Author Kinghao
 * @Date 2020/10/21 16:33
 * @Version 1.0
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Validated
public class RegisterRequest implements BaseRequest {
    @ApiModelProperty(value = "手机号",required = true)
    @NotNull
    private String tel;

    @ApiModelProperty(value = "密码（长度6~20位）",required = true)
    @Size(min=6,max=20)
    @NotNull
    private String password;

    @ApiModelProperty(value = "用户名",required = true)
    @NotNull
    private String username;

    @ApiModelProperty("个性签名")
    private String signature;

    @ApiModelProperty(value = "账户类型（\"personal\"或\"administration\"）",required = true)
    @NotNull
    private String type;

    @ApiModelProperty("性别（\"男\"或\"女\"）")
    private String gender;//转到entity后：0男1女，必须Integer不能int

    @ApiModelProperty("年龄（数值0~200）")
    @Min(0)
    @Max(200)
    private Integer age;

}
