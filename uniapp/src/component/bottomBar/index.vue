<template>
<view class="bottom-bar">
    <view class="left"  >
        <view class="item" v-if="props.isShoppingCar">
            <text>合计</text>
            <u-icon name="rmb" color="red" size="18"></u-icon>
        <text class="money">{{props.totalAmount}}</text>
        </view>
    </view>
    <view class="right" @click="clickHandler">
        <u-button type="primary" :text="btnText"></u-button>
    </view>
</view>
</template>
<script setup lang="ts">
import {ref,computed} from 'vue';
import { useUserStore } from '@/store/modules/user';
const userStore = useUserStore();
const props=withDefaults(defineProps<{
    isShoppingCar:boolean
    totalAmount:number
}>(),{
    isShoppingCar:false,
    totalAmount:0
})
const emit=defineEmits<{
    (e:'checkout'):void
}>()
const btnText=computed(()=>{
    console.log(props.isShoppingCar);
    if(props.isShoppingCar){
        return '去结算'
    }
    if(userStore.isLogin){
        return "去购物车"
    }else {
        return "去登录"
    }
    
    
})
/**
 * 登录方法
 */
 function login() {
    uni.navigateTo({
        url: "/pages/login/index"
    })
}
/**
 * 去购物车
 */
function shoppingCar(){
    uni.navigateTo({
        url: "/pages/shopping/index"
    })
}
/**
 * 点击事件
 */
function clickHandler(){
    if(props.isShoppingCar){
        emit('checkout');
        return;
    }
    if(userStore.isLogin){
        shoppingCar();
    }else {
        login();
    }
}
</script>
<style lang="less" scoped>
    .bottom-bar {
        background-color: #fff;
        border-top:.5rpx solid rgba(0,0,0,.3);
        width: 100%;
        display: flex;
        height: 45px;
        .left {
            display: flex;
            align-items: center;
            padding-left: 20px;
            width: 67%;
            .item {
                display: flex;
            }
            .money {
                color:red;
            }
        }
        .right {
            flex: 1;
            display: flex;
            background-color: #fff;
            z-index: 2;
            ::v-deep button {
                border-radius: 0;
                height: 100%;
                width: 100%;
            }
        }
    }
</style>