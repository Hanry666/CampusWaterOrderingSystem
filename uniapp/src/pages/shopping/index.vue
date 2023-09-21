<template>
    <u-toast ref="toast"></u-toast>
    <view>
        <view class="shopping-page">
            <view class="shopping-box">
                <view class="empty" v-show="shoppingCartList.length === 0">
                    <u-empty mode="data" icon="http://cdn.uviewui.com/uview/empty/car.png">
                    </u-empty>
                </view>
                <u-cell-group v-if="shoppingCartList.length !== 0">
                    <u-cell v-for="(item, index) in shoppingCartList" :key="index" :title="item.productName"
                        :value="item.cartPrice + '元'" :label="'数量:' + item.productQuantity"></u-cell>
                </u-cell-group>
            </view>
            <view class="bottom">
                <bottomVue :isShoppingCar="true" :totalAmount="totalAmount" @checkout="checkOutHandler"></bottomVue>
            </view>
        </view>
    </view>
</template>
<script setup lang="ts">
import bottomVue from "@/component/bottomBar/index.vue"
import { reactive, ref } from 'vue';
import { getShoppingCartApi } from '@/api/shoppingCart'
import { onReady } from "@dcloudio/uni-app";
import { useUserStore } from "@/store/modules/user";
import type { AddProductRes } from "@/api/shoppingCart/types"
const userStore = useUserStore();
const toast = ref();
const isReady = ref(false);
const totalAmount = ref(0);
const shoppingCartList = reactive<AddProductRes>([]);
function getShoppingCartData() {
    isReady.value && toast.value.show({
        type: 'loading',
        title: '正在加载',
        message: "正在加载",
        iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
        duration: 5000
    })
    getShoppingCartApi({
        userId: userStore.userId
    }).then(res => {
        shoppingCartList.length = 0;
        shoppingCartList.push(...res.data.cartProduct);
        totalAmount.value = res.data.totalAmount;
        console.log(shoppingCartList);

    }).finally(() => {
        isReady.value && toast.value.hide();
    })
}
getShoppingCartData()
onReady(() => {
    isReady.value = true;
})

function checkOutHandler() {
    if(shoppingCartList.length===0){
        uni.$u.toast("请至少添加一件商品");
        return ;
    }
    uni.navigateTo({
        url:`/pages/pay/index?cartId=${shoppingCartList[0].cartId}&totalAmount=${totalAmount.value}`
    })
}
</script>
<style lang="less" scoped>
.shopping-page {
    overflow: hidden;
    height: 100vh;
    display: flex;
    flex-direction: column;

    .shopping-box {
        flex: 1;
        overflow: scroll;
    }

    .bottom {
        flex-shrink: 0;
        width: 100%;
        align-self: flex-end;
        justify-self: flex-end;
    }
    
}
</style>