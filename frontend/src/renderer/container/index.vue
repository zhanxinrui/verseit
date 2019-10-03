<template>
  <div>
    <BeeHeader></BeeHeader>
    <Section @tagEvent="tagEvent" :tagList="tagList" @buttonEvent="buttonEvent"></Section>
    <div class="container">
       <!-- 一个三层布局 -->
      <div class = "tile is-ancestor">  
        <div class="tile is-parent">
          <div class="left-space tile is-child is-3"></div>
          <div class="mid-space tile is-child is-5">
    
      <div class="container menu2  " id="index-main">
        <ul class="sub-menu">
          <li class="is-danger">
            <router-link :to="{name: 'pubPoem'}" class="selected1" >名诗</router-link>
          </li>
          <li class="is-warning is-focused">
            <router-link :to="{name: 'userPoem'}">原创</router-link>
          </li>
          <li class="is-info is-focused">
            <router-link :to="{name: 'concernPoem'}">关注</router-link>
          </li>
        </ul>
      </div>
        <router-view></router-view>




          </div>
          <div class="right-space tile is-child is-3"></div>
        </div>
      </div>

      
    </div>
  </div>
</template>

<script>
import BeeHeader from "@/components/common/BeeHeader";
import BeeFooter from "@/components/common/BeeFooter";
import Section from "@/components/common/Section";
import Skeleton from "@/components/common/Skeleton";
import _ from "lodash";
import { sampleBackGroundColor } from "@/utils";
export default {
  name: "NewBeeIndex",
  components: { BeeHeader, Section, BeeFooter, Skeleton },
  data() {
    return {
      tag: "",
      type: "",
      blogs: [
        {
          title: "hello",
          content: "test content"
        }
      ], //blogs分页缓存
      blogLoadingOk: false,
      tagList: ["java", "python", "node", "go", "javascript", "sql"]
    };
  },

  destroyed() {},
  mounted() {
    this.getBlogs();
    this.selectedChange();
  },
  methods: {
    selectedChange() {
      $(".sub-menu li").click(function() {
        console.log("error");
        $(this)
          .siblings("li")
          .children()
          .removeClass("selected1"); // 删除其他兄弟元素的样式
        $(this)
          .children()
          .addClass("selected1"); // 删除其他兄弟元素的样式

        //$(this)..addClass('selected');                            // 添加当前元素的样式
      });
    },
    tagEvent(tag) {
      this.tag = tag;
    },
    buttonEvent(type) {
      this.type = type;
    },
    goBlog(blog) {
      this.$router.push({ path: "/blog/" + blog.id });
    },
    getBlogs() {
      this.blogLoadingOk = false;
      let searchBlog = {};
      _.isEmpty(this.tag)
        ? (searchBlog.tag = "all")
        : (searchBlog.tag = this.tag);

      !_.isEmpty(this.type) ? (searchBlog.sort = this.type) : searchBlog;
      this.$http
        .post("/blog/getBlogByTag", searchBlog, {
          headers: {
            Accept: "application/json;charset=UTF-8"
          }
        })
        .then(res => {
          this.blogLoadingOk = true;
          this.blogs = res.data.data.content;
        });
    },
    sampleBackGroundColor() {
      return sampleBackGroundColor();
    }
  },
  watch: {
    tag(tag) {
      this.getBlogs();
    },
    type(type) {
      this.getBlogs();
    }
  }
};
</script>

<style scoped>
.bd-star-icon {
  font-size: 1.2rem;
  color: #0a0a0a;
  opacity: 0.25;
  bottom: 10px;
  left: 30px;
  position: absolute;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
}
</style>


