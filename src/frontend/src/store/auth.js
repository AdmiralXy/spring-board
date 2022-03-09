//import axios from 'axios'
import Cookies from 'js-cookie'

const remember = 3600000

export default ({
    namespaced: true,
    state: {
        user: null,
        token: null
    },
    getters: {
        user: state => state.user,
        token: state => state.token,
        check: state => state.token !== null
    },
    mutations: {
        SET_TOKEN (state, token) {
            state.token = token
        },

        FETCH_USER_SUCCESS (state, user) {
            state.user = user
        },

        FETCH_USER_FAILURE (state) {
            state.token = null
        },

        LOGOUT (state) {
            state.user = null
            state.token = null
        },

        UPDATE_USER (state, { user }) {
            state.user = user
        }
    },
    actions: {
        saveToken ({ commit }, token) {
            commit('SET_TOKEN', token)
            Cookies.set('token', token, { expires: remember })
        },
        async logout ({ commit }) {
            Cookies.remove('token')
            commit('LOGOUT')
        },
    }
})