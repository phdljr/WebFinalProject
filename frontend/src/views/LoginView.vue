<template>
  <div class="loginBg">
    <div class="img">
      <img src="../assets/logo.png" width="100px" />
    </div>

    <b-form class="login" @submit="login">
      <div class="inputBox">
        아이디
        <b-form-input type="text" v-model="id" />
      </div>
      <div class="inputBox">
        비밀번호
        <b-form-input type="password" v-model="pw" />
      </div>
      <b-button id="loginButton" type="submit"> 로그인 </b-button>
    </b-form>
    <div class="signIn">
      계정이 없으신가요?
      <a @click="$router.push('register')">회원가입</a>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  name: "LoginView",
  components: {},
  data() {
    return {
      id: "",
      pw: "",
    };
  },
  methods: {
    login() {
      let data = {
        id: this.id,
        pw: this.pw,
      };

      // 관리자 정보로 로그인할 경우
      if (data.id === "admin" || data.pw === "admin") {
        this.$store.commit("login", {
                  id: null,
                  age: null,
                  gender: null,
                  isAdmin: true
                });
        this.$router.push('/admin');
        return;
      }

      if (data.id === "" || data.pw === "") {
        alert("정보를 입력해 주세요.");
        return;
      }

      axios
        .post(this.HOST + "/login", data)
        .then((res) => {
          if (res.data == "Fail") {
            alert("다시 입력하세요.");
          }
          // Success가 온다면
          else if (res.data == "Success") {
            axios
              .post(this.HOST + "/loginSuccess", { param: this.id })
              .then((res) => {
                console.log(res);
                this.$store.commit("login", {
                  id: this.id,
                  age: res.data.age,
                  gender: res.data.gender,
                });

                // 이전 페이지로 이동시키게 설정
                if(this.$route.params.state == "wasBooking"){
                  this.$router.push('/ticket?movie='+this.$route.params.title+'&theater=' + this.$route.params.theater + '&time=' + this.$route.params.time)
                }
                else{
                  this.$router.push("/");
                }
              })
              .catch((err) => {
                console.log(err);
              });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
};
</script>
<style>
div {
  box-sizing: border-box;
}
.loginBg {
  width: 768px;
  background-color: rgb(245, 245, 245);
  border: 1px solid rgb(180, 180, 180);
  margin: auto;
  border-radius: 8px;
  margin-top: 0px;
  margin-bottom: 30px;
}
.login {
  text-align: left;
  width: 250px;
  margin: 0 auto;
  margin-top: 10px;
}
.inputBox {
  text-align: left;
  width: 100%;
  margin: 0 auto;
  background: rgba(223, 223, 223, 30%);
  border: rgba(80, 80, 80, 100%);
  border-radius: 6px;
  border-width: 10px;
  margin-top: 10px;
  padding: 7px;
}
.inputBox input {
  width: 100%;
  border: none;
  background: transparent;
}
#loginButton {
  width: 100%;
  margin-top: 15px;
}
.signIn {
  padding: 20px;
  margin-top: 20px;
  border-top: solid rgb(190, 190, 190);
  border-width: 0.1px;
}
</style>
