
## 手动添加hive impala jar到本地
mvn install:install-file       -Dfile=D:\ImpalaJDBC41.jar     -DgroupId=com.cloudera.impala.jdbc       -DartifactId=ImpalaJDBC       -Dversion=2.5.36      -Dpackaging=jar       -DgeneratePom=true

mvn install:install-file     -Dfile=D:\hive-jdbc-1.1.0-cdh5.12.1-standalone.jar    -DgroupId=org.apache.hive.jdbc       -DartifactId=hive-jdbc-standalone       -Dversion=1.1.0-cdh5.12.1      -Dpackaging=jar       -DgeneratePom=true



## sqoop 抽取hive到 mysql
* 检查是否连通 \
sqoop list-databases -connect jdbc:mysql://localhost:3306/test?useSSL=false -username test -password test
 
 * 导出 \
 sqoop export \
 -connect jdbc:mysql://localhost:3306/test?useSSL=false -username test -password test --table test001 \
--hcatalog-database test \
--hcatalog-table test001 --num-mappers 1

* 使用，udf，查出来后，直接写到mysql  
  引用：https://blog.csdn.net/HeatDeath/article/details/79624296
  
## 运行
运行时，指定配置文件为实际的
java -jar target/demo-1.0-SNAPSHOT.jar --spring.config.location=D:\WorkSparce-web\application.properties
