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
          v-for="(theater, index) in movieDetail.theater"
          :key="index"
        >
          <div>
            ▶ 2D | {{ theater.theaterScreen + " " + theater.theaterFloor }} | 총
            {{ theater.numOfSeats }}석
          </div>
          <b-button
            class="timeButton"
            variant="outline-secondary"
            v-for="(time, index) in theater.time"
            :key="index"
            :disabled="time.remainingNumOfSeats <= 0"
            @click="
              $router.push(
                '/ticket?movie=' +
                  movieDetail.movieName +
                  '&theater=' +
                  theater.theaterScreen +
                  '&time=' +
                  time.screenTime
              )
            "
          >
            {{ time.screenTime }}<br />
            <span>{{
              time.remainingNumOfSeats > 0
                ? time.remainingNumOfSeats + "석"
                : "마감"
            }}</span>
          </b-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios";
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
              // }
            ],
          },
        ],
      },
    };
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
          });
        }
      })
      .catch((err) => {
        console.log(err);
      });
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
</style>
