<template>
<view class="my">
	<view class="login-box">
		<img src="/static/unlogin.png" class="img">
	</view>
	<view class="login-message">
		<u-cell-group >
		<u-cell
		size="large"
        :title="userStore.isLogin?`您好，${userStore.userName}`:'您尚未登录'"
        value=""
		clickable
		@click="clickHandler"
        :label="userStore.isLogin?`点击退出登录`:'点击登录'"
		:isLink="true"
        center
    ></u-cell>
	</u-cell-group>
	</view>
	<view class="list">
		<u-cell-group>
		<u-cell icon="setting-fill" clickable title="修改个人信息"></u-cell>
		<u-cell icon="rmb-circle-fill" clickable title="余额" ></u-cell>
		<u-cell icon="home-fill" clickable title="修改地址" @click="changeAddressPage"></u-cell>
	</u-cell-group>
	</view>
	<u-modal v-model="modal.isShow" :show="modal.isShow" showCancelButton closeOnClickOverlay @confirm="confimHandler"
        @cancel="modal.isShow = false" @close="modal.isShow = false"
        content="是否确认退出登录"></u-modal>
</view>
</template>
<script setup lang="ts">
import {reactive} from 'vue';
import {useUserStore} from "@/store/modules/user"
const userStore=useUserStore();
const modal=reactive({
	isShow:false
})
function login(){
	uni.navigateTo({
		url:"/pages/login/index",
		animationType: 'pop-in',
		animationDuration: 200
	})
}
function clickHandler(){
	if(!userStore.isLogin){
		login();
	}else {
		modal.isShow=true;
	}
}
function changeAddressPage(){
	if(!userStore.isLogin){
		uni.$u.toast("请先登录");
		return;
	}
	uni.navigateTo({
		url:"/pages/address/index"
	})
}
function confimHandler(){
	modal.isShow=false;
	userStore.logOut();
}
</script>
<style lang="less" scoped>
    .my {
		padding-top: 70rpx;
		.login-box {
			height: 100px;
			width: 100px;
			margin: 0 auto;
			margin-bottom: 80rpx;
			display: flex;
			align-items: center;
			justify-content: center;
			overflow: hidden;
			.img {
				height: 100%;
				width: 100%;
			}
		}
		.list {
			margin-top: 70rpx;
		}
	}
</style>