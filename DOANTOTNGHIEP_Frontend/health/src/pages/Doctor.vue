<template>
    <div class="row mt-2" style="width: 80%; margin: auto; background: white;">
      <div class="flex align-center mt-4 mb-3">
        <router-link to="/">
          <a href="" style="color: black;"><i class="fa-solid fa-house mr-3"> / </i></a>
        </router-link>
        <p class="title mb-0">{{ serviceName }}</p>
      </div>
      <div class="flex align-center mt-4 mb-3">
        <p style="font-weight: 550 !important; font-size: 20px; margin-bottom: 10px;">{{ doctor.name }}</p>
      </div>
      <div style="font-size: 14px;">
        <!-- Hiển thị các thông tin khác của bác sĩ từ doctorResponse -->
        <p>{{ doctor.position }}</p>
        <p>{{ doctor.description }}</p>
        <p>{{ doctor.price }}</p>
        <!-- Hiển thị lịch hẹn của bác sĩ -->
        <ul>
          <li v-for="schedule in doctor.scheduleResponses" :key="schedule.id">
            {{ schedule.dayofWeek }} - {{ schedule.date }}: {{ schedule.time }}
          </li>
        </ul>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref, onMounted } from 'vue';
  import { useStore } from 'vuex';
  import { useRouter } from 'vue-router';
  
  const router = useRouter()
  
  const store = useStore()
  
  const serviceName = ref('');
  const doctor = ref(null);
  
  onMounted(async () => {
    try {
      // Gọi API để lấy thông tin bác sĩ từ backend
      const response = await fetchDoctorData();
      doctor.value = response;
    } catch (error) {
      console.error('Error fetching doctor data:', error);
    }
  });
  
  // Hàm gọi API để lấy thông tin bác sĩ từ backend
  const fetchDoctorData = async () => {
    const email = ''; // Thay bằng email bác sĩ cần lấy thông tin
    const response = await fetch(`/api/doctor/${email}`);
    if (!response.ok) {
      throw new Error('Failed to fetch doctor data');
    }
    return await response.json();
  };
  </script>
  
  <style scoped>

  </style>
  