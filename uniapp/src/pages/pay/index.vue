<template>
    <view class="pay-page">
        <view class="pay">
            <view class="address">
                <u-cell-group>
                    <u-cell :label="userStore.getAddress.length===0?'请选择地址':'当前地址'" size="large" :title="userStore.getAddress.length===0?'地址':userStore.getAddress" :url="'/pages/address/index?orderId='+nowCartId" isLink></u-cell>
                </u-cell-group>
            </view>
            <view class="money">
                <text>
                    总金额:{{ totalAmount }}元
                </text>
            </view>
        </view>
        <view class="bottom">
            <u-button @click="payHandler" custom-style="width:80%;" type="warning" shape="circle" text="支付"></u-button>
        </view>
    </view>
    <u-toast ref="toast"></u-toast>
</template>
<script setup lang="ts">
import { reactive, ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';
import { useUserStore } from '@/store/modules/user';
import {checkoutApi,PayApi} from "@/api/shoppingCart/index"
const toast=ref();
const userStore=useUserStore();
const nowCartId = ref<number>(-1);
const totalAmount = ref(0);
onLoad((data) => {
    if (!data || !data.cartId || data.totalAmount <= 0) {
        uni.switchTab({
            url: '/pages/index/index',
            success: () => {
                uni.$u.toast("错误！");
            }
        })
        return;
    }
    nowCartId.value = data.cartId;
    totalAmount.value = data.totalAmount;
})
async function payHandler(){
    if(userStore.addressData.addressId===-1||userStore.getAddress.length===0){
        uni.$u.toast("请先选择地址");
        return;
    }
    toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
    try {
        let order= await checkoutApi({
        addressId:userStore.addressData.addressId,
        userId:userStore.userId
        })
        console.log(order);
        
        let success=await PayApi({
            orderNumber:order.data[0].orderNumber,
            totalAmount:order.data[0].totalAmount
        })
        console.log(success);
        uni.reLaunch({
            url:"/pages/index/index",
            success:()=>{
                uni.$u.toast("下单支付成功！");
            },
        })
    }catch(err){
        console.log(err);
        
    }finally{
        toast.value.hide();
    }
}
</script>
<style lang="less" scoped>
.pay-page {
    height: 100vh;
    display: flex;
    flex-direction: column;

    .pay {
        flex: 1;

        .money {
            margin-top: 50px;
            display: flex;
            align-items: center;
            justify-content: center;

            text {
                font-size: 25px;
                color:red;

            }
        }
    }

    .bottom {
        display: flex;
        flex-shrink: 0;
        align-items: center;
        justify-content: center;
        bottom: 0;
        left: 0;
        width: 100%;
        height: 80px;
    }
}
</style>