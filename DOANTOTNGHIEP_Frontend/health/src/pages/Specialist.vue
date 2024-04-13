<template>
    <div>
        <navbar/>
    </div>
    <div class="row mt-2" style="width: 80%; margin: auto;">
        <div class="flex align-center mt-4 mb-3">
            <a href="" style="color: black;"><i class="fa-solid fa-house mr-3"> / </i></a>
            <h3 style="margin-bottom: 0; font-size: 20px;">Khám chuyên khoa</h3>
        </div>
        <div v-for="(slide, index) in slides2" :key="index" class="col-3 mb-4">
            <a href="" style="text-decoration: none; color: black;">
                <div class="slide-item">
                    <img :src="slide.src" alt="Slide Image">
                    <div>{{ slide.title }}</div>
                </div>
            </a>
        </div>
  </div>
  
</template>

<script setup>

import navbar from '@/components/navbar.vue'

import {ref} from 'vue'

const slides2 = ref([
  { src: 'co-xuong-khop.png', link:"", title:"Cơ xương khớp" },
  { src: 'than-kinh.png', link:"", title:"Thần kinh" },
  { src: 'tieu-hoa.png', link:"" , title:"Tiêu hoá"  },
  { src: 'tim-mach.png', link:"" , title:"Tim mạch" },
  { src: 'tai-mui-hong.png', link:""  , title:"Tai Mũi Họng" },
  { src: 'cot-song.png', link:""  , title:"Cột sống" },
  { src: 'y-hoc-co-truyen.png', link:""  , title:"Y học cổ truyền" },
  { src: 'cham-cuu.png', link:"" , title:"Châm cứu"  },
  { src: 'san-phu-khoa.png', link:""  , title:"Sản Phụ khoa" },
  { src: 'sieu-am-thai.png', link:""  , title:"Siêu âm thai" },
  { src: 'nhi-khoa.png', link:""  , title:"Nhi khoa" },
  { src: 'da-lieu.png', link:""  , title:"Da liễu" },
  { src: 'viem-gan.png', link:""  , title:"Bệnh Viêm gan" },
  { src: 'suc-khoe-tam-than.png', link:"" , title:"Sức khoẻ tâm thần"  },
  { src: 'di-ung-mien-dich.png', link:""  , title:"Dị ứng miễn dịch" },
  { src: 'ho-hap-phoi.png', link:""  , title:"Hô hấp - Phổi" },
  { src: 'ngoai-than-kinh.png', link:""  , title:"Ngoại thần kinh" },
  { src: 'nam-hoc.png', link:"" , title:"Nam học"  },
  { src: 'mat.png', link:"" , title:"Chuyên khoa Mắt"  },
  { src: 'than-tiet-nieu.png', link:""  , title:"Thận - Tiết niệu" },
  { src: 'noi-khoa.png', link:""  , title:"Nội khoa" },
  { src: 'tieu-duong.png', link:""  , title:"Tiểu đường - Nội tiết" },
  { src: 'phuc-hoi-chuc-nang.png', link:""  , title:"Phục hồi chức năng" },
  { src: 'da-lieu-tham-my.png', link:""  , title:"Da liễu thẩm mỹ" },
  { src: 'tu-van-tam-ly.png', link:""  , title:"Tư vấn, trị liệu Tâm lý" },
  { src: 'vo-sinh-hiem-muon.png', link:""  , title:"Vô sinh - Hiếm muộn" },
  { src: 'chan-thuong-chinh-hinh.png', link:""  , title:"Chấn thương chỉnh hình" },
]);

</script>

<style scoped>
.slide-item {
  text-align: center;
  font-weight: 500;
}

.slide-item img {
  max-width: 100%;
  height: auto;
}
</style>