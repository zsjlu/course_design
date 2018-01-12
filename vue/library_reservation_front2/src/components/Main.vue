<template>
  <div class="hello">
    <h1>{{ this.$route.params.userId }}</h1>
    <!--
    <p>
      <router-link to="/reserve">预约</router-link>
      <router-link to="/login">登录</router-link>
      <router-link to="/signOut">签离</router-link>
      <router-link to="/signIn">签到</router-link>
      <router-link to="/signOut/temporay">临时签离</router-link>
    </p>
  -->
    
    <el-button type="primary" @click="reserve">预约</el-button>
    <el-button type="primary" @click="signIn">签到</el-button>
    <el-button type="primary" @click="temporarySignOut">临时签离</el-button>
    <el-button type="primary" @click="signOut">签离</el-button>
    <el-button type="primary" @click="getReserveRecord">预约记录</el-button>
    <el-button type="primary" @click="getSignInRecord">签到记录</el-button>
    <el-button type="primary" @click="getSignOutRecord">签离记录</el-button>
    <el-button type="primary" @click="getInobservanceRecord">违规记录</el-button>
    <DatePicker ref="getDate" v-on:changeDate="showChangeDate"></DatePicker>
    <Seat ref="child"></Seat>
  </div>
</template>

<script>
import DatePicker from './DatePicker'
import Seat from './Seat'
export default {
  name: 'Main',
  components:{
    DatePicker,
    Seat
  },
  data () {
    return {
      msg: '',
      userId: '',
      reserveTime: ''
    }
  },
  mounted () {
    this.userId = this.$route.query.userId;
    this.reserveTime = this.$refs.getDate.date;
    this.$refs.child.reserveTime = this.reserveTime;
    //alert(this.reserveTime);
  },
  methods: {
    showChangeDate:function(data) {
      this.reserveTime = this.$refs.getDate.date;
      //alert('showChangeDate:'+this.reserveTime);
      this.$refs.child.callMethod(this.reserveTime);
    },
    reserve() {
    alert(this.$refs.getDate.date);
      this.$http({
        url: 'http://127.0.0.1:8081/reserve',
        method: 'post',
        params: {
                    userId: this.userId,
                    seatId: '101-1001-10001',
                    reserveTime: this.$refs.getDate.date
                }
      }).then((res) => {
        console.log(res.data);
        alert(res.data.msg);
      })
    },
    signOut() {
      alert('signOut');
      this.$http({
        url: 'http://127.0.0.1:8081/sign_out/final',
        method: 'post',
        params: {
                    userId: '3140602026'
                }
      }).then((res) => {
        console.log(res.data);
        alert(res.data.msg);
      })
    },
    signIn() {
      alert('signIn');
      this.$http({
        url: 'http://127.0.0.1:8081/sign_in',
        method: 'post',
        params: {
                    userId: '3140602026'
                }
      }).then((res) => {
        console.log(res.data);
        alert(res.data.msg);
      })
    },
    temporarySignOut() {
      alert('temporarySignOut');
      this.$http({
        url: 'http://127.0.0.1:8081/sign_out/temporary',
        method: 'post',
        params: {
                    userId: '3140602026'
                }
      }).then((res) => {
        console.log(res.data);
        alert(res.data.msg);
      })
    },
    getReserveRecord() {
      //this.$router.push({name:'ReserveRecord',params:{userId:this.userId}});
      this.$router.push({path:'/reserveRecord',query:{userId:this.userId}});
    },
    getSignInRecord() {
      //this.$router.push({name:'SignInRecord',params:{userId:this.userId}});
      this.$router.push({path:'/signInRecord',query:{userId:this.userId}});
    },
    getSignOutRecord() {
      //this.$router.push({name:'SignInRecord',params:{userId:this.userId}});
      this.$router.push({path:'/signOutRecord',query:{userId:this.userId}});
    },
    getInobservanceRecord() {
      //this.$router.push({name:'SignInRecord',params:{userId:this.userId}});
      this.$router.push({path:'/inobservanceRecord',query:{userId:this.userId}});
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
