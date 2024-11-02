<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="店铺名称" prop="shopName">
        <el-input
          v-model="queryParams.shopName"
          placeholder="请输入店铺名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单编号" prop="orderId">
        <el-input
          v-model="queryParams.orderId"
          placeholder="请输入订单编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="调度员编号" prop="dispatcherId">
        <el-input
          v-model="queryParams.dispatcherId"
          placeholder="请输入调度员编号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['logistics:wuliu:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['logistics:wuliu:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['logistics:wuliu:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['logistics:wuliu:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="wuliuList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="物流编号" align="center" prop="wuliuId" />
      <el-table-column label="店铺名称" align="center" prop="shopName" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="调度员编号" align="center" prop="dispatcherId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['logistics:wuliu:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['logistics:wuliu:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改物流信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="wuliuRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="form.shopName" placeholder="请输入店铺名称" />
        </el-form-item>
        <el-form-item label="订单编号" prop="orderId">
          <el-input v-model="form.orderId" placeholder="请输入订单编号" />
        </el-form-item>
        <el-form-item label="调度员编号" prop="dispatcherId">
          <el-input v-model="form.dispatcherId" placeholder="请输入调度员编号" />
        </el-form-item>
        <el-divider content-position="center">调度员信息信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddDispatcher">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteDispatcher">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="dispatcherList" :row-class-name="rowDispatcherIndex" @selection-change="handleDispatcherSelectionChange" ref="dispatcher">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="调度员姓名" prop="dispatcherName" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.dispatcherName" placeholder="请输入调度员姓名" />
            </template>
          </el-table-column>
          <el-table-column label="调度员电话" prop="dispatcherPhone" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.dispatcherPhone" placeholder="请输入调度员电话" />
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Wuliu">
import { listWuliu, getWuliu, delWuliu, addWuliu, updateWuliu } from "@/api/logistics/wuliu";

const { proxy } = getCurrentInstance();

const wuliuList = ref([]);
const dispatcherList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedDispatcher = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    shopName: null,
    orderId: null,
    dispatcherId: null
  },
  rules: {
    shopName: [
      { required: true, message: "店铺名称不能为空", trigger: "blur" }
    ],
    orderId: [
      { required: true, message: "订单编号不能为空", trigger: "blur" }
    ],
    dispatcherId: [
      { required: true, message: "调度员编号不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询物流信息列表 */
function getList() {
  loading.value = true;
  listWuliu(queryParams.value).then(response => {
    wuliuList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    wuliuId: null,
    shopName: null,
    orderId: null,
    dispatcherId: null
  };
  dispatcherList.value = [];
  proxy.resetForm("wuliuRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.wuliuId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加物流信息";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _wuliuId = row.wuliuId || ids.value
  getWuliu(_wuliuId).then(response => {
    form.value = response.data;
    dispatcherList.value = response.data.dispatcherList;
    open.value = true;
    title.value = "修改物流信息";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["wuliuRef"].validate(valid => {
    if (valid) {
      form.value.dispatcherList = dispatcherList.value;
      if (form.value.wuliuId != null) {
        updateWuliu(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addWuliu(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _wuliuIds = row.wuliuId || ids.value;
  proxy.$modal.confirm('是否确认删除物流信息编号为"' + _wuliuIds + '"的数据项？').then(function() {
    return delWuliu(_wuliuIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 调度员信息序号 */
function rowDispatcherIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 调度员信息添加按钮操作 */
function handleAddDispatcher() {
  let obj = {};
  obj.dispatcherName = "";
  obj.dispatcherPhone = "";
  dispatcherList.value.push(obj);
}

/** 调度员信息删除按钮操作 */
function handleDeleteDispatcher() {
  if (checkedDispatcher.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的调度员信息数据");
  } else {
    const dispatchers = dispatcherList.value;
    const checkedDispatchers = checkedDispatcher.value;
    dispatcherList.value = dispatchers.filter(function(item) {
      return checkedDispatchers.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleDispatcherSelectionChange(selection) {
  checkedDispatcher.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('logistics/wuliu/export', {
    ...queryParams.value
  }, `wuliu_${new Date().getTime()}.xlsx`)
}

getList();
</script>
