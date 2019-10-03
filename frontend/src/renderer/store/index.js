import Vue from 'vue'
import Vuex from 'vuex'
import * as actions from './actions'
import * as getters from './getters'

import user from './modules/user'

Vue.use(Vuex);

const debug = process.env.NODE_ENV !== 'production';

export default new Vuex.Store({
    state:{
        //存储toKen
        Authorization:localStorage.getItem('Authorization')?localStorage.getItem('Authorization'):''
    },
    mutations:{
        //修改token,并将token存入localStorage
        changeLogin(state,user){
            state.Authorization = user.Authorization;
            localStorage.setItem('Authorization', user.Authorization);
        }
    }
  actions,
  getters,
  modules: {
    user,
  },
  strict: debug
})