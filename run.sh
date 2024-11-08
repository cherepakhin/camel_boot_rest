# $ echo $JAVA_HOME
# /usr/lib/jvm/java-17-openjdk-amd64
#-------------------------------------------

# 1 method (copy from console idea run):
# OK
# /home/vasi/.jdks/openjdk-19.0.2/bin/java -Dfile.encoding=UTF-8 -Dsun.stdout.encoding=UTF-8 -Dsun.stderr.encoding=UTF-8 -classpath /home/vasi/prog/java/camel/camel_boot_rest_2023/target/classes:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-starter-web/2.5.6/spring-boot-starter-web-2.5.6.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-starter/2.5.6/spring-boot-starter-2.5.6.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.5.6/spring-boot-starter-logging-2.5.6.jar:/home/vasi/.m2/repository/ch/qos/logback/logback-classic/1.2.6/logback-classic-1.2.6.jar:/home/vasi/.m2/repository/ch/qos/logback/logback-core/1.2.6/logback-core-1.2.6.jar:/home/vasi/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.14.1/log4j-to-slf4j-2.14.1.jar:/home/vasi/.m2/repository/org/apache/logging/log4j/log4j-api/2.14.1/log4j-api-2.14.1.jar:/home/vasi/.m2/repository/org/slf4j/jul-to-slf4j/1.7.32/jul-to-slf4j-1.7.32.jar:/home/vasi/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/home/vasi/.m2/repository/org/yaml/snakeyaml/1.28/snakeyaml-1.28.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-starter-json/2.5.6/spring-boot-starter-json-2.5.6.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jdk8/2.12.5/jackson-datatype-jdk8-2.12.5.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/datatype/jackson-datatype-jsr310/2.12.5/jackson-datatype-jsr310-2.12.5.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/module/jackson-module-parameter-names/2.12.5/jackson-module-parameter-names-2.12.5.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-starter-tomcat/2.5.6/spring-boot-starter-tomcat-2.5.6.jar:/home/vasi/.m2/repository/org/apache/tomcat/embed/tomcat-embed-core/9.0.54/tomcat-embed-core-9.0.54.jar:/home/vasi/.m2/repository/org/apache/tomcat/embed/tomcat-embed-el/9.0.54/tomcat-embed-el-9.0.54.jar:/home/vasi/.m2/repository/org/apache/tomcat/embed/tomcat-embed-websocket/9.0.54/tomcat-embed-websocket-9.0.54.jar:/home/vasi/.m2/repository/org/springframework/spring-web/5.3.12/spring-web-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-beans/5.3.12/spring-beans-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-webmvc/5.3.12/spring-webmvc-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-aop/5.3.12/spring-aop-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-context/5.3.12/spring-context-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-expression/5.3.12/spring-expression-5.3.12.jar:/home/vasi/.m2/repository/org/apache/camel/camel-spring-boot-starter/2.25.4/camel-spring-boot-starter-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-spring-boot/2.25.4/camel-spring-boot-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-spring/2.25.4/camel-spring-2.25.4.jar:/home/vasi/.m2/repository/org/springframework/spring-tx/5.3.12/spring-tx-5.3.12.jar:/home/vasi/.m2/repository/org/apache/camel/camel-core-starter/2.25.4/camel-core-starter-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-core/2.25.4/camel-core-2.25.4.jar:/home/vasi/.m2/repository/com/sun/xml/bind/jaxb-core/2.3.0/jaxb-core-2.3.0.jar:/home/vasi/.m2/repository/com/sun/xml/bind/jaxb-impl/2.3.0/jaxb-impl-2.3.0.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-devtools/2.5.6/spring-boot-devtools-2.5.6.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot/2.5.6/spring-boot-2.5.6.jar:/home/vasi/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.5.6/spring-boot-autoconfigure-2.5.6.jar:/home/vasi/.m2/repository/org/projectlombok/lombok/1.18.22/lombok-1.18.22.jar:/home/vasi/.m2/repository/org/slf4j/slf4j-api/1.7.32/slf4j-api-1.7.32.jar:/home/vasi/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/2.3.3/jakarta.xml.bind-api-2.3.3.jar:/home/vasi/.m2/repository/jakarta/activation/jakarta.activation-api/1.2.2/jakarta.activation-api-1.2.2.jar:/home/vasi/.m2/repository/org/springframework/spring-core/5.3.12/spring-core-5.3.12.jar:/home/vasi/.m2/repository/org/springframework/spring-jcl/5.3.12/spring-jcl-5.3.12.jar:/home/vasi/.m2/repository/org/apache/camel/camel-servlet-starter/2.25.4/camel-servlet-starter-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-servlet/2.25.4/camel-servlet-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-http-common/2.25.4/camel-http-common-2.25.4.jar:/home/vasi/.m2/repository/org/apache/camel/camel-jackson/3.2.0/camel-jackson-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-core-engine/3.2.0/camel-core-engine-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-api/3.2.0/camel-api-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-base/3.2.0/camel-base-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-management-api/3.2.0/camel-management-api-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-support/3.2.0/camel-support-3.2.0.jar:/home/vasi/.m2/repository/org/apache/camel/camel-util/3.2.0/camel-util-3.2.0.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/core/jackson-databind/2.12.5/jackson-databind-2.12.5.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/core/jackson-annotations/2.12.5/jackson-annotations-2.12.5.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/core/jackson-core/2.12.5/jackson-core-2.12.5.jar:/home/vasi/.m2/repository/com/fasterxml/jackson/module/jackson-module-jaxb-annotations/2.12.5/jackson-module-jaxb-annotations-2.12.5.jar ru.perm.v.spring.camel.api.CamelRestDslApplication
#-------------------------------------------
# 2 method:
# OK
#./mvnw spring-boot:run
./mvnw -Dspring.profiles.active=DEV spring-boot:run

#-------------------------------------------
# 4. method (build fat jar)
# ./mvnw package
#java -jar target/camel_boot_rest-0.0.1.jar
#-------------------------------------------
# 5. Run jar from temp
# copy to ~/temp
# cp target/camel_boot_rest-0.0.1.jar ~/temp
#~/temp$ ll camel_boot_rest-0.0.1.jar
#  camel_boot_rest-0.0.1.jar - 30 735 435 b
# run
# java -jar ~/temp/camel_boot_rest-0.0.1.jar

# Profile
#./mvnw -Dspring.profiles.active=DEV spring-boot:run

