package de.bringmeister.data;

import com.google.gson.annotations.Expose;
import de.bringmeister.common.JsonPriceUnit;
import javax.money.Monetary;
import lombok.Data;

@Data
public class JsonPriceEntry {
    private String id;

    @Expose
    private  Price price;

    @Expose
    private JsonPriceUnit unit;

    @Data
    public class Price{
        @Expose
        private String currency;
        private Double value;

        public void setValue(Double value){
            this.value = value;
        }

        public void setCurrency(String code){
            Monetary.getCurrency("code");
            currency = code;
        }
    }
}
