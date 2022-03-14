// state
export const state = () => ({
  desks: [],
  desk: {}
})

// getters
export const getters = {
  desks: state => state.desks,
  desk: state => state.desk
}

// mutations
export const mutations = {
  SET_DESKS (state, data) {
    state.desks = data
  },
  SET_DESK (state, data) {
    state.desk = data
  },
  ADD_TASK_TO_DESK (state, task) {
    state.desk.tasks.push({name: task})
  },
  REMOVE_TASK_FROM_DESK (state, id) {
    state.desk.tasks.splice(state.desk.tasks.findIndex(function(i){
      return i.id === id;
    }), 1);
  },
  ADD_POINT_TO_TASK (state, payload) {
    let task = state.desk.tasks.find((i) => i.id === payload.id)
    task.points.push({content: payload.point, status: 'ACTIVE'})
  },
  UPDATE_POINT_STATUS (state, payload) {
    let task = state.desk.tasks.find((i) => i.id === payload.taskId)
    let point = task.points.find((i) => i.id === payload.pointId)
    point.status = point.status === 'ACTIVE' ? 'NOT_ACTIVE' : 'ACTIVE'
  },
  SORT_POINTS (state) {
    state.desk.tasks.forEach(task => {
      task.points.sort((a, b) => {
        return a.id - b.id
      })
    })
  }
}

// actions
export const actions = {
  async fetchDesks ({ commit }) {
    commit('preloader/SET_LOADING_STATUS', true, { root: true })
    try {
      const response = await this.$axios.get('/desks')
      commit('SET_DESKS', response.data)
    } catch (e) {
      commit('SET_DESKS', [])
    } finally {
      commit('preloader/SET_LOADING_STATUS', false, { root: true })
    }
  },
  async fetchDesk ({ commit }, payload) {
    const response = await this.$axios.get('/desks/' + payload).finally(() => {
      commit('preloader/SET_LOADING_STATUS', false, { root: true })
    })
    commit('SET_DESK', response.data)
    commit('SORT_POINTS')
  },
  async storeDesk ({ commit }, data) {
    return await this.$axios.post('/desks', data)
  },
  async updateDesk ({ commit }, { id, data }) {
    return await this.$axios.patch('/desks/' + id, data)
  },
  async deleteDesk ({ commit }, payload) {
    return await this.$axios.delete('/desks/' + payload)
  }
}
