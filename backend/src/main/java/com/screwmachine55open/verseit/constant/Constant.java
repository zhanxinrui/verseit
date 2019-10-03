/**
 * constant类声明了全局需要使用的常量
 *
 * */
package com.screwmachine55open.verseit.constant;

public class Constant {
    private Constant(){
        new AssertionError();
    }

    public static final String BASE_URL = "/verseit";//node重定向到verseit为根地址

    /********* github  第三方登录   *********/

    public static final String GITHUB_ACCESS_TOKEN_URL = "https://github.com/login/oauth/access_token";
    public static final String GITHUB_USER_API_URL = "https://api.github.com/user";
    public static final String GITHUB_client_ID = "7907f9e4308208fa04ca";
    public static final String GITHUB_client_secret = "234b65df70c244b4398a29870d27c2afded04067";


    /********* email     *********/

    public static final String EMAIL_SMTP_KEY = "mail.smtp.host";
    public static final String EMAIL_SMTP_HOST = "smtp.gitrue.com";
    public static final String EMAIL_Account = "admin@gitrue.com";

    /********* activity  *********/

    public static final String AVTIVITI_COMMENT = "comment";
    public static final String AVTIVITI_COMMEND = "commend";


    /**********pic******************/
    public static final String PIC_ADDR ="/image";//通过config里webmvcconfig映射到实体目录
}
