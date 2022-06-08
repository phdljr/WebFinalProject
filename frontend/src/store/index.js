import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    login: false,
    userData: {
      id: null,
      age: null,
      gender: null
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
        id: null,
        age: null,
        gender: null
      };
    },
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()], //vuex 데이터 지속
});
