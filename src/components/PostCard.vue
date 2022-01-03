<template>
  <v-card dark class="mb-3">
    <v-img min-height="300px" max-height="400px"
           :src="post.imageSrc"></v-img>
    <v-divider class="mb-2"></v-divider>
    <div
        class="d-flex justify-space-between"
    >
      <div style="padding-left: 16px;" class="attrFont">
        <v-icon style="margin-bottom: 2px; margin-right: 4px;" small>far fa-eye</v-icon>
        <span>{{ post.views }}</span>
      </div>
      <div class="attrFont" style="padding-right: 16px;">{{ post.creationDate }}</div>
    </div>
    <div
        class="d-flex justify-end attrFont"
        style="padding-right: 16px;"
        v-if="post.updated"
    >updated
    </div>
    <v-card-title>{{ post.title }}</v-card-title>
    <v-divider class="ml-5 mr-5"></v-divider>
    <v-card-subtitle class="text-truncate mr-5 mb-5" style="display: block; height: 40px" v-html="post.postBody">
    </v-card-subtitle>
    <v-card-actions class="d-flex justify-end pb-5 pr-5">
      <v-btn
          color="#D32F2F"
          class="text-capitalize font-weight-medium"
          style="letter-spacing: .5px"
          :to="`/post/${post.id}`"
      >
        Read more...
      </v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
  name: "PostCard",
  props: ['post']
}
</script>

<style scoped>
.attrFont {
  opacity: .5;
  font-size: 15px;
}
</style>
