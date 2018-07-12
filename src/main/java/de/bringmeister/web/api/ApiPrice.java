package de.bringmeister.web.api;

import de.bringmeister.common.JsonPriceUnit;
import de.bringmeister.data.JsonPriceEntry;
import lombok.Data;

@Data
public class ApiPrice {

    private final Double value;
    private final String currency;
    private final JsonPriceUnit unit;

    public ApiPrice(JsonPriceEntry entry){
        this.value = entry.getPrice().getValue();
        this.currency = entry.getPrice().getCurrency();
        this.unit = entry.getUnit();
    }
}
