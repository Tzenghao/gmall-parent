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