<template>
    <div>
        <div> notification </div>
        <button type="button" @click="back()">Back</button>
    </div>
</template>

<script>
    export default {
        name: "notification",
        methods: {
            back()
            {
                this.$router.push('/')
            }
        }
    }
</script>

<style scoped>

</style>




<!--<template>-->
<!--    <div>-->
<!--        <BeeHeader></BeeHeader>-->
<!--        <section class="hero  "  style=" background-color:#f7f7f7">-->
<!--            <div class="hero-body" >-->
<!--                <div class="container">-->
<!--                    <div class="columns is-multiline" >-->
<!--                        <div class="column is-3 "></div>-->
<!--                        <div class="column is-2">-->
<!--                            <img :src="userInfo.avatarPath" class=" header-avatar-big img-circle " alt="头像" >-->
<!--                            <div>-->
<!--                                &nbsp;-->
<!--                                <a v-show="userInfo.github" :href="userInfo.github">-->
<!--                    <span class="icon" style="color: #333;">-->
<!--                      <i class="fa fa-lg fa-github"></i>-->
<!--                    </span>-->
<!--                                </a>-->
<!--                                <a href="https://github.com/pkwenda">-->
<!--                    <span class="icon" style="color: #333;">-->
<!--                      <i class="fa fa-lg fa-google"></i>-->
<!--                    </span>-->
<!--                                </a>-->
<!--                                <a href="https://github.com/pkwenda">-->
<!--                    <span class="icon" style="color: #333;">-->
<!--                      <i class="fa fa-lg fa-weibo"></i>-->
<!--                    </span>-->
<!--                                </a>-->
<!--                                <a href="https://github.com/pkwenda">-->
<!--                    <span class="icon" style="color: #333;">-->
<!--                      <i class="fa fa-lg fa-facebook"></i>-->
<!--                    </span>-->
<!--                                </a>-->

<!--                            </div>-->
<!--                        </div>-->
<!--                        <div class="column is-4">-->
<!--                            <strong style="font-size:25px">{{userInfo.nickName || userInfo.userName}}</strong>-->
<!--                            <br>-->

<!--                            <div>-->
<!--                                <span class="tag is-dark">专栏作者 </span>-->
<!--                                <span class="tag is-success">活跃成员 </span>-->
<!--                            </div>-->
<!--                            <div><i class="fa fa-map-marker"></i><small  > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地球</small></div>-->
<!--                            <div><i class="fa fa-graduation-cap" ></i><small>&nbsp;&nbsp;&nbsp;{{userInfo.school || '暂无'}}</small></div>-->
<!--                            <div><i class="fa fa-briefcase" ></i><small>&nbsp;&nbsp;&nbsp;&nbsp;{{userInfo.company || '暂无'}}&nbsp;&nbsp;{{userInfo.signature}}</small></div>-->
<!--                            <div><i class="fa fa-link"></i><small>&nbsp;&nbsp;&nbsp;&nbsp;<a :href="userInfo.link">{{userInfo.link || '暂无' }}</a></small></div>-->
<!--                        </div>-->

<!--                    </div>-->
<!--                    <div class="column is-3 "></div>-->
<!--                </div>-->
<!--            </div>-->

<!--            <div class="hero-foot">-->
<!--                <div class="container">-->
<!--                    <div class="columns is-multiline" >-->
<!--                        <div class="column is-3 "></div>-->
<!--                        <div class="column is-6 ">-->
<!--                            <nav class="tabs is-boxed">-->
<!--                                <ul>-->
<!--                                    <li  v-for="tag in ['活动', '专栏', '分享', '收藏', '点赞']" v-bind:key="tag" :type="tag">-->
<!--                                        <a>{{tag}}</a>-->
<!--                                    </li>-->

<!--                                </ul>-->

<!--                            </nav>-->
<!--                        </div>-->
<!--                        <div class="column is-3 "></div>-->
<!--                    </div>-->

<!--                </div>-->
<!--            </div>-->

<!--        </section>-->

<!--        <div  id="index-main" class="container" style="margin-top:20px" >&lt;!&ndash;&ndash;&gt; <div  class="columns is-multiline">-->
<!--            <article  class="column is-2"> </article>-->
<!--            <article  class="column is-8">-->

