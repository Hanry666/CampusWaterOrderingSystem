<script lang="ts" setup>
import { reactive, ref, watch } from "vue";
import { addCategoryApi, deleteCategoryApi, getAllCategoriesApi, modCategoryApi } from "@/api/items/category/index";
import type { addCategoryReq } from "@/api/items/category/types";
import { type CategoryData as GetTableData } from "@/api/items/category/types/index";
import { type FormInstance, type FormRules, ElMessage, ElMessageBox } from "element-plus";
import { Search, Refresh, CirclePlus, Delete, Download, RefreshRight } from "@element-plus/icons-vue";
defineOptions({
  name: "products"
});
const loading = ref<boolean>(false);

//#region 增
const dialogVisible = ref<boolean>(false);
const formRef = ref<FormInstance | null>(null);
const formData = reactive<addCategoryReq>({
  categoryName: ""
});
const formRules: FormRules = reactive({
  categoryId: [{ required: true, trigger: "blur", message: "请输入种类id" }],
  imageId: [{ required: true, trigger: "blur", message: "请输入图片ID" }]
});
const handleCreate = () => {
  formRef.value?.validate((valid: boolean) => {
    if (valid) {
      if (currentUpdateId.value === undefined) {
        /**
         * 增
         */
        addCategoryApi(formData).then((res) => {
          console.log(res);

          ElMessage.success("新增成功");
          dialogVisible.value = false;
          getTableData();
        });
      } else {
        /**
         * 改
         */
        modCategoryApi({
          categoryId: currentUpdateId.value,
          ...formData
        }).then((res) => {
          ElMessage.success("修改成功");
          dialogVisible.value = false;
          getTableData();
        });
      }
    } else {
      return false;
    }
  });
};

const resetForm = () => {
  currentUpdateId.value = undefined;
  formData.categoryName = "";
};
//#endregion

//#region 删
const handleDelete = (row: GetTableData) => {
  ElMessageBox.confirm(`正在删除分类：${row.categoryName}，确认删除？`, "提示", {
    confirmButtonText: "确定",
    cancelButtonText: "取消",
    type: "warning"
  }).then(() => {
    deleteCategoryApi({ categoryId: row.categoryId }).then((res) => {
      ElMessage.success("删除成功");
      getTableData();
    });
  });
};
//#endregion

//#region 改
const currentUpdateId = ref<undefined | number>(undefined);
const handleUpdate = (row: GetTableData) => {
  currentUpdateId.value = row.categoryId;
  // formData.username = row.productName
  dialogVisible.value = true;
};
//#endregion

//#region 查
const tableData = reactive<GetTableData[]>([]);
const searchFormRef = ref<FormInstance | null>(null);
const searchData = reactive({
  productName: ""
});
const getTableData = () => {
  loading.value = true;
  getAllCategoriesApi()
    .then((res) => {
      tableData.length = 0;
      tableData.push(...res.data);
      console.log(res, tableData);
    })
    .catch((e) => {
      console.log(e);
      tableData.length = 0;
    })
    .finally(() => {
      loading.value = false;
    });
};
const handleRefresh = () => {
  getTableData();
};

getTableData();
//#endregion
</script>

<template>
  <div class="app-container">
    <el-card v-loading="loading" shadow="never">
      <div class="toolbar-wrapper">
        <div>
          <el-button type="primary" :icon="CirclePlus" @click="dialogVisible = true">新增分类</el-button>
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
          <el-table-column prop="categoryId" label="id" align="center" />
          <el-table-column prop="categoryName" label="分类名" align="center"/>
          <el-table-column fixed="right" label="操作" width="150" align="center">
            <template #default="scope">
              <el-button type="primary" text bg size="small" @click="handleUpdate(scope.row)">修改</el-button>
              <el-button type="danger" text bg size="small" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
    </el-card>
    <!-- 新增/修改 -->
    <el-dialog
      v-model="dialogVisible"
      :title="currentUpdateId === undefined ? '新增分类' : '修改分类'"
      @close="resetForm"
      width="30%"
    >
      <el-form ref="formRef" :model="formData" :rules="formRules" label-width="100px" label-position="left">
        <el-form-item prop="categoryName" label="分类名">
          <el-input v-model="formData.categoryName" :min="0" />
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
