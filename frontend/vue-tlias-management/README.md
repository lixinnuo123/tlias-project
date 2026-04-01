 VUE-TLIAS-MANAGEMENT 前端项目
 
项目介绍
 
Tlias 教学管理系统前端，基于 Vue3 + Vite 开发，与 SpringBoot 后端前后端分离，项目开发中。
 
技术栈
 
Vue3、Vite、Element Plus、Vue Router、Axios、ESLint
 
已完成模块
 
- 部门管理（增删改查）
- 员工管理（分页、增删改查、文件上传）
- 班级管理（增删改查）
- 学员管理（分页、增删改查）
 
待完成模块
 
- 登录/退出
- 报表统计
- 项目打包部署
 
运行说明
 
1. 安装依赖： npm install 
2. 启动项目： npm run dev （访问  http://localhost:5173 ）
3. 配置后端地址：修改  src/utils/request.js  中的  baseURL 
4. 打包部署： npm run build （待完成）
 
项目结构

src/
├── api/        # 接口请求
├── assets/     # 静态资源
├── components/ # 公共组件
├── router/     # 路由配置
├── utils/      # 工具函数
├── views/      # 页面（部门/员工/班级/学员/日志/登录/报表等）
├── App.vue
└── main.js

更新计划

1. 登录/退出
2. 报表统计
3. 项目打包部署
