<template>
  <navbar-vue></navbar-vue>
  <div class="content">
    <router-view></router-view>
  </div>
</template>

<script>
import navbarVue from "./components/Navbar.vue";

export default {
  name: 'App',
  components:{
    navbarVue,
  },
}
</script>


<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  display: flex;
  justify-content: center
}

.content{
  margin-top: 7em;
  width: 980px;
}
</style>
