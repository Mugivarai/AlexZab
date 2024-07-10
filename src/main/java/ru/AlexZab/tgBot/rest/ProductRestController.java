package ru.AlexZab.tgBot.rest;

import org.springframework.web.bind.annotation.*;
import ru.AlexZab.tgBot.entity.Product;
import ru.AlexZab.tgBot.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/rest/products")
public class ProductRestController {

    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    List<Product> getProductsBySearch(
            @RequestParam(value = "categoryId",required = false)
            Long categoryId,
            @RequestParam(value = "name",required = false)
            String name
    ){
        if(categoryId!=null){
            return productService.getProductsByCategoryId(categoryId);
        } else if (name!=null) {
            return productService.getProductsByName(name);
        }else {
            return null;
        }
    }

    @GetMapping("/popular")
    List<Product> getPopularProducts(@RequestParam(value = "limit") Integer limit){
        return productService.getPopularProducts(limit);
    }

}
