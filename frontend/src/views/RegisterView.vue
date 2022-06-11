<template>
  <div class="signUpBg">
    <b-form class="signUp" @submit="submitForm">
      <div class="inputBox">
        <b-button id="checkIdButton" variant="light" @click="checkId"
          >중복 확인</b-button
        >
        <b-form-input
          v-model="registerData.id"
          placeholder="아이디를 입력해 주세요."
          required
          :state="validId"
        />
        <b-form-invalid-feedback :state="validId" v-if="idChecked">
          이미 사용중인 아이디 입니다.
        </b-form-invalid-feedback>
        <b-form-valid-feedback :state="validId">
          사용 가능한 아이디 입니다.
        </b-form-valid-feedback>
      </div>
      <div class="inputBox">
        <b-form-input
          type="password"
          id="password"
          v-model="registerData.pw"
          placeholder="비밀번호를 입력해 주세요."
          required
          :state="validPw"
        />
        <b-form-invalid-feedback :state="validPw">
          비밀번호는 8글자 이상이여야 합니다.
        </b-form-invalid-feedback>
        <b-form-valid-feedback :state="validPw" />
      </div>
      <div class="inputBox">
        <b-form-input
          type="password"
          id="passwordCheck"
          v-model="passwordCheck"
          placeholder="비밀번호를 재입력해 주세요."
          required
          :state="checkPw"
        />
        <b-form-invalid-feedback :state="checkPw">
          비밀번호가 일치하지 않습니다.
        </b-form-invalid-feedback>
        <b-form-valid-feedback :state="checkPw" />
      </div>
      <div class="radioGroup">
        <b-form-group label="성별" v-slot="{ ariaDescribedby }">
          <b-form-radio-group
            v-model="registerData.gender"
            :options="genderOptions"
            :aria-describedby="ariaDescribedby"
            name="gender"
            required
          ></b-form-radio-group>
        </b-form-group>
      </div>
      <div class="inputBox">
        <b-form-input
          type="number"
          id="age"
          v-model="registerData.age"
          placeholder="나이를 입력해 주세요."
          required
        />
      </div>
      <b-form-checkbox
        id="checkbox-1"
        v-model="checkAccept"
        name="checkbox-1"
        value="true"
        unchecked-value="false"
        required
      >
        약관을 모두 읽고 동의하였습니다.
      </b-form-checkbox>
      <b-button id="registerButton" type="submit"> 회원가입 </b-button>
    </b-form>

    <div class="Login">
      계정이 있으신가요?
      <a href="/login">로그인</a>
    </div>
  </div>
</template>
<script>
import axios from "axios";

export default {
  components: {},
  data() {
    return {
      registerData: {
        id: "",
        pw: "",
        gender: "",
        age: "",
      },
      genderOptions: [
        { text: "남자", value: "남자" },
        { text: "여자", value: "여자" },
      ],
      passwordCheck: "",
      validId: null,
      checkAccept: null,
      idChecked: null,
    };
  },
  methods: {
    checkId() {
      this.idChecked = true;

      axios
        .get(this.HOST + "/duplicated/" + this.registerData.id)
        .then((res) => {
          console.log(res);
          if (res.data == "Success") {
            this.validId = true;
          } else {
            // 중복이라면 "Duplicated"
            this.validId = false;
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    submitForm() {
      // 아이디 중복 체크
      if (this.vailidId == false) {
        alert("아이디를 다시 확인해주세요.");
        return;
      }
      
      // 비밀번호 자릿수 체크
      if(this.registerData.pw.length <= 7){
        alert("비밀번호를 다시 확인해주세요.")
        return;
      }
      console.log(this.registerData);

      axios
        .post(this.HOST + "/register", this.registerData)
        .then(() => {
          alert("회원가입이 정상적으로 이루어졌습니다!");
          //로그인 창으로 라우팅
          this.$router.push("login");
        })
        .catch((err) => {
          alert(err);
        });
    },
  },
  computed: {
    checkPw() {
      if (this.registerData.pw == "") {
        return null;
      }
      return this.registerData.pw == this.passwordCheck;
    },
    validPw() {
      if (this.passwordCheck == "") {
        return null;
      }
      return this.registerData.pw.length > 7;
    },
  },
};
</script>

<style>
#submit {
  text-align: left;
}
#submit label {
  display: inline-block;
  width: 100px;
}
body {
  margin: 0;
}
div {
  box-sizing: border-box;
}
.signUpBg {
  width: 768px;
  background-color: rgb(245, 245, 245);
  border: 1px solid rgb(180, 180, 180);
  margin: auto;
  border-radius: 8px;
  margin-top: 0px;
}
.signUp {
  text-align: left;
  width: 400px;
  margin: 0 auto;
}
#loginButton {
  width: 100%;
  margin-top: 15px;
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
  padding: 1em;
}
.inputBox input {
  width: 70%;
  border: none;
  background: transparent;
}
.inputBox button {
  width: 27%;
  float: right;
  font-size: 0.75em;
}
.Login {
  padding: 20px;
  margin-top: 20px;
  border-top: solid rgb(190, 190, 190);
  border-width: 0.1px;
}
.form-check {
  margin-top: 20px;
  margin-bottom: 20px;
}
</style>
