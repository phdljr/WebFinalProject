<template>
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
                <router-link to="/" v-if="isTimeSelected">
                    <b-icon icon="arrow-right-square-fill" size="5x"></b-icon>
                    <br>예매하기
                </router-link>
            </b-col>
        </b-row>
    </div>
</template>

<script>
export default {
    name: "movieBookingView",
    data() {
        return {
            selectMovie:this.$route.query.movie,
            selectTime:{ theater:this.$route.query.theater, time:this.$route.query.time},
            movie:[
                { value: "title", text: "영화 제목임" }
            ],
            timeline:[
                {
                    theaterName:"1관",
                    totalSeat:48,
                    time:[ "12:00", "15:00" ],
                    seat:[20,30]
                },
                {
                    theaterName:"2관",
                    totalSeat:60,
                    time:[ "10:00"],
                    seat:[50]
                },
            ]
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