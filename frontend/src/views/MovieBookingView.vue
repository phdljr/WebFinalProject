<template>
    <div>
        <table class="ticketTable">
            <th>영화</th>
            <th>시간</th>
            <tr>
                <td>
                    <div class="ticketMovie">
                        <b-form-select v-model="selectMovie" :options="movie" :select-size="20"></b-form-select>
                    </div>
                </td>
                <td v-if="selectMovie!=null">
                    <div class="theaterSelect" v-for="(theater, index) in timeline" :key="index">
                        {{theater.theaterName}} (총 {{theater.totalSeat}}석)
                        <br>
                        <div class="timeSelector" v-for="(time, key) in theater.time" :key="key">
                            <b-button
                                variant="outline-secondary"
                                :pressed="selectTime.theater==theater.theaterName&&selectTime.time==time"
                                @click="selectTime={ theater:theater.theaterName, time:time}">
                                {{time}}
                            </b-button>
                            {{theater.seat[key]}}석
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <div class="SeatSelectGo">
            <b-row>
                <b-col>영화 제목:{{selectMovie}}</b-col>
                <b-col>상영관:{{selectTime.theater}} {{selectTime.time}}</b-col>
                <b-col cols="5" md="auto">
                    <!-- 로그인을 했을 때와 안했을 때, 라우터 경로 바꿔주기 -->
                    <!-- <router-link to="/" v-if="isTimeSelected">
                        <b-icon icon="arrow-right-square-fill" size="5x"></b-icon>
                        <br>예매하기
                    </router-link> -->
                    <b-button @click=getSeats v-if="isTimeSelected">
                        <b-icon icon="arrow-right-square-fill" size="5x"></b-icon>
                        <br>예매하기
                    </b-button>
                </b-col>
            </b-row>
        </div>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name: "movieBookingView",
    data() {
        return {
            // selectMovie:this.$route.query.movie,
            // selectMovie:"쥬라기 월드-도미니언",
            // selectTime:{ theater:this.$route.query.theater, time:this.$route.query.time},
            // movie:[
            //     { value: "title", text: "영화 제목임" }
            // ],
            // timeline:[
            //     {
            //         theaterName:"1관",
            //         totalSeat:48,
            //         time:[ "12:00", "15:00" ],
            //         seat:[20,30]
            //     },
            //     {
            //         theaterName:"2관",
            //         totalSeat:60,
            //         time:[ "10:00"],
            //         seat:[50]
            //     },
            // ]
            selectMovie:"", // ㅇ
            selectTime:{ theater:this.$route.query.theater, time:this.$route.query.time}, // 쿼리스트링
            movie:[
                { value: this.$route.query.movie, text: this.$route.query.movie },
                // { value: "나2", text: "나" },
            ],
            timeline:[
                {
                    theaterName:"1관",
                    totalSeat:null,
                    time:[],
                    seat:[]
                },
                {
                    theaterName:"2관",
                    totalSeat:null,
                    time:[],
                    seat:[]
                },
            ]
        }
    },
    methods:{
        // 예매하기 버튼을 눌렀을 때, 실행되는 함수
        // 로그인이 됐을 땐 진행, 로그인이 안돼있으면 로그인 화면으로 이동하라는 alert가 뜸
        // alert에서 '확인'버튼을 눌러야만 login창으로 이동.
        getSeats(){
            // 로그인이 안됐을 때
            if(this.$store.state.login == false){
                let result = confirm('로그인이 필요한 서비스입니다.\n로그인 페이지로 이동하시겠습니까?');
                if(result == true){
                    this.$router.push('/login')
                }
            }
            else{
                this.$router.push({name: 'SeatSelectView', params: {title: this.selectMovie, screenName: this.selectTime.theater, screenTime: this.selectTime.time}})
            }
        }
    },
    computed:{
        isTimeSelected(){
            if(this.selectTime.theater!=null && this.selectTime.time!=null){
                return true
            }
            else{
                return false
            }
        }
    },
    created(){
        axios.get(this.HOST+"/schedules/"+this.$route.query.movie)
        .then(res=>{
            console.log(res)
            this.selectMovie = this.$route.query.movie

            // 1관은 index 0, 2관은 index 1
            for(let i=0;i<res.data.length;i++){
                let data = res.data[i]
                console.log(data)
                if(data.theater == "1관"){
                    this.timeline[0].totalSeat = data.numOfTheaterSeats;
                    this.timeline[0].time.push(data.screenTime);
                    this.timeline[0].seat.push(data.getRemainingSeat);
                }
                else{
                    this.timeline[1].totalSeat = data.numOfTheaterSeats;
                    this.timeline[1].time.push(data.screenTime);
                    this.timeline[1].seat.push(data.getRemainingSeat);
                }
            }
        })
    }
}
</script>

<style>
.ticketTable{
    width: 100%;
}
.ticketTable th{
    width: 50%;
}
.ticketTable td{
    text-align: left;
    vertical-align: top;
    padding: 20px;
}
.theaterSelect{
    width: 100%;
    clear: both;
}
.timeSelector{
    float: left;
}
.timeSelector button{
    margin: 20px;
    margin-right: 10px;
}
</style>