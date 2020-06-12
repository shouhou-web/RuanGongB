const mutations = {
    login(state, info) {
        console.log(info)
        state.token = true
        state.user = info
    },
    logout(state) {
        state.token = false
        state.user = {}
    },
    editInfo(state, res) {
        console.log('修改的个人信息', res)
        state.user.imagePath = res.imagePath;
        state.user.userName = res.userName;
        state.user.userWord = res.userWord;
        state.user.sex = res.sex;
        sessionStorage.clear();
        sessionStorage.setItem("user", JSON.stringify(state.user));
    },
    showLoading(state) {
        state.fullscreenLoading = true;
    },
    hideLoading(state) {
        state.fullscreenLoading = false;
    },
    setSearchKey(state,key) {
        sessionStorage.setItem('key',key)
        state.key = key;
    },
    setSearchPost(state,searchPost) {
        sessionStorage.setItem('searchPost',searchPost)
        state.searchPost = searchPost;
    },
    setProfileClick(state,profileClick) {
        console.log('setprofileclick')
        sessionStorage.setItem('profileClick',profileClick)
        state.profileClick = profileClick;
    },
    setHomeClick(state,homeClick) {
        sessionStorage.setItem('homeClick',homeClick)
        state.homeClick = homeClick;
    }

}

export default mutations