<!--                <ul   st:block="entryList" class="column-entry-list entry-list">&lt;!&ndash;&ndash;&gt;-->
<!--                    <li  v-for="blog in blogs" :key="blog" class="item"><div   st:block="entry"  class="column-entry with-thumb"><div  class="row user-info-row"><div data-v-311d2efa=""  st:block="userPopover"   class="user-popover-box">&lt;!&ndash;&ndash;&gt;<a  href="/user/5823d1a3a22b9d0067fde1f7" target="_blank" rel="" st:name="user" st:state="5823d1a3a22b9d0067fde1f7" class="user-info" data-v-311d2efa=""><span  class="username">&lt;!&ndash; 放人名  头像 &ndash;&gt;</span></a></div>-->
<!--                        <span  class="date" style="color: rgb(186, 189, 194);">{{getTime(new Date(blog.createTime))}}</span>-->
<!--                    </div>-->
<!--                        <div @click="goBlog(blog.id)" class="bd-article-image is-warning" style="height: 180px;background-color: #c1bdbd">-->
<!--                            <strong  class="bd-article-info" style="border: 1px  black;">-->
<!--                <span >-->
<!--                  <strong  class="bd-article-title"  >-->
<!--                  {{blog.title}}-->
<!--                  </strong>-->
<!--                </span>-->
<!--                            </strong>-->
<!--                        </div>-->
<!--                        <div  class="row abstract-row"> <br>-->
<!--                            <a style="font-size: 15px;"  href="/post/5a579d63f265da3e4d72a028" target="_blank" rel="" st:name="abstract" class="abstract with-thumb">-->
<!--                                {{blog.content.length>150 ? blog.content.substring(0,150) : blog.content}}...-->
<!--                            </a>-->

<!--                        </div><div  class="row action-row">-->
<!--                            <ul class="Tweet-actions">-->
<!--                                <li class="Tweet-action Tweet-action&#45;&#45;heart"><a  target="_blank" class="TweetAction TweetAction&#45;&#45;heartEdge web-intent"><div class="Icon Icon&#45;&#45;heartEdge TweetAction-icon Icon&#45;&#45;heartEdge"></div> <span class="TweetAction-stat">-->
<!--              {{blog.commendCount}}-->
<!--            </span></a></li>-->
<!--                                <li class="Tweet-action"><a   class="TweetAction TweetAction&#45;&#45;replyEdge web-intent"><div class="Icon Icon&#45;&#45;reply TweetAction-icon Icon&#45;&#45;replyEdge"></div><span class="TweetAction-stat">-->
<!--              {{blog.commentCount}}-->
<!--            </span></a></li>-->
<!--                                <li class="Tweet-action Tweet-action&#45;&#45;retweet">-->
<!--                                    <a @click="editBlog(blog.id)"  class="TweetAction TweetAction&#45;&#45;retweetEdge web-intent">-->
<!--                                        <div class="Icon Icon&#45;&#45;retweet TweetAction-icon Icon&#45;&#45;retweetEdge "></div>-->
<!--                                    </a></li>-->
<!--                            </ul>-->

<!--                            <div  class="entry-action-box">-->
<!--                                <div  class="read-action view-count">阅读 {{blog.readCount}}</div>&lt;!&ndash;&ndash;&gt;-->
<!--                                <div  st:name="moreBtn" class="read-action more-action"><div  class="icon ion-ios-more"></div>&lt;!&ndash;&ndash;&gt;</div>&lt;!&ndash;&ndash;&gt;</div></div></div>-->
<!--                    </li>&lt;!&ndash;&ndash;&gt;-->

<!--                </ul>-->
<!--            </article>-->
<!--            <article  class="column is-2"></article>-->
<!--        </div></div>-->

<!--    </div>-->



<!--</template>-->

<!--<script>-->
<!--    import userApi from "@/api/user";-->
<!--    import BeeHeader from "@/components/common/BeeHeader";-->
<!--    import { getToken, removeToken } from "@/utils/auto";-->
<!--    import { coverDate } from "@/utils/date";-->

<!--    export default {-->
<!--        name: "User",-->
<!--        components: { BeeHeader },-->
<!--        data() {-->
<!--            return {-->
<!--                userName: this.$route.params.userName || "",-->
<!--                userInfo: {},-->
<!--                blogs: []-->
<!--            };-->
<!--        },-->
<!--        mounted() {-->
<!--            $("nav.tabs")-->
<!--                .find("li")-->
<!--                .click(function() {-->
<!--                    if (!$(this).hasClass("is-active")) {-->
<!--                        $("nav.tabs")-->
<!--                            .find("li")-->
<!--                            .each(function(k, v) {-->
<!--                                $(v).removeClass("is-active");-->
<!--                            });-->
<!--                        $(this).addClass("is-active");-->
<!--                        self.$emit("tagEvent", $(this).attr("type"));-->
<!--                        //todo:应该减少dom移出操作-->
<!--                        $("#index-main").hide();-->
<!--                        newBee("#index-type").animation(500, function() {-->
<!--                            $("#index-main").show();-->
<!--                        });-->
<!--                    }-->
<!--                });-->
<!--            this.getUserInfo();-->
<!--            this.getBlogs();-->
<!--        },-->
<!--        destroyed() {},-->
<!--        methods: {-->
<!--            //放在这里只是为了前期方便大家观看API 后续挪到 axios 拦截 或 vuex 全局管理器中，-->
<!--            getUserInfo() {-->
<!--                this.userInfo = this.$store.state.user.userInfo;-->
<!--                if (this.$store.state.user.userInfo == null) {-->
<!--                    userApi.getUserInfoByUserName(this.userName, response => {-->
<!--                        this.userInfo = response.data;-->
<!--                    });-->
<!--                }-->
<!--            },-->
<!--            getBlogs() {-->
<!--                this.$http.get("/blog/getBlogsByUser").then(res => {-->
<!--                    if (res.data.code === 200) {-->
<!--                        this.blogs = res.data.data.content;-->
<!--                    }-->
<!--                });-->
<!--            },-->
<!--            goBlog(id) {-->
<!--                this.$router.push("/blog/" + id);-->
<!--            },-->
<!--            editBlog(id) {-->
<!--                this.$router.push("/editor/edit/" + id);-->
<!--            },-->
<!--            getTime(date) {-->
<!--                return coverDate(date);-->
<!--            }-->
<!--        }-->
<!--    };-->
<!--</script>-->

