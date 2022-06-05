<template>
  <div class="home">
    <main-movie-chart-vue></main-movie-chart-vue>
  </div>
</template>

<script>
import MainMovieChartVue from "@/components/MainMovieChart.vue";

export default {
  name: "MainView",
  components: {
    MainMovieChartVue,
  },
};
</script>

<style></style>
