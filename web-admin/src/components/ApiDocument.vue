<script setup lang="ts">
import { FormInstance, FormRules, TabsPaneContext } from 'element-plus'
import { onMounted, reactive, ref } from 'vue'
import { statistics } from '~/api/api-log'
import { MyAxiosResponse } from '~/utils/types'

const data = ref({
  hostList: [{host: 'no host', count: 0}],
  siteList: [{site: 'no site', count: 0}]
})
const loadStatistics = () => {
  statistics().then((res: MyAxiosResponse) => {
    if (res.code === 0) {
      data.value = res.data
    }
  })
}

onMounted(() => {
  loadStatistics()
})

const checkList = ref([])
const tabActive: any = ref('first')
const handleTab = (tab: TabsPaneContext) => {
  console.log(tab)
  tabActive.value = tab.paneName
  checkList.value = []
}
const activeStep = ref(0)
const handlePrevStep = () => {
  activeStep.value--
}
const handleNextStep = () => {
  activeStep.value++
}
const drawerVisible = ref(false)
const handleAdd = () => {
  activeStep.value = 0
  drawerVisible.value = true
}
const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  name: '',
  description: '',
})
const rules = reactive<FormRules>({
  paramKey: [
    { required: true, message: '请输入 Api 文档名称', trigger: 'blur' },
    { min: 3, max: 50, message: '名称长度为 3 - 50', trigger: 'blur' },
  ],
  description: [
    { required: true, message: '请输入描述信息', trigger: 'change' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      
    }
  })
}
</script>

<template>
  <div m-10px>
    <el-button type="primary" @click="handleAdd">新建 Api 文档</el-button>
    <el-divider mt-2 mb-2/>
    <el-row :gutter="10">
      <el-col :xs="8" :sm="6" :md="4" :lg="4" :xl="4"  v-for="o in 10" :key="o">
        <el-card mb-5px class="box-card">
          <template #header>
            <span>文档标题</span>
          </template>
          <p class="text item">一共 29 个 Api</p>
          <p class="text item">refer：www.baidu.com</p>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col :span="6">
        <el-menu
          class="el-menu-vertical-demo">
          <el-sub-menu index="1">
            <template #title>
              <el-icon><location /></el-icon>
              <span>Navigator One</span>
            </template>
            <el-menu-item-group title="Group One">
              <el-menu-item index="1-1">item one</el-menu-item>
              <el-menu-item index="1-2">item two</el-menu-item>
            </el-menu-item-group>
            <el-menu-item-group title="Group Two">
              <el-menu-item index="1-3">item three</el-menu-item>
            </el-menu-item-group>
            <el-sub-menu index="1-4">
              <template #title>item four</template>
              <el-menu-item index="1-4-1">item one</el-menu-item>
            </el-sub-menu>
          </el-sub-menu>
          <el-menu-item index="2">
            <el-icon><icon-menu /></el-icon>
            <span>Navigator Two</span>
          </el-menu-item>
          <el-menu-item index="3" disabled>
            <el-icon><document /></el-icon>
            <span>Navigator Three</span>
          </el-menu-item>
          <el-menu-item index="4">
            <el-icon><setting /></el-icon>
            <span>Navigator Four</span>
          </el-menu-item>
        </el-menu>
      </el-col>
      <el-col :span="18">
        <p>sfasdf</p>
      </el-col>
    </el-row>
    <!-- 抽屉框 -->
    <div class="api-log-detail-drawer">
      <el-drawer
        v-model="drawerVisible"
        title="新建 Api 文档"
        direction="ltr"
        size="800px">
        <el-steps mb-10px align-center :space="200" :active="activeStep" finish-status="success">
          <el-step title="基础属性" />
          <el-step title="选择 Api" />
        </el-steps>
        <el-form
          ref="ruleFormRef"
          :model="ruleForm"
          :rules="rules"
          label-position="top"
          label-width="120px"
          class="demo-ruleForm"
          status-icon>
          <el-form-item v-show="activeStep == 0" label="文档名称" prop="name">
            <el-input v-model="ruleForm.name" />
          </el-form-item>
          <el-form-item v-show="activeStep == 0" label="描述信息" prop="description">
            <el-input v-model="ruleForm.description" type="textarea" />
          </el-form-item>
          <el-form-item v-show="activeStep == 1">
            <el-tabs v-model="tabActive" mb-10px tab-position="left" @tab-click="handleTab">
              <el-tab-pane label="Host" name="first">
                <el-card>
                  <el-badge m-10px v-for="item in data.hostList" :key="item.host" :value="item.count" type="info">
                    <el-checkbox-group v-model="checkList">
                      <el-checkbox :label="item.host" border>{{ item.host }}</el-checkbox>
                    </el-checkbox-group>
                  </el-badge>
                </el-card>
              </el-tab-pane>
              <el-tab-pane label="Site" name="second">
                <el-card>
                  <el-badge m-10px v-for="item in data.siteList" :key="item.site" :value="item.count" type="info">
                    <el-checkbox-group v-model="checkList">
                      <el-checkbox :label="item.site" border>{{ item.site }}</el-checkbox>
                    </el-checkbox-group>
                  </el-badge>
                </el-card>
              </el-tab-pane>
            </el-tabs>
            <el-alert :closable="false" :title="`当前已选择 ${checkList.length} 个 Api`" type="warning" />
          </el-form-item>
          <el-form-item>
            <el-button :disabled="activeStep <= 0" type="primary" @click="handlePrevStep">上一步</el-button>
            <el-button :disabled="activeStep >= 2" type="primary" @click="handleNextStep">下一步</el-button>
          </el-form-item>
        </el-form>
      </el-drawer>
    </div>
  </div>
</template>

<style scoped>

</style>

<style>
.api-log-detail-drawer .ep-drawer__header {
  margin-bottom: 0;
}
</style>