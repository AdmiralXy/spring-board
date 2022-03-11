<template>
  <div class="row">
    <div class="col-lg-3 col-md-6 mb-3">
      <div class="card text-dark bg-light mb-3">
        <div class="card-header">Create new desk</div>
        <div class="card-body">
          <a-input v-model.trim="form.name">
            <a-icon slot="prefix" type="profile" />
          </a-input>
          <p class="card-text pt-3">
            <a-button type="primary" @click="store" :disabled="!form.name">
              Create
            </a-button>
          </p>
        </div>
      </div>
    </div>
    <div class="col-lg-3 col-md-6 mb-3" v-for="desk in desks" :key="desk.id">
      <div class="card card-pointer text-dark bg-light mb-3">
        <div class="card-header">{{ desk.name }}</div>
        <div class="card-body">
          <h5 class="card-title">Desk #{{ desk.id }}</h5>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

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
    this.fetchDesks()
  },
  computed: {
    ...mapGetters({
      loadingStatus: 'preloader/loadingStatus',
      desks: 'desk/desks'
    })
  },
  methods: {
    ...mapActions({
      fetchDesks: 'desk/fetchDesks',
      storeDesk: 'desk/storeDesk'
    }),
    store() {
      this.storeDesk(this.form).then(() => {
        this.fetchDesks()
        this.$notification.success({
          message: `'${this.form.name}' board created`,
        })
      }).catch(() => {
        this.$notification.warn({
          message: 'An error has occurred',
          description: 'Unable to create new board.',
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
