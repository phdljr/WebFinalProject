<template>
  <div class="movies">
    <h2>무비차트</h2>
    <div class="MovieList">
      <div class="sortSelect">
        <b-form-select
          v-model="sortSelected"
          :options="sortOptions"
        ></b-form-select>
      </div>
      <b-row :key="key" v-for="(movieRow, key) in chunkMovies" cols="4">
        <b-col :key="index" v-for="(movie, index) in movieRow">
          <div class="movieListRank">No. {{ index+1 + key*4 }}</div>
          <router-link :to="'/movie/' + movie.title"
            ><img :src="'../movies/' + movie.title + '.jpg'"
          /></router-link>
          <div class="movieListDetail">
            <p>{{ movie.title }}</p>
            <p>{{ movie.rate >= 0 ? "예매율 " + Math.ceil(movie.rate * 100) + "%" : "상영 예정" }}</p>
            <b-button
              variant="outline-danger"
              @click="$router.push('/ticket?movie=' + movie.title)"
              >예매하기</b-button
            >
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
var chunk = require("chunk");

export default {
  name: "MovieView",
  components: {},
  data() {
    return {
      movies: [
        // {
        //   title: "",
        //   rate: "",
        //   grade: "",
        // },
      ],
      sortSelected: "book",
      sortOptions: [
        { value: "book", text: "예매율" },
        { value: "rate", text: "평점" },
      ],
    };
  },
  created() {
    // 해당 페이지가 출력되면 영화 데이터를 받아옴
    axios.get(this.HOST+"/movies/sales").then((res) => {
      console.log(res.data)
      this.movies = res.data
    });
  },
  computed: {
    chunkMovies() {
      return chunk(this.movies, 4); // 한 줄당 4개씩 출력
    },
  },
  watch: {
    sortSelected: function (val) {
      this.$router.push("movie?sort=" + val);
    },
  },
};
</script>

<style>
.movies {
  text-align: left;
}
.MovieList {
  border-top: 0.3ch solid;
}
.MovieList img {
  width: 100%;
}
.movieListDetail {
  margin-bottom: 30px;
}
.movieListRank {
  background: #fb4357;
  margin-bottom: 4px;
  color: white;
  text-align: center;
}
.sortSelect {
  margin-bottom: 30px;
  margin-top: 15px;
  display: flex;
  justify-content: right;
}
.sortSelect select {
  width: 7em;
}
</style>
