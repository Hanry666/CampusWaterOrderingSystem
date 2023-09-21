<template>
    <view class="item-list">
        <view class="empty"  v-show="props.itemList.length === 0">
            <u-empty mode="data" icon="http://cdn.uviewui.com/uview/empty/car.png">
        </u-empty>
        </view>

        <view class="item-box" v-for="(item,index) in props.itemList" :key="index">
            <view class="item">
                <view class="img-box">
                    <!-- <image  :src="item.imageUrl"></image> -->
                </view>
                <view class="right">
                    <view class="title">
                        <u-text :lines="1" :text="item.productName"></u-text>
                    </view>
                    <view class="bottom">
                        <view class="money">
                            <u-text mode="price" :text="item.productPrice"></u-text>
                        </view>
                        <view class="add" @click="addItem(item.productId)">
                            <u-icon name="plus-circle-fill" color="#2979ff" size="18"></u-icon>
                        </view>
                    </view>
                </view>
            </view>
        </view>
        <u-toast ref="toast"></u-toast>
    </view>
</template>
<script setup lang="ts">
import { reactive,ref } from 'vue';
import type {ProductCategoriesItemResponseData} from "@/api/user-item/types/index"
import {addProductApi} from "@/api/shoppingCart/index"
import { useUserStore } from '@/store/modules/user';
const userStore=useUserStore();
const props=withDefaults(defineProps<{
  itemList?: ProductCategoriesItemResponseData
}>(),{
    itemList:()=>[]
});
console.log(props.itemList);

const toast=ref();
function addItem(id:number){
    if(!userStore.isLogin){
        uni.$u.toast("请先登录！");
        return ;
    }
    toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
    addProductApi({
        productId:id,
        productQuantity:1,
        userId:userStore.userId
    }).then(res=>{
        uni.$u.toast("加入购物车成功");
        console.log(res);
    }).catch(err=>{
        console.log(err);
        
    }).finally(()=>{
        toast.value.hide();
    })
}

</script>
<style lang="less" scoped>
.item-list {
    margin: 0 15px;
    display: flex;
    flex-direction: column;
    align-items: center;

    padding-top: 10px;
    padding-bottom: 50px;
    overflow: hidden;
    .item-box {
        // height: 3000px;
        width: 95%;
        margin: 0 10px;

        .item {
            display: flex;
            align-items: center;
            margin-bottom: 15px;
            width: 100%;
            height: 80px;
            border-radius: 10px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
            overflow: hidden;

            .img-box {
                width: 30%;
                background-color: antiquewhite;
                height: 100%;
                flex-shrink: 0;
            }

            .right {
                display: flex;
                flex-direction: column;
                height: 100%;
                flex-grow: 1;
                .title {
                    display: flex;
                    align-items: center;
                    margin:0 8px;
                    width: 85%;
                    height: 60%;
                    overflow: hidden;

                }

                .bottom {
                    display: flex;
                    justify-content: space-between;
                    align-items: center;
                    .money {
                        padding-left: 8px;
                    }
                    .add {
                        height: 80%;
                        margin-right: 10px;
                        overflow: hidden;
                        ::v-deep > view {
                            height: 100%;
                            width: 100%;
                        }
                    }
                }
            }
        }
    }
}
</style>