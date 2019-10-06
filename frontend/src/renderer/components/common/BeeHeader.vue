<template>
  <div
    id="bee-header"
    element-loading-text="正在努力请求github..."
    element-loading-background="rgba(0, 0, 0, 0.8)"
  >

    <div class="is-underline" id="fix_nav">
      <div class="container">
        <a class="fixed-icon" @click="handleMenuCommand('/')">
          <img v-bind:src="icon_min" width="25" height="25" alt="verseit" id="icon-top">
        </a>
        <nav class="navbar">
          <div class="login-before is-hidden-mobile" style="padding-top: 5px;">
            <a
              class="navbar-item is-hidden-desktop"
              href="https://github.com/pkwenda/my-bbs"
              target="_blank"
            >
              <span class="icon" style="color: #333;">
                <i class="fa fa-lg fa-github is-size-2"></i>
              </span>
            </a>
          </div>
          <div class="navbar-item is-hidden-desktop">
            <div v-transfer-dom>
              <popup v-model="popupShow" position="right" style="background-color: #fff;">
                <div style="width:200px;">
                  <p class="menu-label"></p>
                  <div class="tags has-addons" style="margin-left:30px;">
                    <span class="tag is-warning">
                      <img :src="icon_min" alt="new-bee 社区" width="50" style="float:left">
                    </span>
                    <span class="tag is-danger">测试版</span>
                  </div>

                  <ul class="menu-list">
                    <li style="padding: 2px 5px 2px 5px;">
                      <a class="button is-warning">Log</a>
                    </li>
                    <li style="padding: 2px 5px 2px 5px;">
                      <a class="button is-success">Personal</a>
                    </li>
                    <li style="padding: 2px 5px 2px 5px;">
                      <a class="button is-info">Setting</a>
                    </li>
                    <li style="padding: 2px 5px 2px 5px;">
                      <a class="button is-danger">Quit</a>
                    </li>
                  </ul>
                </div>
              </popup>
            </div>
            <div class="navbar-burger burger" data-target="navMenuDocumentation" @click="openPopup">
              <span></span>
              <span></span>
              <span></span>
            </div>
          </div>

          <div id="navMenuDocumentation" class="navbar-menu">
            <div class="navbar-start">
              <router-link class="navbar-item is-tab is-active" to="/" name="诗阁">
                <img v-bind:src="squareSrc" width="28" height="28" alt="square">
              </router-link>
              <router-link class="navbar-item is-tab" to="/notification" name="通知">
                <img v-bind:src="notiSrc" width="28" height="28" alt="notification">
              </router-link>
              <router-link class="navbar-item is-tab" to="/personal" name="个人">
                <img v-bind:src="personSrc" width="28" height="28" alt="square">
              </router-link>
              <router-link class="navbar-item is-tab" to="/setting" name="设置">
                <img v-bind:src="setSrc" width="28" height="28" alt="square">
              </router-link>
            </div>
            <div class="navbar-end">
              <router-link class="navbar-item is-tab" to="/compose" name="创作">
                <img v-bind:src="editSrc" width="28" height="28" alt="square">
              </router-link>

              <!-- <div class="qq">Official Account：***</div> -->
              <div class="login-before" style="padding-top: 5px;">
                <!-- pc -->
                <!--<a class="" href="" target="_blank">
                <span class="icon" style="color: #333;">
                </span>
                </a>-->
              </div>

              <div class="navbar-item is-hidden-mobile">
                <div class="field has-addons">
                  <div class="control">
                    <input
                      type="input"
                      class="input"
                      name="email"
                      placeholder="search..."
                      required="required"
                      style="height: 36.4px;"
                    >
                    <input type="hidden" name="redirect" id="name" value="/fr/#thanks">
                  </div>
                  <div class="control">
                    <input type="submit" class="button is-warning" value="GO">
                  </div>
                </div>
              </div>

              <div class="navbar-item is-hidden-mobile">
                <!--<span class="icon is-medium">-->
                <i class="iconfont icon-tixing"></i>
                <!--</span>-->
              </div>

              <el-dropdown v-show="isLogin" @command="handleMenuCommand">
                <span class="el-dropdown-link">
                  <div class="login-after">
                    <a class="is-hidden-mobile" @click="goUserPage" target="_blank">
                      <img
                        :src="userInfo.avatarPath"
                        class="header-avatar img-circle"
                        style="margin-top: 10px"
                      >
                    </a>
                  </div>
                </span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item command="/editor/post/new">Compose</el-dropdown-item>
                  <!--<el-dropdown-item>分享文章</el-dropdown-item>-->
                  <el-dropdown-item command="/user" divided>Personal</el-dropdown-item>
                  <el-dropdown-item v-if="isLogin" command="/setting">Setting</el-dropdown-item>
                  <el-dropdown-item>My Collection</el-dropdown-item>
                  <!-- <el-dropdown-item divided>我的收入</el-dropdown-item>
                  <el-dropdown-item >我的打赏</el-dropdown-item>-->
                  <el-dropdown-item command="exit" divided>Quit</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>

              <div class="login-before">
                <div class="navbar-item">
                  <div class="field is-grouped">
                    <p class="control">
                      <a class="button is-warning" @click="goLogin" v-show="!isLogin">
                        <strong>Log</strong>
                      </a>
                    </p>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </nav>
      </div>
    </div>
  </div>
