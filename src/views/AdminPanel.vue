<template>
  <v-card
      class="d-flex justify-center mt-5 ml-1 mr-1 flex-column pa-2"
      max-width="800px"
      dark
      min-width="200px"
  >
    <AddForm class="ma-2" @addToUI="add"/>
    <div class="text-h3 ma-2">
      List of posts
    </div>
    <v-divider class="mb-6"></v-divider>
    <v-progress-circular indeterminate v-if="loader" />
    <div v-for="post in posts" v-else :key="post.id">
      <ListEl :post="post" @deletePost="deletePost"/>
      <v-divider class="mr-3 ml-3"></v-divider>
    </div>
  </v-card>
</template>

<script>
import ListEl from '../components/ListEl';
import AddForm from '../components/AddForm';

export default {
  name: "AdminPanel",
  components: {AddForm, ListEl},
  data() {
    return {
      posts: [],
      loader: false
    }
  },
  methods: {
    deletePost(id) {
      let index = this.posts.findIndex(key => {
        return key.id === id
      })
      this.posts.splice(index, 1)
    },
    add(obj) {
      this.posts.push(obj)
    }
  },
  async created() {
    if (localStorage.getItem('logged') !== 'true') {
      await this.$router.push('/login')
    }
    this.loader = true
    let posts = await fetch('http://localhost:8000/list', {
      method: 'GET'
    })
    this.posts = await posts.json()
    this.loader = false
  }
}
</script>

<style scoped>

</style>
