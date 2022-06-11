import { createRouter, createWebHistory } from "vue-router";
import MainView from "../views/MainView.vue";
import MovieView from "../views/MovieView.vue";
import MovieDetailView from "../views/MovieDetailView.vue";
import LoginView from "../views/LoginView.vue";
import RegisterView from "../views/RegisterView.vue";
import TheaterView from "../views/TheaterView.vue";
import MyPageView from "../views/MyPageView.vue";
import MovieBookingView from "../views/MovieBookingView.vue";
import SeatSelectView from "../views/SeatSelectView.vue";
import AdminPageView from "../views/AdminPageView.vue";

const routes = [
  {
    path: "/",
    name: "main",
    component: MainView,
  },
  {
    path: "/movieview",
    name: "MovieView",
    component: MovieView,
  },
  {
    path: "/moviedetailview/:movie",
    name: "MovieDetailView",
    component: MovieDetailView,
  },
  {
    path: "/loginview",
    name: "LoginView",
    component: LoginView,
  },
  {
    path: "/registerview",
    name: "RegisterView",
    component: RegisterView,
  },
  {
    path: "/theaterview",
    name: "TheaterView",
    component: TheaterView,
  },
  {
    path: "/mypageview",
    name: "MyPageView",
    component: MyPageView,
  },
  {
    path: "/ticketview",
    name: "MovieBookingView",
    component: MovieBookingView,
  },
  {
    path: "/seatselectview",
    name: "SeatSelectView",
    component: SeatSelectView,
  },
  {
    path: "/adminview",
    name: "AdminPageView",
    component: AdminPageView,
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
