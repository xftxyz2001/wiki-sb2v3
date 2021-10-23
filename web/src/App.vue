<template>
  <a-layout>
    <the-header></the-header>
    <router-view/>
    <the-footer></the-footer>
  </a-layout>
</template>

<script lang="ts">
import {defineComponent} from 'vue';
import TheHeader from '@/components/the-header.vue'; // @ is an alias to /src
import TheFooter from '@/components/the-footer.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'app',
  components: {
    TheHeader,
    TheFooter,
  },
});
</script>
