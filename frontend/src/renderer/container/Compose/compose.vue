<template>
    <div>
        <BeeHeader></BeeHeader>
        <br/>
        <div class="newCompose">
            <div class="compose-title">
                <input ref="compose-title" type="text" style="width: 500px" height="30px" v-model="title"></input>
            </div>
            <div class="compose-content">
                <textarea id="compose-content" type="textArea" style="width: 500px" v-model="content"></textarea>
            </div>
            <div>
                <button type="Button" @click="submit()">Save</button>
            </div>
            <div>
                <button type="Save" @click="submit()">Submit</button>
            </div>
        </div>
    </div>
</template>

<script>
    import BeeHeader from "@/components/common/BeeHeader";
    
    export default {
        name: "compose",
        components: { BeeHeader },
        data() {
            return {
                title: 'title',
                content: 'content'
            }
        },
        methods: {
            submit () {
                this.title = this.$refs.compose-title.value
                alert(this.title)
                this.content = document.getElementById('compose-content').value
            }
        },
        mounted(){
            BeeHeader.methods.toggleActive("创作");
        }
    }
</script>

<style scoped>

    .newCompose{
        width: 600px;
        height: auto;
        min-height: 20px;
        padding: 1px;
        margin: 0 auto;
    }
    .compose-title {
        opacity:0.5;
        padding: 1px;
        margin: 20px 30px 10px;
    }

    .compose-title input{
        text-indent: 1em;
        height: 30px;
        width: 100%;
        line-height: 30;
    }

    .compose-content {
        overflow: auto;
        opacity:0.5;
    }

    .compose-content textarea{
        text-indent: 1em;
        height: 400px;
        width: 100%;
        margin: 0px 30px 10px;
    }
    .newCompose button{
        float: left;
        font-size: 18px;
        height: 28px;
        width: auto;
        border: 1px solid #888;
        margin: 0px 5px 5px 30px;
        transition: all .5s;
    }
    .newCompose button:hover{
        box-shadow: 0 5px 5px rgba(0, 0, 0, 0.5);
        transform: translateY(-1px);
    }
</style>
