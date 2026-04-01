<script setup>
import { ref, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { queryPageApi, addApi, queryByIdApi, updateApi, deleteByIdApi } from '@/api/stu'
import { queryAllApi as queryAllClazzApi } from '@/api/clazz'

//元数据
//班级列表数据
const clazzs = ref([])
//最高学历
const degrees = ref([
  { id: 1, name: '初中' },
  { id: 2, name: '高中' },
  { id: 3, name: '大专' },
  { id: 4, name: '本科' },
  { id: 5, name: '硕士' },
  { id: 6, name: '博士' }
])
//性别
const genders = ref([
  { id: 1, name: '男' },
  { id: 2, name: '女' }
])
//是否院校
const isCollege = ref([
  { id: 1, name: '是' },
  { id: 2, name: '否' }
])
//搜索表单对象
const searchStu = ref({ name: '', degree: '', class: '' });

//查询学员列表
const search = async () => {
  const resule = await queryPageApi(searchStu.value.name, searchStu.value.degree, searchStu.value.class, currentPage.value, pageSize.value);
  if (resule.code) {
    stuList.value = resule.data.rows;
    total.value = resule.data.total;
  }
}
//清空
const clear = () => {
  searchStu.value = { name: '', degree: '', class: '' };
  search();
}

//学员列表数据
const stuList = ref([]);

//分页条
const currentPage = ref(1);//当前页码
const pageSize = ref(10);//每页显示条数
const background = ref(true);//是否显示背景颜色
const total = ref(0);//总记录数

//每页展示记录数变更时触发
const handleSizeChange = () => {
  search();
}
//当前页码变更时触发
const handleCurrentChange = () => {
  search();//查询学员列表
  queryAllClazzs();//查询所有班级数据
}

//组件挂载时触发
onMounted(() => {
  search();//查询学员列表
  queryAllClazzs();//查询所有班级数据

})

//查询所有班级数据
const queryAllClazzs = async () => {
  const resule = await queryAllClazzApi();
  if (resule.code) {
    clazzs.value = resule.data;
  }
}

//新增/修改表单
const stu = ref({
  name: '',
  no: '',
  gender: '',
  phone: '',
  degree: '',
  clazzId: '',
  idCard: '',
  isCollege: '',
  address: '',
  graduationDate: ''
})

//新增学员
const addStu = () => {
  dialogVisible.value = true;
  dialogTitle.value = '新增学员';
  //重置表单
  if (stuForm.value) {//表单已创建
    stuForm.value.resetFields();//清除表单校验
  }
}

// 控制弹窗
const dialogVisible = ref(false)
const dialogTitle = ref('新增学员')

//保存学员
const save = async () => {
  //校验表单
  if (!stuForm.value)return;//校验失败
  stuForm.value.validate(async (valid) => {//表示是否校验通过
    if (valid) {//校验通过
      let resule;
      if (stu.value.id) {//修改
        resule = await updateApi(stu.value);
      } else {//新增
        resule = await addApi(stu.value);
      }
    
      if (resule.code) {//保存成功
        ElMessage.success('保存成功');
        dialogVisible.value = false;
        search();
      } else {//保存失败
        ElMessage.error(resule.msg);
      }
    } else {//校验不通过
      ElMessage.error('表单校验不通过');
    }
  });

}

//表单引用
const stuForm = ref(null);

//表单校验规则
const rules = ref({
  name: [
    { required: true, message: '请输入姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '姓名长度应在2到10个字符之间', trigger: 'blur' }
  ],
  no: [
    { required: true, message: '请输入学号', trigger: 'blur' },
    { min: 2, max: 10, message: '学号长度应在2到10个字符之间', trigger: 'blur' }
  ],
  gender: [
    { required: true, message: '请选择性别', trigger: 'change' }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入有效的手机号', trigger: 'blur' }
  ],
  idCard: [
    { required: true, message: '请输入身份证号', trigger: 'blur' },
    { min: 18, max: 18, message: '身份证号长度应为18位', trigger: 'blur' }
  ],
  isCollege: [
    { required: true, message: '请选择是否院校', trigger: 'change' }
  ],
});

//编辑学员
const edit = async (rows) => {
  const resule = await queryByIdApi(rows.id);
  if (resule.code) {
        stu.value = resule.data;
    dialogVisible.value = true;
    dialogTitle.value = '修改学员';
    //对毕业日期进行处理
    stu.value.graduationDate = rows.value.graduationDate ? new Date(rows.value.graduationDate) : null;
  }
}

//删除学员
const del = async (rows) => {
  const resule = await delApi(rows.id);
  if (resule.code) {
    ElMessage.success('删除成功');
    search();
  } else {
    ElMessage.error(resule.msg);
  }
}

//删除学员
const deleteById = async (id) => {
  //弹出确认框
  ElMessageBox.confirm('确定删除该学员吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {
    //确认删除
    const resule = await deleteByIdApi(id);
    if (resule.code) {
      ElMessage.success('删除成功');
      //查询
      search();
    } else {//失败
      ElMessage.error('删除失败');
    }
  }
  ).catch(() => {//取消删除
    ElMessage.info('您已取消删除');
  })
}

//记录勾选的学员id
const selectedStuIds = ref([]);
const handleSelectionChange = (val) => {
  selectedStuIds.value = val.map(item => item.id);
}

//批量删除学员
const deleteByIds = async () => {
  //弹出确认框
  ElMessageBox.confirm('确定删除选中的学员吗？', '提示', {
    confirmButtonText: '确定',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(async () => {//确认删除
    //判断是否勾选了学员
    if (selectedStuIds.value && selectedStuIds.value.length > 0) {
      const resule = await deleteByIdApi(selectedStuIds.value);
      if (resule.code) {
        ElMessage.success('删除成功');
        //查询
        search();
      } else {//失败
        ElMessage.error('删除失败');
      }
    }else{
      ElMessage.error('您没有选择任何要删除的数据');
    }
  }).catch(() => {//取消删除
    ElMessage.info('您已取消删除');
  })
}
</script>

<template>
  <h1>学员管理</h1>
  <!-- 搜索栏 -->
  <div class="container">
    <el-form :inline="true" :model="searchStu" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="searchStu.name" placeholder="请输入学员姓名" />
      </el-form-item>
      <el-form-item label="最高学历">
        <el-select v-model="searchStu.degree" placeholder="请选择最高学历">
          <el-option label="初中" value="1" />
          <el-option label="高中" value="2" />
          <el-option label="大专" value="3" />
          <el-option label="本科" value="4" />
          <el-option label="硕士" value="5" />
          <el-option label="博士" value="6" />
        </el-select>
      </el-form-item>
      <el-form-item label="所属班级">
        <el-select v-model="searchStu.class" placeholder="请选择所属班级">

          所属班级

        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="search">查询</el-button>
        <el-button type="info" @click="clear">清除</el-button>
      </el-form-item>
    </el-form>
  </div>

  <!-- 功能按钮 -->
  <div class="container">
    <el-button type="primary" @click="addStu">新增学员</el-button>
    <el-button type="danger" @click="deleteByIds">批量删除</el-button>
  </div>

  <!-- 表格 -->
  <div class="container">
    <el-table :data="stuList" border style="width: 100%" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column prop="name" label="姓名" width="110" align="center" />
      <el-table-column prop="no" label="学号" width="110" align="center" />
      <el-table-column prop="class" label="班级" width="130" align="center">
        <template #default="scope">
          <span v-if="scope.row.clazzId == 1">JavaEE就业163期</span>
          <span v-else-if="scope.row.clazzId == 2">前端就业90期</span>
          <span v-else-if="scope.row.clazzId == 3">JavaEE就业165期</span>
          <span v-else-if="scope.row.clazzId == 4">JavaEE就业166期</span>
          <span v-else-if="scope.row.clazzId == 5">大数据就业58期</span>
          <span v-else-if="scope.row.clazzId == 6">JavaEE就业167期</span>
          <span v-else>其他</span>
        </template>
      </el-table-column>
      <el-table-column prop="gender" label="性别" width="100" align="center">
        <template #default="scope">
          {{ scope.row.gender == 1 ? '男' : '女' }}
        </template>
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="190" align="center" />
      <el-table-column prop="degree" label="最高学历" width="140" align="center">
        <template #default="scope">
          <span v-if="scope.row.degree == 1">初中</span>
          <span v-else-if="scope.row.degree == 2">高中</span>
          <span v-else-if="scope.row.degree == 3">大专</span>
          <span v-else-if="scope.row.degree == 4">本科</span>
          <span v-else-if="scope.row.degree == 5">硕士</span>
          <span v-else-if="scope.row.degree == 6">博士</span>
          <span v-else>其他</span>
        </template>
      </el-table-column>
      <el-table-column prop="violationCount" label="违纪次数" width="110" align="center" />
      <el-table-column prop="violationScore" label="违纪扣分" width="110" align="center" />
      <el-table-column prop="updateTime" label="最后操作时间" width="150" align="center" />
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="edit(scope.row)"><el-icon>
              <EditPen />
            </el-icon>编辑</el-button>
          <el-button type="danger" size="small" @click="deleteById(scope.row.id)"><el-icon>
              <Delete />
            </el-icon>删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>

  <!-- 分页条 -->
  <div class="container">
    <el-pagination v-model:current-page="currentPage" v-model:page-size="pageSize"
      :page-sizes="[5, 10, 20, 30, 50, 75, 100]" :background="background" layout="total, sizes, prev, pager, next, jumper"
      :total="total" @size-change="handleSizeChange" @current-change="handleCurrentChange" />
  </div>
  <!-- 新增学员/修改学员对话框 -->
  <el-dialog v-model="dialogVisible" :title="dialogTitle">
    <el-form :model="stu" :rules="rules" ref="stuForm" label-width="80px">
      <!-- 基本信息 -->
      <!-- 第一行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="姓名" prop="name">
            <el-input v-model="stu.name" placeholder="请输入学员姓名"></el-input>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="学号" prop="no">
            <el-input v-model="stu.no" placeholder="请输入学员学号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第二行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="性别" prop="gender">
            <el-select v-model="stu.gender" placeholder="请选择性别" style="width: 100%;">
              <el-option v-for="g in genders" :key="g.id" :label="g.name" :value="g.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

        <el-col :span="12">
          <el-form-item label="手机号" prop="phone">
            <el-input v-model="stu.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第三行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="身份证号" prop="idCard">
            <el-input v-model="stu.idCard" placeholder="请输入学员身份证号"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="是否院校" prop="isCollege">
            <el-select v-model="stu.isCollege" placeholder="请选择" style="width: 100%;">
              <el-option v-for="i in isCollege" :key="i.id" :label="i.name" :value="i.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>

      </el-row>

      <!-- 第四行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="联系地址">
            <el-input v-model="stu.address" placeholder="请输入联系地址" style="width: 100%;" />
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="最高学历">
            <el-select v-model="stu.degree" placeholder="请选择" style="width: 100%;">
              <el-option v-for="d in degrees" :key="d.id" :label="d.name" :value="d.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>

      <!-- 第五行 -->
      <el-row :gutter="20">
        <el-col :span="12">
          <el-form-item label="毕业时间">
            <el-date-picker v-model="stu.graduationDate" type="date" style="width: 100%;" placeholder="选择日期"
              format="YYYY-MM-DD" value-format="YYYY-MM-DD"></el-date-picker>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="所属班级">
            <el-select v-model="stu.clazzId" placeholder="请选择班级" style="width: 100%;">
              <el-option v-for="c in clazzs" :key="c.id" :label="c.name" :value="c.id"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>

    <!-- 底部按钮 -->
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="save">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<style scoped>
.container {
  margin: 10px 0px;
}

.avatar {
  height: 40px;
}

.avatar-uploader .avatar {
  width: 78px;
  height: 78px;
  display: block;
}

.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 78px;
  height: 78px;
  text-align: center;
  border-radius: 10px;
  /* 添加灰色的虚线边框 */
  border: 1px dashed var(--el-border-color);
}
</style>