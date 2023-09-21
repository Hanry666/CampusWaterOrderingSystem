<template>
	<view class="reg">
		<view class="title">
			注册
		</view>
		<!-- 注意，如果需要兼容微信小程序，最好通过setRules方法设置rules规则 -->
		<view class="form-box">
			<u-form labelPosition="left" errorType="toast" :model="userInfo" :rules="rules" ref="form">
				<view class="item">
					<u-form-item label="姓名" prop="name" ref="name">
						<u-input placeholder="请输入姓名" v-model="userInfo.name" border="none"></u-input>
					</u-form-item>
				</view>
				<view class="item">
					<u-form-item label="性别" prop="sex" @click="showSex = true;" ref="sex">
						<u-input v-model="userInfo.sex" disabled disabledColor="#ffffff" placeholder="请选择性别"
							border="none"></u-input>
						<template #right>
							<u-icon name="arrow-right"></u-icon>
						</template>
					</u-form-item>
				</view>
				<view class="item">
					<u-form-item labelWidth="50" label="手机号" prop="phone" ref="phone">
						<u-input placeholder="请输入手机号" v-model="userInfo.phone" border="none"></u-input>
					</u-form-item>
				</view>
				<!-- <view class="item">
					<u-form-item label="地址" prop="address" ref="address">
						<u-input placeholder="请输入地址" v-model="userInfo.address" border="none"></u-input>
					</u-form-item>
				</view> -->
				<view class="item">
					<u-form-item label="角色" prop="role" @click="showRole = true;" ref="role">
						<u-input v-model="userInfo.role" disabled disabledColor="#ffffff" placeholder="请选择角色"
							border="none"></u-input>
						<template #right>
							<u-icon name="arrow-right"></u-icon>
						</template>
					</u-form-item>
				</view>
				<view class="item">
					<u-form-item label="密码" prop="password" ref="password">
						<u-input type="password" clearable placeholder="请输入密码" v-model="userInfo.password"
							border="none"></u-input>
					</u-form-item>
				</view>
				<view class="item">
					<u-form-item prop="pwd" ref="pwd">
						<u-input type="password" clearable placeholder="请重复输入密码" prefixIcon="lock"
							prefixIconStyle="font-size: 20px;" v-model="userInfo.pwd" border="none"></u-input>
					</u-form-item>
				</view>
			</u-form>
		</view>
		<view class="btn-box">
			<u-button type="primary" text="提交" @click="submit"></u-button>
		</view>
		<u-action-sheet :show="showSex" :actions="actions.sex" title="请选择性别" @close="showSex = false" @select="sexSelect">
		</u-action-sheet>
		<u-action-sheet :show="showRole" :actions="actions.role" title="请选择角色" @close="showRole = false"
			@select="roleSelect">
		</u-action-sheet>
		<u-toast ref="toast"></u-toast>
	</view>
</template>

<script setup lang="ts">
import { reactive, ref } from "vue";
import { getRegApi, deliveryRegApi } from "@/api/login/index"
let showSex = ref(false);
let showRole = ref(false);
const form = ref();
const toast = ref();
const userInfo = reactive({
	name: '',
	sex: '',
	phone: '',
	// address: '',
	password: "",
	pwd: "",
	role: "",
})
const actions = reactive({
	sex: [{
		name: '男',
	},
	{
		name: '女',
	}],
	role: [{
		name: "个人用户"
	}, {
		name: "配送员"
	}]
})
const rules = reactive({
	name: {
		type: 'string',
		required: true,
		message: '请填写姓名',
		trigger: ['blur', 'change']
	},
	sex: {
		type: 'string',
		max: 1,
		required: true,
		message: '请选择性别',
		trigger: ['blur', 'change']
	},
	phone: [{
		required: true,
		message: '请输入手机号',
		trigger: ['change', 'blur'],
	}, {
		// 自定义验证函数
		validator: (rule: any, value: any, callback: any) => {
			// 上面有说，返回true表示校验通过，返回false表示不通过
			// uni.$u.test.mobile()就是返回true或者false的
			return uni.$u.test.mobile(value);
		},
		message: '手机号码不正确',
		// 触发器可以同时用blur和change
		trigger: ['change', 'blur'],
	}],
	// address: {
	// 	type: 'string',
	// 	required: true,
	// 	message: '请填写地址',
	// 	trigger: ['blur', 'change']
	// },
	role: {
		type: 'string',
		required: true,
		message: '请选择角色',
		trigger: ['blur', 'change']
	},
	password: [{
		type: 'string',
		required: true,
		message: '请填写密码',
		trigger: ['blur', 'change']
	},
	{
		type: 'string',
		required: true,
		min: 6,
		message: '密码至少6位',
		trigger: ['blur', 'change']
	},
	],
	pwd: [{
		type: 'string',
		required: true,
		message: '请重复输入密码',
		trigger: ['blur', 'change']
	}, {
		validator: (rule: any, value: any, callback: any) => {
			if (value === userInfo.password) {
				return true;
			} else {
				return false;
			}

		},
		message: '两次输入密码不同',
		// 触发器可以同时用blur和change
		trigger: ['change', 'blur'],
	}]
})
function sexSelect(e: any) {
	userInfo.sex = e.name
	form.value.validateField('userInfo.sex')
}
function roleSelect(e: any) {
	userInfo.role = e.name;
	form.value.validateField('userInfo.role')
}
function submit() {
	form.value.validate().then((res: any) => {
		reg();

	}).catch((error: any) => {
		uni.$u.toast(error[0].message)
	})
}
async function reg() {
	toast.value.show({
		type: 'loading',
		title: '正在加载',
		message: "正在加载",
		iconUrl: 'https://cdn.uviewui.com/uview/demo/toast/loading.png',
		duration: 5000
	})
	if (userInfo.role === '个人用户') {
		getRegApi({
			userName: userInfo.name,
			userPhone: userInfo.phone,
			userPassword: userInfo.password,
			gender: userInfo.sex,
			role: userInfo.role,	
		}).then(res => {
			uni.redirectTo({
				url: '/pages/login/index',
				success: (success) => {
					uni.$u.toast('注册成功');
				},
			})
		}).catch((err: any) => {
			uni.$u.toast(err);
		}).finally(() => {
			toast.value.hide();
		})

	}else {
		deliveryRegApi({
			deliveryName:userInfo.name,
			deliveryPassword:userInfo.password,
			deliveryPhone:userInfo.phone,
			gender:userInfo.sex,
			role:userInfo.role
		}).then(res => {
			uni.redirectTo({
				url: '/pages/login/index',
				success: (success) => {
					uni.$u.toast('注册成功');
				},
			})
		}).catch((err: any) => {
			uni.$u.toast(err);
		}).finally(() => {
			toast.value.hide();
		})
	}




}
</script>


<style lang="less" scoped>
.reg {
	display: flex;
	flex-direction: column;
	align-items: center;
	justify-content: center;

	.title {
		margin-top: 50rpx;
		font-size: 30px;
	}

	.form-box {
		margin-top: 50rpx;
		padding: 0 10px;
		width: 90%;

		.item {
			border-radius: 5px;
			box-shadow: 0px 0px 2px rgba(55, 40, 254, 0.3);
			padding: 0 8px;
			margin-top: 15px;
		}
	}

	.btn-box {
		margin-top: 60rpx;
		width: 60%;
		display: flex;
	}
}
</style>