package trungitnt95.springboot001.controllers.products;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestRestApiController {

    @GetMapping("/api/v1/test-generate-api-document")
    public List<Integer> testGenerateApiDocument() {
        return List.of(1,2,3,4,5);
    }
}
