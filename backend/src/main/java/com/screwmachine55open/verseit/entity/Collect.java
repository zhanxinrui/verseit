package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
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
//@Document(collection="Collect")
@NoArgsConstructor
@AllArgsConstructor
public class Collect extends ArrayList  implements Serializable {

    @ApiModelProperty(value = "分类名")
    private String collectDir;

    @ApiModelProperty(value = "可见性")
    private Boolean visible;

    @ApiModelProperty(value = "分类下所有诗歌的id")
    private Collection<String> poems = new LinkedHashSet<>();;

//    @Override
//    public int size() {
//        return items.size();
//    }

}
