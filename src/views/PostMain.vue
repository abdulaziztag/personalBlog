<template>
  <v-card outlined class="mt-5" max-width="800px" width="100%">
    <v-img :src="post.imageSrc" width="100%"/>
    <v-card-title class="text-h3">
      {{ post.title }}
    </v-card-title>
    <v-divider></v-divider>
    <v-card-subtitle
        class="d-flex justify-space-between"
    >
      <div class="attrFont">
        <v-icon style="margin-bottom: 2px; margin-right: 4px;" small>far fa-eye</v-icon>
        <span>{{ post.views }}</span>
      </div>
      <div class="attrFont">{{ post.creationDate }}</div>
    </v-card-subtitle>
    <v-card-subtitle
        class="d-flex justify-end attrFont pt-0 pb-0"
        v-if="post.updated"
    >updated
    </v-card-subtitle>
    <v-card-subtitle
        style="text-indent: 25px ;width: 100%"
        v-html="post.postBody"
    ></v-card-subtitle>
    <v-divider></v-divider>
    <v-card-title class="text-h4">Comments</v-card-title>
    <v-card-subtitle>Add comment</v-card-subtitle>
    <div class="ma-3 d-flex flex-column justify-end">
      <v-text-field
          outlined
          v-model="username"
          label="Name"
          color="purple lighten-1"
      />
      <v-textarea
          outlined
          v-model="commentBody"
          color="purple lighten-1"
          label="Comment"
      />
      <div class="justify-end d-flex">
        <v-btn color="purple" dark @click="addComment">Add comment</v-btn>
      </div>
    </div>
    <v-divider></v-divider>
    <CommentsContent v-for="comment in comments" :key="comment.id" :comment="comment"/>
  </v-card>
</template>

<script>
import CommentsContent from '../components/CommentsContent';

export default {
  name: "PostMain",
  components: {CommentsContent},
  data() {
    return {
      post: [],
      comments: [],
      username: '',
      commentBody: ''
    }
  },
  methods: {
    async addComment() {
      let res = await fetch('http://localhost:8000/comments/', {
        method: 'POST',
        headers: {
          'Content-type': 'application/json'
        },
        body: JSON.stringify({
          username: this.username,
          commentBody: this.commentBody,
          postId: this.$route.params.id
        })
      })
      this.comments.unshift(await res.json())
      this.username = ''
      this.commentBody = ''
    }
  },
  async created() {
    try {
      let res = await fetch(`http://localhost:8000/list/${this.$route.params.id}`, {
        method: 'GET'
      })
      this.post = await res.json()

      let comments = await fetch(`http://localhost:8000/comments/${this.$route.params.id}`, {
        method: 'GET'
      })

      this.comments = await comments.json()
      this.comments = this.comments.reverse()

      await fetch(`http://localhost:8000/list/${this.$route.params.id}`, {
        method: 'PUT',
        headers: {
          'Content-type': 'application/json'
        },
        body: JSON.stringify({
          title: this.post.title,
          postBody: this.post.postBody,
          updated: false,
          views: ++this.post.views,
          imageSrc: this.post.imageSrc
        })
      })
    } catch (e) {
      throw new Error(e)
    }
  }
}
</script>

<style scoped>
.attrFont {
  opacity: .8;
  font-size: 15px;
}
</style>
