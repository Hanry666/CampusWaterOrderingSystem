<template>
    <view class="user-address">

        <view class="address">
            <view class="empty" v-show="addressList.length === 0">
                <u-empty mode="data" icon="http://cdn.uviewui.com/uview/empty/car.png">
                </u-empty>
            </view>
            <view class="address-box">
                <view class="address-item" v-for="(item, index) in addressList" :key="index">
                    <view class="left">
                        <view class="title">{{ item.campus }}{{ item.building }}{{ item.houseNumber }}</view>
                    </view>
                    <view class="right">
                        <u-icon v-show="nowCartId!==-1" name="checkmark-circle" @click="chooseAddressHandler(item)" custom-style="margin-right:10px;"
                            size="25"></u-icon>
                        <u-icon @click="modAddressHandler(item.addressId)" custom-style="margin-right:10px;" name="edit-pen-fill"
                            size="25"></u-icon>
                        <u-icon @click="delAddressHandler(item.addressId)" name="close-circle-fill" size="25"></u-icon>
                    </view>
                </view>
            </view>

        </view>
        <view class="bottom">
            <u-button @click="addAddressHandler" custom-style="width:80%;" type="primary" shape="circle" text="添加地址"></u-button>
        </view>
    </view>
    <u-popup :show="popup.isShow" mode="center" closeable :round="10" @close="clearForm">
        <view class="popup">
            <view class="title">
                <text>修改地址</text>
            </view>
            <view class="form-box">
                <u-form labelPosition="left" errorType="toast" :model="addressInfo" :rules="rules" ref="form">
                    <u-form-item prop="campus" ref="campus">
                        <u-input clearable placeholder="请输入学校" v-model="addressInfo.campus"></u-input>
                    </u-form-item>
                    <u-form-item prop="building" ref="building">
                        <u-input clearable placeholder="请输入楼栋号" v-model="addressInfo.building"></u-input>
                    </u-form-item>
                    <u-form-item prop="houseNumber" ref="houseNumber">
                        <u-input clearable placeholder="请输入门牌号" v-model="addressInfo.houseNumber"></u-input>
                    </u-form-item>
                </u-form>
            </view>
            <view class="btn-box">
                <u-button @click="submitForm" type="primary" custom-style="margin-top:20px; width:80%;"
                    :hairline="true" text="提交"></u-button>
            </view>
        </view>
        <u-toast ref="toast"></u-toast>
    </u-popup>
    <u-modal v-model="modal.isShow" :show="modal.isShow" showCancelButton closeOnClickOverlay @confirm="confirmModalHandler"
        @cancel="modal.isShow = false" @close="modal.isShow = false"
        :content="modal.isDel ? '是否确认删除地址' : '是否确认设为使用地址'"></u-modal>
</template>
<script setup lang="ts">
import { onLoad, onReady } from "@dcloudio/uni-app";
import { ref, reactive } from 'vue';
import { getAllUserAddressApi, addUserAddressApi, addOrderAddressIdApi, deleteAddressApi, modAddressApi } from "@/api/address/index"
import type { GetAllUserAddressRes,GetAllUserAddressData } from "@/api/address/types"
import { useUserStore } from "@/store/modules/user"
const userStore = useUserStore();
const toast = ref();
const form = ref();
const addressList = reactive<GetAllUserAddressRes>([])  //地址信息列表
const nowAddressId = ref(-1); //当前选中id
const nowAddress=reactive<GetAllUserAddressData>({
    addressId:-1,
    building:'',
    campus:'',
    houseNumber:''
});
const nowCartId=ref(-1);
onLoad((data)=>{
    if(data&&data.cartId){

    }
})
//#region 查全部地址
const isReady = ref(false);//dom是否加载完成
function getAddress() {
    if (!userStore.isLogin) {
        uni.$u.toast("请先登录！");
        uni.switchTab({
            url: '/pages/index/index',
            success: (success) => {
                uni.$u.toast("请先登录！");
            },
        })
        return;
    }

    isReady.value && toast.value.show({
        type: 'loading',
        title: '正在加载',
        message: "正在加载",
        iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
        duration: 5000
    })
    getAllUserAddressApi({
        userId: userStore.userId
    }).then(res => {
        addressList.length = 0;
        addressList.push(...res.data);
    }).catch(() => {
        addressList.length = 0;
    }).finally(() => {
        isReady.value && toast.value.hide();
    })
}

onLoad(() => {
    getAddress();
})
onReady(() => {
    isReady.value = true;
})
//#endregion


//#region 模态框部分 删除地址 


