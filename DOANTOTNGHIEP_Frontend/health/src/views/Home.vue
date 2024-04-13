<template>  
    <div>
        <navbar/>
    </div>
    <div class="mt-2" style="width: 90%; margin: auto">
      <v-carousel
                height=""
                width=""
                :show-arrows="hover"
                :cycle="true"
                :hide-delimiter-background="true"
            >
                <v-carousel-item v-for="(slide, i) in slides" :key="i">
                <v-sheet height="100%">
                    <div class="d-flex fill-height justify-center align-center ">
                        <a href="">
                            <div class="text-h2">
                                <img :src="slide.src" alt="" style="border-radius: 20px; max-height: 60vh; width: 80vw; object-fit: cover; ">
                            </div>
                        </a>
                    </div>
                </v-sheet>
                </v-carousel-item>
            </v-carousel>
    </div>

    <div class="mt-4" style="width: 80%; margin: auto;">
        <div class="comprehensive-service  mt-3 col-xl-12 " 
        style=" font-family: Montserrat, sans-serif !important; font-weight: 13px;">
            <div>
                <h2>Dịch vụ toàn diện</h2>
            </div>
            <div class="row mt-4">
                <div v-for="item in fullService" :key="item.text" class="col-6  itemService">
                    <div style="width: 100%; height: 100%; border: 2px solid rgb(241, 238, 238);; border-radius: 20px; margin-left: 0; ">
                        <a href="item.link" style="display: flex;">
                            <div style="display: flex; align-items: center;">
                                <img :src="item.img" alt="image" style="max-width: 100%; max-height: 100%;">
                            </div>
                            <div style="display: flex; align-items: center; flex: 1;">
                                <h3 style="margin-bottom: 0; font-size: 3vw;">{{ item.text }}</h3>
                            </div>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="slider-container" style=" font-family: Montserrat, sans-serif !important; font-weight: 13px;">
        <div>
            <h2>Chuyên khoa</h2>
        </div>
        <div class="slide-bar">
            <div class="slider" style="margin-top: 2%;">
            <!-- Vòng lặp để hiển thị các mục từ mảng slides2 -->
            <div v-for="(slide, index) in slides2" :key="index" class="">
            <div class="slide-item">
                <a href="" style="text-decoration: none; color: black;">
                    <img :src="slide.img" alt="slide" class="slide-img">
                    <h4 class="slide-title">{{ slide.name }}</h4>
                </a>
            </div>
            </div>
            </div>
        </div>
    </div>



    <div>
        <nav-footer/>
    </div>
</template>

<script setup>
import navbar from '@/components/navbar.vue'
import navFooter from '../components/navFooter.vue'
import {ref, computed, watch, onMounted} from 'vue'
import { text } from '@fortawesome/fontawesome-svg-core';
import  Axios  from 'axios';


const slides = ref([
  { src: 'slide1.png' },
  { src: 'slide2.png' },
  { src: 'slide3.png' },
  { src: 'slide4.jpg' },
  { src: 'slide5.png' },
]);

const fullService = [
    {link:"", img: "https://cdn.bookingcare.vn/fo/w128/2023/06/07/161905-iconkham-chuyen-khoa.png", text:"Khám Chuyên khoa"},
    {link:"", img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161817-iconkham-tu-xa.png", text:"Khám từ xa"},
    {link:"", img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161350-iconkham-tong-quan.png", text:"Khám tổng quát"},
    {link:"", img:"https://cdn.bookingcare.vn/fo/w128/2023/06/07/161410-iconkham-nha-khoa.png", text:"Khám Nha khoa"},
    // {img:"", text:""},
    // {img:"", text:""},
]



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

// const getExaminationService = async() => {
//     try {
//         const response = await Axios.get("http://localhost:8088/api/v1/user/getExaminationService?serviceTypeName=" + "Khám chuyên khoa");
//         slides2.value = response.data;
//     } catch (error) {
//         alert("error")
//     }
// }

onMounted(
    async() => {
    try {
        const response = await Axios.get("http://localhost:8088/api/v1/user/getExaminationService?serviceTypeName=" + "Khám chuyên khoa");
        slides2.value = response.data;
    } catch (error) {
        alert("error")
    }
}
)




</script>

<style scoped>
.itemService {
    /* border: 2px solid rgb(241, 238, 238);
    border-radius: 20px; */
    height: 100px;
    display: flex;
    align-items: center;
    justify-content: center;
    /* margin-left: 5%; */
    margin-bottom: 3%;
}

.itemService a {
    margin-left: 10% ;
    display: flex;
    gap: 5%;
    text-decoration: none;
    color: black;
    height: 100%;
    width: 100%;
}
    @media (min-width: 0) {
            .itemService {
                height: 50px;
            }
            .itemService img {
                width: 25px;
            }
            .itemService h3 {
            font-size: 10px !important;
            }
            .image-title {
            font-size: 10px;
            }
        }
    @media (min-width: 768px) {
        .itemService {
                height: 70px;
            }
        .itemService img {
            width: 35px;
        }
        .itemService h3 {
            font-size: 13px !important;
        }
        .image-title {
            font-size: 13px;
        }
    }
    @media (min-width: 992px) {
        .itemService {
                height: 90px;
            }
        .itemService img {
            width: 45px;
        }
        .itemService h3 {
            font-size: 16px !important;
        }
        .image-title {
            font-size: 16px;
        }
    }

    @media (min-width: 1200px) {
        .itemService {
                height: 100px;
            }
        .itemService img {
            width: 50px;
            height: auto;
        }
        .itemService h3 {
            font-size: 22px !important;
        }
        .image-title {
            font-size: 22px;
        }
        .slide-title {
            text-align: center;
            margin-top: 5px;
            font-size: 25px !important;
        }
    }

.image-title {
    text-align: center; /* Căn giữa văn bản */
    color: black;
    font-weight: 400;
}


.slider-container {

  width: 80%;
  margin: auto;

}
.slide-bar {
    overflow-x: auto;
    white-space: nowrap; /* Ngăn các mục bị xuống dòng */
}

.slider {
  display: inline-flex; /* Hiển thị các mục trên cùng một hàng */
}

.slide-item {
    margin-right: 20px;
  width: 30vw; /* Sử dụng 30% chiều rộng của viewport */
  max-width: 300px; /* Tối đa 300px để tránh co quá mức */
}

.slide-img { 
    width: 100%;
  height: auto; /* Đảm bảo tỷ lệ khung hình được duy trì */
  object-fit: cover; 
}

.slide-title {
  text-align: center;
  margin-top: 10px;
  font-size: 2vw;
}


</style>