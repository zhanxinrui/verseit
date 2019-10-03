<template>
    <div>
        <div class="tile is ancestor  " v-for="poem in poems" v-bind:key="poem" style="margin:0 0 10em 0">
            <div class="tile  is-vertical is-parent is-1  transp-div"  style="padding:0 0 !important; margin:0 0 !important; ">
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
                        avatar:"http://img5.imgtn.bdimg.com/it/u=1966024900,143657959&fm=26&gp=0.jpg",
                        title: "风尘晴晚",
                        content: "风吹槐影暗香浮，雨打芭蕉篆泅出。\n" +
                            "\n" +
                            "煮茶销夜难自梦，鹣鲽双意各栖孤。\n" +
                            "\n" +
                            "博山心字冷银炉，画屏梅枝冻玉壶。\n" +
                            "\n" +
                            "巫山除却寻夜雨，潇湘晴晚又更烛。",
                        comment: "comment",
                        commendCount:"123",

                    },
                    {
                        avatar:"http://img5.imgtn.bdimg.com/it/u=1966024900,143657959&fm=26&gp=0.jpg",
                        title: "咏蚊子",
                        content: "夜幕垂临螡蚋叮，\n" +
                            "眼前耳后不闲停。\n" +
                            "如今肆虐原生种，\n" +
                            "引进新蚊正宿营。",
                        comment: "comment2",
                        commendCount:"123",


                    },
                    {
                        avatar:"http://img5.imgtn.bdimg.com/it/u=1966024900,143657959&fm=26&gp=0.jpg",
                        title: "蜗牛吟 ",
                        content: "身背家业做游侠， \n" +
                            "走到哪里哪是家。 \n" +
                            "不慕虚荣居一室， \n" +
                            "逍遥自在走天涯。",
                        comment: "comment2",
                        commendCount:"123",


                    },
                    {
                        avatar:"http://img5.imgtn.bdimg.com/it/u=1966024900,143657959&fm=26&gp=0.jpg",

                        title: "乌衣巷",
                        content: "乌衣巷口景依旧，不见当年帝王家。 \n" +
                            "百姓入室称上主，紫燕绕梁闹喳喳。",
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

