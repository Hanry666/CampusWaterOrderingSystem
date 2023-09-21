<template>
<view class="sender-index">
    <view class="empty"  v-show="dataList.length === 0">
            <u-empty mode="data" icon="http://cdn.uviewui.com/uview/empty/car.png">
        </u-empty>
        </view>
        <u-toast ref="toast"></u-toast>
    <view class="order-box">
        <view class="item" v-for="(item,index) in dataList" :key="index">
            <view class="top">
                <view class="number">状态:{{ item.orderStatus }}</view>
                <view class="price">{{item.orderPrice}}元</view>

            </view>
            <view class="address-box">
                <view class="address">
                    {{ item.campus }}{{ item.building }}{{ item.houseNumber }}
                </view>
                <view class="user">
                    用户:{{ item.userName }}   
                </view>
                <view class="phone">
                    电话:{{ item.userPhone }}
                </view>
            </view>
            <view class="bottom">
                <u-button text="抢单" plain type="primary" @click="claim(item.orderNumber)"></u-button>
            </view>
        </view>
    </view>
</view>
</template>
<script setup lang="ts">
import {reactive,ref} from 'vue';
import {getdeliveryAvailableApi,deliveryClaimApi} from '@/api/delivery/index'
import type {DeliveryAvailableRes} from "@/api/delivery/types"
import { useUserStore } from '@/store/modules/user';
const userStore=useUserStore();
const toast=ref();
let dataList=reactive<DeliveryAvailableRes>([]);
function getData(){
    getdeliveryAvailableApi().then(res=>{
        dataList.length=0;
        dataList.push(...res.data)     
    })
}
function claim(orderNumber:number){
    toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
    deliveryClaimApi({
        deliveryId:userStore.userId,
        orderNumber
    }).then(res=>{
        uni.$u.toast("抢单成功！");
        getData();
    }).finally(()=>{
        toast.value.hide();
    })
}
getData();
</script>
<style lang="less" scoped>
    .sender-index {
        width: 100%;
        padding-bottom: 50px;
        .empty {
            margin-top: 100px;
        }
        .order-box {
            margin: 0 15px;
            margin-top: 10px;
            
            .item {
                margin-bottom: 20px;
                border-radius: 10px;
                box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
                overflow: hidden;
                .top {
                    height: 30px;
                    display: flex;
                    align-items: center;
                    justify-content: space-between;
                    .number {
                        margin-left: 10px;
                    }
                    .price {
                        margin-right: 10px;
                        color: red;
                    }
                }
                .address-box {
                    margin: 10px 20px;
                    margin-top: 5px;
                    font-size: 32rpx;
                    font-weight: 500;
                    .address {
                        display: flex;
                        align-items: center;
                        justify-content: center;
                        font-size: 23px;
                        font-weight: 500;
                    }
                    .user,.phone {
                    margin-top: 10px;
                }
                }

                .bottom {
                    height: 45px;
                    margin: 0 20px;
                    margin-bottom: 10px;
                }
                }
            }
        }
</style>