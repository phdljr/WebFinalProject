<template>
  <div class="movies">
    <h2>무비차트</h2>
    <div class="MovieList">
      <div class="sortSelect">
        <b-form-select v-model="sortSelected" :options="sortOptions"></b-form-select>
      </div>
      <b-row :key="key" v-for="(movieRow, key) in chunkMovies" cols="4">
        <b-col :key="key" v-for="(movie, key) in movieRow">
          <div class="movieListRank">
            No. {{movie.rank}}
          </div>
          <router-link :to="'/movie/'+movie.title"><img src="https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85833/85833_320.jpg"></router-link>
          <div class="movieListDetail">
            <p>{{movie.title}}</p>
            <p>예매율 ??%</p>
            <b-button variant="outline-danger" @click="$router.push('/ticket?movie='+movie.title)">예매하기</b-button>
          </div>
        </b-col>
      </b-row>
    </div>
  </div>
</template>

<script>
//import axios from 'axios'
var chunk = require('chunk')

export default {
  name: 'MovieView',
  components: {
  },
  data() {
    return {
      movies:[
        {
          rank:1,
          title:"title"
        },
        {
          rank:2,
          title:"몰루"
        },
        {
          rank:3,
          title:"몰루"
        },
        {
          rank:4,
          title:"몰루"
        },
        {
          rank:5,
          title:"몰루"
        },
        {
          rank:6,
          title:"몰루"
        },
        {
          rank:7,
          title:"몰루"
        },
      ],
      sortSelected: this.$route.query.sort,
      sortOptions: [
        { value: "book", text: '예매율' },
        { value: 'rate', text: '평점' }
      ]
    }
  },
  mounted(){
    console.log(this.$route.query.sort)
    if(this.$route.query.sort == null){
      this.$router.push('movie?sort=book')
    }
  },
  computed:{
    chunkMovies(){
      return chunk(this.movies, 4)
    }
  },
  watch:{
    sortSelected:function(val){
      this.$router.push('movie?sort='+val)
    }
  }
}
</script>

<style>
.movies{
  text-align: left;
}
.MovieList{
  border-top: 0.3ch solid;
}
.MovieList img{
  width: 100%;
}
.movieListDetail{
  margin-bottom: 30px;
}
.movieListRank{
  background: #fb4357;
  margin-bottom: 4px;
  color: white;
  text-align: center;
}
.sortSelect{
  margin-bottom: 30px;
  margin-top: 15px;
  display: flex;
  justify-content: right;
}
.sortSelect select{
  width: 7em;
}
</style>
