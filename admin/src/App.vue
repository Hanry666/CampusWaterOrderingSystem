<script lang="ts" setup>
import { useTheme } from "@/hooks/useTheme";
import zhCn from "element-plus/es/locale/lang/zh-cn";
import { useRouter } from "vue-router";
const router = useRouter();
console.log(router);

const { initTheme } = useTheme();

/** 初始化主题 */
initTheme();
/** 将 Element Plus 的语言设置为中文 */
const locale = zhCn;
</script>

<template>
  <ElConfigProvider :locale="locale">
    <router-view />
  </ElConfigProvider>
</template>
