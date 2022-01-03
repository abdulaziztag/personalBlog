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
            block color="success" class="mb-5"
            dark
            v-bind="attrs"
            v-on="on"
        >
          <v-icon v-bind="attrs" v-on="on" x-small class="mr-2">fas fa-plus</v-icon>
          Add post
        </v-btn>
      </template>
      <v-card>
        <v-card-title>
          <span class="text-h5">Add post</span>
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
              @click="save"
              :disabled="loader"
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
        :color="snackBarText===200? 'success': 'error'"
    >
      {{ snackBarText === 200 ? "Post added successfully" : "An error occured" }}

      <template v-slot:action="{ attrs }">
        <v-btn
            text
            dark
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
  name: "AddForm",
  data() {
    return {
      dialog: false,
      apiKey: 'dyrnlr9dgmtbj9lohnf2hlb141dz53xcoah1njs2vewjhyza',
      editor: '',
      title: '',
      imageSrc: '',
      loader: false,
      snackBar: false,
      snackBarText: ''
    }
  },
  methods: {
    async save() {
      this.loader = true
      let res = await fetch('http://localhost:8000/list', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          title: this.title,
          postBody: this.editor,
          imageSrc: this.imageSrc,
          views: 0,
          updated: false
        })
      })
      this.$emit('addToUI', await res.json())
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
