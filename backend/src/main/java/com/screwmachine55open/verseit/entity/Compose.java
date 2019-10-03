package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 16:55
 * @Version 1.0
 */

@Getter
@Setter
@ToString(callSuper = false)
//@Document(collection="Compose")
@NoArgsConstructor
@AllArgsConstructor
public class Compose extends ArrayList implements Serializable {
    @ApiModelProperty(value = "创作分类文件夹")
    private String composeDir;

    @ApiModelProperty(value = "可见性")
    private Boolean visible;

    @ApiModelProperty(value = "分类下的所有诗歌")
    private Collection<String> poems =new HashSet<>();
}