<!--<style scoped>-->
<!--    body {-->
<!--        background-color: #f7f7f7;-->
<!--    }-->
<!--    .item {-->
<!--        padding: 2.5rem;-->
<!--        border-bottom: 1px solid hsla(0, 0%, 59%, 0.1);-->
<!--    }-->
<!--    .user-info-row {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--    }-->

<!--    .thumb-row {-->
<!--        position: relative;-->
<!--        padding-top: 23.4%;-->
<!--        margin-bottom: 2rem;-->
<!--    }-->

<!--    .abstract-row .abstract {-->
<!--        width: 100%;-->
<!--        max-height: 7.800000000000001rem;-->
<!--        line-height: 1.5;-->
<!--        letter-spacing: 0.02rem;-->
<!--        color: #8b8b8b;-->
<!--        cursor: pointer;-->
<!--        overflow: hidden;-->
<!--        font-size: 1.3rem;-->
<!--    }-->
<!--    .action-row {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-pack: justify;-->
<!--        -ms-flex-pack: justify;-->
<!--        justify-content: space-between;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--        margin-top: 1.5rem;-->
<!--        color: rgba(24, 37, 50, 0.3);-->
<!--    }-->
<!--    .action,-->
<!--    .entry-action-box {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--    }-->

<!--    .abstract-row .title:visited {-->
<!--        color: #909090;-->
<!--    }-->

<!--    .abstract-row .title {-->
<!--        width: 100%;-->
<!--        margin-bottom: 0.8rem;-->
<!--        font-size: 2rem;-->
<!--        color: #000;-->
<!--        line-height: 1.3;-->
<!--        font-weight: 600;-->
<!--        letter-spacing: 0.04rem;-->
<!--    }-->

<!--    .action-row .action-list .action,-->
<!--    .action-row .action-list {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--        -webkit-box-pack: center;-->
<!--        -ms-flex-pack: center;-->
<!--        justify-content: center;-->
<!--        position: relative;-->
<!--    }-->
<!--    ul {-->
<!--        padding: 0;-->
<!--        margin: 0;-->
<!--    }-->

<!--    user agent stylesheet ul ul,-->
<!--    ol ul {-->
<!--        list-style-type: circle;-->
<!--    }-->
<!--    .action-row .action-list .action,-->
<!--    .action-row .action-list {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--        -webkit-box-pack: center;-->
<!--        -ms-flex-pack: center;-->
<!--        justify-content: center;-->
<!--        position: relative;-->
<!--    }-->

<!--    ul {-->
<!--        padding: 0;-->
<!--        margin: 0;-->
<!--    }-->
<!--    .action-row .action-list .action.active {-->
<!--        color: #fff;-->
<!--        border-color: #83c73a;-->
<!--        background-color: #83c73a;-->
<!--    }-->
<!--    .action-row .action-list .action {-->
<!--        cursor: pointer;-->
<!--        padding: 0 1rem;-->
<!--        height: 25px;-->
<!--        line-height: 25px;-->
<!--        white-space: nowrap;-->
<!--        border: 1px solid #f1f1f1;-->
<!--        border-radius: 1px;-->
<!--        background-color: #fff;-->
<!--        transition: color, border-color, background-color 0.3s;-->
<!--    }-->
<!--    .action-row .action-list .action,-->
<!--    .action-row .action-list {-->
<!--        display: -webkit-box;-->
<!--        display: -ms-flexbox;-->
<!--        display: flex;-->
<!--        -webkit-box-align: center;-->
<!--        -ms-flex-align: center;-->
<!--        align-items: center;-->
<!--        -webkit-box-pack: center;-->
<!--        -ms-flex-pack: center;-->
<!--        justify-content: center;-->
<!--        position: relative;-->
<!--    }-->
<!--    li {-->
<!--        list-style: none;-->
<!--    }-->
<!--    user agent stylesheet li {-->
<!--        display: list-item;-->
<!--        text-align: -webkit-match-parent;-->
<!--    }-->
<!--</style>-->
