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
          v-for="(book, index) in this.$store.state.userData.reservations"
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
                <img :src="'../images/' + book.movieName + '.jpg'" style="width: 100px" />
              </b-th>
            </b-tr>
            <b-tr>
              <b-td>관람극장</b-td>
              <b-td colspan="3" class="text-left">구미 CGV</b-td>
              <b-td>관람인원</b-td>
              <b-td colspan="3" class="text-left"
                >일반 {{ book.numOfPeople }}</b-td
              >
            </b-tr>
            <b-tr>
              <b-td>관람일시</b-td>
              <b-td colspan="3" class="text-left">{{ book.screenDate + " " + book.screenTime }}</b-td>
              <b-td>관람좌석</b-td>
              <b-td colspan="3" class="text-left"
                ><span v-for="(temp, index) in book.numOfPeople" :key="index"
                  >{{ book.rows[index] + book.columns[index] + " " }}
                </span></b-td
              >
            </b-tr>
            <b-tr>
              <b-td>상영관</b-td>
              <b-td colspan="3" class="text-left">{{
                book.floor + "층" + book.screen
              }}</b-td>
              <b-td>매수</b-td>
              <b-td colspan="3" class="text-left">{{ book.numOfPeople }}매</b-td>
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
export default {
  name: "MyPageView",
  components: {},
  data() {
    return {
      reservations: [
        // {
        //   title: "범죄도시 2",
        //   imgUrl:
        //     "https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85689/85689_320.jpg",
        //   theater: "CGV 구미",
        //   numOfPeople: "3",
        //   date: "2022.05.08(일) 14:20",
        //   seats: ["I23", "I10"], // 이건 따로 수정
        //   floorOfTheater: "1층 1관",
        //   numOfTicket: "2",
        //   totalPrice: "24,000",
        // },
        // {
        //   title: "극장판 포캣몬스터",
        //   imgUrl:
        //     "https://img.cgv.co.kr/Movie/Thumbnail/Poster/000085/85833/85833_320.jpg",
        //   theater: "CGV 구미",
        //   numOfPeople: "4",
        //   date: "2022.05.08(일) 14:20",
        //   seats: ["A3", "A4"], // 이건 따로 수정
        //   floorOfTheater: "2층 2관",
        //   numOfTicket: "2",
        //   totalPrice: "22,000",
        // },
      ],
    };
  },
  methods: {},
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
