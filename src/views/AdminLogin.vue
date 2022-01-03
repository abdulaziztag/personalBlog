<template>
  <v-card
      dark
      class="d-flex justify-center mt-5 ml-1 mr-1 flex-column pa-5"
      max-width="450px"
      width="100%"
  >
    <v-card-title class="text-h4">Admin Panel</v-card-title>
    <v-card-actions class="d-flex flex-column">
      <v-text-field
          outlined
          color="amber"
          style="width: 100%;"
          label="Username"
          v-model="username"
      ></v-text-field>
      <v-text-field
          outlined
          color="amber"
          style="width: 100%;"
          label="Password"
          type="password"
          v-model="password"
      ></v-text-field>

    </v-card-actions>
    <div class="d-flex justify-end">
      <v-btn outlined color="success" @click="login">Login</v-btn>
    </div>
    <v-snackbar
        v-model="snackbar"
        multi-line
        :color="response ? 'success' : 'error'"
    >
      {{ response ? 'Logged in successfully' : 'Something went wrong' }}

      <template v-slot:action="{ attrs }">
        <v-btn
            text
            v-bind="attrs"
            @click="snackbar = false"
        >
          Close
        </v-btn>
      </template>
    </v-snackbar>
  </v-card>
</template>

<script>
export default {
  name: "AdminLogin",
  data() {
    return {
      username: '',
      password: '',
      response: false,
      snackbar: false
    }
  },
  methods: {
    async login() {
      let response = await fetch('http://localhost:8000/adminLogin', {
        method: 'POST',
        headers: {
          'Content-type': 'application/json'
        },
        body: JSON.stringify({
          username: this.username.trim(),
          password: this.password.trim()
        })
      })
      this.snackbar = true
      this.response = await response.json()
      if (this.response) {
        localStorage.setItem('logged', 'true')
        setTimeout(() => {
          this.$router.push('/admin')
        }, 500)
      }
    },
  },
  async created() {
    console.log(localStorage.getItem('logged'))
    if (localStorage.getItem('logged') === 'true') {
      await this.$router.push('/admin')
    }
  }
}
</script>

<style scoped>

</style>
