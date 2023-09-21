<script setup lang="ts">
import { onLaunch, onShow, onHide } from "@dcloudio/uni-app";
import { useUserStore } from "./store/modules/user";
const userStore=useUserStore()
onLaunch(() => {
  userStore.verifyToken();
});
onShow(() => {
  console.log("App Show");
});
onHide(() => {
  console.log("App Hide");
});
console.log(import.meta.env.VITE_BASE_API);

</script>
<style>

</style>
