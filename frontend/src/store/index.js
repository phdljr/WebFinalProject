import { createStore } from "vuex";
import createPersistedState from "vuex-persistedstate";

export default createStore({
  state: {
    login: false,
    userData: {
      id: null,
      age: null,
      gender: null,
      reservations: [],
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
        gender: null,
        reservations: [],
      };
    },
  },
  actions: {},
  modules: {},
  plugins: [createPersistedState()], //vuex 데이터 지속
});
