<template>
  <div class="MovieChartContent">
    <div>
      <b-row>
        <b-col>
          <h3 style="text-align: left">무비차트</h3>
        </b-col>
        <b-col>
          <router-link to="/movie?sort=book" style="float: right">전체보기</router-link>
        </b-col>
      </b-row>
      <b-list-group horizontal id="movieChart">
        <b-list-group-item
          class="d-flex justify-content-between align-items-center"
        >
          <div class="swipeButton">
            <button v-if="page != 0">
              <b-icon
                icon="arrow-left-circle"
                size="3x"
                @click="page = page - 1"
              />
            </button>
          </div>
        </b-list-group-item>
        <b-list-group-item :key="key" v-for="(movie, key) in listMovieChart">
          <b-overlay :show="showOverlayList[key]" rounded="sm">
            <b-card
              :title="movie.title"
              :img-src="'../movies/' + movie.title + '.jpg'"
              img-alt="Image"
              img-top
              tag="article"
              style="width: 200px;height: 450px;"
              class="mb-2"
              :aria-hidden="showOverlayList[key] ? 'true' : null"
              @mouseover="showOverlayList[key] = true"
            >
              <p>{{movie.rate >= 0 ? "예매율 " + Math.ceil(movie.rate * 100) + "%" : "상영 예정"}}</p>
            </b-card>
            <template #overlay>
              <div class="text-center" @mouseout="showOverlayList[key] = false">
                <b-button
                  variant="outline-danger"
                  size="sm"
                  aria-describedby="cancel-label"
                  @click="$router.push('/movie/' + movie.title)"
                >
                  상세보기
                </b-button>
                <b-button
                  variant="outline-danger"
                  size="sm"
                  aria-describedby="cancel-label"
                  @click="$router.push('/ticket?movie=' + movie.title)"
                >
                  예매하기
                </b-button>
              </div>
            </template>
          </b-overlay>
        </b-list-group-item>
        <b-list-group-item
          class="d-flex justify-content-between align-items-center"
        >
          <div class="swipeButton">
            <button v-if="page != maxPage">
              <b-icon
                icon="arrow-right-circle"
                size="3x"
                @click="page = page + 1"
              />
            </button>
          </div>
        </b-list-group-item>
      </b-list-group>
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  name: "MainView",
  components: {},
  data() {
    return {
      page: 0,
      show: false,
      movieChart: [
        // {
        //   title: "",
        //   rate: "",
        //   grade: "",
        // },
      ],
      showOverlayList: [false, false, false, false, false],
    };
  },
  methods: {
  },
  computed: {
    listMovieChart() {
      return this.movieChart.slice(this.page * 5, this.page * 5 + 5);
    },
    maxPage() {
      return Math.ceil(this.movieChart.length / 5) - 1;
    },
  },
  mounted() {
    axios.get(this.HOST+"/movies/sales").then((res) => {
      console.log(res.data)
      this.movieChart = res.data
    });
  },
};
</script>

<style>
#movieChart button {
  margin-bottom: 0.5em;
  margin-top: 0.5em;
}
.swipeButton {
  width: 48px;
}
.swipeButton button {
  width: 48px;
  height: 48px;
  padding: 0%;
  border: 0px;
  background-color: rgba(255, 255, 255, 1);
}
.MovieChartContent {
  width: 100%;
  display: flex;
  justify-content: center;
}
</style>
