1. 本模板基于springboot与vue-admin-template二次开发
> 该模板旨在为更复杂的开发做基础 本模板内容较少 方便各位在短时间内看懂并改造本项目   
> springboot: https://spring.io/  
> vue-element-admin: https://panjiachen.github.io/vue-element-admin-site/zh/

2. 如何使用
> **git clone 或者 下载压缩包**  
> 对于springboot 2.7，你需要**jdk >= 8**  
> > 你需要通过src/main/resources/create-table.sql文件建立table  
> > 并在src/main/resources/application-dev.yaml中配置MySQL数据库信息  
> > 
> 对于vue-admin-template，最好不超过 **nodejs 16**  
> > 在vue-admin-template-upgraded文件夹下打开终端  
> > 输入npm install --registry=https://registry.npm.taobao.org 安装依赖  
> > 通过npm run dev运行前端项目

3. 模板整合了什么
> springboot:
> > 基础web三层结构   
> > mybatis-plus  
> > 集成controller基类  
> > knife4j(仅ui) 运行项目后可通过此链接查看knife4j文档 http://localhost:8080/doc.html  
> > cors跨域认证   
> > 数据一致性  
> > jwt令牌 与 登录校验  
> > 元数据处理  
> > 全局异常处理   
> > 公共字段填充  
> > 配置文件分割  
> > 热部署
> > 
> vue-admin-template: 
> > 提供一个最常见的含CURD功能的表格界面  
> > 删去了前端模板中大部分没有用到的功能，使得vue-admin-template更容易进行二次开发  
> > 在vue-admin-template上配置了权限管理  
