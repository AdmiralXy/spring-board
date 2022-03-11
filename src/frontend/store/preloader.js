// state
export const state = () => ({
  loadingStatus: false
})

// getters
export const getters = {
  loadingStatus: state => state.loadingStatus,
}

// mutations
export const mutations = {
  SET_LOADING_STATUS (state, status) {
    state.loadingStatus = status
  }
}

// actions
export const actions = {
  setLoadingStatus ({ commit }, status) {
    commit('SET_LOADING_STATUS', status)
  },
}
