<template>
  <div id="Test">
    <quill-editor ref="myTextEditor"
              v-model="content" :options="quillOption">
    </quill-editor>
  </div>
</template>

<script>
import { quillEditor } from 'vue-quill-editor'
import quillConfig from './quill-config.js'

export default {
  components: {
    quillEditor
  },
  data () {
    return {
      content: '<h2>hello quill-editor</h2>',
      quillOption: quillConfig,
    }
  }
}
</script>

<style>

</style>