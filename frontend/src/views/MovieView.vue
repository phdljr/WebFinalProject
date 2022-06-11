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
          <router-link :to="'/moviedetailview/' + movie.title">
            <div class="movie-media-rate">
              <img :src="'../movies/' + movie.title + '.jpg'"/>
              <span :class="setMediaRateImg(movie.mediaRating)"></span>
            </div>
          </router-link>
          <div class="movieListDetail">
            <p>{{ movie.title }}</p>
            <p>예매율: {{ movie.rate * 100 }}% | 평점: {{ movie.grade.toFixed(2) }}★</p>
            <!-- <p v-if="sortShow == 'book'">예매율: {{ movie.rate * 100 }}%</p>
            <p v-else>평점: {{ movie.rate }}★</p> -->
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
        //   grade: ""
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
    searchMovie() {
      if(this.searchInput == null ||this.searchInput == ""){
        alert("값을 입력해주세요.")
        return;
      }

      axios.get(this.HOST + "/search/"+this.searchInput).then((res) => {
        console.log(res.data);
        if(res.data == [] || res.data.length == 0){
          alert("검색 결과가 없습니다.")
          return;
        }
        this.movies = res.data;
      });
    },
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
    setMediaRateImg(mediaRating){
      let result = "ico-grade "
      switch(mediaRating){
        case "전체":
          result += "grade-all"
          break;
        case "12세":
          result += "grade-12"
          break;
        case "15세":
          result += "grade-15"
          break;
        case "청불":
          result += "grade-19"
          break;
      }
      console.log(result)
      return result;
    }
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
.movie-media-rate{
  position: relative;
}
.ico-grade{ display:block; position:absolute; left:5px; top:5px; width:21px; height:21px; background:url('/public/media-rate-img.png') no-repeat;font:0/0 a;zoom:1;}
.ico-grade.grade-all{ background-position:-30px 0;}/* 전체 */
.ico-grade.grade-12{ background-position:-51px 0;}/* 12세 */
.ico-grade.grade-15{ background-position:-72px 0;}/* 15세 */
.ico-grade.grade-19{ background-position:-93px 0;}/* 청불 */
</style>
