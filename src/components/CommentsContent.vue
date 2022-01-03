<template>
  <v-list-item two-line class="ma-1">
    <v-list-item-content>
      <v-list-item-title class="d-flex align-center">
        {{ comment.username }}
        <v-spacer></v-spacer>
        <div class="attrFont">{{ comment.creationDate }}</div>
      </v-list-item-title>
      <v-list-item-subtitle>{{ comment.commentBody }}</v-list-item-subtitle>
    </v-list-item-content>
  </v-list-item>
</template>

<script>
export default {
  name: "CommentsContent",
  props: ['comment']
}
</script>

<style scoped>
.attrFont {
  opacity: .5;
  font-size: 15px;
}
</style>
