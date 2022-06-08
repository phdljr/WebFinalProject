<template>
  <div class="MovieChartContent">
    <div>
      <b-row>
        <b-col>
          <h3 style="text-align: left">무비차트</h3>
        </b-col>
        <b-col>
          <router-link to="/movie" style="float: right">전체보기</router-link>
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
              img-src="https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85689/85689_320.jpg"
              img-alt="Image"
              img-top
              tag="article"
              style="width: 200px"
              class="mb-2"
              :aria-hidden="showOverlayList[key] ? 'true' : null"
              @mouseover="showOverlayList[key] = true"
            >
              <p>예매율 XX%</p>
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
        { title: "쥬라기 월드-도미니언" },
        { title: "2" },
        { title: "3" },
        { title: "4" },
        { title: "5" },
        { title: "6" },
        { title: "7" },
        { title: "8" },
        { title: "9" },
        { title: "10" },
        { title: "11" },
      ],
      showOverlayList: [false, false, false, false, false],
    };
  },
  methods: {
    getMovieChart() {
      const key =
        "consumer_key=" +
        "09cd8b73e1e74ba39b80" +
        "&consumer_secret=" +
        "c36d02cd136f451c9a55";
      axios
        .get(
          "https://sgisapi.kostat.go.kr/OpenAPI3/auth/authentication.json?" +
            key
        )
        .then((res) => {
          console.log(res);
        });
    },
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
    //this.getMovieChart()
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
