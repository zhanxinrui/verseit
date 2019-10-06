package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.checkerframework.checker.units.qual.A;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 15:58
 * @Version 1.0
 */

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Concern {
    @ApiModelProperty(value="关注分组")
    private String concernDir;

    @ApiModelProperty(value = "可见性")
    private  Boolean visible;

    @ApiModelProperty(value = "文件夹类型")
    private String type;// poet or user.
    @ApiModelProperty(value = "分类下的所有关注诗人")
    private Collection<String> poets= new LinkedList<>();
    @ApiModelProperty(value = "分类下的所有关注用户")
    private Collection<String> users= new LinkedList<>();

}
