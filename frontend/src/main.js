import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue3 from "bootstrap-vue-3";
import BootstrapIcon from "@dvuckovic/vue3-bootstrap-icons";
import vue3StarRatings from "vue3-star-ratings";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

const app = createApp(App);
app.component("b-icon", BootstrapIcon);
app.component("vue3-star-ratings", vue3StarRatings);
app.use(store);
app.use(router);
app.use(BootstrapVue3);
app.mount("#app");

// app.config.globalProperties.HOST = "http://117.20.209.64:8080"; // 종렬 집 테스트
// app.config.globalProperties.HOST = "http://202.31.200.219:8080"; // 종렬 랩실 테스트
app.config.globalProperties.HOST = "http://202.31.200.215:8080"; // 민수 테스트
