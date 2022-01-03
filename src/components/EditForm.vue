<template>
  <v-row justify="center">
    <v-dialog
        dark
        v-model="dialog"
        persistent
        max-width="800px"
    >
      <template v-slot:activator="{ on, attrs }">
        <v-btn
            color="warning"
            icon
            fab
            small
            class="mr-4 ml-4 mb-3"
            v-bind="attrs"
            @click="getEditForm"
            v-on="on"
        >
          <v-icon small>fas fa-pen</v-icon>
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Edit post</span>
        </v-card-title>
        <v-card-text>
          <v-container>
            <v-row>
              <v-col cols="12">
                <v-text-field
                    v-model="title"
                    outlined
                    color="purple lighten-1"
                    label="Title of post"
                ></v-text-field>
              </v-col>
              <v-col cols="12">
                <p class="text-h6">Post body</p>
                <editor
                    :api-key="apiKey"
                    :disabled=false
                    v-model="editor"
                    dark
                    :init="{plugins: `advlist lists preview hr
                     searchreplace wordcount code fullscreen insertdatetime
                     table emoticons paste help`,
                     height: '400px'
                  }"
                />
              </v-col>
              <v-col cols="12">
                <v-text-field
                    label="Image url"
                    outlined
                    v-model="imageSrc"
                    color="purple lighten-1"
                    class="mt-5"
                ></v-text-field>
              </v-col>
            </v-row>
          </v-container>
        </v-card-text>
        <v-card-actions>
          <v-spacer></v-spacer>
          <v-btn
              color="red darken-1"
              @click="dialog = false"
              :disabled="loader"
          >
            <v-progress-circular indeterminate v-if="loader" />
            Close
          </v-btn>
          <v-btn
              color="green darken-1"
              :disabled="loader"
              @click="saveEditedMessage"
          >
            <v-progress-circular indeterminate v-if="loader" />
            Save
          </v-btn>
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-snackbar
        v-model="snackBar"
        :timeout="10000"
        dark
        :color="snackBarText===200? 'success': 'error'"
    >
      {{ snackBarText === 200 ? "Post edited successfully" : "An error occured" }}

      <template v-slot:action="{ attrs }">
        <v-btn
            text
            light
            v-bind="attrs"
            @click="snackBar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-row>
</template>

<script>
export default {
  name: "EditForm",
  props: ['index'],
  data() {
    return {
      dialog: false,
      apiKey: 'dyrnlr9dgmtbj9lohnf2hlb141dz53xcoah1njs2vewjhyza',
      editor: '',
      title: '',
      imageSrc: '',
      loader: false,
      snackBar: false,
      snackBarText: '',
      viewCount: 0
    }
  },
  methods: {
    async getEditForm() {
      let response = await fetch(`http://localhost:8000/list/${this.index}`)
      let data = await response.json()
      this.title = data.title
      this.editor = data.postBody
      this.imageSrc = data.imageSrc
      this.viewCount = data.views
    },
    async saveEditedMessage() {
      this.loader = true
      let res = await fetch(`http://localhost:8000/list/${this.index}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          title: this.title,
          postBody: this.editor,
          imageSrc: this.imageSrc,
          updated: true,
          views: this.viewCount
        })
      })
      this.$emit('addToUI', {title: this.title, postBody: this.editor})
      this.snackBarText = res.status
      this.snackBar = true
      this.loader = false
      this.dialog = false
      this.editor = ''
      this.title = ''
      this.imageSrc = ''
    }
  }
}
</script>

<style scoped>

</style>
