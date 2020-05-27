import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
// import actions from './actions'
// import getters from './getters'

Vue.use(Vuex)

const state = {
    token: true,
    user: {
        userID: 10000,
        userName: '守候',
        userPassword: '123456',
        userSex: 0,
        imagePath:'https://img-static.mihoyo.com/communityweb/upload/57b90e40dc9dc200b5edd78dee9c2229.png',
        experience: 100,
        userLevel: 9,
        userIdentity: true,
        userState: true
    }
}

const store = new Vuex.Store({
    state,
    mutations
})

export default store
