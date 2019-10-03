<template>
    <div>
        <BeeHeader></BeeHeader>
        <br/>
        <ul >
            <li class="button is-danger is-focused"><router-link :to="{name: 'collect'}">collect</router-link></li>
            <li class="button is-warning is-focused"><router-link :to="{name: 'concernUser'}">concernUser</router-link></li>
            <li class="button is-info is-focused"><router-link :to="{name: 'myCompose'}">myCompose</router-link></li>
        </ul>
        <router-view></router-view>
    </div>
</template>



<script>
    import BeeHeader from "../../components/common/BeeHeader";

    export default {
        name: "personal",
        components: {BeeHeader},
        mounted(){
            BeeHeader.methods.toggleActive("个人");
        }
    }
</script>

<style scoped>

</style>
