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
    editInfo(state, imagePath, userName, userWord, userSex) {
        state.user.imagePath = imagePath;
        state.user.userName = userName;
        state.user.userWord = userWord;
        state.user.userSex = userSex;
    }
}

export default mutations

