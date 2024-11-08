### Простой проект с Java, Camel, Rest, Spring boot

Java 11

````shell
$ export JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64
````

run test:

````shell
$ ./mvnw clean test
````

run app:

````shell
$ ./mvnw -Dspring.profiles.active=DEV spring-boot:run
````
(см. run.sh)

Примеры GET запросов:

````shell
$ http :9090/hello-world

"Welcome to java techie"
````

````shell
$ http http://127.0.0.1:9090/getOrders
[
    {
        "id": 67,
        "name": "Mobile",
        "price": 5000.0
    },
....
]
````

````shell
$ http http://127.0.0.1:9090/getOrderById/67
[
    {
        "id": 67,
        "name": "Mobile",
        "price": 5000.0
    },
....
]
````



POST запрос:

````shell
$ cat doc/new_order.json 
{
  "id": 70,
  "name": "Shoes",
  "price": 70000.0
}

$ http POST :9090/addOrder < doc/new_order.json

{
    "id": 70,
    "name": "Shoes",
    "price": 70000.0
}

````

### Dependencies apache camel

````xml
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-spring-boot-starter</artifactId>
    <version>2.24.0</version>
</dependency>
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-servlet-starter</artifactId>
    <version>2.24.0</version>
</dependency>
<dependency>
    <groupId>org.apache.camel</groupId>
    <artifactId>camel-jackson</artifactId>
    <version>2.24.0</version>
</dependency>
````

###  Rest APIs

Основная настройка Camel REST в классе: 
[https://github.com/cherepakhin/camel_boot_rest/blob/main/src/main/java/ru/perm/v/spring/camel/api/resource/ApplicationResource.java](https://github.com/cherepakhin/camel_boot_rest/blob/main/src/main/java/ru/perm/v/spring/camel/api/resource/ApplicationResource.java)

````java
@Component
public class ApplicationResource extends RouteBuilder {


    @BeanInject
    private OrderService orderService;

    @BeanInject
    private OrderProcessor processor;

    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").port(9090).host("localhost")
                .bindingMode(RestBindingMode.json);

        rest().get("/hello-world").produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(constant("Hello world")).log("Get /hello-world").endRest();

        rest().get("/getOrders").produces(MediaType.APPLICATION_JSON_VALUE)
                .route()
                .setBody(() -> orderService.getOrders())
                .log("Get /getOrders")
                .endRest();

        rest().get("/getOrder/{id}")
                .produces(MediaType.APPLICATION_JSON_VALUE)
                .outType(Order.class)
                .route()
                .log("Header ${header.id}")
                .to("bean:orderServiceImpl?method=getOrderById(${header.id})")
                .endRest();

        rest().post("/addOrder")
                .consumes(MediaType.APPLICATION_JSON_VALUE)
                .type(Order.class)
                .outType(Order.class)
                .route()
                .process(processor)
                .log("Post /addOrder")
                .endRest();
    }

}

````

### Интеграционное тестирование

Интеграционные тесты помечены @Tag("integration"). Запуск только интеграционных тестов:

````shell
$ ./mvnw clean test -Dgroups="integration"
````

прогон только unit тестов (исключить интеграционные тесты @Tag("integration")):

````shell
$ ./mvnw clean test -DexcludedGroups="integration"
````


### Сборка fat файла

````shell
$ ./mvnw package
````

Собранный файл будет в target/camel_boot_rest-0.0.1.jar

Запуск:

````shell
$  java -jar target/camel_boot_rest-0.0.1.jar
````

Смена основного порта __9090__ (задан в application.properties) на __8960__:

````shell
$ export SPRING_APPLICATION_JSON='{"server":{"port":8960}}'
$ java -jar camel_boot_rest-0.0.1.jar
````

Проверка:

````shell
$ http :8960/hello-world

"Hello world"

````

### Сборка в Jenkins

![jenkins_build](doc/jenkins_build.png)

### Интеграционные тесты для этого проекта с RestAssured

[https://github.com/cherepakhin/camel_boot_rest_restassured_test](https://github.com/cherepakhin/camel_boot_rest_restassured_test)

###  Profile

````shell
$ ./mvnw -Dspring.profiles.active=DEV spring-boot:run
````

Пример установки Profile для тестов в ru.perm.v.spring.camel.api.conf.AppConfigForDevTest
[https://github.com/cherepakhin/camel_boot_rest/blob/main/src/main/java/ru/perm/v/spring/camel/api/conf/ApplicationResource.java](https://github.com/cherepakhin/camel_boot_rest/blob/main/src/main/java/ru/perm/v/spring/camel/api/resource/ApplicationResource.java)

### Maven Report

Генерация отчета о проекте:

В pom.xml добавлена секция:

````shell
	<reporting>
		<plugins>
			<plugin>
				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-surefire-report-plugin</artifactId>
				<version>3.1.2</version>
			</plugin>
		</plugins>
	</reporting>
````

генерация:

````shell
$ ./mvnw site
````

Отчет будет в /target/site/index.html

### TODO

Camel после версии 3.0.0 предоставляет инструменты тестирования Spring Boot с JUnit5. Рекомендуется использовать аннотацию org.apache.camel.test.spring.junit5.CamelSpringBootTest. "This replaces the Junit4 @RunWith annotation using SpringRunner.class or CamelSpringBootRunner.class. To enable autoconfiguration of the Camel context and other Spring boot auto-configurable components, use the annotation"
[https://camel.apache.org/components/4.8.x/others/test-spring-junit5.html](https://camel.apache.org/components/4.8.x/others/test-spring-junit5.html)