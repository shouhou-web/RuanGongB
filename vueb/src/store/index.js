import Vue from 'vue'
import Vuex from 'vuex'

import mutations from './mutations'
// import actions from './actions'
// import getters from './getters'

Vue.use(Vuex)

const state = {
    token: false,
    fullscreenLoading: false,
    key: '',
    user: {
        // userID: 10002,
        // userName: '守候',
        // userPassword: '123456',
        // userSex: 0,
        // userWord: '系统原装签名，送给每一位小可爱~',
        // imagePath: 'https://img-static.mihoyo.com/communityweb/upload/57b90e40dc9dc200b5edd78dee9c2229.png',
        // experience: 100,
        // userLevel: 9,
        // experience: 300,
        // userIdentity: 1,//0普通，1管理员
        // userState: 0, //0正常，1被禁
        // isNew: 0 //0正常，1还没看新手上路
    },
    searchPost: [],
    profileClick: 0,
    homeClick : 1
}

const store = new Vuex.Store({
    state,
    mutations
})

export default store
