<template>
<view class="list">
    <userListVue v-if="userStore.role"></userListVue>
    <senderListVue v-else></senderListVue>
</view>
</template>
<script setup lang="ts">
import {} from 'vue';
import { useUserStore } from '@/store/modules/user';
import userListVue from "./user/index.vue"
import senderListVue from "./sender/index.vue";
const userStore=useUserStore();

</script>
<style lang="less" scoped>
    .list {
        margin: 0 15px;
        padding-top: 20px;
    }
</style>