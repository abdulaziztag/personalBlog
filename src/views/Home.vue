<template>
  <v-card
      class="d-flex justify-center mt-5 ml-1 mr-1 flex-column transparent elevation-0"
      max-width="800px"
      min-width="200px"
  >
    <PostCard
        v-for="post in posts"
        :key="post.id"
        :post="post"
    />
  </v-card>
</template>

<script>
import PostCard from '../components/PostCard';
export default {
  name: 'Home',
  components: {
    PostCard
  },
  data() {
    return {
      posts: []
    }
  },
  async created() {
    let posts = await fetch('http://localhost:8000/list', {
      method: 'GET'
    })
    this.posts = await posts.json()
    this.posts.sort((a, b) => a.id - b.id).reverse()
  }
}
</script>
