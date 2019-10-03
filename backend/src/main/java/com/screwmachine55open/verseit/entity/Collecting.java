package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 16:55
 * @Version 1.0
 */

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Collecting {
    @ApiModelProperty(value = "分类名")
    private String collectingName;

    @ApiModelProperty(value = "可见性")
    private Boolean visible;

    @ApiModelProperty(value = "分类下所有诗歌的id")
    private Collection<String> poems=new LinkedHashSet<>();
}
