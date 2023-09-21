<template>
    <view class="sender-list">
        <view class="nav">
            <u-subsection :list="navList" :current="navNowPage" @change="navChangeHandler"></u-subsection>
        </view>
        <view class="list">
            <orderVue :orderList="orderList" :isHistory="isHistory" @refesh="refesh"></orderVue>
        </view>
    </view>
</template>
<script setup lang="ts">
import {reactive,ref,watch} from 'vue';
import orderVue from "@/component/deliveryOrder/index.vue"
import {useUserStore} from "@/store/modules/user"
import {deliveryOrdersActiveApi,deliveryOrdersHistoryApi} from "@/api/delivery-order/index"
import type {DeliveryOrdersRes} from "@/api/delivery-order/types/index"
import {onShow} from "@dcloudio/uni-app"
const userStore=useUserStore();
const navList=reactive(['正在进行订单', '已完成订单']);
const navNowPage=ref(0);
const isHistory=ref(false);
let orderList=reactive<DeliveryOrdersRes>([]);
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
function navChangeHandler(num:number) {
    navNowPage.value=num;
}
function refesh(){  
    if(navNowPage.value===0){
        getActiveData()
    }else {
        getHistoryData();
    }
}
function getActiveData(){
    deliveryOrdersActiveApi({deliveryId:userStore.userId}).then(res=>{
        orderList.length=0;
        orderList.push(...res.data);
        isHistory.value=false;
    })
   
}
function getHistoryData(){
    deliveryOrdersHistoryApi({deliveryId:userStore.userId}).then(res=>{
        orderList.length=0;
        orderList.push(...res.data);
        isHistory.value=true;
    }) 
    
}
onShow(()=>{
    if(navNowPage.value===0){
        getActiveData()
    }else {
        getHistoryData();
    }
})
</script>
<style lang="less" scoped>

</style>