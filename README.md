### My tests 20:03:2024

run app:

````shell
mvn spring-boot:run
````

run tests:

````shell
http :9090/hello-world

"Welcome to java techie"

http :9090/getOrders
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
