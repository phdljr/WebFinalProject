<template>
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
                    class="timeButton"
                    variant="outline-secondary"
                    v-for="(time, index) in theater.time"
                    :key="index"
                    @click="$router.push('/ticket?movie='+movieDetail.title+'&theater=' + theater.name + '&time=' + time.time)">
                    {{time.time}}<br>
                    {{time.seat}}석
                </b-button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            movieDetail: {
                title: "title",
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
</style>