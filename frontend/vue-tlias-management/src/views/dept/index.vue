<script setup>
import { ref, onMounted } from 'vue';
import { queryAllApi, addApi, queryByIdApi, updateApi, deleteByIdApi } from '@/api/dept';
import { ElMessage, ElMessageBox } from 'element-plus';


//钩子函数
onMounted(() => {
  search();
})
//查询
const search = async () => {
  const resule = await queryAllApi();
  if (resule.code) {//可以将“==1”去掉 JS隐式类型转换 0-false,其他数字-true;''-false,其他都是true;null,undefined--false
    deptList.value = resule.data;
  }

}
const deptList = ref([])

//Dialog对话框
const dialogFormVisible = ref(false);
const formTitle = ref('');
const dept = ref({
  name: ''
});

const addDept = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增部门';
  dept.value.name = '';

  //清空校验状态
  if (deptFormRef.value) {//如果deptFormRef.value存在
    deptFormRef.value.clearValidate();
  }
}

const save = async () => {
  //校验表单
  if (!deptFormRef.value.validate()) {//校验失败
    return;
  }
  deptFormRef.value.validate(async (valid) => {//表示是否校验通过
    if (valid) {
      //校验成功
      let resule;
      if (dept.value.id) {
        //根据是否有id来判断是添加还是修改
        resule = await updateApi(dept.value);
      } else {
        //新增
        resule = await addApi(dept.value);
      }
      if (resule.code) {
        //提示信息
        ElMessage.success('操作成功');
        //关闭对话框
        dialogFormVisible.value = false;
        //查询
        search();
      } else {//失败
        ElMessage.error('操作失败');
      }
    } else {
      ElMessage.error('表单校验不通过');
    }
  });


};

//表单校验规则
const rules = ref({
  name: [
    { required: true, message: '部门名称是必填项', trigger: 'blur' },
    { min: 2, max: 10, message: '部门名称长度应该在2-10位', trigger: 'blur' },
  ]
})
const deptFormRef = ref();

//编辑
const edit = async (id) => {
  formTitle.value = '修改部门';
  //清空校验状态
  if (deptFormRef.value) {//如果deptFormRef.value存在
    deptFormRef.value.clearValidate();
  }

  const resule = await queryByIdApi(id);
  if (resule.code) {
    dialogFormVisible.value = true;
    dept.value = resule.data;
  }
}

//删除
const delById = async (id) => {
  //弹出确认框
  ElMessageBox.confirm('确定删除吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    //确认删除
    const resule = await deleteByIdApi(id);
    if (resule.code) {
      ElMessage.success('操作成功');
      //查询
      search();
    } else {//失败
      ElMessage.error('操作失败');
    }
  }
  ).catch(() => {//取消删除
    ElMessage.info('您已取消删除操作');
  })
}
</script>

<template>
  <h1>部门管理</h1>
  <div class="container">
    <el-button type="primary" @click="addDept">+新增部门</el-button>
  </div>

  <!-- 表格 -->
  <div class="container">
    <el-table :data="deptList" border style="width: 100%">
      <el-table-column type="index" width="100" align="center" />
      <el-table-column prop="name" label="部门名称" width="260" align="center" />
      <el-table-column prop="updateTime" label="最后操作时间" width="300" align="center" />
      <el-table-column prop="address" label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row.id)">编辑</el-button>
          <el-button type="danger" size="small" @click="delById(scope.row.id)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- Dialog对话框 -->

  <el-dialog v-model="dialogFormVisible" :title="formTitle" width="500">
    <el-form :model="dept" :rules="rules" ref="deptFormRef">
      <el-form-item label="部门名称" label-width="80px" prop="name">
        <el-input v-model="dept.name" />
      </el-form-item>
    </el-form>
    <template #footer>
      <div class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="save">确定</el-button>
      </div>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 10px 0;
}
</style>
