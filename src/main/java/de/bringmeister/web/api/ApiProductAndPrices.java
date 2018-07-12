package de.bringmeister.web.api;

import com.google.common.collect.Lists;
import de.bringmeister.data.JsonPriceEntry;
import de.bringmeister.data.XmlProductEntry;
import java.util.List;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ApiProductAndPrices {

    @NotBlank
    String id;

    @NotBlank
    String description;

    @NotBlank
    String sku;

    List<ApiPrice> prices = Lists.newArrayList();

    public ApiProductAndPrices(XmlProductEntry product, List<JsonPriceEntry> jsonPriceEntries) {
        this.id = product.getId();
        this.description = product.getDescription();
        this.sku = product.getSku();

        jsonPriceEntries.stream().forEach(price -> addPrice(price));
    }

    public void addPrice(JsonPriceEntry priceEntry){
        prices.add(new ApiPrice(priceEntry));
    }
}
