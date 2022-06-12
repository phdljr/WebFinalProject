<template>
  <div>
    <div class="dateSelect">
      <b-button variant="outline-secondary" :pressed="true" class="date">
        <div class="month">06월<br />화</div>
        <div class="day">14</div>
      </b-button>
    </div>
    <div class="movieSelect">
      <div class="movieTitle">
        <div style="float: left">
          ({{ movieDetail.mediaRating }}) {{ movieDetail.movieName }}
        </div>
        <div style="float: left; margin-left: 1em; color: #a0a0a0">
          {{ movieDetail.genre }} / {{ movieDetail.runtime }} /
          {{ movieDetail.releaseDate }} 개봉
        </div>
        <div
          class="timeSelect"
          v-for="(theater, key) in movieDetail.theater"
          :key="key"
        >
          <div>
            ▶ 2D | {{ theater.theaterScreen + " " + theater.theaterFloor }} | 총
            {{ theater.numOfSeats }}석
          </div>
          <div
            class="saleButtons"
            v-for="(time, index) in theater.time"
            :key="index"
          >
            <b-button
              v-b-toggle="['collapse-' + key + '-' + index]"
              class="timeButton"
            :variant="getPolicyColor(time.discountPolicy)"
            >
              {{ time.screenTime }}<br />
              {{
                time.remainingNumOfSeats > 0
                  ? time.remainingNumOfSeats + "석"
                  : "마감"
              }}<br />
              <span v-if="time.discountPolicy!='none'">
                {{time.discountRate + (time.discountPolicy=='rate' ? "% 할인" : "원 할인")}}
              </span>
            </b-button>
            <b-collapse :id="'collapse-' + key + '-' + index" class="mt-2">
              <b-card id="collapseCard">
                <b-form @submit="applySale(theater.theaterScreen, time)">
                  <b-form-radio-group
                    id="radio-group-1"
                    v-model="time.discountPolicy"
                    :options="saleOptions"
                    name="radio-options"
                    required
                  ></b-form-radio-group>
                  <b-form-group id="input-group-1" label-for="input-1">
                    <b-form-input
                      id="input-1"
                      type="number"
                      v-model="time.discountRate"
                      :min="time.discountPolicy == 'rate' ? 1 : 0"
                      :max="time.discountPolicy == 'rate' ? 100 : 5000"
                      :disabled="time.discountPolicy == 'none'"
                      :value="time.discountPolicy == 'none' ? 0 : time.discountRate"
                      required
                    ></b-form-input>
                    <b-button type="submit"> 적용 </b-button>
                  </b-form-group>
                </b-form>
              </b-card>
            </b-collapse>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      movieDetail: {
        // 고정
        genre: "",
        mediaRating: "",
        movieName: "",
        releaseDate: "",
        runtime: "",

        // 유동
        theater: [
          {
            theaterScreen: "1관",
            numOfSeats: 0,
            theaterFloor: "1층",
            time: [
              // {
              //     screenTime: "",
              //     remainingNumOfSeats: 0
              //     discountPolicy: "",
              //     discountRate: "",
              // }
            ],
          },
          {
            theaterScreen: "2관",
            numOfSeats: 0,
            theaterFloor: "2층",
            time: [
              // {
              //     screenTime: "",
              //     remainingNumOfSeats: 0
              //     discountPolicy: "",
              //     discountRate: "",
              // }
            ],
          },
        ],
      },
      saleOptions: [
        { text: "정률할인", value: "rate" },
        { text: "정액할인", value: "fix" },
        { text: "할인 취소", value: "none" },
      ],
    };
  },
  methods: {
    makeSaleData() {
      axios
      .get(this.HOST + "/schedules/구미 CGV")
      .then((res) => {
        console.log(res.data);
        this.movieDetail.genre = res.data[0].genre;
        this.movieDetail.mediaRating = res.data[0].mediaRating;
        this.movieDetail.movieName = res.data[0].movieName;
        this.movieDetail.releaseDate = res.data[0].releaseDate;
        this.movieDetail.runtime = res.data[0].runtime;

        for (let i = 0; i < res.data.length; i++) {
          this.movieDetail.theater[res.data[i].theaterFloor - 1].numOfSeats =
            res.data[i].numOfSeats;
          this.movieDetail.theater[res.data[i].theaterFloor - 1].time.push({
            screenTime: res.data[i].screenTime,
            remainingNumOfSeats: res.data[i].remainingNumOfSeats,
            discountPolicy: res.data[i].discountPolicy,
            discountRate: res.data[i].discountRate
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });
    },
    getPolicyColor(discountPolicy){
      switch(discountPolicy){
        case "rate":
          return "outline-success"
        case "fix":
          return "outline-danger"
        default:
          return "outline-secondary"
      }
    },
    applySale(screenName, time) {
      let data = {
        discountPolicy: time.discountPolicy,
        discountRate: time.discountRate,
        screenName: screenName,
        screenTime: time.screenTime,
        theaterName: "구미 CGV"
      }

      // // 정률할인 범위 체크 : 1~100 사이의 값만 입력 가능
      // if(data.discountPolicy == "rate"){
      //   if(!(data.discountRate > 0 || data.discountRate <= 100)){
      //     alert("정률 할인은 1~100 사이의 값만 입력할 수 있습니다.")
      //     return;
      //   }
      // }

      // // 정액할인 범위 체크 : 1 이상의 값만 입력 가능
      // if(data.discountPolicy == "fix"){
      //   if(data.discountRate > 5000){
      //     alert("정액 할인은 5000 보다 작은 값만 입력할 수 있습니다.")
      //     return;
      //   }
      // }

      axios.post(this.HOST+"/movie/discount",data).then(res=>{
        console.log(res.data)
        if(res.data == "Success"){
          alert("성공적으로 적용되었습니다.")
          this.$router.go();
        }
        else if(res.data == "PriceIsNegative"){
          alert("할인이 적용된 가격이 음수가 될 수 없습니다.")
        }
        else{
          alert("잘못된 할인 정책입니다.")
        }
      }).catch(err=>{
        console.log(err)
      })
    },
  },
  computed: {
    totalSeat() {
      var total = 0;
      for (var temp in this.movieDetail.seat) {
        total += parseInt(this.movieDetail.seat[temp]);
      }
      return total;
    },
  },
  created() {
    this.makeSaleData(); //이거 axios로 값 가져온뒤에 실행해야함
    if (this.$store.state.userData.isAdmin == true) {
      return;
    } else {
      //alert("잘못된 접근입니다.")
      //this.$router.push("/")
    }
  },
};
</script>

<style>
.dateSelect {
  width: 100%;
  text-align: left;
  border-bottom: 1px solid #333333;
}
.date {
  margin: 10px;
}
.month {
  float: left;
  font-size: 1em;
  text-align: center;
}
.day {
  float: left;
  font-size: 2em;
}
.movieSelect {
  width: 100%;
  text-align: left;
}
.timeSelect {
  width: 100%;
  float: left;
  padding: 3em;
}
.timeButton {
  float: left;
  margin-top: 10px;
  margin-right: 1em;
}
.movieTitle {
  padding-top: 20px;
  border-bottom: none;
}
.saleButtons div {
  width: 100%;
  float: left;
}
.saleButtons button {
  margin-top: 10px;
}
</style>
