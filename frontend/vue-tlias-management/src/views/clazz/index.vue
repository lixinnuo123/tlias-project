<script setup>
import { ref, onMounted } from 'vue';
import { queryAllApi, addApi, queryByIdApi, updateApi, deleteByIdApi } from '@/api/clazz';
import { ElMessage ,ElMessageBox} from 'element-plus';


//钩子函数
onMounted(() => {
  search();
})

const search = async () => {
  const resule = await queryAllApi();
  if (resule.code) {
    clazzList.value = resule.data;
  }
}

const clazzList = ref([]);

//Dialog对话框
const dialogFormVisible = ref(false);
const formTitle = ref('');
const clazz = ref({
  name: ''
});

const addClazz = () => {
  dialogFormVisible.value = true;
  formTitle.value = '新增班级';
  clazz.value.name = '';
  //清空校验状态
  if (clazzFormRef.value) {//如果clazzFormRef.value存在
    clazzFormRef.value.clearValidate();
  }
}

const save = async () => {
  //校验表单
  if (!clazzFormRef.value.validate()) {//校验失败
    return;
  }
  clazzFormRef.value.validate(async (valid) => {//表示是否校验通过
    if (valid) {
      //校验成功
      let resule;
      if (clazz.value.id) {
        resule = await updateApi(clazz.value);
      } else {
        resule = await addApi(clazz.value);
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
    { required: true, message: '班级名称是必填项', trigger: 'blur' },
    { min: 2, max: 10, message: '班级名称长度应该在2-10位', trigger: 'blur' },
  ]
})
const clazzFormRef = ref();

//编辑
const edit = async (id) => {
  formTitle.value = '修改班级';
  //清空校验状态
  if (clazzFormRef.value) {//如果clazzFormRef.value存在
    clazzFormRef.value.clearValidate();
  }

  const resule = await queryByIdApi(id);
  if (resule.code) {
    dialogFormVisible.value = true;
    clazz.value = resule.data;
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
  }).catch(() => {//取消删除
    ElMessage.info('您已取消删除操作');
  })
}
</script>

<template>
  <h1>班级管理</h1>
  <div class="container">
    <el-button type="primary" @click="addClazz">+新增班级</el-button>
  </div>

  <div class="container">
    <el-table :data="clazzList" border style="width: 100%">
      <el-table-column type="index" width="100" align="center" />
      <el-table-column prop="name" label="班级名称" width="260" align="center" />
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
    <el-form :model="clazz" :rules="rules" ref="clazzFormRef">
      <el-form-item label="班级名称" label-width="80px" prop="name">
        <el-input v-model="clazz.name" />
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