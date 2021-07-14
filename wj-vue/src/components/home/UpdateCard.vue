<template>
  <el-card class="box-card">
    <div slot="header" class="clearfix">
      <span style="font-weight: bold;font-size: 20px">最近更新</span>
    </div>
    <div class="block">
      <el-timeline>
        <el-timeline-item id="time" timestamp="2021/7/21" placement="top" color="snow">
          <el-card class="card-in">
            <h4>实现第一部分基本功能</h4>
            <p>42 提交于 2021/7/21 15:50</p>
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
    height: 300px;
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
