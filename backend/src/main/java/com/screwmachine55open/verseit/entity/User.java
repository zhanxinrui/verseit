package com.screwmachine55open.verseit.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

/**
 * @Author: wuquanda
 * @Date: 2019/5/2 16:48
 * @Version 1.0
 */

@Document(collection="User")
@Getter
@Setter
@ToString(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {//缓存必须将对象序列化

    @Id
    @ApiModelProperty(value = "用户id")
    private  String userId;

//    @ApiModelProperty(value = "账户名")
//    private String account;

    @ApiModelProperty(value = "密码")
    private String password;

    @Indexed
    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "性别")
    private  String sex;

    @ApiModelProperty(value = "电话号码")
    private String phone;

    @ApiModelProperty(value = "邮箱")
    private  String email;

    @ApiModelProperty(value = "自我简介")
    private  String introduction;

    @ApiModelProperty(value = "关注")
    private Collection<Concern> concerns=new LinkedList<>();;


    @ApiModelProperty(value = "收藏")
    private Collection<Collect> collects =new LinkedList<>();

    @ApiModelProperty(value = "创作")
    private Collection<Compose> Composes=new LinkedList<>();


    @ApiModelProperty(value = "githubId")
    private String githubNodeId;

    @ApiModelProperty(value = "头像")
    private String  avatarPath;

}
