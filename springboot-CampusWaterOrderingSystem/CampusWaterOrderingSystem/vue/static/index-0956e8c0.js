import{af as e,b as a,a as l,r as t,f as r,ag as o,h as n,X as d,k as i,j as u,i as c,o as s,z as m,A as g,m as p,aj as y,ak as v,al as f,E as h,am as _,_ as b}from"./index-2dac3e0d.js";const C={class:"app-container"},k={class:"toolbar-wrapper"},w={class:"table-wrapper"},x=b(a({name:"products",__name:"index",setup(a){const b=l(!1),x=l(!1),I=l(null),N=t({categoryName:""}),V=t({categoryId:[{required:!0,trigger:"blur",message:"请输入种类id"}],imageId:[{required:!0,trigger:"blur",message:"请输入图片ID"}]}),E=()=>{var a;null==(a=I.value)||a.validate((a=>{if(!a)return!1;var l;void 0===z.value?(l=N,e({url:"category/addCategory",method:"POST",params:l})).then((e=>{h.success("新增成功"),x.value=!1,A()})):function(a){return e({url:"category/modCategory",method:"modCategory",params:a})}({categoryId:z.value,...N}).then((e=>{h.success("修改成功"),x.value=!1,A()}))}))},T=()=>{z.value=void 0,N.categoryName=""},j=a=>{_.confirm(`正在删除分类：${a.categoryName}，确认删除？`,"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((()=>{var l;(l={categoryId:a.categoryId},e({url:"category/deleteCategory",method:"DELETE",params:l})).then((e=>{h.success("删除成功"),A()}))}))},z=l(void 0),q=t([]);l(null),t({productName:""});const A=()=>{b.value=!0,e({url:"category/getAllCategories",method:"get"}).then((e=>{q.length=0,q.push(...e.data)})).catch((e=>{q.length=0})).finally((()=>{b.value=!1}))},B=()=>{A()};return A(),(e,a)=>{const l=r("el-button"),t=r("el-tooltip"),h=r("el-table-column"),_=r("el-table"),A=r("el-card"),D=r("el-input"),U=r("el-form-item"),L=r("el-form"),O=r("el-dialog"),P=o("loading");return s(),n("div",C,[d((s(),i(A,{shadow:"never"},{default:u((()=>[m("div",k,[m("div",null,[c(l,{type:"primary",icon:p(y),onClick:a[0]||(a[0]=e=>x.value=!0)},{default:u((()=>[g("新增分类")])),_:1},8,["icon"])]),m("div",null,[c(t,{content:"下载"},{default:u((()=>[c(l,{type:"primary",icon:p(v),circle:""},null,8,["icon"])])),_:1}),c(t,{content:"刷新表格"},{default:u((()=>[c(l,{type:"primary",icon:p(f),circle:"",onClick:B},null,8,["icon"])])),_:1})])]),m("div",w,[c(_,{data:q},{default:u((()=>[c(h,{type:"selection",width:"50",align:"center"}),c(h,{prop:"categoryId",label:"id",align:"center"}),c(h,{prop:"categoryName",label:"分类名",align:"center"}),c(h,{fixed:"right",label:"操作",width:"150",align:"center"},{default:u((e=>[c(l,{type:"primary",text:"",bg:"",size:"small",onClick:a=>{return l=e.row,z.value=l.categoryId,void(x.value=!0);var l}},{default:u((()=>[g("修改")])),_:2},1032,["onClick"]),c(l,{type:"danger",text:"",bg:"",size:"small",onClick:a=>j(e.row)},{default:u((()=>[g("删除")])),_:2},1032,["onClick"])])),_:1})])),_:1},8,["data"])])])),_:1})),[[P,b.value]]),c(O,{modelValue:x.value,"onUpdate:modelValue":a[3]||(a[3]=e=>x.value=e),title:void 0===z.value?"新增分类":"修改分类",onClose:T,width:"30%"},{footer:u((()=>[c(l,{onClick:a[2]||(a[2]=e=>x.value=!1)},{default:u((()=>[g("取消")])),_:1}),c(l,{type:"primary",onClick:E},{default:u((()=>[g("确认")])),_:1})])),default:u((()=>[c(L,{ref_key:"formRef",ref:I,model:N,rules:V,"label-width":"100px","label-position":"left"},{default:u((()=>[c(U,{prop:"categoryName",label:"分类名"},{default:u((()=>[c(D,{modelValue:N.categoryName,"onUpdate:modelValue":a[1]||(a[1]=e=>N.categoryName=e),min:0},null,8,["modelValue"])])),_:1})])),_:1},8,["model","rules"])])),_:1},8,["modelValue","title"])])}}}),[["__scopeId","data-v-5d0c1fad"]]);export{x as default};