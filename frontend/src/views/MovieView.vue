<template>
  <div class="movies">
    <h2>무비차트</h2>
    <div class="MovieList">
      <div class="searchInput">
        <b-form-input
          v-model="searchInput"
          placeholder="배우 또는 감독"
        ></b-form-input>
        <b-button @click="searchMovie">Go</b-button>
      </div>
      <div class="sortSelect">
        <b-form-select
          v-model="sortSelected"
          :options="sortOptions"
        ></b-form-select>
        <b-button @click="sortMovie">Go</b-button>
      </div>
      <b-row :key="key" v-for="(movieRow, key) in chunkMovies" cols="4">
        <b-col :key="index" v-for="(movie, index) in movieRow">
          <div class="movieListRank">No. {{ index + 1 + key * 4 }}</div>
          <router-link :to="'/moviedetailview/' + movie.title"
            ><img :src="'../movies/' + movie.title + '.jpg'"
          /></router-link>
          <div class="movieListDetail">
            <p>{{ movie.title }}</p>
            <!-- <p>예매율: {{ movie.rate * 100 }}% | 평점: {{ movie.rate }}★</p> -->
            <p v-if="sortShow == 'book'">예매율: {{ movie.rate * 100 }}%</p>
            <p v-else>평점: {{ movie.rate }}★</p>
            <b-button variant="outline-danger" @click="goTicket(movie.title)"
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
        //   mediaRating: "",
        //   title: "",
        //   rate: "",
        // },
      ],
      sortShow: "book", // 텍스트 표기용
      sortSelected: "book",
      sortOptions: [
        { value: "book", text: "예매율" },
        { value: "rate", text: "평점" },
      ],
      searchInput: null, // 사람 이름으로 검색
    };
  },
  methods: {
    searchMovie() {},
    sortMovie() {
      if (this.sortSelected == "book") {
        this.sortBySale();
        this.sortShow = "book";
      } else {
        this.sortByGrade();
        this.sortShow = "grade";
      }
    },
    sortBySale() {
      axios.get(this.HOST + "/movies/sales").then((res) => {
        console.log(res.data);
        this.movies = res.data;
      });
    },
    sortByGrade() {
      axios.get(this.HOST + "/movies/rates").then((res) => {
        console.log(res.data);
        this.movies = res.data;
      });
    },
    goTicket(title) {
      if (title == "범죄도시 2") {
        this.$router.push("/ticketview?movie=" + title);
      } else {
        alert("구미 CGV에서 상영중인 영화가 아닙니다.");
      }
    },
  },
  created() {
    // 해당 페이지가 출력되면 영화 데이터를 받아옴(예매율순)
    this.sortBySale();
  },
  computed: {
    chunkMovies() {
      return chunk(this.movies, 4); // 한 줄당 4개씩 출력
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
.searchInput {
  margin-bottom: 30px;
  margin-top: 15px;
  display: flex;
  justify-content: right;
  float: left;
}
</style>
