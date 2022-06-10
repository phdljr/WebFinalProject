<template>
    <div class="selectSeatDiv">
        <div class="selectSeatTitle">인원/좌석</div>
        <b-row>
            <b-col style="vertical-align:middle">
                인원 수
                <b-form-group
                    v-slot="{ ariaDescribedby }"
                    style="width: 60%;float:right"
                    >
                    <b-form-radio-group
                        id="btn-radios-2"
                        v-model="personNum"
                        :options="personSelect"
                        :aria-describedby="ariaDescribedby"
                        button-variant="outline-primary"
                        size="lg"
                        name="radio-btn-outline"
                        buttons
                    ></b-form-radio-group>
                    </b-form-group>
            </b-col>
            <b-col>
                <p>CGV 구미 | {{seatData.theater}} | 남은좌석 {{seatData.maxcol*seatData.maxrow - seatData.selectedCol.length}} / {{seatData.maxcol*seatData.maxrow}}</p>
                <h3>2022.06.14 (화) {{this.$route.params.screenTime}} ~ {{calcTime(this.$route.params.screenTime)}}</h3>
            </b-col>
        </b-row>
        <b-row>
            <div class="seatTableRow" v-for="(row, rowNum) in seatTable" :key="rowNum">
                {{String.fromCharCode(65+rowNum)}}열
                <b-button
                    :pressed="seat==2"
                    :disabled="seat==1"
                    v-for="(seat, colNum) in row"
                    :key="colNum"
                    @click="chooseSeat(rowNum, colNum)"
                    :variant="seat==1?'danger':'outline-secondary'"
                >
                </b-button>
            </div>
        </b-row>
        <b-row>
            <b-col>
                <h5 style="text-align: right;" v-if="selectSeat.length!=0">일반 : {{price}}원 X {{selectSeat.length}}</h5>
                <h5 style="text-align: right;" v-if="selectSeat.length!=0">총 금액 : {{price * selectSeat.length}}원</h5>
                <b-button id="bookButton" @click="makeReserve">예매하기</b-button>
            </b-col>
        </b-row>
    </div>
</template>

<script>
import axios from "axios";

export default {
    name:"SeatSelectView",
    data() {
        return {
            personNum:1,
            personSelect:[
                { text:'1', value:1 },
                { text:'2', value:2 },
                { text:'3', value:3 },
                { text:'4', value:4 }
            ],
            price: 0, // 한 자리에 필요한 금액
            runtime: "", // 영화 상영 시간
            seatData:{
                // title: "",
                // theater: "",
                // maxcol:8,
                // maxrow:10,
                // selectedRow:['A','C','E'],
                // selectedCol:[3,7,5]
                title: "",
                theater: "",
                maxcol: 0,
                maxrow: 0,
                selectedRow:[],
                selectedCol:[]
            },
            selectSeat:[
                // {'A', 0}
            ]
        }
    },
    methods:{
        zeros(dimensions) {
            var array = [];
            for (var i = 0; i < dimensions[0]; ++i) {
                array.push(dimensions.length == 1 ? 0 : this.zeros(dimensions.slice(1)));
            }
            return array;
        },
        chooseSeat(row, col){
            for (var key in this.selectSeat){
                if(this.selectSeat[key].row == row && this.selectSeat[key].col == col){
                    this.selectSeat.splice(key,1)
                    return null
                }
            }
            if(this.selectSeat.length >= this.personNum){
                alert("선택할 수 있는 좌석 수를 초과하였습니다.")
            }
            else{
                this.selectSeat.push({ row, col })
            }
        },
        // 만드는 중...
        makeReserve() {
            let row_arr = [];
            let col_arr = [];
            for(let index = 0; index < this.selectSeat.length; index++){
                row_arr.push(String.fromCharCode(this.selectSeat[index]['row'] + 65));
                col_arr.push(this.selectSeat[index]['col']);
            }

            let data = {
                userId: this.$store.state.userData.id,
                numOfPeople: this.selectSeat.length,
                price: this.selectSeat.length * this.price,
                rows: row_arr,
                columns: col_arr,
                theaterName: "구미 CGV",
                screenName: this.$route.params.screenName,
                screenTime: this.$route.params.screenTime,
                movieName: this.seatData.title,
            };

            axios.post(this.HOST + "/reserve", data).then(res=>{
                console.log(res.data)
                if(res.data === "Success"){
                    alert("정상적으로 예매됐습니다!")
                    this.$router.push("/")
                }
            })
        },
        calcTime(screenTime){
            let date = screenTime.split(':')
            let hour = parseInt(date[0])
            let min = parseInt(date[1])

            let temp = Number(this.runtime.replace('분', ''))
            hour += parseInt(temp/60)
            min += temp%60

            return hour + ":" + min;
        }
    },
    created(){
        axios.post(this.HOST+"/reservedSeats", {
            theaterName : "구미 CGV",
            screenName: this.$route.params.screenName,
            screenTime: this.$route.params.screenTime
        }).then(res=>{
            this.price = res.data.price,
            this.runtime = res.data.runtime,
            this.seatData = {
                title: this.$route.params.title,
                theater: this.$route.params.screenName,
                maxcol: res.data.numOfColumns,
                maxrow: res.data.numOfRows,
                selectedRow: res.data.rows,
                selectedCol: res.data.columns
            }
        })
    },
    computed:{
        seatTable(){
            var table = this.zeros([this.seatData.maxrow,this.seatData.maxcol])
            for (var key in this.seatData.selectedRow){
                table[this.seatData.selectedRow[key].charCodeAt(0)-65][this.seatData.selectedCol[key]] = 1
            }
            for (var seat in this.selectSeat){
                table[this.selectSeat[seat].row][this.selectSeat[seat].col] = 2
            }
            return table
        },
    },
    watch:{
        personNum:function(){
            this.selectSeat=[]
        }
    }
    
}
</script>

<style>
.selectSeatDiv{
    width: 100%;
    text-align: center;
}
.selectSeatTitle{
    background-color: #444444;
    border-radius: 5px;
    padding: 0.3em;
    color: #cecece;
    margin-bottom: 20px;
}
.seatTableRow button{
    width: 40px;
    height: 40px;
    margin: 10px;
}
#bookButton{
    margin-top: 50px;
    float: right;
}
</style>