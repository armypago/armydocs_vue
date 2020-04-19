<template>
  <div>
    <v-img
        :src="require('@/assets/dashboard/profile-bg.jpg')"
        height="450"
    >
      <v-container fluid fill-height>
        <v-row justify="center" align="center">
          <v-col>
            <v-row justify="center" align="center">
              <v-avatar size="150">
                <img :src="user.img" />
              </v-avatar>
            </v-row>
            <v-row justify="center" align="center">
              <div class="head-text head-title">
                  {{ user.type }}) {{ user.name }}
              </div>
            </v-row>
            <v-row justify="center" align="center">
              <v-icon color="red" class="mr-1">mdi-menu</v-icon>
              <div class="head-text">
                {{ user.type2 }}, {{ user.unit }}
              </div>
            </v-row>
          </v-col>
        </v-row>
        <v-row justify="center" align="center">
          <v-col cols="3">
            <v-row
              justify="center"
              align="center"
              class="head-num"
            >{{ userSurvey.mySurveyCount }}
            </v-row>
            <v-row
              justify="center"
              align="center"
              class="head-text"
            >내가 작성한 설문
            </v-row>
          </v-col>

          <div class="vertical-line"></div>

          <v-col cols="3">
            <v-row
              justify="center"
              align="center"
              class="head-num"
            >{{ userSurvey.availSurveyCount }}</v-row>
            <v-row
              justify="center"
              align="center"
              class="head-text"
            >현재 참여 가능한 설문
            </v-row>
          </v-col>

          <div class="vertical-line"></div>

          <v-col cols="3">
            <v-row
              justify="center"
              align="center"
              class="head-num"
            >{{ user.registerdate }}
            </v-row>
            <v-row
              justify="center"
              align="center"
              class="head-text"
            >회원가입일
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-img>

    <v-app-bar class="appbar">
      <v-container fluid>
        <v-row justify="center" align="center">
          <v-col cols="8">
            <v-row justify="center">
              <v-btn active-class="active-btn" exact to="/survey" depressed>
                진행중인 설문
              </v-btn>
              <v-btn active-class="active-btn" exact to="/mysurvey" depressed>
                My 설문
              </v-btn>
              <v-btn active-class="active-btn" exact to="/admin" depressed>
                승인을 기다리는 목록
              </v-btn>

              <v-spacer></v-spacer>

              <v-btn active-class="active-btn" exact to="/" color="error">
                <v-icon class="mr-2">mdi-clipboard-check-outline</v-icon> 설문지 작성
              </v-btn>
            </v-row>
          </v-col>
        </v-row>
      </v-container>
    </v-app-bar>

  </div>
</template>

<script>
export default {
  data: () => ({
    user: {
      name: "최준영",
      type: "병사",
      type2: "육군",
      rank: "상병",
      unit: "관리대대",
      img: "https://cdn.vuetifyjs.com/images/john.jpg",
      registerdate: "2019-10-16"
    },
    userSurvey: {
      mySurveyCount: 2,
      availSurveyCount: 20
    }
  })
};
</script>

<style scoped>
.head-num {
  font-weight: bold;
  color: red;
}
.head-title {
  font-size: 1.6rem;
  margin-top: 15px;
}
.head-text {
  color: white;
}
.vertical-line {
  display: block;
  width: 1px;
  height: 60px;
  background-color: #fff;
}
.v-btn {
    font-size: 0.95rem !important;
}
.appbar{
    margin-bottom: 2rem;
}

.active-btn {
    background-color: transparent !important;
    color: red;
}

.v-btn:before {
  background-color: transparent !important;
}
.theme--light{
  background-color: unset !important;
}
</style>