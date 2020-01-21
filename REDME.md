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
    
容器中的对象默认是单例。
    Web项目是天然多线程的
    线程问题？
        多个线程操作同样的变量，变量又读又写。
    不要在单实例写共享变量，不然会有线程安全问题
        可以通过ThreadLocal进行线程的变量共享

考虑事务
       1）哪些东西是一定要回滚的，哪些东西即使出错了是不必要回滚的
           商品的核心信息（基本数据、sku）保存的时候，不要受到无关信息影响。
           无关信息出问题，核心信息也不用回滚
       2）事务的传播行为:propagation：当前方法的事务【是否和别人公用一个事务】如何传播下去（里面的方法如果用事务，是否和他公用一个事务）
          REQUIRED（必须的）：
               如果以前有事务，就和之前的事务公用一个事务，没有就创建一个事务
          REQUIRES_NEW（总是用新的事务）：
               创建一个新的事务，如果以前有事务，暂停前面的事务
          SUPPORTS（支持）：
               之前有事务，就以事务的方式运行，没有事务也可以
          MANDATORY（强制）：
               一定要有事务，如果没事务就报错
          NOT_SUPPORTED（不支持）：
               不支持在事务内运行，如果已经有事务了，就挂起当前存在的事务
          NEVER（从不使用）：
               不支持在事务内运行，如果已经有事务了，抛异常
          NESTED（）：
               开启一个子事务（MySQL不支持），需要支持还原点功能的数据库
          总结：
               传播行为过程中，只要REQUIRES_NEW被执行过就一定成功，不管后面出不出问题，异常机制还是一样的，出现异常代码后不执行。
               REQUIRED只要感觉到异常就一定回滚。和外事务是什么传播行为无关。
               传播行为总是来定义，当一个事务存在的时候，他内部事务该怎么执行。
事务Spring中是怎么做的
          TransactionManager
          AOP做的
          CGLIB动态代理实现的
          这里如果直接调本类方法，相当于直接在内存中进行，所以只会有外面一个事务生效(自己类调用自己类里面的方法，就是一个复制粘贴)
          对象.方法()才能加上事务（对象调用方法时，使用的是代理对象来对方法进行调用的）。
          事务的问题：
               Service自己调用自己的方法，无法加上真正的自己内部调整的各个事务
               解决：
                   1）通过 对象.方法()解决(通过代理对象来对方法进行调用)
事务的最终解决方案：
    1）普通加事务。导入jdbc-starter，@EnableTransactionManagement,加@Transactional
    2）方法自己调自己类里面的加不上事务
        1、导入aop包，开启代理对象相关功能
               spring-boot-starter-aop
        2、获取当前类的真正代理对象，调用方法即可
                a、@EnableAspectJAutoProxy(exposeProxy = true)
                b、获取代理对象
                    AopContext.currentProxy();
隔离级别：解决读写加锁问题的数据库底层方案(隔离级别越高，性能越低，数据一致性越完整)   
    读未提交、读已提交、可重复读(快照)、串行化。
异常回滚策略：
    异常：
        运行时异常（不受检查异常）
        编译时异常（受检查异常）
            要么throw要么try-catch
    运行时异常默认是一定回滚
    编译时异常默认是不回滚的
    rollbackFor指定哪些异常一定回滚 
    noRollbackFor指定哪些异常一定不回滚 

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
 
