package ru.perm.v.spring.camel.api.route;


import org.apache.camel.ProducerTemplate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

//@CamelSpringBootTest
@EnableAutoConfiguration
@SpringBootTest
@Disabled
public class SampleDirectToFileRouteTest {

    @Autowired
    ProducerTemplate producerTemplate;

    @Test
    void toTempFile() throws IOException {
        Path path = Paths.get("temp/temp_dir/outputfile.txt"); // catalog "temp/temp_dir/" is in project catalog
        Files.deleteIfExists(path);
        String content = "Content abcdef";
        producerTemplate.requestBody("direct:toTempFile", content);

        assertTrue(Files.exists(path));

        List<String> contentWrited = Files.readAllLines(path);
        assertEquals(content, contentWrited.get(0));
    }

    @Test
    void existTempFileWithFile() {
        //File
        assertTrue(new File("/home/vasi/temp/temp_file1.txt").isFile());
        assertTrue(new File("/home/vasi/temp").exists());
    }

    @Test
    void existTempFileWithPaths_AbsolutePath() {
        Path path = Paths.get("/home/vasi/temp/temp_file1.txt"); // use Paths

        assertTrue(Files.exists(path));
        assertTrue(path.toFile().exists());
    }

    @Test
    void existTempFileWithPaths_HomePath() {
        Path pathFromHome = Paths.get("temp/file_in_temp.txt"); // on HOME dir: ~/temp/temp_file.txt
        assertEquals("/home/vasi/prog/java/camel/camel_boot_rest_2023/temp/file_in_temp.txt",
                pathFromHome.toAbsolutePath().toString());

        assertTrue(pathFromHome.toFile().exists());
    }

}
