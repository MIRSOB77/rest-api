package de.bringmeister.web.api;

import de.bringmeister.data.JsonPriceEntry;
import de.bringmeister.data.XmlProductEntry;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class ApiProduct {
    @NotBlank
    String id;

    @NotBlank
    String description;

    @NotBlank
    String sku;

    public ApiProduct(XmlProductEntry product){
        this.id = product.getId();
        this.description = product.getDescription();
        this.sku = product.getSku();
    }
}