const modal = reactive({
    isShow: false,
    isDel: false
})
function chooseAddress() {
    if(nowAddress.addressId===-1) {
        uni.$u.toast("设置失败");
        return false;
    }
    userStore.setAddress(nowAddress)
    uni.navigateBack({
        success:()=>{
            uni.$u.toast("设置成功");
        }
    })
}
function delAddress() {
    toast.value.show({
        type: 'loading',
        title: '正在加载',
        message: "正在加载",
        iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
        duration: 5000
    })
    deleteAddressApi({
        addressId:nowAddressId.value
    }).then(res=>{
        uni.$u.toast("修改成功！");
        modal.isShow=false;
        getAddress();
    }).catch(err=>{

    }).finally(()=>{
        toast.value.hide();
    })
}
/**
 * 确认模态框
 */
function confirmModalHandler() {
    if (modal.isDel) {
        delAddress()
        } else {
        chooseAddress()
    }
}
function chooseAddressHandler(address:GetAllUserAddressData) {
    nowAddressId.value=address.addressId;
    nowAddress.addressId=address.addressId;
    nowAddress.building=address.building;
    nowAddress.campus=address.campus;
    nowAddress.houseNumber=address.houseNumber;
    modal.isDel = false;
    modal.isShow = true;
}
function delAddressHandler(addressId:number) {
    nowAddressId.value=addressId;
    modal.isDel = true;
    modal.isShow = true;
}
//#endregion

//#region 提示框部分 添加地址 修改地址
interface AddressInfo {
    campus:string;
    building:string;
    houseNumber:string;
}
/**
 * 地址信息
 */
 const addressInfo = reactive<AddressInfo>({
    campus: '',
    building: '',
    houseNumber: ''
})
/**
 * 验证规则
 */
const rules = reactive({
    campus: {
        type: 'string',
        required: true,
        message: '请输入学校',
        trigger: ['blur', 'change']
    },
    building: {
        type: 'string',
        required: true,
        message: '请输入楼栋号',
        trigger: ['blur', 'change']
    },
    houseNumber: {
        type: 'string',
        required: true,
        message: '请输入门牌号',
        trigger: ['blur', 'change']
    }
})


const popup = reactive({
    isShow: false,
    isMod: false
})
function addAddress(){
    toast.value.show({
        type: 'loading',
        title: '正在加载',
        message: "正在加载",
        iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
        duration: 5000
    })
    addUserAddressApi({
        ...addressInfo,
        userId:userStore.userId
    }).then(res=>{
        uni.$u.toast("添加成功！");
        clearForm();
        getAddress();
    }).catch(err=>{

    }).finally(()=>{
        toast.value.hide();
    })
}
function modAddress(){
    toast.value.show({
        type: 'loading',
        title: '正在加载',
        message: "正在加载",
        iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
        duration: 5000
    })
    modAddressApi({
        ...addressInfo,
        addressId:nowAddressId.value
    }).then(res=>{
        uni.$u.toast("修改成功！");
        clearForm();
        getAddress();
    }).catch(err=>{

    }).finally(()=>{
        toast.value.hide();
    })
    
}
function submitForm(){
    form.value.validate().then((res: any) => {
		if(popup.isMod){
            modAddress();
        }else {
            addAddress();
        }
	}).catch((error: any) => {
		uni.$u.toast(error[0].message)
	})
}
function addAddressHandler() {
    popup.isMod = false;
    popup.isShow = true;

}

function modAddressHandler(addressId:number) {
    nowAddressId.value=addressId;
    popup.isMod = true;
    popup.isShow = true;

}
function clearForm(){
    popup.isShow = false;
    type Key=keyof AddressInfo;
    for(let key in addressInfo){
       let typedkey= key as Key;
       addressInfo[typedkey]='';
    }
}
//#endregion

</script>



<style lang="less" scoped>
.popup {
    width: 80vw;
    margin: 50px 15px;

    .title {
        font-size: 22px;
        margin-bottom: 10px;
        transform: translateY(-20px);
        display: flex;
        align-items: center;
        justify-content: center;
    }
}

.user-address {
    display: flex;
    flex-direction: column;
    height: 100vh;

    .address {
        flex-grow: 1;
        overflow: hidden;

        .address-box {
            height: 100%;
            overflow: scroll;

            .address-item {
                margin: 0 10px;
                padding: 20px 20px;
                border-bottom: .5px solid #666;
                display: flex;

                .left {
                    display: flex;
                    align-items: center;
                    flex-grow: 1;
                    font-weight: 600;
                    font-size: 20px;
                }

                .right {
                    margin-left: 10px;
                    flex-shrink: 0;
                    display: flex;
                    align-items: center;
                    justify-content: center;
                }
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