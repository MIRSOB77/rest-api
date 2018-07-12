package de.bringmeister.web;

import de.bringmeister.common.JsonPriceUnit;
import de.bringmeister.data.JsonPriceEntry;
import de.bringmeister.data.XmlProductEntry;
import de.bringmeister.web.api.ApiPrice;
import de.bringmeister.web.api.ApiProduct;
import de.bringmeister.web.api.ApiProductAndPrices;
import de.bringmeister.web.utils.PriceUnitConverter;
import java.util.List;
import java.util.stream.Collectors;
import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductEndpoint {

    @Autowired
    ProductService productService;

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        dataBinder.registerCustomEditor(JsonPriceUnit.class, new PriceUnitConverter());
    }

    
    @GetMapping
    public List<ApiProduct> getAllMasterProducts(){
        return productService.getAllProducts().stream().map(ApiProduct::new).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ApiProductAndPrices getMasterProduct(@PathVariable("id") String id){

        Pair<XmlProductEntry, List<JsonPriceEntry>> productWithPrices = productService.getMasterProduct(id);
        return new ApiProductAndPrices(productWithPrices.getKey(),productWithPrices.getValue());
    }

    @GetMapping("/{skuId}/{unit}")
    public ApiPrice getPriceForUnit(@PathVariable("skuId") String skuId, @PathVariable("unit") JsonPriceUnit unit){
        return new ApiPrice(productService.getPrice(skuId, unit));
    }

}
