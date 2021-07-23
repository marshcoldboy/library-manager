<template>
  <div :class="className" :style="{height:height,width:width}" />
</template>

<script>
import echarts from 'echarts'
import resize from './mixins/resize'
require('echarts/theme/macarons') // echarts theme

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: 'chart'
    },
    width: {
      type: String,
      default: '250px'
    },
    height: {
      type: String,
      default: '250px'
    }
  },
  data () {
    return {
      chart: null
    }
  },
  mounted () {
    this.$nextTick(() => {
      this.initChart()
    })
  },
  beforeDestroy () {
    if (!this.chart) {
      return
    }
    this.chart.dispose()
    this.chart = null
  },
  methods: {
    initChart () {
      this.chart = echarts.init(this.$el, 'macarons')

      this.chart.setOption({
        tooltip: {
          trigger: 'item',
          formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
          left: 'center',
          bottom: '10',
          data: ['文学', '流行', '文化', '生活', '经管', '科技']
        },
        series: [
          {
            name: '图书类型',
            type: 'pie',
            roseType: 'radius',
            radius: [10, 50],
            center: ['50%', '50%'],
            data: [
              { value: 320, name: '文学' },
              { value: 240, name: '流行' },
              { value: 149, name: '文化' },
              { value: 100, name: '生活' },
              { value: 59, name: '经管' },
              {value: 78, name: '科技'}
            ],
            animationEasing: 'cubicInOut',
            animationDuration: 2600
          }
        ]
      })
    }
  }
}
</script>
