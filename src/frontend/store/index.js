export const actions = {
  nuxtClientInit ({ commit, getters }) {
    const token = this.$cookies.get('token')
    if (token && !getters['auth/token']) {
      commit('auth/SET_TOKEN', token)
    }
  }
}
