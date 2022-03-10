<template>
  <div>
    <p>Hello world!</p>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex';

export default {
  middleware: 'auth',
}
</script>

<style>

</style>
