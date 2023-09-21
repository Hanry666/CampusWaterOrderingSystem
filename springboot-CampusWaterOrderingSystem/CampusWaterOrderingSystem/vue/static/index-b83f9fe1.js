import{af as e,b as a,a as l,r as t,w as r,f as n,ag as s,h as u,X as o,k as i,j as d,o as c,i as p,m as g,ah as m,A as h,ai as f,z as _,ak as b,al as v,t as y,am as z,E as w,H as C,I as P,_ as N}from"./index-2dac3e0d.js";import{u as S}from"./usePagination-526c8b6d.js";const k={class:"app-container"},x={class:"toolbar-wrapper"},I=(e=>(C("data-v-d37a8de1"),e=e(),P(),e))((()=>_("div",null,null,-1))),T={class:"table-wrapper"},B={class:"pager-wrapper"},E=N(a({name:"products",__name:"index",setup(a){const C=l(!1),{paginationData:P,handleCurrentChange:N,handleSizeChange:E}=S(),U=a=>{z.confirm(`正在删除用户：${a.userName}，确认删除？`,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{var l;(l={userId:a.userId},e({url:"users/deleteUser",method:"DELETE",params:l})).then((e=>{w.success("删除成功"),V()}))}))},j=t([]),L=l(null),R=t({userName:""}),V=()=>{var a,l;C.value=!0,0!==R.userName.length?(a={pageNum:P.currentPage,pageSize:P.pageSize},l={userName:R.userName},e({url:"users/selectUsersByuserName",method:"POST",data:{...a,param:l}})).then((e=>{j.length=0,P.total=e.data.totalRecords,j.push(...e.data.usersList)})).catch((e=>{j.length=0})).finally((()=>{C.value=!1})):function(a){return e({url:"users/getAllUsers",method:"POST",data:a})}({pageSize:P.pageSize,pageNum:P.currentPage}).then((e=>{j.length=0,P.total=e.data.totalRecords,j.push(...e.data.usersList)})).catch((e=>{j.length=0})).finally((()=>{C.value=!1}))},A=()=>{1===P.currentPage&&V(),P.currentPage=1},D=()=>{var e;R.userName="",null==(e=L.value)||e.resetFields(),1===P.currentPage&&V(),P.currentPage=1},F=()=>{V()};return r([()=>P.currentPage,()=>P.pageSize],V,{immediate:!0}),(e,a)=>{const l=n("el-input"),t=n("el-form-item"),r=n("el-button"),w=n("el-form"),S=n("el-card"),V=n("el-tooltip"),O=n("el-table-column"),H=n("el-table"),X=n("el-pagination"),$=s("loading");return c(),u("div",k,[o((c(),i(S,{shadow:"never",class:"search-wrapper"},{default:d((()=>[p(w,{ref_key:"searchFormRef",ref:L,inline:!0,model:R},{default:d((()=>[p(t,{prop:"productName",label:"用户名"},{default:d((()=>[p(l,{modelValue:R.userName,"onUpdate:modelValue":a[0]||(a[0]=e=>R.userName=e),placeholder:"请输入"},null,8,["modelValue"])])),_:1}),p(t,null,{default:d((()=>[p(r,{type:"primary",icon:g(m),onClick:A},{default:d((()=>[h("查询")])),_:1},8,["icon"]),p(r,{icon:g(f),onClick:D},{default:d((()=>[h("重置")])),_:1},8,["icon"])])),_:1})])),_:1},8,["model"])])),_:1})),[[$,C.value]]),o((c(),i(S,{shadow:"never"},{default:d((()=>[_("div",x,[I,_("div",null,[p(V,{content:"下载"},{default:d((()=>[p(r,{type:"primary",icon:g(b),circle:""},null,8,["icon"])])),_:1}),p(V,{content:"刷新表格"},{default:d((()=>[p(r,{type:"primary",icon:g(v),circle:"",onClick:F},null,8,["icon"])])),_:1})])]),_("div",T,[p(H,{data:j},{default:d((()=>[p(O,{type:"selection",width:"50",align:"center"}),p(O,{prop:"userId",label:"id",align:"center"}),p(O,{prop:"userName",label:"用户名",align:"center"}),p(O,{prop:"userPhone",label:"电话",align:"center"}),p(O,{prop:"gender",label:"性别",align:"center"}),p(O,{prop:"addressId",label:"地址id",align:"center"},{default:d((e=>[_("span",null,y(e.row.addressId||"-"),1)])),_:1}),p(O,{prop:"balance",label:"余额",align:"center"},{default:d((e=>[_("span",null,y(e.row.balance||0),1)])),_:1}),p(O,{prop:"priority",label:"权重",align:"center"}),p(O,{prop:"userPassword",label:"密码",align:"center"},{default:d((e=>[p(r,{type:"primary",text:"",bg:"",size:"small",onClick:a=>{return l=e.row.userPassword,void z.alert(l,"用户密码",{confirmButtonText:"关闭"});var l}},{default:d((()=>[h("查看密码")])),_:2},1032,["onClick"])])),_:1}),p(O,{fixed:"right",label:"操作",width:"150",align:"center"},{default:d((e=>[p(r,{type:"danger",text:"",bg:"",size:"small",onClick:a=>U(e.row)},{default:d((()=>[h("删除")])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])]),_("div",B,[p(X,{background:"",layout:g(P).layout,"page-sizes":g(P).pageSizes,total:g(P).total,"page-size":g(P).pageSize,currentPage:g(P).currentPage,onSizeChange:g(E),onCurrentChange:g(N)},null,8,["layout","page-sizes","total","page-size","currentPage","onSizeChange","onCurrentChange"])])])),_:1})),[[$,C.value]])])}}}),[["__scopeId","data-v-d37a8de1"]]);export{E as default};
