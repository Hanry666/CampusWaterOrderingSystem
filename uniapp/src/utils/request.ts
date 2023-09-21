import ajax from "uni-ajax";
import type {AjaxConfigType} from "uni-ajax"
import { useUserStoreHook } from "@/store/modules/user";

const url=import.meta.env.VITE_BASE_API||'';
const request=ajax.create({
    baseURL:url,
    timeout:2000
} as AjaxConfigType);

//请求拦截器
request.interceptors.request.use(config=>{
    //在发送请求之前做些什么
    const userStore=useUserStoreHook();
    config.header['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
    config.header['token']=userStore.token; 
    return config;
},err=>{
    // 对请求错误做些什么
    return Promise.reject(err);
})

//响应拦截器
request.interceptors.response.use((res)=>{
    // 对响应数据做点什么
    if(res.data.code!==200){
        if(res.data.code==='204'){
            useUserStoreHook().tokenTimeOut();
            return Promise.reject('token过期');
        }
        uni.$u.toast(res.data.msg);
        return Promise.reject(res.data.msg);
    }else{
        return res.data;
    }

},err=>{
    uni.$u.toast('服务器错误');
    return Promise.reject('服务器错误');
    
})

export default request;