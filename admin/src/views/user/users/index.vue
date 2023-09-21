<script lang="ts" setup>
import { reactive, ref, watch } from "vue"
import { getAllUsersApi, selectUsersByuserNameApi, deleteUserApi } from "@/api/users/user"
import { type GetAllUsersData as GetTableData } from "@/api/users/user/types/index"
import { type FormInstance, ElMessage, ElMessageBox } from "element-plus"
import { Search, Refresh, Download, RefreshRight } from "@element-plus/icons-vue"
import { usePagination } from "@/hooks/usePagination"
defineOptions({
  name: "products"
})
const loading = ref<boolean>(false)
const { paginationData, handleCurrentChange, handleSizeChange } = usePagination()


//#region 删
const handleDelete = (row: GetTableData) => {
  ElMessageBox.confirm(`正在删除用户：${row.userName}，确认删除？`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    deleteUserApi({ userId: row.userId }).then(() => {
      ElMessage.success("删除成功")
      getTableData()
    })
  })
}
//#endregion


//#region 查
const tableData = reactive<GetTableData[]>([])
const searchFormRef = ref<FormInstance | null>(null)
const searchData = reactive({
  userName: ""
})
const getTableData = () => {

  loading.value = true
  if (searchData.userName.length !== 0) {
    selectUsersByuserNameApi(
      {
        pageNum: paginationData.currentPage,
        pageSize: paginationData.pageSize
      },
      { userName: searchData.userName }
    )
      .then((res) => {
        tableData.length = 0
        paginationData.total = res.data.totalRecords;
        tableData.push(...res.data.usersList)
      })
      .catch((e) => {
        console.log(e)
        tableData.length = 0
      })
      .finally(() => {
        loading.value = false
      })
  } else {
    getAllUsersApi({
      pageSize: paginationData.pageSize,
      pageNum: paginationData.currentPage
    })
      .then((res) => {
        tableData.length = 0
        paginationData.total = res.data.totalRecords;
        tableData.push(...res.data.usersList)
        console.log(res, tableData);

      })
      .catch((e) => {
        console.log(e)
        tableData.length = 0
      })
      .finally(() => {
        loading.value = false
      })
  }
}
const handleSearch = () => {
  if (paginationData.currentPage === 1) {
    getTableData()
  }
  paginationData.currentPage = 1
}
const resetSearch = () => {
  searchData.userName = '';
  searchFormRef.value?.resetFields()
  if (paginationData.currentPage === 1) {
    getTableData()
  }
  paginationData.currentPage = 1
}
const handleRefresh = () => {
  getTableData()
}
//#endregion

/** 监听分页参数的变化 */
watch([() => paginationData.currentPage, () => paginationData.pageSize], getTableData, { immediate: true })

function showDetails(val: string) {
  console.log(val)

  ElMessageBox.alert(val, "用户密码", {
    confirmButtonText: "关闭"
  })
}
</script>

<template>
  <div class="app-container">
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-form ref="searchFormRef" :inline="true" :model="searchData">
        <el-form-item prop="productName" label="用户名">
          <el-input v-model="searchData.userName" placeholder="请输入" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">查询</el-button>
          <el-button :icon="Refresh" @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="loading" shadow="never">
      <div class="toolbar-wrapper">
        <div />
        <div>
          <el-tooltip content="下载">
            <el-button type="primary" :icon="Download" circle />
          </el-tooltip>
          <el-tooltip content="刷新表格">
            <el-button type="primary" :icon="RefreshRight" circle @click="handleRefresh" />
          </el-tooltip>
        </div>
      </div>
      <div class="table-wrapper">
        <el-table :data="tableData">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column prop="userId" label="id" align="center" />
          <el-table-column prop="userName" label="用户名" align="center" />
          <el-table-column prop="userPhone" label="电话" align="center" />
          <el-table-column prop="gender" label="性别" align="center" />
          <el-table-column prop="addressId" label="地址id" align="center">
            <template #default="scope">
              <span>{{ scope.row.addressId || '-' }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="balance" label="余额" align="center">
            <template #default="scope">
              <span>{{ scope.row.balance || 0 }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="priority" label="权重" align="center" />
          <el-table-column prop="userPassword" label="密码" align="center">
            <template #default="scope">
              <el-button type="primary" text bg size="small" @click="showDetails(scope.row.userPassword)">查看密码</el-button>
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">
              <el-button type="danger" text bg size="small" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pager-wrapper">
        <el-pagination background :layout="paginationData.layout" :page-sizes="paginationData.pageSizes"
          :total="paginationData.total" :page-size="paginationData.pageSize" :currentPage="paginationData.currentPage"
          @size-change="handleSizeChange" @current-change="handleCurrentChange" />
      </div>
    </el-card>
  </div>
</template>

<style lang="scss" scoped>
.search-wrapper {
  margin-bottom: 20px;

  :deep(.el-card__body) {
    padding-bottom: 2px;
  }
}

.toolbar-wrapper {
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
}

.table-wrapper {
  margin-bottom: 20px;
}

.pager-wrapper {
  display: flex;
  justify-content: flex-end;
}
</style>
