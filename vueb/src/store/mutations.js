const mutations = {
    login(state,info) {
        console.log(info)
        state.token = true,
        state.user = info
    },
    logout(state) {
        state.token = false,
        state.user = {}
    },
    editInfo(state, res) {
        console.log('修改的个人信息',res)
        state.user.imagePath = res.imagePath;
        state.user.userName = res.userName;
        state.user.userWord = res.userWord;
        state.user.userSex = res.userSex;
    },
    showLoading(state) {
        state.fullscreenLoading = true;
    },
    hideLoading(state) {
        state.fullscreenLoading = false;
    }
}

export default mutations

