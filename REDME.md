rpc原理，两个不同的服务（不同机器【不同进程】），建立连接，传输数据。

该工程(普通的maven工程)为父工程用于聚合，不继承（全体打包、发布、测试、构建）

api工程用于抽取所有的bean、模型类、异常、通用类等(每一个工程都依赖于api工程)
common工程用于工具类通用类，并且依赖api工程

每一个服务需要
        1、配置整合dubbo
        2、配置整合MybatisPlus
        
mybatis-starter中有springboot提供的默认数据源HikariPool,这个数据源比阿里的数据源效率更高。

在这里通过sharding-jdbc(增强版的数据库驱动，轻量级)来管理多数据源，而不使用myCat(MyCat是重量级框架)。

elk日志分析：
    ElasticSearch：存储和检索数据。
        端口：     9200：暴露的web端口   9300：分布式情况下各个节点通信的端口
    Logstash：收集日志并存放到ElasticSearch中。
        端口：默认端口4560
    Kibanna：可视化界面，为 Elasticsearch设计的开源分析和可视化平台。可以使用 Kibana 来搜索,查看存储在 Elasticsearch 索引中的数据并与之交互。
        端口： 默认5601端口，通过该端口进入可视化界面访问数据
  交互逻辑：所有模块将日志数据收集发给4560端，然后给9200端口写入，然后通过5601端口查看日志信息

SpringMVC支持使用【JSR303】方式进行校验
    1、springboot默认导了第三放校验框架hibernate-validator
使用JSR303的三大步:
    1）给需要校验数据的javaBean上标注校验注解
    2）javax.validation和hibernate.validator的注解都可以使用(@Pattern(regexp = '')可以使用复杂的正则校验)
    3）JSR303规范规定的都可以
    4)告诉springboot，这个需要校验:@Valid
            springmvc进入方法之前，确定参数值得时候就会进行校验，如果校验出错，直接返回错误，不执行controller代码
    5)如何感知校验成功还是失败
            只需要给开启了校验得javaBean参数后面，紧跟一个BindingResult对象就可以获取到校验结果；
                只要又BindingResult，即使校验错了，方法也会执行。我们需要手动处理
                
统一的异常处理:
    1）通过注解ControllerAdvice标注全局异常处理类，ExceptionHandler注解标注处理的不同异常类型的方法
    2）如果方法有异常通知的话需要在异常通知处将异常封装成RuntimeException异常并抛出throw new RuntimeException(throwable);
    否则异常会被截获而不会进行异常的统一处理。

缓存的使用场景:
    一些固定的数据，不太变化的数据，高频访问的数据（基本不变），变化频率较低的都可以放入缓存，加速系统访问效率
    缓存的目的，提高系统查询效率，提高性能。
    Redis引入RedisAutoConfiguration场景，Redis的配置文件RedisProperties类(配置参数都在里面)
    1）将菜单缓存起来，以后查询直接去缓存中拿即可
redis中存数据默认是使用jdk的序列化方式存放数据(这种序列化方式不能跨语言；json、xml是跨语言的)。
    需要将默认的序列化器改为json序列化方式。

设计模式：模板模式
    操作xxx都有对应的xxxTemplate
    例如：jdbcTemplate、RestTemplate、RedisTemplate
    
Oss相关操作接口:
    1、阿里云上传
            前端页面form表单文档上传---》后台（收到文件流）---》ossClient.upload到阿里云
                如果直接从前端上传，可以节省文件上传到后台那部分得流量
       1）这里使用的是别人的对象存储，要使用自己的需要在前端项目中修改阿里云地址改为自己的
       2）修改配置文件中的参数数据
       3）在阿里云官网开启自己oss对象存储的跨域访问
跨域可以安全限制某些服务器能跨(Oss生成令牌需要限制跨域)：
    @AliasFor("origins")
    String[] value() default {};
    
#该项目的服务暴漏端口：商品服务：208xx;用户服务:209xx;营销服务:210xx

/**
 * VO: (View Object/Value Object) 视图对象，把专门交给前端的数据封装成vo;或者是前端传给后台的数据。
 * DAO: (Database Access Object) 数据库访问对象；专门用来对数据库进行crud的对象。XxxMapper
 * POJO: (Plain Old Java Object) 古老单纯的Java对象。javaBean(封装数据的)
 * DO: (Data Object) 数据对象---POJO (DataBase Object) 数据对象(专门用来封装数据库表的实体类)
 * TO: (Transfer Object) 传输对象
 *      1）服务之间互调，为了数据传输封装对象
 *      2）service之间互调，为了数据传输封装对象
 * DTO: (Data Transfer Object) 和TO一样
 *
 * exclude = {DataSourceAutoConfiguration.class}
 *      不进行数据源的自动配置，避免导入了mysql依赖但是没有配置而报错。
 *
 * 如果导入的依赖，引入了一个自动配置场景
 *      1）这个场景自动配置默认生效，我们就必须配置它
 *      2）不想配置的解决办法：
 *          1、 引入的时候排除这个场景依赖
 *          2、 通过SpringBootApplication的exclude参数排除掉这个场景的自动配置类
 *
 *  rpc远程调用需要给实体类实现序列化接口
 */
 
 