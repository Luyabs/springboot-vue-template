## 1. 本模板基于springboot与vue-admin-template二次开发
> 该模板旨在为更复杂的开发做基础 本模板内容较少 方便各位在短时间内看懂并改造本项目   
> springboot: https://spring.io/  
> vue-element-admin: https://panjiachen.github.io/vue-element-admin-site/zh/

## 2. 如何使用
> **git clone 或者 下载压缩包**  
> 对于springboot 2.7，你需要**jdk >= 8**  
> > 你需要通过src/main/resources/create-table.sql文件建立table  
> > 并在src/main/resources/application-dev.yaml中配置MySQL数据库信息  
> > 
> 对于vue-admin-template，最好不超过 **nodejs 16**  
> > 在vue-admin-template-upgraded文件夹下打开终端  
> > 输入npm install --registry=https://registry.npm.taobao.org 安装依赖  
> > 通过npm run dev运行前端项目

## 3. 模板整合了什么
> springboot:
> > 基础web三层结构   
> > mybatis-plus (包含元数据处理等配置)   
> > knife4j(仅ui) 运行项目后可通过此链接查看knife4j文档 http://localhost:8080/doc.html  
> > cors跨域认证等MVC配置 (/config/WebMvcConfig)   
> > 数据一致性 (Result)   
> > 集成Sa-Token框架的登录校验    
> > 全局异常处理 (/common/exception)  
> > 配置文件分割  
> > 热部署    
> > restTemplate    
> > 提供AOP实现的简单日志    
> > 拦截器配置的参考  
> > mybatis-plus代码生成器

> vue-admin-template: 
> > 提供一个最常见的含CURD功能的表格界面  
> > 删去了前端模板中大部分没有用到的功能，使得vue-admin-template更容易进行二次开发  
> > 在vue-admin-template上配置了权限管理  

## 4. 我该如何使用这个模板
> 1. 首先你需要完成第1与第2步，之后试着先运行Spring Boot项目，再运行Vue项目。  
> 2. 如何做自己的项目：先在数据库中定义完表结构，然后去Spring Boot的代码生成器/src/test/..../CodeGenerator中调整一些private属性，随后运行该测试文件  
> 3. 如果碰到问题 请参考下面部分。如果你准备删除项目里原有的controller或service等, 请不要删除/src/.../service/base下的文件，如要删除请调整代码生成器中有关service父类的配置  
  
## 5. 以下是一些使用代码生成器后可能需要处理的问题
```严重``` 如果无法启动，请先考虑swagger版本冲突。解决方法：先在pom.xml注释掉springfox-boot-starter与swagger-xx    
```警告``` entity中除自动填充字段外LocalDateTime对应的属性可能需要手动添加 @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")   
```警告``` entity中自动填充字段需要额外设置 @TableField(fill = FieldFill.INSERT) 或Field.INSERT_UPDATE    
```警告``` entity中自动生成的字段需要修改主键自增策略  @TableId(type = IdType.ASSIGN_ID)    
```可添加``` 生成出来的service与controller没有任何代码   
```可添加``` 生成出来entity没有swagger注解   
```可添加``` 为controller添加@Api等swagger注解   
```如果以上都没法解决问题请issue或手写代码```
