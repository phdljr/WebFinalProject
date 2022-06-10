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
                <div class="timeSelect" v-for="(theater, index) in movieDetail.theater" :key="index">
                    <div>▶ 2D | {{theater.name}} | 총 {{theater.totalSeat}}석</div>
                    <b-button
                        v-b-toggle.collapse-1
                        class="timeButton"
                        variant="outline-secondary"
                        v-for="(time, index) in theater.time"
                        :key="index">
                        {{time.time}}<br>
                        {{time.seat}}석
                    </b-button>
                    <b-collapse id="collapse-1" class="mt-2">
                        <b-card>
                            <p class="card-text">{{theater}}</p>
                        </b-card>
                    </b-collapse>
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
        if(this.$store.state.userData.isAdmin == true){
            return;
        }
        else{
            alert("잘못된 접근입니다.")
            this.$router.push("/")
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
#collapse-1{
    width: 100%;
    float: left;
}
</style>