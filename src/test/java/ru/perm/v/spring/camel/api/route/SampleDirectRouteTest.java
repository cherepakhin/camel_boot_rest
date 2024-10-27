package ru.perm.v.spring.camel.api.route;


import org.apache.camel.EndpointInject;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
//import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

//@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest(
        properties = { "camel.springboot.name=customName" }
)
public class SampleDirectRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @EndpointInject(ref = "mock:file:sampleOutput?fileName=output.txt")
    MockEndpoint mockEndpoint;


}
