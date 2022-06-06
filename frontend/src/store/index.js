import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    login: false,
    userData: {
      userId: "test",
      userGender: null,
      userAge: null,
      bookingMovie: [],
    },
  },
  getters: {},
  mutations: {
    login(state, userData) {
      state.login = true;
      state.userData = userData;
    },
    logout(state) {
      state.login = false;
      state.userData = {
        userId: null,
        userGender: null,
        userAge: null,
        bookingMovie: [],
      };
    },
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()], //vuex 데이터 지속
});
