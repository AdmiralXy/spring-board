export default function ({ $axios, redirect }) {
  $axios.onError(error => {
    if (error.response.status === 401 && $axios.getToken()) {
      redirect('/')
    } else if (error.response.status >= 500) {
      console.log("ERROR 500!!!")
    }
  })
}
