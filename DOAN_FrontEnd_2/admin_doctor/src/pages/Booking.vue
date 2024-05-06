<template>
    <div style="background: #f6f6f6;">
        <div class="p-10" style="max-width: 800px; margin: auto;">
            <div style="width: 100%; margin: auto; display: flex;">
                <a href="">
                    <img :src="scheduleTime.avata" alt="" style="width: 80px; height: 80px; border-radius: 50%;">
                </a>
                <div style="margin-left: 5%;">
                    <p style="margin-bottom: 3px;">ĐẶT LỊCH KHÁM</p>
                    <p style="margin-bottom: 3px;">
                        <a href="" style="text-decoration: none; color: #337ab7; font-weight: 600 !important;">
                            {{ scheduleTime.position }}{{ scheduleTime.name }}
                        </a>
                    </p>
                    <p>{{ scheduleTime.time }} - {{ scheduleTime.selectedDay }}</p>
                </div>
            </div>
        </div>
    </div>
    <div class="mb-10">
        <div class="" style="max-width: 800px; margin: auto;">
            <div class="mt-3 mb-3" style="width: 90%; margin: auto;">
                <div class="price" style="width: 200px; height: 60px; border: 1px red solid; 
                display: flex; flex-direction: column; align-items: center; justify-content: center;">
                    <div style="display: flex; align-items: center;">
                        <input type="radio" checked="checked" name="price">
                        <span style="margin-left: 5px;">Giá khám</span>
                    </div>
                    <p style="margin-bottom: 0;">{{ (scheduleTime.price) }}.000đ</p>
                </div>
            </div>
            <div class="form-input mt-4" style="width: 90%; margin: auto;">
                <a-form :model="formState">
                    <a-form-item>
                        <a-input v-model:value="formState.name" placeholder="Họ tên bệnh nhân ( bắt buộc )">
                            <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
                        </a-input>
                    </a-form-item>
                    <p>Hãy ghi rõ Họ Và Tên, viết hoa những chữ cái đầu tiên, ví dụ: Trần Văn Phú</p>

                    <a-form-item label="">
                    <a-radio-group v-model:value="formState.gender">
                        <a-radio value="1">Nam</a-radio>
                        <a-radio value="2">Nữ</a-radio>
                    </a-radio-group>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.year" placeholder="Năm sinh ( bắt buộc )">
                            <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
                        </a-input>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.phone" placeholder="Số điện thoại liên hệ ( bắt buộc )">
                            <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
                        </a-input>
                    </a-form-item>

                    <a-form-item>
                        <a-input v-model:value="formState.email" placeholder="Email ( bắt buộc )">
                            <template #prefix><UserOutlined style="color: rgba(0, 0, 0, 0.25)" /></template>
                        </a-input>
                    </a-form-item>

                    <a-form-item label="">
                        <a-textarea v-model:value="formState.address" placeholder="Địa chỉ" />
                    </a-form-item>

                    <a-form-item label="">
                        <a-textarea v-model:value="formState.reason" placeholder="Lý do khám ( bắt buộc )" />
                    </a-form-item>

                    <a-form-item label="">
                        <a-textarea v-model:value="formState.history" placeholder="Lịch sử khám bệnh ( nếu có )" />
                    </a-form-item>

                    <a-form-item>
                        <input type="file" @change="handleFileChange" />
                    </a-form-item>

                    <a-form-item :wrapper-col="{ span: 14, offset: 8 }">
                        <a-button type="primary" @click="onSubmit">Xác nhận đặt khám</a-button>
                    </a-form-item>
                </a-form>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, computed, watchEffect } from 'vue';
import { useStore } from 'vuex';
import { useRouter } from 'vue-router';

const router = useRouter()

const store = useStore()

const scheduleTime = computed(() => store.state.scheduleTime);


import { reactive, toRaw } from 'vue';

const formState = reactive({
  name: '',
  gender: '', 
  year: '',
  phone: '',
  email: '',
  address: '',
  reason: '',
  history: '',
  file: null,
});

const onSubmit = () => {
  console.log('submit!', toRaw(formState));
};

const handleFileChange = (event) => {
  // Lưu thông tin về file được chọn vào formState
  formState.file = event.target.files[0];
};

</script>

<style scoped>

.form-input {
  margin-left: auto; /* Đặt margin-left: auto để căn phải */
}

.ant-form-item {
    margin-bottom: 10px !important;
}

</style>