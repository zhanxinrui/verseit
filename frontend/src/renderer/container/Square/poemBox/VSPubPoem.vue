<template>
<div class="side">
  <div class="profile-callout">
    <div class="profile-container">
      <div class="overlay ">
        <img class="VS-bg" src="static/image/published-bg.jpg">
        <div class="VS-text">
          <p style="margin:10px 0px; z-index:2;"><strong>{{poem.title}}</strong></p>
          <p style="margin:10px 0px;z-index:2;">{{poem.content}}</p>
        </div>
        <div class="VS-bottom"style="margin:20px 0px 0px 0px;z-index:2;" >
          <a class="tag is-left has-addons is-transparent VS-bottom-item VS-item-star " @click="clickStar()" >
            <img v-bind:src="starSrc"  width="18" height="18" alt="star">
          </a>
          <a class="tag is-right  is-transparent VS-bottom-item VS-item-comment " @click="clickComment()">
            <img v-bind:src="commentSrc"  width="18" height="18" alt="star">
            <span class=" is-left is-small">{{poem.commentCount}}</span>
          </a>
          <a href="#" class="tag is-right  is-transparent VS-bottom-item VS-item-like" @click="clickLike()">
            <img v-bind:src="likeSrc"  width="18" height="18" alt="like">
            <span class=" is-left is-small">{{poem.likeCount}}</span>
          </a>
        </div>
      </div>
    </div>
  </div>
  <div class="VS-comment-container panel">
    <div class="VS-comment-item VS-comment-input panel-block ">
      <ul class="columns" style="width:100%">
        <li class="is-three-quarters column is-narrow is-small"><textarea placeholder="发表评论" class="input is-primary is-rounded" ></textarea></li>
        <li class="column is-two-quarters" ><span class="emoji"></span></li>
        <li class="column"><button @click="sendComment" class="button is-narrow is-small"> 发送</button></li>
      </ul>
    </div>
    <div class = "VS-comment-list" v-for= "comment in poem.comments" v-bind:key="commentList" >
      <VScomment v-bind:comment="comment"></VScomment>
    </div>
  </div>
</div>
</template>

<script>
import newBeeIndex from "@/container/index.vue";
import hollowStarSrc from "@/assets/icon/star_hollow.png";
import fullStarSrc from "@/assets/icon/star_normal.png";
import blinkStarSrc from "@/assets/icon/star_blink.png";
import hollowLikeSrc from "@/assets/icon/like_hollow_thin.png";
import fullLikeSrc from "@/assets/icon/like.png";
import commentSrc from "@/assets/icon/chat_black.png";
import VScomment from "@/container/Square/poemBox/VScomment";

export default {
  name: "VSPubPoem",
  components: { VScomment },

  // components:{"VScomment"},
  props:['poem'],
  data() {
    return {
      hollowStarSrc: hollowStarSrc,
      starSrc: hollowStarSrc,
      fullStarSrc: fullStarSrc,
      blinkStarSrc: blinkStarSrc,
      hollowLikeSrc: hollowLikeSrc,
      fullLikeSrc: fullLikeSrc,
      likeSrc: hollowLikeSrc,
      commentSrc: commentSrc,
    } 
  },
  methods: {
    // test1(){console.log("yes");},
    clickStar(e) {
      var $star = $(".VS-item-star");
      console.log("clicked")
      if ($star.hasClass("selected")) {
        console.log("remove class");
        this.starSrc = this.hollowStarSrc;
        console.log("starSrc", this.starSrc);
        $star.removeClass("selected");
      } else {
        this.starSrc = this.fullLikeSrc;
        setTimeout(() => {
          console.log("im ok");
        }, 2000);
        this.starSrc = this.fullStarSrc;
        $star.addClass("selected");
      }
    },
    clickLike(e) {
      var $like = $(".VS-item-like");
      if ($like.hasClass("selected")) {
        this.likeSrc = this.hollowLikeSrc;
        // console.log("this.likecount",this.poem.likeCount);
        this.poem.likeCount--;
        $like.removeClass("selected");
      } else {
        console.log("selected");
        // console.log("poem.likeCount", poem.likeCount);
        // setTimeout (test,100);
        this.likeSrc = this.fullLikeSrc;
        // $like.children("img").setAttribute("src", this.fullStarSrc);
        this.poem.likeCount++;
        $like.addClass("selected");
      }
    },
    clickComment(e) {
      console.log("have Clicked Comment;  waitng finished");
    }
    // clickStar
  }
};
</script>
<style scoped>
#status {
width: 600px;
height: 400px;
border-style: ridge;
border-color: black;
border-width: thin;
background: #1dcaff;
}
.title {
width: 600px;
height: 50px;
border-bottom: 1px dotted;
}
.content {
height: 250px;
border-bottom: 1px dotted;
border-bottom-width: thin;
}
</style>