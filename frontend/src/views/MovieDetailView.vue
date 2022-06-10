<template>
  <div>
    <!-- {{$route.params.movie}} -->
    <div class="MovieDetail">
      <img :src="movieDetail.img" />
      <div class="movieContent">
        <h1>{{ movieDetail.title }}</h1>
        <!-- <h7>{{ movieDetail.titleEng }}</h7> -->
        <div class="score">예매율: {{Math.ceil(movieDetail.ticketSales * 100)}}%</div>

        <h6
          >감독 : {{ movieDetail.director }} / 배우 : {{ actorsPrint }}</h6
        ><br />
        <h6>장르 : {{ movieDetail.genre }} / 기본: {{movieDetail.mediaRate=="전체" ? movieDetail.mediaRate : movieDetail.mediaRate+" 이상"}} / {{movieDetail.runtime}}</h6
        ><br />
        <h6>개봉일: {{ movieDetail.releaseDate }}</h6
        ><br />
        <b-button
          ref="cancel"
          variant="outline-danger"
          size="sm"
          aria-describedby="cancel-label"
          @click="goTicket(movieDetail.title)"
        >
          예매하기
        </b-button>
      </div>
    </div>
    <div class="movieGraph">
      <movie-detail-chart-vue
        ref="movieGraph"
        :ageData="movieDetail.ageDistribution"
        :sexData="movieDetail.genderDistribution"
      ></movie-detail-chart-vue>
    </div>
    <div class="commentTable">
      <h3 style="margin-top: 80px">댓글</h3>
      <div class="commentInput">
        <vue3-star-ratings v-model="rating"/>
        <textarea v-model="comment"></textarea>
        <b-button variant="success">저장</b-button>
      </div>
      <b-row v-for="(row, index) in commentTable" :key="index">
        <b-col v-for="(comment, index) in row" :key="index">
          <div class="commentBox">
            <p>{{ comment.userId }}</p>
            <div class="comment">
              {{ comment.comment }}
            </div>
            <b-button>
              <b-icon icon="heart-fill" @click="addLike(comment)"></b-icon>
              {{ comment.like }}
            </b-button>
            <span style="margin-left: 10px;">{{comment.commentDate}}</span>
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
import axios from "axios";
import chunk from "chunk";
import MovieDetailChartVue from "@/components/MovieDetailChart.vue";

export default {
  components: {
    MovieDetailChartVue,
  },

  data() {
    return {
      movieDetail: {
        title: this.$route.params.movie,
        // titleEng: "",
        img: "../movies/" + this.$route.params.movie + ".jpg",
        // persons: [],
        director: "",
        actors: [],
        genre: "",
        releaseDate: "",
        mediaRate: "",
        runtime: "",
        ticketSales: "",
        genderDistribution: [],
        ageDistribution: [],
        comments:[{
          userId: "",
          comment: "",
          like: "",
          commentDate: ""
        }]
      },
      comment:'',
      rating:0, // 0~5
    };
  },
  methods:{
    addLike(comment){
      // 로그인이 안됐을 때
      console.log(comment)
      if(this.$store.state.login == false){
        let result = confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?');
        if(result == true){
            this.$router.push('/login')
        }
      }
      else{
        axios.post(this.HOST+"/addLike", {
          comment: this.movieDetail.comments.comment,
          commentDate: this.movieDetail.comments.commentDate
        }).then(res=>{
          if(res.data == "Success"){
            comment.like++;
          }
        }).catch(err=>{
          console.log(err)
        })
      }
    },
    goTicket(title){
      if(title == "범죄도시 2"){
        this.$router.push('/ticket?movie=' + title)
      }
      else{
        alert("구미 CGV에서 상영중인 영화가 아닙니다.")
      }
    }
  },
created(){
  console.log("접속 시: " + this.$route.params.movie)
  // 영화 상세 데이터 받아오기
  axios.get(this.HOST+"/movie/"+this.$route.params.movie).then(res=>{
      console.log(res)
      this.movieDetail = res.data
      this.movieDetail.title = this.$route.params.movie
      this.movieDetail.titleEng = "영어"
      this.movieDetail.img = "../movies/" + this.$route.params.movie + ".jpg"
      this.movieDetail.genderDistribution = [res.data.genderDistribution, 1-res.data.genderDistribution]
    })
  },
  computed: {
    commentTable() {
      return chunk(this.movieDetail.comments, 2);
    },
    actorsPrint(){
      return this.movieDetail.actors.join(', ')
    }
  },
  watch:{
    rating:function(val){
      this.rating = Math.round(val*2)/2
    }
  }
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
.commentInput{
  border-top: 1px solid #333333;
  padding-top: 20px;
  margin-bottom: 80px;
  clear: both;
  overflow: hidden;
}
.commentInput textarea{
  width:90%;
  height: 120px;
  float: left;
  resize: none;
}
.commentInput button{
  float: right;
  height: 120px;
  width: 8%;
}
</style>
