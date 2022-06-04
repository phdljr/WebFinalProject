import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import BootstrapVue3 from "bootstrap-vue-3";
import BootstrapIcon from "@dvuckovic/vue3-bootstrap-icons";

import "bootstrap/dist/css/bootstrap.css";
import "bootstrap-vue-3/dist/bootstrap-vue-3.css";

createApp(App).component("b-icon", BootstrapIcon).use(store).use(router).use(BootstrapVue3).mount("#app");