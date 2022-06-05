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
                        backgroundColor: ["#8e8e8e ", "#c6567c"],
                        data: this.sexData
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
                            text: '성별 예매 분포'
                        }
                    }
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
                    labels: ["10대", "20대", "30대", "40대", "50대"],
                    datasets: [{
                        backgroundColor: "#8e8e8e ",
                        data: this.ageData
                    }]
                },
                options: {
                    maintainAspectRatio: false,
                    plugins: {
                        legend: {
                            display: false
                        },
                    }                    
                }
            })
            sex
        }
    },
    mounted() {
        this.sexChart()
        this.ageChart()
    },
}
</script>

<style>
</style>