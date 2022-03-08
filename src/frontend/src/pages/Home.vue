<template>
  <el-row class="main-container" :justify="'center'" :align="'middle'">
    <div class="main-container__form">
      <p class="main-container__title">Spring Board Application</p>
      <el-form
          ref="form"
          :model="sizeForm"
          label-width="auto"
          :label-position="labelPosition"
          :size="size">
        <el-form-item label="Username">
          <el-input v-model="sizeForm.username"></el-input>
        </el-form-item>
        <el-form-item label="Password">
          <el-input v-model="sizeForm.password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary">Register</el-button>
          <el-button>Cancel</el-button>
        </el-form-item>
      </el-form>
    </div>
  </el-row>
</template>

<script>
import {reactive} from "vue";

export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  data: function () {
    return {
      sizeForm: reactive({
        username: '',
        password: ''
      })
    }
  },
  methods: {}
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.main-container {
  height: 80vh;
}

.main-container__title {
  font-size: 24px;
  color: #56a37f;;
  margin-bottom: 50px;
}

.main-container__form {
  border: 1px solid #A4A4A4;
  padding: 2rem;
  -webkit-box-shadow: 2px 2px 10px -3px rgba(0,0,0,0.27);
  box-shadow: 2px 2px 10px -3px rgba(0,0,0,0.27);
  border-radius: 6px;
}
</style>
