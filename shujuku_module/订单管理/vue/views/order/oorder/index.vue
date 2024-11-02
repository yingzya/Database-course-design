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
      <el-form-item label="订单金额" prop="orderMoney">
        <el-input
          v-model="queryParams.orderMoney"
          placeholder="请输入订单金额"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订餐方式" prop="orderWay">
        <el-select v-model="queryParams.orderWay" placeholder="请选择订餐方式" clearable>
          <el-option
            v-for="dict in order_way"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="顾客电话" prop="consPhone">
        <el-input
          v-model="queryParams.consPhone"
          placeholder="请输入顾客电话"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顾客姓名" prop="consName">
        <el-input
          v-model="queryParams.consName"
          placeholder="请输入顾客姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="顾客地址" prop="consAddre">
        <el-input
          v-model="queryParams.consAddre"
          placeholder="请输入顾客地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="审核状态" prop="checked">
        <el-select v-model="queryParams.checked" placeholder="请选择审核状态" clearable>
          <el-option
            v-for="dict in checked"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
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
          v-hasPermi="['order:oorder:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:oorder:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:oorder:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['order:oorder:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="oorderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单编号" align="center" prop="orderId" />
      <el-table-column label="店铺名称" align="center" prop="shopName" />
      <el-table-column label="订单金额" align="center" prop="orderMoney" />
      <el-table-column label="订餐方式" align="center" prop="orderWay">
        <template #default="scope">
          <dict-tag :options="order_way" :value="scope.row.orderWay"/>
        </template>
      </el-table-column>
      <el-table-column label="顾客电话" align="center" prop="consPhone" />
      <el-table-column label="顾客姓名" align="center" prop="consName" />
      <el-table-column label="顾客地址" align="center" prop="consAddre" />
      <el-table-column label="审核状态" align="center" prop="checked">
        <template #default="scope">
          <dict-tag :options="checked" :value="scope.row.checked"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['order:oorder:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['order:oorder:remove']">删除</el-button>
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

    <!-- 添加或修改订单管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="oorderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="form.shopName" placeholder="请输入店铺名称" />
        </el-form-item>
        <el-form-item label="订单金额" prop="orderMoney">
          <el-input v-model="form.orderMoney" placeholder="请输入订单金额" />
        </el-form-item>
        <el-form-item label="订餐方式" prop="orderWay">
          <el-select v-model="form.orderWay" placeholder="请选择订餐方式">
            <el-option
              v-for="dict in order_way"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="顾客电话" prop="consPhone">
          <el-input v-model="form.consPhone" placeholder="请输入顾客电话" />
        </el-form-item>
        <el-form-item label="顾客姓名" prop="consName">
          <el-input v-model="form.consName" placeholder="请输入顾客姓名" />
        </el-form-item>
        <el-form-item label="顾客地址" prop="consAddre">
          <el-input v-model="form.consAddre" placeholder="请输入顾客地址" />
        </el-form-item>
        <el-form-item label="审核状态" prop="checked">
          <el-select v-model="form.checked" placeholder="请选择审核状态">
            <el-option
              v-for="dict in checked"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
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

<script setup name="Oorder">
import { listOorder, getOorder, delOorder, addOorder, updateOorder } from "@/api/order/oorder";

const { proxy } = getCurrentInstance();
const { order_way, checked } = proxy.useDict('order_way', 'checked');

const oorderList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
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
    orderMoney: null,
    orderWay: null,
    consPhone: null,
    consName: null,
    consAddre: null,
    checked: null,
  },
  rules: {
    shopName: [
      { required: true, message: "店铺名称不能为空", trigger: "blur" }
    ],
    orderMoney: [
      { required: true, message: "订单金额不能为空", trigger: "blur" }
    ],
    orderWay: [
      { required: true, message: "订餐方式不能为空", trigger: "change" }
    ],
    consPhone: [
      { required: true, message: "顾客电话不能为空", trigger: "blur" }
    ],
    consName: [
      { required: true, message: "顾客姓名不能为空", trigger: "blur" }
    ],
    consAddre: [
      { required: true, message: "顾客地址不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询订单管理列表 */
function getList() {
  loading.value = true;
  listOorder(queryParams.value).then(response => {
    oorderList.value = response.rows;
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
    orderId: null,
    shopName: null,
    orderMoney: null,
    orderWay: null,
    consPhone: null,
    consName: null,
    consAddre: null,
    checked: null,
    createTime: null
  };
  proxy.resetForm("oorderRef");
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
  ids.value = selection.map(item => item.orderId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加订单管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _orderId = row.orderId || ids.value
  getOorder(_orderId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改订单管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["oorderRef"].validate(valid => {
    if (valid) {
      if (form.value.orderId != null) {
        updateOorder(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addOorder(form.value).then(response => {
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
  const _orderIds = row.orderId || ids.value;
  proxy.$modal.confirm('是否确认删除订单管理编号为"' + _orderIds + '"的数据项？').then(function() {
    return delOorder(_orderIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/oorder/export', {
    ...queryParams.value
  }, `oorder_${new Date().getTime()}.xlsx`)
}

getList();
</script>
