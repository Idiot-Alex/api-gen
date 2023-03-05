<script lang="ts" setup>
import { onMounted, reactive, ref } from 'vue'
import { list } from '../api/api-log'

const tableParams = reactive({
  pageNo: 1, 
  pageSize: 20
})
const tableData = ref([])
const tabledDataTotal = ref(0)

const loadData = () => {
  list(tableParams).then(res => {
    tableData.value = res.records
    tabledDataTotal.value = res.total
  })
}

const handleSizeChange = (val: number) => {
  tableParams.pageSize = val
  loadData()
  console.log(`${val} items per page`)
}
const handleCurrentChange = (val: number) => {
  tableParams.pageNo = val
  loadData()
  console.log(`current page: ${val}`)
}

onMounted(() => {
  console.log('mounted')
  loadData()
})

const handleClick = () => {
  console.log('click')
}

</script>

<template>
  <div class="common-layout">
    <el-container>
      <el-aside width="200px">
        <AsideMenu />
      </el-aside>
      <el-container>
        <el-header>
          <TopHeader />
        </el-header>
        <el-main>
          <el-table :data="tableData" style="width: 100%">
            <el-table-column fixed prop="url" label="url" width="150" show-overflow-tooltip />
            <el-table-column prop="method" label="method" width="120" />
            <el-table-column prop="resourceType" label="resourceType" width="120" />
            <el-table-column prop="status" label="status" width="120" />
            <el-table-column prop="text" label="text" width="600" show-overflow-tooltip />
            <!-- <el-table-column fixed="right" label="Operations" width="120">
              <template #default>
                <el-button link type="primary" size="small" @click="handleClick"
                  >Detail</el-button
                >
                <el-button link type="primary" size="small">Edit</el-button>
              </template>
            </el-table-column> -->
          </el-table>
          <el-pagination
            v-model:current-page="tableParams.pageNo"
            v-model:page-size="tableParams.pageSize"
            :page-sizes="[20, 100, 200]"
            background
            layout="total, sizes, prev, pager, next"
            :total="tabledDataTotal"
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange" />
          <el-divider/>
          <nav>
            <RouterLink to="/home">Home</RouterLink>
            <RouterLink to="/about">About</RouterLink>
          </nav>
          <HelloWorld msg="You did it!" />
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<style scoped>
.logo {
  display: block;
  margin: 0 auto 2rem;
}

nav {
  width: 100%;
  font-size: 12px;
  text-align: center;
  margin-top: 2rem;
}

nav a.router-link-exact-active {
  color: var(--color-text);
}

nav a.router-link-exact-active:hover {
  background-color: transparent;
}

nav a {
  display: inline-block;
  padding: 0 1rem;
  border-left: 1px solid var(--color-border);
}

nav a:first-of-type {
  border: 0;
}
</style>