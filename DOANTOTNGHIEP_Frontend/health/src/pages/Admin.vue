<template>
  <div style="width: 80vw; height: 400px; margin: auto; display: flex; margin-top: 10px;">
    <div class="col-xl-3 col-lg-3" style=" background: grey;">
      <vs-button class="" href="" style=" width: 95%;text-decoration: none; color: white; display: block; margin-bottom: 10px;" 
      v-for="item in navigation" :key="item.name" @click.prevent="handleNavigation(item)">{{ item.name }}</vs-button>
    </div>
    <div class="col-xl-9 col-lg-9" v-if="selectedItem=='Lịch hẹn'">
      <div class="ml-5">
        <p>Lịch hẹn</p>
      </div>
    </div>
  </div>
</template>

<script setup>
  import { ref } from 'vue'
  
  const navigation = [
    { name: 'Thông tin cá nhân', href: '#', current: false },
    { name: 'Danh sách bác sĩ', href: '#', current: true },
    { name: 'Lịch hẹn', href: '#', current: false },
    // { name: '', href: '#', current: false },
  ]
  
  const selectedItem = ref(null);
  
  const handleNavigation = (item) => {
    selectedItem.value = item.name;
  }
</script>

<style>

</style>
