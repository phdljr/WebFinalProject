import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import MovieView from "../views/MovieView.vue";
import MovieDetailView from "../views/MovieDetailView.vue";

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/movie",
    name: "movie",
    component: MovieView,
  },
  {
    path: "/movie/:movie",
    name: "movieDetail",
    component: MovieDetailView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
