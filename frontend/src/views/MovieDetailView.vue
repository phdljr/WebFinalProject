<template>
  <!-- {{$route.params.movie}} -->
  <div class="MovieDetail">
    <img :src="movieDetail.img" />
    <div class="movieContent">
      <h1>{{ movieDetail.title }}</h1>
      <h7>{{ movieDetail.titleEng }}</h7>
      <div class="score">예매율: 모름</div>

      <h7
        >감독 : {{ movieDetail.director }} / 배우 : {{ movieDetail.actors }}</h7
      ><br />
      <h7>장르 : {{ movieDetail.genre }}</h7
      ><br />
      <h7>개봉일: {{ movieDetail.openingDate }}</h7
      ><br />
      <b-button
        ref="cancel"
        variant="outline-danger"
        size="sm"
        aria-describedby="cancel-label"
        @click="$router.push('#')"
      >
        예매하기
      </b-button>
    </div>
  </div>
  <div class="movieGraph">
    <movie-detail-chart-vue
      :ageData="movieDetail.ageChart"
      :sexData="movieDetail.sexChart"
    ></movie-detail-chart-vue>
  </div>
  <div class="commentTable">
    <h3 style="margin-top: 80px">댓글</h3>
    <b-row v-for="(row, index) in commentTable" :key="index">
      <b-col v-for="(comment, index) in row" :key="index">
        <div class="commentBox">
          <p>{{ comment.user }}</p>
          <div class="comment">
            {{ comment.comment }}
          </div>
          <b-button>
            <b-icon icon="heart-fill"></b-icon>
            {{ comment.like }}
          </b-button>
        </div>
      </b-col>
    </b-row>
  </div>
  <div class="comment"></div>
</template>

<script>
import chunk from "chunk";
import MovieDetailChartVue from "@/components/MovieDetailChart.vue";

export default {
  components: {
    MovieDetailChartVue,
  },

  data() {
    return {
      movieDetail: {
        title: "영화 제목",
        titleEng: "title",
        img: "https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85813/85813_320.jpg",
        director: "감독님",
        actors: ["배우1", "배우2"],
        genre: "범죄, 액션",
        age: "15세 이상",
        openingDate: "2022.05.18",
        comment: [
          { user: "유저1", comment: "댓글임", like: 2 },
          { user: "유저2", comment: "댓글임", like: 3 },
          { user: "유저3", comment: "댓글임", like: 2 },
          { user: "유저4", comment: "댓글임", like: 2 },
          { user: "유저5", comment: "댓글임", like: 2 },
          { user: "유저6", comment: "댓글임", like: 27 },
          { user: "유저7", comment: "댓글임", like: 1 },
          { user: "유저8", comment: "댓글임", like: 5 },
        ],
        sexChart: [34, 27],
        ageChart: [21, 27, 18, 16, 12],
      },
    };
  },
  computed: {
    commentTable() {
      return chunk(this.movieDetail.comment, 2);
    },
  },
};
</script>

<style>
.MovieDetail {
  width: 100%;
  height: max-content;
  text-align: left;
  clear: both;
  overflow: hidden;
}
.MovieDetail img {
  width: 180px;
  float: left;
  margin-right: 20px;
}
.MovieDetail h5 {
  margin-bottom: 1em;
}
.score {
  width: 780px;
  border-bottom: 1px solid #333333;
  margin-bottom: 1em;
}
.movieContent {
  float: left;
  width: 780px;
}
.movieGraph {
  width: 100%;
  clear: both;
  overflow: hidden;
  margin-top: 70px;
}
.commentTable {
  text-align: left;
}
.comment {
  height: 200px;
}
.commentBox {
  border-bottom: 1px solid #333333;
  padding: 20px;
}
</style>
