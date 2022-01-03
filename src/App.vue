<template>
  <div id="app">
    <v-app dark>
      <v-app-bar height="58px" dark color="#8E24AA" app fixed>
        <v-btn
            class="text-h5 mb-1 text-capitalize pa-1"
            to="/"
            text
            :ripple="false"
            plain
            active-class="no-active"
        >
          PBlog
        </v-btn>
        <v-spacer></v-spacer>
        <v-btn color="#8E24AA" depressed>
          About author
        </v-btn>
      </v-app-bar>
      <div style="margin-top: 58px;" class="d-flex justify-center mb-7">
        <transition name="route-animation" mode="out-in">
          <router-view/>
        </transition>
      </div>
    </v-app>
  </div>
</template>

<script>
export default {
  data() {
    return {
      logoutBtn: false
    }
  },
  created() {
    this.logoutBtn = localStorage.getItem('logged') === 'true' && this.$router.currentRoute.fullPath === '/admin'
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  background: url("https://images.unsplash.com/photo-1432821596592-e2c18b78144f?ixid=MnwxMjA3fDB8MHxzZWFyY2h8Mnx8YmxvZ3xlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&w=1000&q=80") no-repeat center fixed;
  background-size: cover;
}

.v-btn--active.no-active::before {
  opacity: 0 !important;
}

.route-animation-enter-active {
  animation: .3s slide-enter forwards;
}

.route-animation-leave-active {
  animation: .3s slide-leave forwards;
}

@keyframes slide-leave {
  from {
    opacity: 1;
  }
  to {
    opacity: 0;
  }
}

@keyframes slide-enter {
  from {
    opacity: 0;
  }
  to {
    opacity: 1;
  }
}

</style>
