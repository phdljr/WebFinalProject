<template>
  <div class="navbar">
    <nav class="navbar navbar-expand-md navbar-dark fixed-top bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" @click="$router.push('/')">CGV</a>
        <button
          class="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarCollapse"
          aria-controls="navbarCollapse"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav me-auto mb-2 mb-md-0">
            <li class="nav-item">
              <a class="nav-link" @click="$router.push('/movieview')">영화</a>
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="$router.push('/theaterview')">극장</a>
            </li>
          </ul>
        </div>
        <div
          class="collapse navbar-collapse"
          id="navbarCollapse"
          v-if="isLogin"
        >
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li v-if="$store.state.userData.isAdmin==true" class="nav-item">
              <a class="nav-link" @click="$router.push('/adminview')"
                >관리자 페이지</a
              >
            </li>
            <li v-if="$store.state.userData.isAdmin==false" class="nav-item">
              <a class="nav-link" @click="$router.push('/mypageview')"
                >마이페이지</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="logout">로그아웃</a>
            </li>
          </ul>
        </div>
        <div v-else class="collapse navbar-collapse" id="navbarCollapse">
          <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <a class="nav-link" @click="$router.push('/registerview')"
                >회원가입</a
              >
            </li>
            <li class="nav-item">
              <a class="nav-link" @click="$router.push('/loginview')">로그인</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "navbarVue",
  methods: {
    logout() {
      this.$store.commit("logout");
      this.$router.push("/");
    },
  },
  computed: {
    isLogin() {
      console.log(this.$store.state.login);
      return this.$store.state.login;
    },
  },
};
</script>

<style>
.navbar {
  height: 3.5em;
}
</style>
