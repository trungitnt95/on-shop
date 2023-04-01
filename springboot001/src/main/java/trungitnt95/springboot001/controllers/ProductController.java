package trungitnt95.springboot001.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController {

    @GetMapping(value = {"/v1", "/v2"})
    public List<String> getProducts() {
        return List.of("v1", "v2");
    }
}