</template>


<script>
import { TransferDom, Popup } from "vux";
import oAutoApi from "@/api/oAuth";
import userApi from "@/api/user";
import iconsrc from "@/assets/icon/logo-min.png";
import squareSrc from "@/assets/icon/01-square.png";
import notiSrc from "@/assets/icon/02-notification.png";
import personSrc from "@/assets/icon/03-personal.png";
import setSrc from "@/assets/icon/04-setting.png";
import editSrc from "@/assets/icon/05-edit.png";
import { getQueryString, github2newBee, EP } from "@/utils";
import {
  getInfo,
  getToken,
  removeToken,
  getUserName,
  setToken,
  setInfo
} from "@/utils/auto";
import _ from "lodash";
import { debug } from "util";

export default {
  name: "BeeHeader",
  components: {
    Popup
  },
  data() {
    return {
      popupShow: false,
      isLogin: false,
      user: {},
      loading: false,
      userInfo: {},
      icon_min: iconsrc,
      squareSrc: squareSrc,
      notiSrc: notiSrc,
      personSrc: personSrc,
      setSrc: setSrc,
      editSrc: editSrc
    };
  },
  created() {},
  destroyed() {},
  mounted() {
    // oautoApi.getUser("ac07d320e8ca4f30eb92", response => {
    //   debugger;
    // });
    this.auth();
    this.auto();
    this.scrollMenu();
    this.menuHover();
  },
  methods: {
    scrollMenu() {
      //滚动隐藏导航栏和显示导航栏
      //设置导航滚动到多少再消失(可自己设置)
      var fix_length = 200;
      // 获取导航节点
      var $nav = $("#fix_nav");
      if ($nav.offset) console.log("nav", $nav.offset);
      // 获取导航节点距离顶部位置
      // var navTop = document.getElementsByClassName("").offsetTop;
      // console.log("navTop",navTop);
      var navTop = $nav.offset().top;
      // var navTop = 0;
      // 获取导航的高度(包括border)
      var navH = $nav.outerHeight();
      // 设置初始位置
      var winTop_1 = 0;
      // 判断浏览器宽度(可用于自适应是否启用此效果)
      var winWidth = $(window).width();
      // 判断滚动条
      $(window).on("scroll", function() {
        // 滚动条距离顶部距离
        var winTop_2 = $(window).scrollTop();
  
        //开始浮动，不过不显示
        if (winTop_2 > navTop) {
          $nav.addClass("fixed-nav"); //固定在最上方还上一点
          if (winTop_2 > winTop_1) {
            $nav.addClass("fix_sec");
            if (winTop_2 > fix_length) {
              //如果大于就隐藏
              $nav.removeClass("fix_sec");
            }
          }
        } else {
          $nav.removeClass("fixed-nav");
        }
        //判断鼠标向上滚动，显示出来
        if (winTop_2 > winTop_1) {
          $nav.removeClass("fixed-nav-appear");
        } else if (winTop_2 < winTop_1) {
          // console.log("should appear");
          $nav.addClass("fixed-nav-appear");
          // console.log("nav.top", $nav.offset().top);
          $nav.removeClass("fix_sec"); //也可以不要
        }
        // 滚动后距离赋值
        winTop_1 = $(window).scrollTop();
      });
    },

    toggleActive(name) {
      // $(".navbar-start>navbar-item").click(()=>{
      // items = $(".navbar-start>navbar-item");
      // })

      // Get all "navbar-burger" elements
      var navbarBurgers = $(".navbar-start .navbar-item");
      console.log("nabvsdkflajs", navbarBurgers);
      for (var i = 0; i < navbarBurgers.length; i++) {
        console.log("eacj aere", navbarBurgers[i]);
        // var $temp = $navbarBurgers[i];
        console.log("vallll:", $(navbarBurgers[i]).attr("name"));
        if ($(navbarBurgers[i]).attr("name") == "诗阁")
          $(navbarBurgers[i]).removeClass("is-active");
        if ($(navbarBurgers[i]).attr("name") == name)
          $(navbarBurgers[i]).addClass("is-active");
      }
    },

    auth() {
      console.log("invoke auth");
      let code = getQueryString("code");//如果包含有code 说明使用github登录
      //github返回code码
      if (!_.isEmpty(code)) {
        console.log("test production");
        this.loading = true;
        oAutoApi.getUser(code, response => {
          //code就是github用户的code eg: ac07d320e8ca4f30eb92  需要github认证是哪个人然后才能存用户和登录
          let user = github2newBee(response);
          console.log("user:" + user);
          userApi.saveUser(user, response => {
            //再保存用户
            //console.log("resp avatar_url",response.data.avatar_url);
            let loginJson = {
              userName: response.data.userName,
              githubNodeId: response.data.githubNodeId,
              avatarPath: response.data.avatarPath
            };
            this.login(loginJson);
          });
        });
      }    },
    menuHover() {
      var navbarBurgers = $(".navbar-start .navbar-item");
      var tmp = new Array();

        for (let i = 0; i < navbarBurgers.length; i++) {
        tmp[i] = $(navbarBurgers[i]).html();//很迷  。。
        
        console.log("before",tmp[i])
        }
        for (let i = 0; i < navbarBurgers.length; i++) {
        console.log("hover....", navbarBurgers[i]);
        // var $temp = $navbarBurgers[i];

        $(navbarBurgers[i]).on("mouseenter",function() {//必须要用$
          console.log("name:",$(this).attr("name"));
           $(this).text($(this).attr("name"));
        });
        $(navbarBurgers[i]).on({"mouseleave":function(){
          console.log("i",i)
          console.log("leave",tmp[i]);
           $(this).html(tmp[i]);
        }});
        // console.log("i:",$navbarBurgers[i]);
      }
    },
    getUserInfo(userName) {
      userApi.getUserInfoByUserName(userName, response => {
        console.log("response data", response.data);

        this.userInfo = this.$store.state.user.userInfo = response.data;
        console.log("getusesrinfo" + this.userInfo.avatarPath);
      });
    },
    goLogin() {
      this.$router.push("/login");
    },
    goUserPage() {
      this.$router.push(`/user/${getUserName()}`);
    },
    auto() {
      // alert("asdf"+iconsrc);]
      // alert("icon_src",icon_src);
      //如果token为空就应该跳转。
      //尝试使用token进行登录，如果没有token或没登录上就返回主页。

      if (!$store.state.Authorization) {
        this.isLogin = true; //应该放到vuex中
        console.log("is log?", this.isLogin);

        //this.user.email = getToken();
        this.userInfo = this.$store.state.user.userInfo;
        //console.log('username',getUserName())
        //console.log("userInfo1",this.userInfo);

        if (this.$store.state.user.userInfo == null) {
          this.getUserInfo(getUserName());
          console.log("info" + getInfo());
          console.log("userInfohaha ", this.userInfo);
          //console.log("userInfo",this.userInfo["avatarPath"]);
          console.log(this.userInfo.userName);
        }
        this.$forceUpdate();

        return;
      }

      this.isLogin = false;
    },
    login(loginJson) {
      let loginJsonCopy = { ...loginJson }; //...将数组拆成单个
      let token = {
        token: EP(loginJson)
      };
      userApi.login(token, response => {
        loginJsonCopy.id = response.data.userId;
        console.log("loginjson!:", loginJsonCopy);
        // setToken(EP(loginJsonCopy));  token不放到cookie中
        setInfo(
          JSON.stringify({
            userName: response.data.userName,
            avatarPath: response.data.avatarPath
          })
        );
        this.userToken = response.message;
        this.changeLogin({Authorization:this.userToken});
        this.getUserInfo(getUserName());
        console.log("info", this.userInfo);
        //setInfo(JSON.stringify({ userName:"hah" }));
        window.location.href = "/";
        //vm.isLogin = true;
        //this.$router.push("/");
        //this.$forceUpdate();
      });
    },
    exit() {
      removeToken();
    },
    handleMenuCommand(router) {
      if (router === "exit") {
        this.exit();
        this.auto();
        return;
      }
      if (router === "/user" || router === "/setting")
        router += `/${getUserName()}`;
      this.$router.push(router);
    },
    openPopup() {
      this.popupShow = true;
    }
  }
};
</script>
