微服务文件服务器

1、Eureka Server 高可用
# 通过 spring.profiles.active 指定使用那个 profile 启动
java -jar eureka.jar --spring.profiles.active=peer1
java -jar eureka.jar --spring.profiles.active=peer2
java -jar eureka.jar --spring.profiles.active=peer3