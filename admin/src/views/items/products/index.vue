<script lang="ts" setup>
import { reactive, ref, watch } from "vue"
import { getAllProductsApi, selectProductByProductNameApi,addProductsApi,deleteProductsApi,modProductsApi } from "@/api/items/products/index"
import type {AddProductsReq} from "@/api/items/products/types"
import { type ProductsData as GetTableData } from "@/api/items/products/types/index"
import { type FormInstance, type FormRules, ElMessage, ElMessageBox } from "element-plus"
import { Search, Refresh, CirclePlus, Delete, Download, RefreshRight } from "@element-plus/icons-vue"
import { usePagination } from "@/hooks/usePagination"
defineOptions({
  name: "products"
})
const loading = ref<boolean>(false)
const { paginationData, handleCurrentChange, handleSizeChange } = usePagination()

//#region 增
const dialogVisible = ref<boolean>(false)
const formRef = ref<FormInstance | null>(null)
const formData = reactive<AddProductsReq>({
  categoryId:0,
  imageId:0,
  productDescription:'',
  productDetails:'',
  productName:'',
  productPrice:0,
  productStatus:'',
  productStock:0,
})
const formRules: FormRules = reactive({
  categoryId: [{ required: true, trigger: "blur", message: "请输入种类id" }],
  imageId: [{ required: true, trigger: "blur", message: "请输入图片ID" }]
})
const handleCreate = () => {
  formRef.value?.validate((valid: boolean) => {
    if (valid) {
      if (currentUpdateId.value === undefined) {
        /**
         * 增
         */
         addProductsApi(formData).then(res=>{
          ElMessage.success("新增成功")
          dialogVisible.value = false
          getTableData()
         })
      } else {
        /**
         * 改
         */
        modProductsApi({
          productId:currentUpdateId.value,
          ...formData
        }).then(res=>{
          ElMessage.success("修改成功")
          dialogVisible.value = false
          getTableData()
        })
      }
    } else {
      return false
    }
  })
}

const resetForm = () => {
  currentUpdateId.value = undefined
  formData.categoryId=0
  formData.imageId=0,
  formData.productDescription=''
  formData.productDetails=''
  formData.productName=''
  formData.productPrice=0
  formData.productStatus=''
  formData.productStock=0
}
//#endregion

//#region 删
const handleDelete = (row: GetTableData) => {
  ElMessageBox.confirm(`正在删除用户：${row.productName}，确认删除？`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    deleteProductsApi({productId:row.productId}).then(res=>{
            ElMessage.success("删除成功")
      getTableData()
    })
  })
}
//#endregion

//#region 改
const currentUpdateId = ref<undefined | number>(undefined)
const handleUpdate = (row: GetTableData) => {
  currentUpdateId.value = row.categoryId;
  // formData.username = row.productName
  dialogVisible.value = true
}
//#endregion

//#region 查
const tableData = reactive<GetTableData[]>([])
const searchFormRef = ref<FormInstance | null>(null)
const searchData = reactive({
  productName: ""
})
const getTableData = () => {
  loading.value = true
  if (searchData.productName.length !== 0) {
    selectProductByProductNameApi(
      {
        pageNum: paginationData.currentPage,
        pageSize: paginationData.pageSize
      },
      { productName: searchData.productName }
    )
      .then((res) => {
        tableData.length = 0
        paginationData.total=res.data.totalRecords;
        tableData.push(...res.data.productList)
      })
      .catch((e) => {
        console.log(e)
        tableData.length = 0
      })
      .finally(() => {
        loading.value = false
      })
  } else {
    getAllProductsApi({
      pageSize: paginationData.pageSize,
      pageNum: paginationData.currentPage
    })
      .then((res) => {
        tableData.length = 0
        paginationData.total=res.data.totalRecords;
        tableData.push(...res.data.productList)
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

  ElMessageBox.alert(val, "商品详情", {
    confirmButtonText: "关闭"
  })
}
</script>

<template>
  <div class="app-container">
    <el-card v-loading="loading" shadow="never" class="search-wrapper">
      <el-form ref="searchFormRef" :inline="true" :model="searchData">
        <el-form-item prop="productName" label="商品名">
          <el-input v-model="searchData.productName" placeholder="请输入" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" :icon="Search" @click="handleSearch">查询</el-button>
          <el-button :icon="Refresh" @click="resetSearch">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
    <el-card v-loading="loading" shadow="never">
      <div class="toolbar-wrapper">
        <div>
          <el-button type="primary" :icon="CirclePlus" @click="dialogVisible = true">新增商品</el-button>
        </div>
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
          <el-table-column prop="productId" label="id" align="center" />
          <el-table-column prop="productName" label="商品名" align="center"> </el-table-column>
          <el-table-column prop="productDescription" label="商品描述" align="center" />
          <el-table-column prop="productPrice" label="商品价格" align="center" />
          <el-table-column prop="productStock" label="商品库存" align="center"> </el-table-column>
          <el-table-column prop="productStatus" label="商品状态" align="center" />
          <el-table-column prop="productDetails" label="商品详情" align="center">
            <template #default="scope">
              <el-button type="primary" text bg size="small" @click="showDetails(scope.row.productDetails)"
                >展示详情</el-button
              >
            </template>
          </el-table-column>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">
              <el-button type="primary" text bg size="small" @click="handleUpdate(scope.row)">修改</el-button>
              <el-button type="danger" text bg size="small" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="pager-wrapper">
        <el-pagination
          background
          :layout="paginationData.layout"
          :page-sizes="paginationData.pageSizes"
          :total="paginationData.total"
          :page-size="paginationData.pageSize"
          :currentPage="paginationData.currentPage"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
        />
      </div>
    </el-card>
    <!-- 新增/修改 -->
    <el-dialog
      v-model="dialogVisible"
      :title="currentUpdateId === undefined ? '新增商品' : '修改商品'"
      @close="resetForm"
      width="30%"
    >
      <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px" label-position="left">
        <el-form-item prop="categoryId" label="分类id">
          <el-input-number v-model="formData.categoryId" :min="0"  />
        </el-form-item>
        <el-form-item prop="imageId" label="图片id">
          <el-input-number v-model="formData.imageId" :min="0"  />
        </el-form-item>
        <el-form-item prop="productName" label="产品名称">
          <el-input v-model="formData.productName" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="productDescription" label="商品描述">
          <el-input v-model="formData.productDescription" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="productPrice" label="商品价格">
          <el-input-number v-model="formData.productPrice" :precision="2" :min="0"  />
        </el-form-item>
        <el-form-item prop="productStock" label="商品库存">
          <el-input-number v-model="formData.productStock" :min="0"  />
        </el-form-item>
        <el-form-item prop="productStatus" label="商品状态">
          <el-input v-model="formData.productStatus" placeholder="请输入" />
        </el-form-item>
        <el-form-item prop="productDetails" label="商品细节">
          <el-input v-model="formData.productDetails" placeholder="请输入" />
        </el-form-item>
      </el-form>
      <template #footer>
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleCreate">确认</el-button>
      </template>
    </el-dialog>
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
