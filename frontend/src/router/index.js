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
import MovieReviewView from "../views/MovieReviewView.vue"

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
  {
    path: "/login",
    name: "login",
    component: LoginView,
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
  },
  {
    path: "/theater",
    name: "theater",
    component: TheaterView,
  },
  {
    path: "/myPage",
    name: "myPage",
    component: MyPageView,
  },
  {
    path: "/ticket",
    name: "MovieBookingView",
    component: MovieBookingView,
  },
  {
    path: "/seatSelect",
    name: "SeatSelectView",
    component: SeatSelectView,
  },
  {
    path: "/review",
    name: "MovieReviewView",
    component: MovieReviewView,
  }
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

export default router;
