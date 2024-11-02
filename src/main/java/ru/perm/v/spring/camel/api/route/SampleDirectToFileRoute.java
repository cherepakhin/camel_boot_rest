package ru.perm.v.spring.camel.api.route;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class SampleDirectToFileRoute extends RouteBuilder {
    public void configure() throws Exception {
        from("direct:toTempFile")
                .log("Received Message is ${body} and Headers are ${headers}")
                .to("file:temp/temp_dir?fileName=outputfile.txt"); // PROJECT_DIR/temp/temp_dir/outputfile.txt
    }
}