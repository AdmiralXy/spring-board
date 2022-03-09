export default ({
    namespaced: true,
    state: {
        loading: false
    },
    getters: {
        loading: state => state.loading
    },
    mutations: {
        SET_LOADING (state, status) {
            state.loading = status
        },
    },
    actions: {
        setLoadingStatus ({ commit }, status) {
            commit('SET_LOADING', status)
        }
    }
})