<template>
    <b-col style="width: 480px;height: 480px;float: left;">
        <canvas id="sexChart"></canvas>
    </b-col>
    <b-col style="width: 480px;height: 480px;float: left;">
        <canvas id="ageChart"></canvas>
    </b-col>
</template>

<script>
import { Chart, registerables } from 'chart.js';
Chart.register(...registerables);

export default {
    name:'movieDetailChart',
    props:["sexData", "ageData"],
    data() {
        return {
            
        }
    },
    methods: {
        sexChart(){
            const ctx = document.getElementById("sexChart")
            console.log(ctx)
            const sex = new Chart(ctx, {
                type: 'pie',
                data: {
                    labels: ["남성", "여성"],
                    datasets: [{
                        label: "Population (millions)",
                        backgroundColor: ["#00bfcd ", "#c6567c"],
                        data: this.dataPercent(this.sexData),
                    }]
                },
                options: {
                    responsive: true,
                    plugins: {
                        legend: {
                            position: 'top',
                        },
                        title: {
                            display: true,
                            text: '성별 예매 분포(%)'
                        },
                    },
                }
            })
            sex
        },
        ageChart(){
            const ctx = document.getElementById("ageChart")
            console.log(ctx)
            const sex = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ["10대 미만", "10대", "20대", "30대", "40대", "50대", "60대 이상"],
                    datasets: [{
                        backgroundColor: this.setColor(this.ageData),
                        data: this.dataPercent(this.ageData),
                    }]
                },
                options: {
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            display: false
                        },
                        title: {
                            display: true,
                            text: '연령별 예매 분포(%)'
                        },
                    }                    
                }
            })
            sex
        },
        dataPercent(data){
            var sum = 0;
            var arr = [];
            for(var key in data){
                sum += data[key]
            }
            for(var index in data){
                arr.push(data[index]*100/sum)
            }
            return arr;
        },
        setColor(data){
            let ageArr = [ "#8e8e8e", "#8e8e8e", "#8e8e8e", "#8e8e8e", "#8e8e8e", "#8e8e8e", "#8e8e8e"]
            let maxIndex = data.indexOf(Math.max(...data))
            ageArr[maxIndex] = "#ee7f00"

            return ageArr;
        }
    },
    watch:{
        sexData:function(){
            this.sexChart()
        },
        ageData:function(){
            this.ageChart()
        }
    }
}
</script>

<style>
</style>