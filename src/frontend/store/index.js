export const actions = {
  nuxtClientInit ({ commit, getters }) {
    const token = this.$cookies.get('Bearer')
    if (token && !getters['auth/token']) {
      this.$axios.setToken(token, 'Bearer')
      commit('auth/SET_TOKEN', token)
    }
  }
}
