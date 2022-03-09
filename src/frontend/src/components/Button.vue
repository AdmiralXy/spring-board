<template>
  <b-button :variant="variant" :type="type" :disabled="disabled">
    <span v-if="!loading">
      <slot/>
    </span>
    <span v-else>
      <b-spinner small></b-spinner>
      <span class="ms-2">Loading...</span>
    </span>
  </b-button>
</template>

<script>
export default {
  name: 'ButtonComponent',

  props: {

    type: {
      type: String,
      default: 'submit'
    },

    variant: {
      type: String,
      default: 'primary'
    },

    loading: {
      type: Boolean,
      default: false
    },

    disabled: {
      type: Boolean,
      default: false
    },
  }
}
</script>
