<template>
    <div>
        <div class="dateSelect">
            <b-button variant="outline-secondary" class="date">
                <div class="month">
                    06월<br>월
                </div>
                <div class="day">
                    13
                </div>
            </b-button>
            <b-button variant="outline-secondary" class="date">
                <div class="month">
                    06월<br>화
                </div>
                <div class="day">
                    14
                </div>
            </b-button>
        </div>
        <div class="movieSelect">
            <div class="movieTitle">
                <div style="float:left">({{movieDetail.age}}) {{movieDetail.title}}</div>
                <div style="float:left;margin-left:1em;color: #a0a0a0;">{{movieDetail.genre}} / {{movieDetail.openingDate}}</div>
                <div class="timeSelect" v-for="(theater, key) in movieDetail.theater" :key="key">
                    <div>▶ 2D | {{theater.name}} | 총 {{theater.totalSeat}}석</div>
                    <div class="saleButtons" v-for="(time, index) in theater.time" :key="index">
                        <b-button
                            v-b-toggle="['collapse-'+key+'-'+index]"
                            class="timeButton"
                            variant="outline-secondary"
                            >
                            {{time.time}}<br>
                            {{time.seat}}석<br>
                        </b-button>
                        <b-collapse :id="'collapse-'+key+'-'+index" class="mt-2">
                            <b-card id="collapseCard">
                                <b-form @submit="applySale(key,index)">
                                    <b-form-radio-group
                                        id="radio-group-1"
                                        v-model="saleData[key][index]['discount']"
                                        :options="saleOptions"
                                        name="radio-options"
                                        required
                                    ></b-form-radio-group>
                                    <b-form-group
                                        id="input-group-1"
                                        label-for="input-1"
                                    >
                                        <b-form-input
                                        id="input-1"
                                        type="number"
                                        v-model="saleData[key][index]['number']"
                                        required
                                        ></b-form-input>
                                        <b-button type="submit">
                                            적용
                                        </b-button>
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
export default {
    data() {
        return {
            movieDetail: {
                title: "쥬라기 월드-도미니언",
                genre: "범죄, 액션",
                age: "15세 이상",
                openingDate: "2022.05.18",
                theater:[
                    {
                        name:"1관",
                        totalSeat:80,
                        time:[
                            {
                                time:"12:00",
                                seat:30
                            },
                            {
                                time:"20:30",
                                seat:40
                            }
                        ]
                    },
                    {
                        name:"2관",
                        totalSeat:80,
                        time:[
                            {
                                time:"18:30",
                                seat:50
                            },
                        ]
                    }
                ]
            },
            saleOptions:[
                {text:"정률할인",value:"rate"},
                {text:"정액할인",value:"amount"}
            ],
            saleData:[]
        }
    },
    methods:{
        makeSaleData(){
            //이거 axios로 값 가져온뒤에 실행해야함
            for(var key in this.movieDetail.theater){
                var arr = []
                for(var index in this.movieDetail.theater[key].time){
                    this.movieDetail.theater[key].time[index]
                    var sale = {discount:'', number:0}
                    arr.push(sale)
                }
                this.saleData.push(arr);
            }
        },
        applySale(a, b){
            console.log(this.saleData[a][b])
            console.log(this.movieDetail.theater[a].name)//관 이름
            console.log(this.movieDetail.theater[a].time[b].time)//시간
        }
    },
    computed:{
        totalSeat(){
            var total = 0
            for(var temp in this.movieDetail.seat){
                total += parseInt(this.movieDetail.seat[temp]);
            }
            return total
        }
    },
    created(){
        this.makeSaleData()//이거 axios로 값 가져온뒤에 실행해야함
        if(this.$store.state.userData.isAdmin == true){
            return;
        }
        else{
            //alert("잘못된 접근입니다.")
            //this.$router.push("/")
        }
    }
}
</script>

<style>
.dateSelect{
    width: 100%;
    text-align: left;
    border-bottom: 1px solid #333333;
}
.date{
    margin: 10px;
}
.month{
    float: left;
    font-size: 1em;
    text-align: center;
}
.day{
    float: left;
    font-size: 2em;
}
.movieSelect{
    width: 100%;
    text-align: left;
}
.timeSelect{
    width: 100%;
    float:left;
    padding:3em
}
.timeButton{
    float: left;
    margin-top: 10px;
    margin-right: 1em;
}
.movieTitle{
    padding-top: 20px;
    border-bottom: none;
}
.saleButtons div{
    width: 100%;
    float: left;
}
.saleButtons button{
    margin-top: 10px;
}
</style>