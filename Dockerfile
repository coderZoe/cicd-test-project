# 一阶段提取
FROM 192.168.31.193:20080/docker-proxy/eclipse-temurin:21-jre-alpine AS extractor

WORKDIR workspace

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} application.jar
# 运行Spring Boot的layertools来提取所有层到独立的目录中
# 这会创建 common-dependencies, project-dependencies, spring-boot-loader, application 等目录
RUN java -Djarmode=layertools -jar application.jar extract


# 二阶段构建
FROM 192.168.31.193:20080/docker-proxy/eclipse-temurin:21-jre-alpine
# 设置工作目录
WORKDIR /app

# 关键步骤：按稳定性从高到低的顺序，逐层复制文件。
# 每一个COPY指令都会创建一个新的Docker镜像层。
# Docker会缓存这些层，如果层的内容没有变化，构建时会直接使用缓存。
COPY --from=extractor /workspace/dependencies/ ./

COPY --from=extractor /workspace/spring-boot-loader/ ./

COPY --from=extractor /workspace/snapshot-dependencies/ ./

COPY --from=extractor /workspace/application/ ./

# 暴露Spring Boot应用程序的默认端口
EXPOSE 8080

# Spring Boot分层应用需要使用 JarLauncher 来启动
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]