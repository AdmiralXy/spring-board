<template>
  <div class="row">
    <h3 class="pb-5">{{ this.desk.name }}</h3>
    <div class="col-lg-3 col-md-6 mb-3">
      <div class="card text-dark bg-light mb-3">
        <div class="card-header">Create new task list</div>
        <div class="card-body">
          <a-input v-model.trim="form.name">
            <a-icon slot="prefix" type="profile" />
          </a-input>
          <p class="card-text pt-3">
            <a-button type="primary" @click="addTask(form.name)" :disabled="!form.name">
              Create
            </a-button>
          </p>
        </div>
      </div>
    </div>
    <div class="col-lg-3 col-md-6 mb-3" v-for="task in desk.tasks" :key="task.id">
      <div class="card text-dark bg-light mb-3">
        <div class="card-header d-flex justify-content-between">
          {{ task.name }}
          <a-icon type="delete" @click="removeTask(task.id)" />
        </div>
        <div class="card-body">
          <a-input-search placeholder="Enter a new point" v-model="point" @search="addPoint(task.id)">
            <a-button slot="enterButton" :disabled="!point">
              <a-icon type="caret-right" />
            </a-button>
          </a-input-search>
        </div>
        <div class="card-body card-list">
          <ul class="list-group">
            <li class="list-group-item d-flex justify-content-between" v-for="point in task.points">
              {{ point.content }}
              <a-button :type="point.status === 'ACTIVE' ? 'primary' : 'danger'" size="small" @click="togglePointStatus(task.id, point.id)">
                <a-icon type="check" v-if="point.status !== 'ACTIVE'" />
                <a-icon type="close" v-if="point.status === 'ACTIVE'" />
              </a-button>
            </li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'

export default {
  middleware: 'auth',
  data() {
    return {
      form: {
        name: null
      },
      point: ''
    }
  },
  mounted() {
    this.fetchDesk(this.$route.params.id)
  },
  computed: {
    ...mapGetters({
      desk: 'desk/desk'
    })
  },
  methods: {
    ...mapActions({
      fetchDesk: 'desk/fetchDesk',
      storeDesk: 'desk/storeDesk'
    }),
    ...mapMutations({
      addTaskToDesk: 'desk/ADD_TASK_TO_DESK',
      removeTaskFromDesk: 'desk/REMOVE_TASK_FROM_DESK',
      addPointToTask: 'desk/ADD_POINT_TO_TASK',
      updatePointStatus: 'desk/UPDATE_POINT_STATUS'
    }),
    addTask(name) {
      this.addTaskToDesk(name)
      this.fetchDesk(this.$route.params.id)
      this.store()
    },
    removeTask(id) {
      this.removeTaskFromDesk(id)
      this.store()
    },
    addPoint(id) {
      this.addPointToTask({id: id, point: this.point})
      this.store()
      this.point = ''
    },
    togglePointStatus(taskId, pointId) {
      this.updatePointStatus({taskId, pointId})
      this.store()
    },
    store() {
      this.storeDesk(this.desk).then(() => {
        this.fetchDesk(this.$route.params.id)
        this.$notification.success({
          message: `Task list updated`,
        })
      }).catch(() => {
        this.$notification.warn({
          message: 'An error has occurred',
          description: 'Unable to update your desk.',
        })
      })
    }
  }
}
</script>

<style scoped>
.card {
  min-height: 100%;
  display: block !important;
}

.card-list {
  display: block !important;
  flex: none !important;
  padding: 0 1rem 0 1rem !important;
}

</style>
