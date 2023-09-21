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
         @confirm="ordersComplete"
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
                    <view class="name">用户姓名:{{ item.userName}}</view>
                    <view class="phone">用户电话号码:{{ item.userPhone }}</view>
                </view>
                <view class="bottom">
                    <view style="margin-right: 32px;" v-show="!props.isHistory">
                        <u-button  @click="(modal.isShow=true)&&(nowChooseId=item.orderId)" text="完成订单" plain type="primary"></u-button>
                    </view>
                    
                    <u-button text="查看详情" plain type="primary" @click="showDetails(item.items)"></u-button>
                </view>
            </view>
        </view>
    </view>
</template>
<script setup lang="ts">
import { reactive,ref} from 'vue';
import  {deliveryOrdersComplete} from "@/api/delivery-order/index"
import type {DeliveryOrdersRes} from "@/api/delivery-order/types/index"
import type {ListItemData} from "@/api/user-order/types";
const toast=ref();
/**
 * 模态框
 */
const modal=reactive({
    isShow:false,
    content:"是否确认完成订单？"
})
/**
 * 弹出框
 */
const popup=reactive({
    isShow:false
})
const nowChooseId=ref(-1); //当前选中的id
const nowItemList=reactive<Array<ListItemData>>([])
const props=withDefaults(defineProps<{
    orderList:DeliveryOrdersRes,
    isHistory:boolean
}>(),{
    orderList: ()=>[],
    isHistory:false
})
const emit=defineEmits<{
    (e:'refesh'):void
}>()
function ordersComplete(){
    modal.isShow=false;
    toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
    deliveryOrdersComplete({
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
                view {
                    margin-top: 5px;
                }
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