<template>
    <div>
        <div class="tile is ancestor  " v-for="poem in poems" v-bind:key="poem" style="margin:0 0 10em 0">
            <div class="tile  is-vertical is-parent is-1  transp-div" style="padding:0 0 !important; margin:0 0 !important; ">
                <article class=" is-child box transp-div" style="width:100%; height:100% ; padding:0 0 !important; margin:0 0 !important; ">
                    <img :src=poem.avatar alt="杜甫" width="48" height="48" class="transp-div" style="padding:10px 0px 1px 0px!important; margin:0 0 !important;">
                </article>

            </div>
            <div class="tile is-vertical is-parent is-9" >
                <div class="box box-transparent">
                    <div class="media">
                        <div class="media-content">
                            <div class=" ">
                                <p style="margin:10px 0px;"><strong>{{poem.title}}</strong></p>
                                <p style="margin:10px 0px;">{{poem.content}}</p>
                                <p style="margin:20px 0px 0px 0px;" >
                                    <a class="tag is-medium is-lightyellow has-addons">
                                        <i class="fa fa-caret-up"></i>
                                        <span class="icon is-medium">{{poem.star}}</span>
                                    </a>
                                    <a class="tag is-medium is-lightblue">
                                        <i class="fa fa-caret-down"></i>
                                    </a>
                                    <a href="#" class="ml-10">
                                        <i class="fa fa-comment"></i>
                                        <span>{{poem.commendCount}}</span>
                                    </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>




    </div>
</template>

<script>
    import newBeeIndex from "@/container/index.vue";
    export default {
        name: "pubPoem",
        data() {
            return {
                poems: [
                    {
                        avatar:"https://pbs.twimg.com/profile_images/561614005714690049/pw_IxPTO_normal.png",
                        title: "浣溪沙",
                        content:  "照日深红暖见鱼，连溪绿暗晚藏乌。\n 黄童白叟聚睢盱\n麋鹿逢人虽未惯，猿猱闻鼓不须呼。\n归家说与采桑姑。",
                        comment: "comment",
                        commendCount:"123",

                    },
                    {
                        avatar: "https://pbs.twimg.com/profile_images/597812009162436610/jb15Gegy_normal.jpg",
                        title: "静夜思",
                        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
                        comment: "comment2",
                        commendCount:"123",


                    },
                    {
                        avatar:"https://pbs.twimg.com/profile_images/530443756114153472/nSmN8eUj_normal.jpeg",
                        title: "静夜思",
                        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
                        comment: "comment2",
                        commendCount:"123",


                    },
                    {
                        avatar:"http://img2.imgtn.bdimg.com/it/u=550536681,1005265139&fm=11&gp=0.jpg",
                        title: "静夜思",
                        content: "床前明月光，疑是地上霜。\n举头望明月，低头思故乡。",
                        comment: "comment2",
                        commendCount:"123",


                    }
                ]
            };
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
