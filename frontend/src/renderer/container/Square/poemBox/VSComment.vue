
<template>
      <div class = "VS-comment-item panel-block">
      <p class="VS-comment-avatar"></p>
      <p class="VS-comment-content">
        <p class="VS-comment-text">
          <a v-bind:href="comment.avatar"></a>
          {{comment.commentText}}
        </p>
        <p class="VS-comment-bottom">
          <span class="VS-comment-time"></span>
          <ul class="VS-comment-bottom-list">
            <!-- checkreply -->
            <li><a href="javascript:clickReplyDetail" v-if="'replyTo' in comment">回复记录</a></li>
            <!-- reply  -->
            <li><a href="javascript:clickReply">回复</a></li>
            <!-- like -->
            <li>
              <a href="#" class="tag is-right  VS-comment-bottom-item VS-item-like" @click="clickLike">
                <img v-bind:src="likeSrc"  width="10" height="10" alt="like">
                <span class=" is-left is-small">{{comment.likeCount}}</span>
              </a>
            </li>
          </ul>
        </p>
      </p>
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

export default {
  name: "VScomment",
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
    clickLike() {
      var $like = $(".VS-item-like");
      if ($like.hasClass("selected")) {
        this.likeSrc = this.hollowLikeSrc;
        this.poem.likeCount--;
        $like.removeClass("selected");
      } else {
        console.log("selected");
        this.likeSrc = this.fullLikeSrc;
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
</template>
