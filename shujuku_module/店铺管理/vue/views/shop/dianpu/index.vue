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
      <el-form-item label="店铺地址" prop="shopAddress">
        <el-input
          v-model="queryParams.shopAddress"
          placeholder="请输入店铺地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="店铺电话" prop="shopPhone">
        <el-input
          v-model="queryParams.shopPhone"
          placeholder="请输入店铺电话"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="营业状态" prop="shopStatus">
        <el-select v-model="queryParams.shopStatus" placeholder="请选择营业状态" clearable>
          <el-option
            v-for="dict in yingye_status"
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
          v-hasPermi="['shop:dianpu:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['shop:dianpu:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['shop:dianpu:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['shop:dianpu:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="dianpuList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="店铺ID" align="center" prop="shopId" />
      <el-table-column label="店铺名称" align="center" prop="shopName" />
      <el-table-column label="店铺地址" align="center" prop="shopAddress" />
      <el-table-column label="店铺电话" align="center" prop="shopPhone" />
      <el-table-column label="营业状态" align="center" prop="shopStatus">
        <template #default="scope">
          <dict-tag :options="yingye_status" :value="scope.row.shopStatus"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['shop:dianpu:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['shop:dianpu:remove']">删除</el-button>
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

    <!-- 添加或修改店铺管理对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="dianpuRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="店铺名称" prop="shopName">
          <el-input v-model="form.shopName" placeholder="请输入店铺名称" />
        </el-form-item>
        <el-form-item label="店铺地址" prop="shopAddress">
          <el-input v-model="form.shopAddress" placeholder="请输入店铺地址" />
        </el-form-item>
        <el-form-item label="店铺电话" prop="shopPhone">
          <el-input v-model="form.shopPhone" placeholder="请输入店铺电话" />
        </el-form-item>
        <el-form-item label="营业状态" prop="shopStatus">
          <el-select v-model="form.shopStatus" placeholder="请选择营业状态">
            <el-option
              v-for="dict in yingye_status"
              :key="dict.value"
              :label="dict.label"
              :value="parseInt(dict.value)"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-divider content-position="center">菜品管理信息</el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button type="primary" icon="Plus" @click="handleAddDish">添加</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button type="danger" icon="Delete" @click="handleDeleteDish">删除</el-button>
          </el-col>
        </el-row>
        <el-table :data="dishList" :row-class-name="rowDishIndex" @selection-change="handleDishSelectionChange" ref="dish">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="售价" prop="price" width="150">
            <template #default="scope">
              <el-input v-model="scope.row.price" placeholder="请输入售价" />
            </template>
          </el-table-column>
          <el-table-column label="售卖状态" prop="status" width="150">
            <template #default="scope">
              <el-select v-model="scope.row.status" placeholder="请选择售卖状态">
                <el-option
                  v-for="dict in dish_status"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                ></el-option>
              </el-select>
            </template>
          </el-table-column>
          <el-table-column label="更新时间" prop="updateTime" width="240">
            <template #default="scope">
              <el-date-picker clearable
                v-model="scope.row.updateTime"
                type="date"
                value-format="YYYY-MM-DD"
                placeholder="请选择更新时间">
              </el-date-picker>
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

<script setup name="Dianpu">
import { listDianpu, getDianpu, delDianpu, addDianpu, updateDianpu } from "@/api/shop/dianpu";

const { proxy } = getCurrentInstance();
const { yingye_status, dish_status } = proxy.useDict('yingye_status', 'dish_status');

const dianpuList = ref([]);
const dishList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const checkedDish = ref([]);
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
    shopAddress: null,
    shopPhone: null,
    shopStatus: null
  },
  rules: {
    shopName: [
      { required: true, message: "店铺名称不能为空", trigger: "blur" }
    ],
    shopAddress: [
      { required: true, message: "店铺地址不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询店铺管理列表 */
function getList() {
  loading.value = true;
  listDianpu(queryParams.value).then(response => {
    dianpuList.value = response.rows;
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
    shopId: null,
    shopName: null,
    shopAddress: null,
    shopPhone: null,
    shopStatus: null
  };
  dishList.value = [];
  proxy.resetForm("dianpuRef");
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
  ids.value = selection.map(item => item.shopId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加店铺管理";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _shopId = row.shopId || ids.value
  getDianpu(_shopId).then(response => {
    form.value = response.data;
    dishList.value = response.data.dishList;
    open.value = true;
    title.value = "修改店铺管理";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["dianpuRef"].validate(valid => {
    if (valid) {
      form.value.dishList = dishList.value;
      if (form.value.shopId != null) {
        updateDianpu(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addDianpu(form.value).then(response => {
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
  const _shopIds = row.shopId || ids.value;
  proxy.$modal.confirm('是否确认删除店铺管理编号为"' + _shopIds + '"的数据项？').then(function() {
    return delDianpu(_shopIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 菜品管理序号 */
function rowDishIndex({ row, rowIndex }) {
  row.index = rowIndex + 1;
}

/** 菜品管理添加按钮操作 */
function handleAddDish() {
  let obj = {};
  obj.price = "";
  obj.image = "";
  obj.status = "";
  obj.updateTime = "";
  dishList.value.push(obj);
}

/** 菜品管理删除按钮操作 */
function handleDeleteDish() {
  if (checkedDish.value.length == 0) {
    proxy.$modal.msgError("请先选择要删除的菜品管理数据");
  } else {
    const dishs = dishList.value;
    const checkedDishs = checkedDish.value;
    dishList.value = dishs.filter(function(item) {
      return checkedDishs.indexOf(item.index) == -1
    });
  }
}

/** 复选框选中数据 */
function handleDishSelectionChange(selection) {
  checkedDish.value = selection.map(item => item.index)
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('shop/dianpu/export', {
    ...queryParams.value
  }, `dianpu_${new Date().getTime()}.xlsx`)
}

getList();
</script>
