package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.Collection;
import java.util.LinkedHashSet;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 16:37
 * @Version 1.0
 */

@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @ApiModelProperty(value = "评论者id")
    private String userId;

    @ApiModelProperty(value = "评论者对作品的印象标签")
    private Collection<String> label=new LinkedHashSet<>();

    @ApiModelProperty(value = "评论标题")
    private  String title;

    @ApiModelProperty(value = "评论内容")
    private String content;
}
