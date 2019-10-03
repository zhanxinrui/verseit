package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 19:50
 * @Version 1.0
 */

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    @ApiModelProperty(value = "被关注用户id")
    private String userId;

    @ApiModelProperty(value = "可见性")
    private Boolean visible;

    @ApiModelProperty(value = "备注姓名")
    private String remarkName;

}
