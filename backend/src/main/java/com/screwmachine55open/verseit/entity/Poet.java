package com.screwmachine55open.verseit.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import java.io.Serializable;
import java.util.*;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 14:45
 * @Version 1.0
 */

@Document(collection="Poet")
@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class Poet {
    @Id
    @ApiModelProperty(value="诗人id")
    private String poetId;

    @ApiModelProperty(value = "诗人姓名")
    private String poetName;

    @ApiModelProperty(value="诗人简介")
    private  String  introduction;

    @ApiModelProperty(value="诗人标签")
    private Collection<String> label=new LinkedHashSet<>();

    @ApiModelProperty(value="诗人作品")
    private  Collection<Poem> poems=new ArrayList<>();

}
