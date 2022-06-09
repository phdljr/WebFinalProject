<template>
  <div class="my-page">
    <div class="profile">
      <div class="person-info">
        <h3>
          <strong>{{ this.$store.state.userData.id }}님</strong>
        </h3>
      </div>
    </div>
    <div class="my-cgv">
      <h5 class="text-left">예매 내역</h5>
      <div class="my-booking">
        <b-table-simple
          small
          responsive
          v-for="(book, index) in this.reservations"
          :key="index"
        >
          <b-thead>
            <b-tr>
              <b-th colspan="6" class="text-left">{{ book.movieName }}</b-th>
              <b-th colspan="2" class="text-right"
                >{{ book.price }}원</b-th
              >
            </b-tr>
          </b-thead>
          <b-tbody>
            <b-tr>
              <b-th rowspan="4">
                <img :src="'../movies/' + book.movieName + '.jpg'" style="width: 100px" />
              </b-th>
            </b-tr>
            <b-tr>
              <b-td>관람극장</b-td>
              <b-td colspan="2" class="text-left">구미 CGV</b-td>
              <b-td>관람인원</b-td>
              <b-td colspan="4" class="text-left"
                >일반 {{ book.numOfPeople }}</b-td
              >
            </b-tr>
            <b-tr>
              <b-td>관람일시</b-td>
              <b-td colspan="2" class="text-left">{{ book.screenDate + " " + book.screenTime }}</b-td>
              <b-td>관람좌석</b-td>
              <b-td colspan="4" class="text-left"
                ><span v-for="(temp, index) in book.numOfPeople" :key="index"
                  >{{ book.rows[index] + book.columns[index] + " " }}
                </span></b-td
              >
            </b-tr>
            <b-tr>
              <b-td>상영관</b-td>
              <b-td colspan="2" class="text-left">{{
                book.floor + "층" + book.screen
              }}</b-td>
              <b-td>매수</b-td>
              <b-td colspan="4" class="text-left">{{ book.numOfPeople }}매</b-td>
            </b-tr>
          </b-tbody>
          <b-tfoot>
            <b-tr>
              <b-td colspan="7" variant="secondary" class="text-right">
                총 결제금액: <b>{{ book.price }}원</b>
              </b-td>
            </b-tr>
          </b-tfoot>
        </b-table-simple>
      </div>
    </div>
  </div>
</template>
<script>
import axios from "axios";
export default {
  name: "MyPageView",
  components: {},
  data() {
    return {
      reservations: [
        // {
        //   "columns": [
        //     0
        //   ],
        //   "floor": 0,
        //   "movieName": "string",
        //   "numOfPeople": 0,
        //   "price": 0,
        //   "rows": [
        //     "string"
        //   ],
        //   "screen": "string",
        //   "screenDate": "string",
        //   "screenTime": "string"
        // }
      ],
    };
  },
  methods: {},
  created(){
    axios.post(this.HOST+"/reservations", {param: this.$store.state.userData.id}).then(res=>{
      this.reservations = res.data
    }).catch(err=>{
      console.log(err)
    })
  }
};
</script>
<style>
.profile {
  margin-bottom: 10px;
}
.my-booking {
  border: 1px solid black;
  padding: 20px;
}
.text-left {
  text-align: left;
}
.text-right {
  text-align: right;
}
</style>
