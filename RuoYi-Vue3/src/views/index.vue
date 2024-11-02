<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12">
        <blockquote class="text-warning" style="font-size: 24px">
          GXU外卖管理系统    
          <p style="font-size: 16px; color: black;">本管理系统基于若依前后端分离版进行二次开发，并运用了通义灵码以及若依自带的代码生成器进行开发</p>
          美团
          <el-link href="https://www.meituan.com/" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          >
          <!-- &nbsp;&nbsp;&nbsp; 腾讯云服务器秒杀区
          <el-link href="http://txy.ruoyi.vip" type="primary" target="_blank"
            >>☛☛点我进入☚☚</el-link
          ><br /> -->
          <!-- <h4 class="text-danger">
            云产品通用红包，可叠加官网常规优惠使用。(仅限新用户)
          </h4> --> 
        </blockquote>
        <hr />
      </el-col>
    </el-row>
    
    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <!-- <h2>若依后台管理框架</h2>
        <p>
          一直想做一款后台管理系统，看了很多优秀的开源项目但是发现没有合适自己的。于是利用空闲休息时间开始自己写一套后台系统。如此有了若依管理系统，她可以用于所有的Web应用程序，如网站管理后台，网站会员中心，CMS，CRM，OA等等，当然，您也可以对她进行深度定制，以做出更强系统。所有前端后台代码封装过后十分精简易上手，出错概率低。同时支持移动客户端访问。系统会陆续更新一些实用功能。
        </p>
        <p>
          <b>当前版本:</b> <span>v{{ version }}</span>
        </p>
        <p>
          <el-tag type="danger">&yen;免费开源</el-tag>
        </p> -->
        <p>
          <el-button
            type="primary"
            icon="Cloudy"
            plain
            @click="goTarget('https://www.gxu.edu.cn/')"
            >访问GXU主页</el-button
          >
          <el-button
            icon="HomeFilled"
            plain
            @click="goTarget('https://scei.gxu.edu.cn/')"
            >访问计电学院主页</el-button
          >
          <el-button
            icon="HomeFilled"
            plain
            @click="goTarget('https://ruoyi.vip/')"
            >访问若依开源官网</el-button
          >
        </p>
      </el-col>

      <el-col :sm="24" :lg="12" style="padding-left: 50px">
        <el-row>
          <el-col :span="12">
            <h2>技术选型</h2>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="6">
            <h4>后端技术</h4>
            <ul>
              <li>SpringBoot</li>
              <li>Spring Security</li>
              <li>JWT</li>
              <li>MyBatis</li>
              <li>Druid</li>
              <li>Fastjson</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>前端技术</h4>
            <ul>
              <li>Vue</li>
              <li>Vuex</li>
              <li>Element-ui</li>
              <li>Axios</li>
              <li>Sass</li>
              <li>Quill</li>
              <li>...</li>
            </ul>
          </el-col>
          <el-col :span="6">
            <h4>其它配置</h4>
            <ul>
              <li>MySQL</li>
              <li>Navicat</li>
              <li>Redis</li>
              <li>JavaJDK1.8</li>
            </ul>
          </el-col>
        </el-row>
      </el-col>
    </el-row>
    <el-divider />
    <el-row :gutter="20">
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>联系信息</span>
            </div>
          </template>
          <div class="body">
            <p>
              <i class="el-icon-s-promotion"></i> 官网：<el-link
                href="http://www.gxu.edu.cn"
                target="_blank"
                >http://www.gxu.edu.cn</el-link
              >
            </p>
            <p>
              <i class="el-icon-user-solid"></i> QQ群：<s> 满937441 </s> <s> 满887144332 </s> 
              <s> 满101046199 </s> <s> 满136919097 </s> <s> 满143961921 </s> <s> 满174951577 </s> <a href="http://qm.qq.com/cgi-bin/qm/qr?_wv=1027&k=Lj0nHRAPD5CZv1jTOciuxXVloBJLS2Lp&authKey=Q2RxC6%2Ffxney9yOGBY0sDJxFhX9b7o1FRY1bsESmkbcZ4PFt6Vx92FpVo9O1u9p4&noverify=0&group_code=161281055" target="_blank">161281055</a>
            </p>
            <p>
              <i class="el-icon-chat-dot-round"></i> 微信：<a
                href="javascript:;"
                >/ *杨志颖</a
              >
            </p>
            <p>
              <i class="el-icon-money"></i> 支付宝：<a
                href="javascript:;"
                class="支付宝信息"
                >/ *王天顺</a
              >
            </p>
          </div>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>更新日志</span>
            </div>
          </template>
          <el-collapse accordion>
            <!-- <el-collapse-item title="v3.8.7 - 2023-12-08">
            </el-collapse-item> -->
            <el-collapse-item title="v1.0.5 - 2024-10-28">
              <ol>
                <li>新增店铺信息模块，完成店铺的增删查改功能，并创建了新的数据字典</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.4 - 2024-10-25">
              <ol>
                <li>新增物流信息模块，完成物流的增删查改功能，并创建了新的数据字典</li>
                <li>同时将物流表与调度员表进行关联，主表为物流表，子表为调度员表</li>
              </ol>
            </el-collapse-item>
            
            <el-collapse-item title="v1.0.3 - 2024-10-25">
              <ol>
                <li>新增订单管理模块，完成订单的增删查改功能，并创建了新的数据字典</li>
                <li>订餐方式可选择线上订餐或者线下订餐</li>
              </ol>
            </el-collapse-item>

            <el-collapse-item title="v1.0.2 - 2024-10-24">
              <ol>
                <li>新增员工管理模块，完成员工的增删查改功能，并创建了新的数据字典</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.1 - 2024-10-04">
              <ol>
                <li>新增菜品管理模块</li>
                <li>新增售卖状态字典</li>
              </ol>
            </el-collapse-item>
            <el-collapse-item title="v1.0.0 - 2024-10-02">
              <ol>
                <li>GXU外卖管理系统正式发布</li>
              </ol>
            </el-collapse-item>
          </el-collapse>
        </el-card>
      </el-col>
      <el-col :xs="24" :sm="24" :md="12" :lg="8">
        <el-card class="update-log">
          <template v-slot:header>
            <div class="clearfix">
              <span>技术支持</span>
            </div>
          </template>
          <div class="body">
            <img
              src="@/assets/images/QQ.jpg"
              alt="donate"
              style="width:50%"
            />
            <span style="display: inline-block; height: 30px; line-height: 30px"
              >你可以添加作者QQ进行交流</span
            >
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
const version = ref('3.8.7')

function goTarget(url) {
  window.open(url, '__blank')
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }
}
</style>

