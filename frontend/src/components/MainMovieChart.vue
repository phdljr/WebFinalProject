<template>
  <div class="MovieChartContent">
    <div>
      <b-row>
        <b-col>
          <h3 style="text-align: left">무비차트</h3>
        </b-col>
        <b-col>
          <router-link to="/movieview" style="float: right">전체보기</router-link>
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
        <b-list-group-item class="container" :key="key" v-for="(movie, key) in listMovieChart">
          <b-card
            :img-src="'../movies/' + movie.title + '.jpg'"
            :img-alt="movie.title"
            img-top
            tag="article"
            style="width: 200px;height: 450px;"
            class="mb-2 title"
            :aria-hidden="showOverlayList[key] ? 'true' : null"
            @mouseover="showOverlayList[key] = true"
          >
            <b-card-title class="title">{{movie.title}}</b-card-title>
            <span :class="setMediaRateImg(movie.mediaRating)"></span>
            <p>예매율: {{ movie.rate * 100 }}%</p>
            <p>평점: {{Math.round(movie.grade * 100) / 100}}★</p>
          </b-card>
          <div class="overlay">
            <div class="overlayButton" @mouseover="showOverlayList[key] = false">
              <b-button
                variant="outline-danger"
                size="sm"
                aria-describedby="cancel-label"
                @click="$router.push('/moviedetailview/' + movie.title)"
              >
                상세보기
              </b-button>
              <br>
              <b-button
                variant="outline-danger"
                size="sm"
                aria-describedby="cancel-label"
                @click="goTicket(movie.title)"
              >
                예매하기
              </b-button>
            </div>
          </div>
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
        {
          title: "테스트",
          rate: "3",
          grade: "5",
          mediaRating: "15세",
        },{
          title: "테스트",
          rate: "3",
          grade: "5",
          mediaRating: "15세",
        },
      ],
      showOverlayList: [false, false, false, false, false],
    };
  },
  methods: {
    goTicket(title){
      if(title == "범죄도시 2"){
        this.$router.push('/ticketview?movie=' + title)
      }
      else{
        alert("구미 CGV에서 상영중인 영화가 아닙니다.")
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
    },
    overlayOut(key){
      if(this.showOverlayList[key] == true){
        this.showOverlayList[key] = false
      }
    }
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
.media-rate{
  position: absolute;
  top: 5px;
  left: 5px;
}
.title{
  display: block;
  color: #333333;
  white-space: nowrap;
  text-overflow: ellipsis;
  overflow: hidden;
}
.overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  background-color: rgba(255, 255, 255, 1);
  display: inline-block;
  vertical-align: middle;
}
.overlayButton{
  position: absolute;left: 50%;top: 50%;
  transform: translate(-50%,-50%)
}
.container:hover .overlay {
  opacity: 0.9;
}
.ico-grade{ display:block; position:absolute; left:5px; top:5px; width:21px; height:21px; background:url('/public/media-rate-img.png') no-repeat;font:0/0 a;zoom:1;}
.ico-grade.grade-all{ background-position:-30px 0;}/* 전체 */
.ico-grade.grade-12{ background-position:-51px 0;}/* 12세 */
.ico-grade.grade-15{ background-position:-72px 0;}/* 15세 */
.ico-grade.grade-19{ background-position:-93px 0;}/* 청불 */
</style>
