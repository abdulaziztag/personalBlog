<template>
  <v-list-item two-line>
    <v-list-item-content>
      <v-list-item-title>{{ post.title }}</v-list-item-title>
      <v-list-item-subtitle v-html="post.postBody" class="text-truncate" style="height: 20px"></v-list-item-subtitle>
    </v-list-item-content>
    <v-list-item-action class="d-flex flex-row">
      <EditForm :index="post.id" @addToUI="editPost"/>
      <v-btn color="error" @click.stop="dialog = true" icon fab small>
        <v-icon small>fas fa-trash</v-icon>
      </v-btn>
    </v-list-item-action>
    <v-dialog persistent dark v-model="dialog">
      <v-card class="pa-4">
        <v-card-subtitle>Are you sure to delete this item?</v-card-subtitle>
        <v-card-actions class="d-flex justify-end">
          <v-btn small text color="primary" @click="dialog = false">Cancel</v-btn>
          <v-btn small text color="error" @click="deletePost">Delete</v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
  </v-list-item>
</template>

<script>
import EditForm from './EditForm';
export default {
  name: "ListEl",
  components: {EditForm},
  props: ['post'],
  data() {
    return {
      dialog: false,
      editDialog: false
    }
  },
  methods: {
    async deletePost() {
      await fetch(`http://localhost:8000/list/${this.post.id}`, {
        method: 'DELETE'
      })
      this.$emit('deletePost', this.post.id)
      this.dialog = false
    },
    editPost({title, postBody}) {
      this.post.title = title
      this.post.postBody = postBody
    }
  }
}
</script>

<style scoped>

</style>
