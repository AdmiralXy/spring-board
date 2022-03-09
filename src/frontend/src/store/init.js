import Cookies from 'js-cookie'

export default ({
    namespaced: true,
    actions: {
        clientInit({commit, rootGetters}) {
            const token = Cookies.get('token')
            if (token && !rootGetters['token'])
                commit('auth/SET_TOKEN', token, {root: true})
        }
    }
})