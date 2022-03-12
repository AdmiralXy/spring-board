import {notification} from 'ant-design-vue'

export default function ({ $axios, store, redirect }) {
  $axios.onError(error => {
    if ((error.response.status === 401) && store.getters['auth/token']) {
      notification.warn({
        message: 'Not authorized',
        description: 'Please log in.',
      })
      store.dispatch('auth/logout')
      redirect({ name: 'login' })
    } else if (error.response.status > 500) {
      notification.error({
        message: 'Internal Server Error',
        description: 'Please, contact the service administrator.',
      })
    } else if (error.response.status > 500) {
      notification.error({
        message: 'Internal Server Error',
        description: 'Please, contact the service administrator.',
      })
    }
  })
}
