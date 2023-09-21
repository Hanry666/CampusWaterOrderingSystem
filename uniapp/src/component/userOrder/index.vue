<template>
    <view class="order">
        <view class="empty"  v-show="props.orderList.length === 0">
            <u-empty mode="data" icon="http://cdn.uviewui.com/uview/empty/car.png">
        </u-empty>
        </view>
        <u-toast ref="toast"></u-toast>
        <u-popup closeable :show="popup.isShow" mode="top"  @close="popup.isShow=false" >
        <view style="margin:0 10px;margin-bottom: 32px;">
            <view class="item" v-for="(item,index) in nowItemList" :key="index" style="margin-top: 20px;">
                <view>商品id:{{ item.productId }}</view>
                <view>商品名称:{{ item.productName }}</view>
                <view>商品数量:{{ item.productQuantity }}</view>
                <view>商品价格:{{ item.totalAmount }}</view>
            </view>
        </view>
	</u-popup>
    <u-modal v-model="modal.isShow"
         :show="modal.isShow" 
         showCancelButton  
         closeOnClickOverlay
         @confirm="modalConfirmHandler"
		@cancel="modal.isShow=false"
		@close="modal.isShow=false"
         :content='modal.content'></u-modal>
        <view class="order-box">
            <view class="item" v-for="(item, index) in props.orderList" :key="index">
                <view class="message-box">
                    <view class="id">订单ID:{{item.orderId }}</view>
                    <view class="date">日期:{{ item.orderDate }}</view>
                    <view class="total">本单价值:{{ item.totalAmount }}</view>
                    <view class="status">订单状态:{{ item.orderStatus }}</view>
                    <view class="number">订单号:{{ item.orderNumber }}</view>
                    <view class="name">配送员用户名:{{ item.deliveryName }}</view>
                    <view class="phone">配送员电话号码:{{ item.deliveryPhone }}</view>
                </view>
                <view class="bottom">
                    <u-button v-if="!isHistory&&!(item.orderStatus==='已支付')" @click="(modal.isShow=true)&&(nowChooseId=item.orderId)" :customStyle="{marginRight: '32px'}" text="确认收货" plain type="primary"></u-button>
                    <u-button @click="showDetails(item.items)" text="查看详情" plain type="primary"></u-button>
                </view>
            </view>
        </view>
    </view>
</template>
<script setup lang="ts">
import {reactive,ref } from 'vue';
import {getConfirmApi} from "@/api/user-order"
import type {ActiveRes} from "@/api/user-order/types/index"
import type {ListItemData} from "@/api/user-order/types";
const nowItemList=reactive<Array<ListItemData>>([])
const toast=ref();
const props=withDefaults(defineProps<{
    orderList:ActiveRes
    isHistory:boolean
}>(),{
    orderList: ()=>[],
    isHistory:false
})
const emit=defineEmits<{
    (e:'refesh'):void
}>()
/**
 * 模态框
 */
 const modal=reactive({
    isShow:false,
    content:"是否确认收货？"
})
/**
 * 弹出框
 */
const popup=reactive({
    isShow:false
})
const nowChooseId=ref(-1); //当前选中的id


function modalConfirmHandler(){
    modal.isShow=false;
    toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
    getConfirmApi({
        orderId:nowChooseId.value
    }).then(res=>{
        uni.$u.toast("操作成功！");
        emit("refesh");
    }).finally(()=>{
        toast.value.hide();
    })
}
function showDetails(item:Array<ListItemData>){
    nowItemList.length=0;
    nowItemList.push(...item);
    popup.isShow=true;
}
</script>
<style lang="less" scoped>
.order {
    width: 100%;
    padding-bottom: 50px;

    .order-box {
        margin: 0 15px;
        margin-top: 10px;

        .item {
            margin-bottom: 20px;
            border-radius: 10px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.3);
            overflow: hidden;

            // .top {
            //     height: 30px;
            //     display: flex;
            //     align-items: center;
            //     justify-content: flex-end;

            //     .price {
            //         margin-right: 10px;
            //         color: red;
            //     }
            // }

            // .address {
            //     margin: 10px 20px;
            //     margin-top: 5px;
            //     font-size: 32rpx;
            //     font-weight: 500;
            // }
            .message-box {
                margin: 10px 20px;
            }
            .bottom {
                display: flex;
                height: 45px;
                margin: 0 20px;
                margin-bottom: 10px;
            }
        }
    }
}</style>