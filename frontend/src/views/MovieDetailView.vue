<template>
  {{$route.params.movie}}
  <div class="MovieDetail">
    <img :src="movieDetail.img">
    <div class="movieContent">
      <h1>{{movieDetail.name}}</h1>
      <h7>{{movieDetail.nameEng}}</h7>
      <div class="score">예매율: 모름</div>
      <h7>감독:{{movieDetail.director}} / 배우:{{movieDetail.actors}}</h7><br>
      <h7>asdf</h7><br>
      <h7>asdf</h7><br>
      <b-button
          ref="cancel"
          variant="outline-danger"
          size="sm"
          aria-describedby="cancel-label"
          @click="$router.push('#')"
      >
          예매하기
      </b-button>
    </div>
  </div>
  <div class="movieGraph">
    <div></div>
  </div>
  <div class="comment">

  </div>
</template>

<script>
export default {
  data() {
    return {
      movieDetail:{
        name:"영화 제목",
        nameEng:"name",
        img:"https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85813/85813_320.jpg",
        director:"감독님",
        actors:["배우1","배우2"],
        genre:"범죄, 액션",
        age:"15세 이상",
        openingDate:"2022.05.18"
      }
    }
  },
}
</script>

<style>
.MovieDetail{
  width: 100%;
  text-align: left;
}
.MovieDetail img{
  width: 180px;
  float: left;
  margin-right: 20px;
}
.MovieDetail h5{
  margin-bottom: 1em;
}
.score{
  width: 780px;
  border-bottom: 1px solid #333333;
  margin-bottom: 1em;
}
.movieContent{
  float: left;
  width: 780px;
}
</style>