import { createSSRApp } from "vue";
import App from "./App.vue";
import uviewPlus from 'uview-plus';
import "uview-plus/index.scss";
import store from "./store";
import "@/static/mian.css";

export function createApp() {
  const app = createSSRApp(App);
  app.use(uviewPlus);
  app.use(store);
  return {
    app,
  };
}