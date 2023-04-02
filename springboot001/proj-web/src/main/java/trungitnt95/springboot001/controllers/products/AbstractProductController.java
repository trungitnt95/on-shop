package trungitnt95.springboot001.controllers.products;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import trungitnt95.springboot001.controllers.AbstractController;
import trungitnt95.springboot001.services.ProductService;
import trungitnt95.springboot001.entities.ProductEntity;

import java.util.List;

public abstract class AbstractProductController<T> extends AbstractController<T, ProductEntity> {
    protected ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public List<T> getProducts(@RequestParam(name = "name", required = false) String name,
                               @RequestParam(name = "tag", required = false) String tag,
                               @RequestParam(name = "sortOnField", required = false) String sortOnField,
                               @RequestParam(name = "sortManner", required = false) String sortManner,
                               @RequestParam(name = "pageIndex", required = false) String pageIndex) {
        return getMapper().toDtos(productService.getProducts());
    }

    @GetMapping("/{id}")
    public T getProduct(@PathVariable("id") Long productId) {
        return getMapper().toDto(productService.getProductDetail(productId));
    }
}
