<template>

<div>
  <div class="tile is ancestor  " v-for="poem in poems" v-bind:key="poem" style="margin:0 0 10em 0">
    <div class="tile  is-vertical is-parent is-1  transp-div" style="padding:0 0 !important; margin:0 0 !important; ">
      <article class=" is-child transp-div .with-2-border-radius" style="width:100%; height:100% ; padding:12px 0px 0px 0px;  ">
        <img :src=poem.avatar alt="杜甫" width="48" height="48" class="transp-div with-shadow" >
      </article>
    </div>
    <div class="tile is-vertical is-parent is-9 " >
      <!-- <div class="published-decorate"></div> -->
      <div>
        <div class="media ">
          <div class="media-content">
            <VSPubPoem v-bind:poem="poem"></VSPubPoem>  
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</template>
<script>
import newBeeIndex from "@/container/index.vue";
import _ from "lodash";
import {
  TransferDom,
  Popup
} from "vux";
import userApi from "@/api/user";
import {
  getQueryString,
  github2newBee,
  EP
} from "@/utils";
import {
  debug
} from "util";
import VSPubPoemBot from "@/container/Square/poemBox/VSPubPoem";
export default {
  name: "pubPoem",
  components: { VSPubPoem },
  data() {
    return {
      poems: [{
        avatar: "https://images.cnblogs.com/cnblogs_com/captainhook/1560747/o_4bc1908318fcf20456c28%20(1).jpg",
        title: "浣溪沙",
        content: "照日深红暖见鱼，连溪绿暗晚藏乌。\n 黄童白叟聚睢盱\n麋鹿逢人虽未惯，猿猱闻鼓不须呼。\n归家说与采桑姑。照日深红暖见鱼，连溪绿暗晚藏乌。\n 黄童白叟聚睢盱\n麋鹿逢人虽未惯，猿猱闻鼓不须呼。\n归家说与采桑姑。",
        comments: [{
          userName: "zhan",
          avatar: "https://images.cnblogs.com/cnblogs_com/captainhook/1560747/o_4bc1908318fcf20456c28%20(1).jpg",
        commentCount: 123,
        likeCount: 22,
          commentText: "这是一条没有情感的评论",
          time: "10/8 11:30"
        }, {
          userName: "zhan",
          avatar: "https://images.cnblogs.com/cnblogs_com/captainhook/1560747/o_4bc1908318fcf20456c28%20(1).jpg",
        commentCount: 123,
        likeCount: 22,
          commentText: "这是第二条没有情感的评论",
          time: "10/8 11:20"
        }, {
          userName: "xin",
          replyTo: "zhan",
          avatar: "https://wx4.sinaimg.cn/mw690/75c88ec7ly1g68fnhctnnj20u00g7add.jpg",
          likeCount: "12",
          commentText: "这是第二条没有情感的评论",
          time: "10/8 11:31"
        }],
        commentCount: 123,
        likeCount: 22
      }, {
        avatar: "http://img3.imgtn.bdimg.com/it/u=2073014997,3871528144&fm=26&gp=0.jpg",
        title: "静夜思",
        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
        comment: "comment2",
        commentCount: 123,
        likeCount: 22
      }, {
        avatar: "http://img3.imgtn.bdimg.com/it/u=2073014997,3871528144&fm=26&gp=0.jpg",
        title: "静夜思",
        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
        comment: "comment2",
        commentCount: 123,
        likeCount: 22
      }, {
        avatar: "http://img2.imgtn.bdimg.com/it/u=550536681,1005265139&fm=11&gp=0.jpg",
        title: "静夜思",
        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
        comment: "comment2",
        commentCount: 123,
        likeCount: 22
      }]
    };
  },
  methods: {
    // test1(){console.log("yes");},
    clickStar(e) {
      var $star = $(".VS-item-star");
      console.log("clicked")
      if ($star.hasClass("selected")) {
        console.log("remove class");
        // $star.children("img").setAttribute("src", fullStarSrc);
        this.starSrc = this.hollowStarSrc;
        // console.log("hollowLikeSrc",hollowStarSrc);
        console.log("starSrc", this.starSrc);
        // this.$set(this.starSrc,this.hollowStarSrc);
        $star.removeClass("selected");
      } else {
        this.starSrc = this.fullLikeSrc;
        setTimeout(() => {
          console.log("im ok");
        }, 2000);
        this.starSrc = this.fullStarSrc;
        $star.addClass("selected");
        // console.log("has selected?",$star.hasClass("selected"));
      }
    },
    clickLike(e) {
      // var $like = $(".VS-item-like");
      var $like = $(e.currentTarget);
      console.log("clicked");
      if ($like.hasClass("selected")) {
        console.log("remove class");
        $like.children("img").setAttribute("src", this.hollowStarSrc);
        // this.likeSrc = this.hollowLikeSrc;
        poem.likeCount--;
        // $like.children("img").setAttribute("src", hollowLikeSrc);
        $like.removeClass("selected");
      } else {
        console.log("selected");
        console.log("poem.likeCount", poem.likeCount);
        // setTimeout (test,100);
        // this.likeSrc = this.fullLikeSrc;
        $like.children("img").setAttribute("src", this.fullStarSrc);
        poem.likeCount++;
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