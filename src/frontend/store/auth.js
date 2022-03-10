// state
export const state = () => ({
  token: null
})

// getters
export const getters = {
  token: state => state.token,
}

// mutations
export const mutations = {
  SET_TOKEN (state, token) {
    state.token = token
  },

  LOGOUT (state) {
    state.user = null
    state.token = null
  },
}

// actions
export const actions = {
  saveToken ({ commit, dispatch }, { token, remember }) {
    commit('SET_TOKEN', token)

    this.$cookies.set('Bearer', token, {
      path: '/',
      maxAge: remember ? 3600000 : 3600
    })
  }
}
