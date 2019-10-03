package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 15:39
 * @Version 1.0
 */

@Document(collection="Poem")
@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Poem {

    @Id
    @ApiModelProperty(value = "诗歌作品id")
    private String poemId;

    @ApiModelProperty(value = "作者id,即poetId或者userId")
    private String authorId;

    @ApiModelProperty(value = "诗歌作品名")
    private String poemName;

    @ApiModelProperty(value = "诗歌内容")
    private String content;

    @ApiModelProperty(value = "是否可见")
    private  Boolean visible;

    @ApiModelProperty(value = "印象标签")
    private Collection<String> label=new LinkedHashSet<>();;

    @ApiModelProperty(value = "收藏数")
    private  Long collect;

    @ApiModelProperty(value = "点赞数")
    private  Long like;

    @ApiModelProperty(value = "评论")
    private Collection<Comment> comments=new LinkedList<>();

}
