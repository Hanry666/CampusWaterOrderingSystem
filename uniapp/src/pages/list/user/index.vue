<template>
    <view class="user-list">
        <u-toast ref="toast"></u-toast>
        <view class="nav">
            <u-subsection :list="navList" :current="navNowPage" @change="navChangeHandler"></u-subsection>
        </view>
        <view class="list">
            <orderVue @refesh="refesh" :isHistory="navNowPage===1?true:false" :orderList="orderList"></orderVue>
        </view>
    </view>
</template>
<script setup lang="ts">
import {onShow} from "@dcloudio/uni-app"
import {reactive,ref,watch} from 'vue';
import orderVue from "@/component/userOrder/index.vue"
import {useUserStore} from "@/store/modules/user"
import {getActiveApi,getHistoryApi} from "@/api/user-order/index"
import type {ActiveResponseData} from "@/api/user-order/types/index"
const userStore=useUserStore();
const navList=reactive(['正在进行订单', '历史订单']);
const navNowPage=ref(0);
let orderList=reactive<Array<ActiveResponseData>>([]);
watch(navNowPage,(newValue,oldValue)=>{
    if(!userStore.judgeLogin()){
        return;
    }
    if(newValue===0){
        getActiveData()
    }else {
        getHistoryData();
    }
},{
    immediate:true
})
onShow(()=>{
    if(navNowPage.value===0){
        getActiveData()
    }else {
        getHistoryData();
    }  
})
function refesh(){
    if(navNowPage.value===0){
        getActiveData()
    }else {
        getHistoryData();
    }  
}
function navChangeHandler(num:number) {
    navNowPage.value=num;
}
function getActiveData(){
    getActiveApi({userId:userStore.userId}).then(res=>{
        orderList.length=0;
        orderList.push(...res.data);
        
    })
}
function getHistoryData(){
    getHistoryApi({userId:userStore.userId}).then(res=>{
        orderList.length=0;
        orderList.push(...res.data);
    }) 
}
</script>
<style lang="less" scoped>


</style>