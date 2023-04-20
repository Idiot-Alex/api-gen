<script setup lang="ts">
import { FormInstance, FormRules } from 'element-plus';
import { onMounted, reactive, ref, watch } from 'vue'
import { list, save, del } from '~/api/sys-config'
import { MyAxiosResponse } from '~/utils/types'
import { formatDateTime, calcHeight } from '~/utils/util'

const formData = reactive({
  paramKey: '',
})
const loading = ref(false)
const tableData = ref([])
const tabledDataTotal = ref(0)
const formInline = ref()
const pagination = ref()
const tableHeight = ref('300px')
const tableParams = reactive({
  pageNo: 1, 
  pageSize: 20,
  paramKey: formData.paramKey
})
const drawerVisible = ref(false)

watch(formData, (newVal) => {
  tableParams.paramKey = newVal.paramKey
})

const loadData = () => {
  loading.value = true
  list(tableParams).then((res: MyAxiosResponse) => {
    if (res.code === 0) {
      tableData.value = res.data
      tabledDataTotal.value = res.total
    }
    loading.value = false
  })
}
const onSubmit = () => {
  loadData()
}

const handleAdd = () => {
  drawerVisible.value = true
}

const handleEdit = (row: Object) => {
  drawerVisible.value = true
}

const handleDelete = (id: string) => {
  del(id).then((res: MyAxiosResponse) => {
    if (res.code === 0) {
      loadData()
    }
  })
}

const handleSizeChange = (val: number) => {
  tableParams.pageSize = val
  loadData()
}
const handleCurrentChange = (val: number) => {
  tableParams.pageNo = val
  loadData()
}

onMounted(() => {
  tableHeight.value = calcHeight(window.innerHeight, [120, formInline.value.$el.clientHeight, pagination.value.$el.clientHeight]) + 'px'
  loadData()
})

const ruleFormRef = ref<FormInstance>()
const ruleForm = reactive({
  paramKey: '',
  paramType: '',
  paramValue: '',
  description: '',
})

const rules = reactive<FormRules>({
  paramKey: [
    { required: true, message: '请输入参数名称', trigger: 'blur' },
    { min: 3, max: 20, message: '参数名称长度为 3 - 20', trigger: 'blur' },
  ],
  paramType: [
    { required: true, message: '请选择参数类型', trigger: 'change' },
  ],
  paramValue: [
    { required: true, message: '请输入参数值', trigger: 'change' },
  ],
})

const submitForm = async (formEl: FormInstance | undefined) => {
  if (!formEl) return
  await formEl.validate((valid, fields) => {
    if (valid) {
      console.log('submit!')
    } else {
      console.log('error submit!', fields)
    }
  })
}
</script>

<template>
  <div ml-5px m-a>
    <el-form :inline="true" :model="formData" ref="formInline" pt-10px text-left>
      <el-form-item label="参数名称">
        <el-input v-model="formData.paramKey" placeholder="请输入参数名称" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit">查询</el-button>
        <el-button type="success" @click="handleAdd">新增</el-button>
      </el-form-item>
    </el-form>
    <el-divider class="m-0!" />
    <el-table v-loading="loading" :data="tableData" :height="tableHeight">
      <el-table-column fixed prop="paramKey" label="参数名称" show-overflow-tooltip width="150" />
      <el-table-column prop="paramValue" label="参数值" min-width="100" />
      <el-table-column prop="paramType" label="参数类型" width="80" >
        <template #default="{row}">
          <el-tag type="info">{{ row.paramType }}</el-tag>
        </template>
      </el-table-column>
      <el-table-column prop="description" label="描述信息" width="200" />
      <el-table-column prop="createTime" label="创建时间" width="160" >
        <template #default="{row}">
          <span>{{ formatDateTime(row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="{row}">
          <el-button type="primary" size="small" @click="handleEdit(row)">编辑</el-button>
          <el-popconfirm title="是否确认删除该记录?" confirm-button-text="是" cancel-button-text="否" 
            confirm-button-type="danger" cancel-button-type="primary" width="200" 
            @confirm="handleDelete(row.idStr)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
              </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      ref="pagination"
      mt-20px
      v-model:current-page="tableParams.pageNo"
      v-model:page-size="tableParams.pageSize"
      :page-sizes="[20, 100, 200]"
      background
      layout="total, sizes, prev, pager, next"
      :total="tabledDataTotal"
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange" 
    />
    <!-- 抽屉框 -->
    <div class="api-log-detail-drawer">
      <el-drawer
        v-model="drawerVisible"
        title="配置信息"
        direction="ltr"
        size="600px"
      >
      <el-form
        ref="ruleFormRef"
        :model="ruleForm"
        :rules="rules"
        label-width="120px"
        class="demo-ruleForm"
        status-icon
      >
        <el-form-item label="参数名称" prop="paramKey">
          <el-input v-model="ruleForm.paramKey" />
        </el-form-item>
        <el-form-item label="参数类型" prop="paramType">
          <el-select v-model="ruleForm.paramType" placeholder="请选择参数类型">
            <el-option label="字符串" value="string" />
            <el-option label="对象" value="object" />
            <el-option label="数组" value="array" />
          </el-select>
        </el-form-item>
        <el-form-item label="参数值" prop="paramValue">
          <el-input v-model="ruleForm.paramValue" type="textarea" :autosize="{ minRows: 5, maxRows: 10 }" />
        </el-form-item>
        <el-form-item label="参数描述" prop="description">
          <el-input v-model="ruleForm.description" type="textarea" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm(ruleFormRef)">提交</el-button>
        </el-form-item>
      </el-form>
      </el-drawer>
    </div>
  </div>
</template>

<style scoped>

</style>