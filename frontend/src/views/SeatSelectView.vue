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
                CGV 구미 | {{seatData.theater}} | 남은좌석 {{seatData.maxcol*seatData.maxrow - seatData.selectedCol.length}} / {{seatData.maxcol*seatData.maxrow}}
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
                <b-button id="bookButton">예매하기</b-button>
            </b-col>
        </b-row>
    <div>
        <h1>좌석 선택하셈</h1>
    </div>
</template>

<script>
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
            seatData:{
                title:"영화이름임",
                theater:"1관",
                maxcol:8,
                maxrow:10,
                selectedRow:['A','C','E'],
                selectedCol:[3,7,5]
            },
            selectSeat:[]
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
                this.selectSeat.push({row,col})
            }
        }
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