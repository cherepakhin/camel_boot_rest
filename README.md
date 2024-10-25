### My tests 20:03:2024

run app:

````shell
./mvnw spring-boot:run
````

run tests:

````shell
http :9090/hello-world

"Welcome to java techie"

http http://127.0.0.1:9090/getOrders
[
    {
        "id": 67,
        "name": "Mobile",
        "price": 5000.0
    },
....
]
````

http POST :9090/addOrder < ~/temp/order.json


# spring-camel-rest-dsl
How to expose Rest API using Spring Boot with Apache Camel

## Dependencies we have to add for apache camel

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

##  Rest APIs
### Post method
restConfiguration().component("servlet").port(9090).host("localhost").bindingMode(RestBindingMode.json);

		rest().post("/addOrder").consumes(MediaType.APPLICATION_JSON_VALUE).type(Order.class).outType(Order.class)
				.route().process(processor).endRest();


### Сборка fat файла

````shell
./mvnw package
````

Собранный файл будет в target/camel_boot_rest-0.0.1.jar

Запуск:

````shell
java -jar camel_boot_rest-0.0.1.jar
````

Смена основного порта __9090__ (задан в application.properties) на __8960__:

````shell
export SPRING_APPLICATION_JSON='{"server":{"port":8960}}'
java -jar camel_boot_rest-0.0.1.jar
````

Проверка:

````shell
$ http :8960/hello-world

"Hello world"

````