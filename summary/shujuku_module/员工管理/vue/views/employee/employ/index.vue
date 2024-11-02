<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工姓名" prop="employeeName">
        <el-input
          v-model="queryParams.employeeName"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="员工账号" prop="account">
        <el-input
          v-model="queryParams.account"
          placeholder="请输入员工账号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phoneNumber">
        <el-input
          v-model="queryParams.phoneNumber"
          placeholder="请输入手机号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="账号状态" prop="accountStatus">
        <el-select v-model="queryParams.accountStatus" placeholder="请选择账号状态" clearable>
          <el-option
            v-for="dict in employ_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="最后操作时间" prop="lastOperationTime">
        <el-date-picker clearable
          v-model="queryParams.lastOperationTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择最后操作时间">
        </el-date-picker>
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
          v-hasPermi="['employee:employ:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['employee:employ:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['employee:employ:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['employee:employ:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="员工工号" align="center" prop="employeeId" />
      <el-table-column label="员工姓名" align="center" prop="employeeName" />
      <el-table-column label="员工账号" align="center" prop="account" />
      <el-table-column label="手机号" align="center" prop="phoneNumber" />
      <el-table-column label="账号状态" align="center" prop="accountStatus">
        <template #default="scope">
          <dict-tag :options="employ_status" :value="scope.row.accountStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="最后操作时间" align="center" prop="lastOperationTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.lastOperationTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['employee:employ:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['employee:employ:remove']">删除</el-button>
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

    <!-- 添加或修改员工管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="employRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工姓名" prop="employeeName">
          <el-input v-model="form.employeeName" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="员工账号" prop="account">
          <el-input v-model="form.account" placeholder="请输入员工账号" />
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNumber">
          <el-input v-model="form.phoneNumber" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="账号状态" prop="accountStatus">
          <el-select v-model="form.accountStatus" placeholder="请选择账号状态">
            <el-option
              v-for="dict in employ_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="最后操作时间" prop="lastOperationTime">
          <el-date-picker clearable
            v-model="form.lastOperationTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择最后操作时间">
          </el-date-picker>
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

<script setup name="Employ">
import { listEmploy, getEmploy, delEmploy, addEmploy, updateEmploy } from "@/api/employee/employ";

const { proxy } = getCurrentInstance();
const { employ_status } = proxy.useDict('employ_status');

const employList = ref([]);
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
    employeeName: null,
    account: null,
    phoneNumber: null,
    accountStatus: null,
    lastOperationTime: null
  },
  rules: {
    employeeName: [
      { required: true, message: "员工姓名不能为空", trigger: "blur" }
    ],
    account: [
      { required: true, message: "员工账号不能为空", trigger: "blur" }
    ],
    phoneNumber: [
      { required: true, message: "手机号不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询员工管理列表 */
function getList() {
  loading.value = true;
  listEmploy(queryParams.value).then(response => {
    employList.value = response.rows;
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
    employeeId: null,
    employeeName: null,
    account: null,
    phoneNumber: null,
    accountStatus: null,
    lastOperationTime: null
  };
  proxy.resetForm("employRef");
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
  ids.value = selection.map(item => item.employeeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加员工管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _employeeId = row.employeeId || ids.value
  getEmploy(_employeeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改员工管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["employRef"].validate(valid => {
    if (valid) {
      if (form.value.employeeId != null) {
        updateEmploy(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addEmploy(form.value).then(response => {
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
  const _employeeIds = row.employeeId || ids.value;
  proxy.$modal.confirm('是否确认删除员工管理编号为"' + _employeeIds + '"的数据项？').then(function() {
    return delEmploy(_employeeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('employee/employ/export', {
    ...queryParams.value
  }, `employ_${new Date().getTime()}.xlsx`)
}

getList();
</script>
