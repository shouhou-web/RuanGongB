import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
// import actions from './actions'
// import getters from './getters'

Vue.use(Vuex)

const state = {
    token: true,
    user:{}
}

const store = new Vuex.Store({
    state,
    mutations
})

export default store
