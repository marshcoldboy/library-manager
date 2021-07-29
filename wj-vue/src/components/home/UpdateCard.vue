<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="font-weight: bold;font-size: 20px">最近公告</span>
    </div>
    <div class="block">
      <el-timeline>
        <el-timeline-item id="time" timestamp="2021/7/29" placement="top" color="snow">
          <el-card class="card-in">
            <h4>从2021/7/1开始，超期归还图书将缴纳罚款</h4>
            <p>提交于 2021/6/30 15:50</p>
          </el-card>
          <el-card class="card-in">
            <h4>新书到库：程序员修炼之道、深入理解计算机系统</h4>
            <p>提交于 2021/7/29 15:32</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
    </div>
  </el-card>
</template>

<script>
  export default {
    name: 'UpdateCard'
  }
</script>

<style scoped>
  .box-card {
    margin-top: 5px;
    width: 988px;
    text-align: left;
    background: rgba(0,0,0,0.3);
  }
  .clearfix {
    color: powderblue;
  }
  .block {
    color: snow;
  }
  #time {
    color: snow;
  }
  .card-in {
    background: rgba(0,0,0,0.1);
    color: snow;
  }
</style>
