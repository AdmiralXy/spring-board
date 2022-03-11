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
    state.token = null
  },
}

// actions
export const actions = {
  saveToken ({ commit }, { token, remember }) {
    commit('SET_TOKEN', token)

    this.$cookies.set('Bearer', token, {
      maxAge: remember ? 3600000 : 3600
    })
    this.$axios.setToken(token, 'Bearer')
  },
  logout ({ commit }) {
    commit('LOGOUT')
    this.$axios.setToken(false)
    this.$cookies.remove('Bearer')
  }
}
