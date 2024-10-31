package ru.perm.v.spring.camel.api.route;


import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
public class SampleDirectRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    void toTempFile() throws IOException {
        producerTemplate.requestBody("direct:toTempFile", "Content abcdef");
        Path path = Paths.get("file:temp/?fileName=output.txt");
//        assertTrue(new File("file:temp/temp_dir?fileName=output.txt").isFile());
//        assertFalse(Files.exists(path));
//        assertTrue(new File("/simpleOutput/output.txt").exists());
    }

    @Test
    void existTempFileWithFile() {
        //File
        assertTrue(new File("/home/vasi/temp/temp_file1.txt").isFile()); //OK
        assertTrue(new File("/home/vasi/temp").exists()); //OK
    }

    @Test
    void existTempFileWithPaths() {
        //Paths
        Path path = Paths.get("/home/vasi/temp/temp_file1.txt");
        assertTrue(Files.exists(path)); //OK
        assertTrue(path.toFile().exists()); //OK

        Path pathFromHome = Paths.get("temp/file_in_temp.txt"); // on HOME dir: ~/temp/temp_file.txt
        assertEquals("/home/vasi/prog/java/camel/camel_boot_rest_2023/temp/file_in_temp.txt",
                pathFromHome.toAbsolutePath().toString()); //OK

        assertTrue(pathFromHome.toFile().exists()); //OK
    }

}
