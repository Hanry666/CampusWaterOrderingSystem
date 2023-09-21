<template>
<view class="index">
  <u-toast ref="toast"></u-toast>
  <senderIndex v-if="userStore.isLogin&&!userStore.role"></senderIndex>
  <userIndex v-else></userIndex>
  
</view>
</template>
<script setup lang="ts">
import {ref} from 'vue';
import senderIndex from "./sender/index.vue";
import userIndex from "./user/index.vue";
import {useUserStore} from "@/store/modules/user"
const userStore=useUserStore();
const toast=ref();
let show=ref(false);
</script>
<style lang="less" scoped>
    
</style>