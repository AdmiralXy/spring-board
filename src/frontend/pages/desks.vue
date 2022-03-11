<template>
  <div style="padding: 20px;">
    <a-row :gutter="16">
      <a-col :span="4" v-for="desk in desks" :key="desk.id">
        <a-card :title="desk.name" :bordered="false">
          Open
          <a-icon type="caret-right" />
        </a-card>
      </a-col>
      <a-col :span="4" :hidden="loadingStatus">
        <a-card title="Create new desk" :bordered="false" @click="store">
          <div class="desk__new">
            <a-icon type="plus-circle" />
          </div>
        </a-card>
      </a-col>
    </a-row>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  middleware: 'auth',
  data() {
    return {
      form: {
        username: null,
        password: null
      },
      remember: true,
      error: false
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
      this.storeDesk({name: 'name'}).then((response) => {
        this.fetchDesks()
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

<style>
.desk__new {
  font-size: 21px;
}

.ant-card-head-title, .ant-card-body {
  color: #fff;
}

.ant-card {
  background-color: #6d97ef;
}

.desk__new {
  display: flex;
  justify-content: center;
}
</style>
