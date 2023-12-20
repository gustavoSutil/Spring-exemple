package com.example.demo.Files;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequestMapping(path = "/file")
public class FilesController{
    private static final String path = "C:\\despertador\\dist";
    private static final String fileName = "main.exe";

    @GetMapping("/exe")
    public ResponseEntity<Resource> getFile() throws MalformedURLException {
        Path filePath = Paths.get(path).resolve(fileName);

        Resource resource = new UrlResource(filePath.toUri());
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getFilename());

            return ResponseEntity.ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(resource);
    }

    @GetMapping("/version")
    public List<String> getLastedVersion(){
        return List.of("version", "1.1.1.1");
    }


}
