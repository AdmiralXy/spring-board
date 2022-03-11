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
        <div class="card-body" v-for="point in task.points">
          <h5 class="card-title">{{ point.content }}</h5>
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
      }
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
      removeTaskFromDesk: 'desk/REMOVE_TASK_FROM_DESK'
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
    store() {
      this.storeDesk(this.desk).then(() => {
        this.fetchDesk(this.$route.params.id)
        this.$notification.success({
          message: `Task list created/updated`,
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
}

.card-pointer {
  cursor: pointer;
  transition-duration: 0.2s;
}

.card-pointer:hover {
  transform: scale(1.05);
  transition-duration: 0.2s;
}
</style>
