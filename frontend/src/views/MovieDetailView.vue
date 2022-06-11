<template>
  <div>
    <!-- {{$route.params.movie}} -->
    <div class="MovieDetail">
      <img :src="movieDetail.img" />
      <span :class="setMediaRateImg(movieDetail.mediaRate)"></span>
      <div class="movieContent">
        <h1>{{ movieDetail.title }}</h1>
        <div class="score">
          예매율: {{ Math.ceil(movieDetail.ticketSales * 100) }}% | 평점:
          {{ Math.round(movieDetail.avgOfGrade * 100) / 100 }}
        </div>

        <h6>감독 : {{ movieDetail.director }} / 배우 : {{ actorsPrint }}</h6>
        <br />
        <h6>
          장르 : {{ movieDetail.genre }} / 기본:
          {{
            movieDetail.mediaRate == "전체"
              ? movieDetail.mediaRate
              : movieDetail.mediaRate + " 이상"
          }}
          / {{ movieDetail.runtime }}
        </h6>
        <br />
        <h6>개봉일: {{ movieDetail.releaseDate }}</h6>
        <br />
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
        <vue3-star-ratings v-model="rating" />
        <textarea v-model="comment"></textarea>
        <b-button variant="success" @click="addComment">저장</b-button>
      </div>
      <b-row v-for="(row, index) in commentTable" :key="index">
        <b-col cols="6" v-for="(comment, index) in row" :key="index">
          <div class="commentBox">
            <div class="idBox">
              {{ comment.userId }} | 평점: {{comment.grade}}★
              <span v-if="$store.state.userData.id == comment.userId">
                <b-button variant="primary" @click="reviseComment(comment.comment,$event)">수정</b-button>
                <b-button variant="danger" @click="deleteComment(comment)">삭제</b-button>
              </span>
            </div>
            <div class="comment">
              {{ comment.comment }}
            </div>
            <div class="reviseComment">
              <textarea v-model="comment.commnet"></textarea>
            </div>
            <b-button @click="addLike(comment)">
              <!-- 자신이 이미 좋아요를 누른 후기는 가득 한 하트로 표시 -->
              <b-icon
                v-if="selectedLikeArr.includes(comment.userId)"
                icon="heart-fill"
              ></b-icon>
              <b-icon v-else icon="heart"></b-icon>
              {{ comment.like }}
            </b-button>
            <span style="margin-left: 10px">{{ comment.commentDate }}</span>
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
        img: "../movies/" + this.$route.params.movie + ".jpg",
        director: "",
        actors: [],
        genre: "",
        releaseDate: "",
        mediaRate: "",
        runtime: "",
        ticketSales: "",
        avgOfGrade: "",
        genderDistribution: [],
        ageDistribution: [],
        comments: [
          // {
          //   userId: "",
          //   comment: "",
          //   like: "",
          //   commentDate: "",
          //   grade: "",
          // },
        ],
      },
      comment: "",
      selectedLikeArr: [], // 자신이 좋아요를 눌렀던 후기의 작성자 아이디가 들어갈 배열
      rating: 2.5, // 0~5
    };
  },
  methods: {
    // 좋아요 클릭
    // 이미 좋아요가 된 상태라면, 해제함
    addLike(comment) {
      console.log("클릭");
      // 로그인이 안됐을 때
      if (this.$store.state.login == false) {
        let result = confirm(
          "로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?"
        );
        if (result == true) {
          this.$router.push("/loginview");
        }
      } else {
        // 내가 작성한 후기에 좋아요를 누를 경우
        if (comment.userId == this.$store.state.userData.id) {
          alert("자신이 작성한 후기에 좋아요를 누를 수 없습니다.");
          return;
        }

        // 이미 좋아요를 누른 후기일 경우
        if (this.selectedLikeArr.includes(comment.userId)) {
          let select = confirm(
            "이미 좋아요를 눌렀습니다.\n좋아요를 취소하시겠습니까?"
          );

          // 눌렀던 좋아요를 다시 삭제할 경우
          if (select == true) {
            this.removeLike(comment);
          }
          return;
        }

        axios
          .post(this.HOST + "/addLike", {
            commentUserId: comment.userId,
            movieName: this.movieDetail.title,
            userId: this.$store.state.userData.id,
          })
          .then((res) => {
            if (res.data == "Success") {
              this.selectedLikeArr.push(comment.userId);
              comment.like++;
            }
          })
          .catch((err) => {
            console.log(err);
          });
      }
    },

    // 좋아요 삭제
    removeLike(comment) {
      axios
        .post(this.HOST + "/removeLike", {
          commentUserId: comment.userId,
          movieName: this.movieDetail.title,
          userId: this.$store.state.userData.id,
        })
        .then((res) => {
          if (res.data == "Success") {
            delete this.selectedLikeArr[
              this.selectedLikeArr.indexOf(comment.userId)
            ];
            comment.like--;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 후기 등록
    addComment() {
      // 로그인이 안됐을 때
      if (this.$store.state.login == false) {
        let result = confirm(
          "로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?"
        );
        if (result == true) {
          this.$router.push({
                        name: 'LoginView',
                        params: {
                            state: "wasReviewing",
                            title: this.movieDetail.title,
                            theater: null,
                            time: null
                        }
                    })
        }
        return;
      }

      if (this.comment == "") {
        alert("후기를 작성해 주세요.");
        return;
      }

      axios
        .post(this.HOST + "/addComment", {
          comment: this.comment,
          movieName: this.movieDetail.title,
          userId: this.$store.state.userData.id,
          grade: this.rating,
        })
        .then((res) => {
          // 정상적으로 후기를 등록한 경우, 후기 데이터 다시 얻어오기
          // 이미 자신이 후기를 등록했다면, 이미 등록했다고 alert 띄우기
          if (res.data == "Success") {
            axios
              .get(this.HOST + "/movie/" + this.$route.params.movie)
              .then((res) => {
                console.log(res);
                this.movieDetail.comments = res.data.comments;
                this.comment = "";
                alert("후기를 등록했습니다.");
              })
              .catch((err) => {
                console.log(err);
              });
          } else if (res.data == "AlreadyExist") {
            alert("이미 후기를 작성하셨습니다.");
            this.comment = "";
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 후기 삭제
    deleteComment(comment) {
      if (!confirm("정말로 작성하신 후기를 삭제하시겠습니까?")) {
        return;
      }

      axios
        .post(this.HOST + "/deleteComment", {
          commentUserId: comment.userId,
          movieName: this.movieDetail.title,
          userId: this.$store.state.userData.id,
        })
        .then((res) => {
          // 정상적으로 후기를 삭제한 경우, 후기 데이터 다시 얻어오기
          if (res.data == "Success") {
            axios
              .get(this.HOST + "/movie/" + this.$route.params.movie)
              .then((res) => {
                console.log(res);
                alert("후기를 삭제했습니다.");
                this.movieDetail.comments = res.data.comments;
              })
              .catch((err) => {
                console.log(err);
              });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },

    // 후기 수정
    // 수정 버튼 클릭 시, 입력칸이 뜨도록
    reviseComment(comment, event){
      var revisedCommnet = event.target.parentNode.parentNode.parentNode.childNodes[2].childNodes[0].value
      if(event.target.parentNode.parentNode.parentNode.childNodes[2].style.display == "block"){
        console.log("여기에 수정시 댓글 가져오기")
        console.log(revisedCommnet)//이게 댓글 수정된거 가져온거임
        axios.post(this.HOST+"/reviseComment", {
          commentUserId: this.$store.state.userData.id,
          movieName: this.movieDetail.title,
          newComment: revisedCommnet,
        }).then(res=>{
          if(res.data == "Success"){
            alert("후기가 수정되었습니다.");
            this.$router.go();
            return;
          }
        }).catch(err=>{
          console.log(err)
        })
      }
      event.target.parentNode.parentNode.parentNode.childNodes[2].style.display = "block"
      event.target.parentNode.parentNode.parentNode.childNodes[2].childNodes[0].value = comment
      event.target.parentNode.parentNode.parentNode.childNodes[1].style.display = "none"
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
    console.log("접속 시: " + this.$route.params.movie);
    // 영화 상세 데이터 받아오기
    axios.get(this.HOST + "/movie/" + this.$route.params.movie).then((res) => {
      console.log(res);
      this.movieDetail = res.data;
      this.movieDetail.title = this.$route.params.movie;
      this.movieDetail.titleEng = "영어";
      this.movieDetail.img = "../movies/" + this.$route.params.movie + ".jpg";
      this.movieDetail.genderDistribution = [
        res.data.genderDistribution,
        1 - res.data.genderDistribution,
      ];
    });
    axios
      .get(this.HOST + "/" + this.$store.state.userData.id + "/likes")
      .then((res) => {
        console.log(res.data);
        this.selectedLikeArr = res.data;
      })
      .catch((err) => {
        console.log("좋아요 에러")
        console.log(err);
      });
  },
  computed: {
    commentTable() {
      return chunk(this.movieDetail.comments, 2);
    },
    actorsPrint() {
      return this.movieDetail.actors.join(", ");
    },
  },
  watch: {
    rating: function (val) {
      this.rating = Math.round(val * 2) / 2;
    },
  },
};
</script>

<style>
.MovieDetail {
  width: 100%;
  height: max-content;
  position: relative;
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
  clear: both;
  margin-bottom: 6px;
}
.commentBox {
  border-bottom: 1px solid #333333;
  padding: 20px;
}
.commentInput {
  border-top: 1px solid #333333;
  padding-top: 20px;
  margin-bottom: 80px;
  clear: both;
  overflow: hidden;
}
.commentInput textarea {
  width: 90%;
  height: 120px;
  float: left;
  resize: none;
}
.commentInput button {
  float: right;
  height: 120px;
  width: 8%;
}
.idBox{
  width: 100%;
  height: 40px;
  float: left;
  margin-bottom: 10px;
}
.commentBox span{
  float: right;
}
.commentBox span button{
  margin-left: 5px;
}
.reviseComment{
  display: none;
  clear: both;
}
.reviseComment textarea{
  height: 200px;
  resize: none;
  width: 100%;
}
.ico-grade{ display:block; position:absolute; left:5px; top:5px; width:21px; height:21px; background:url('/public/media-rate-img.png') no-repeat;font:0/0 a;zoom:1;}
.ico-grade.grade-all{ background-position:-30px 0;}/* 전체 */
.ico-grade.grade-12{ background-position:-51px 0;}/* 12세 */
.ico-grade.grade-15{ background-position:-72px 0;}/* 15세 */
.ico-grade.grade-19{ background-position:-93px 0;}/* 청불 */
</style>
