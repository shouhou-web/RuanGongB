const mutations = {
    login(state,info) {
        console.log(info)
        state.token = true,
        state.user = info
    },
    logout(state) {
        state.token = false,
        state.user = {}
    }
}

export default mutations

