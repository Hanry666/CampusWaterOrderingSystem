import { defineStore } from "pinia";
import store from "../index";
import Names from "../emum";
import {verifyTokenApi} from "@/api/login/index";
import type {LoginResponseData,DeliveryLoginRes} from "@/api/login/types/index";
import type {GetAllUserAddressData} from "@/api/address/types"
type UserMessage=LoginResponseData;

interface UserState extends UserMessage {
    isLogin:boolean
    role:boolean
    addressData:GetAllUserAddressData
}
export const useUserStore=defineStore(Names.User,{
    state():UserState{
        return {
            isLogin:false,
            role:true,
            token:'',
            userId:-1,
            userName:'',
            addressData:{
                addressId:-1,
                building:'',
                campus:'',
                houseNumber:''
            }
        }
    },
    actions:{
        /**
         * 设置token
         */
        setToken(token:string){
            if(token.length===0){
                return false;
            }
            try {
                uni.setStorageSync('token',token);
            } catch (e) {
                return false;
            }
            return true;
        },
        /**
         * 获取token
         */
        getToken(){
            const token=uni.getStorageSync("token")||'';
            this.token=token;
        },
        /**
         * 清除token
         */
        clearToken(){
            uni.removeStorageSync("token");
        },
        /**
         * 登录方法
         * @param data 
         */
        login(data:LoginResponseData|DeliveryLoginRes,isUser:boolean){
            if(!this.setToken(data.token)){
                uni.$u.toast("token设置错误");
                return false;
            }
            if(!isUser){
                this.userId=(data as DeliveryLoginRes).deliveryId;
                this.userName=(data as DeliveryLoginRes).deliveryName;
            }else{
                this.userId=(data as LoginResponseData).userId; 
                this.userName=(data as LoginResponseData).userName;    
            }
            this.isLogin=true;
            this.role=isUser;
            this.token=data.token;         
            return true;
        },
        /**
         * 退出登录方法
         */
        logOut(isTimeOut:boolean=false){
            this.clearToken();
            this.$reset();
            uni.switchTab({
                url:"/pages/index/index",
                animationType: 'pop-in',
                animationDuration: 200,
                success:()=>{
                    if(isTimeOut){
                        uni.$u.toast("token验证失败,请重新登录!"); 
                    }else{
                        uni.$u.toast("退出登录成功");    
                    }
                             
                }
            })
        },
        /**
         * 判断是否登录
         */
        judgeLogin(){
            if(!this.isLogin){
                uni.$u.toast("请先登录");
                return false;
            }else {
                return true;
            }
        },
        /**
         * 检查token是否有效
         */
        verifyToken(){
            this.getToken();
            if(!this.token){
                console.log('取消验证token');
                return;
            }
            verifyTokenApi({
                token:this.token
            }).then(res=>{
                this.userId=res.data.id;
                this.userName=res.data.name;
                if(res.data.role==='个人用户'){
                    this.role=true;
                }else{
                    this.role=false;
                }
                this.isLogin=true;
            }).catch(err=>{
                uni.$u.toast("token失效");
                this.clearToken();
                this.$reset();
            })
        },
        /**
         * token过期
         */
        tokenTimeOut(){
            this.logOut(true);
        },
        /**
         * 设置地址
         */
        setAddress(data:GetAllUserAddressData){
            this.addressData=data;
        }
    },
    getters:{
        getAddress(state){
            const {addressId,building,campus,houseNumber} =state.addressData;
            if(addressId===-1){
                return  '';
            }
            return campus+building+houseNumber;
        }
    }
})

/** 在 setup 外使用 */
export function useUserStoreHook() {
    return useUserStore(store)
  }