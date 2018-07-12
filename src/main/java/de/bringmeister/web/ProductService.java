package de.bringmeister.web;

import com.google.common.collect.Lists;
import com.google.common.io.Resources;
import com.google.gson.Gson;
import de.bringmeister.common.JsonPriceUnit;
import de.bringmeister.data.JsonPriceEntry;
import de.bringmeister.data.XmlProductEntry;
import de.bringmeister.data.XmlProducts;
import de.bringmeister.exception.EntityNotFoundEcxeptiom;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import javafx.util.Pair;
import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
public class ProductService {

    List<Class> contextClasses = Lists.newArrayList(XmlProducts.class);
    private XmlProducts xmlProducts;
    private MultiValueMap<String, JsonPriceEntry> jsonPrices = new LinkedMultiValueMap<>();

    @PostConstruct
    public void initData() throws JAXBException, IOException {
        JAXBContext jaxbContext = JAXBContext.newInstance((Class[])this.contextClasses.toArray(new Class[this.contextClasses.size()]));

        xmlProducts = (XmlProducts)jaxbContext.createUnmarshaller().unmarshal(initReader("products/products.xml"));

        List<JsonPriceEntry> pricesList = Arrays.<JsonPriceEntry>asList(new Gson().fromJson(initReader("products/prices.json"), JsonPriceEntry[].class));

        pricesList.stream().forEach(entry -> jsonPrices.add(entry.getId(),entry));
    }

    private Reader initReader(String path) throws IOException {
        return new InputStreamReader(Resources.getResource(path).openStream());
    }


    public List<XmlProductEntry> getAllProducts(){
        return xmlProducts.getProducts();
    }

    public Pair<XmlProductEntry,List<JsonPriceEntry>> getMasterProduct(String id) {
         XmlProductEntry masterProduct = xmlProducts.getProducts().stream().filter(master -> master.getId().equals(id)).findFirst().orElseThrow(() -> new EntityNotFoundEcxeptiom("product not found"));

         return new Pair<>(masterProduct, jsonPrices.get(masterProduct.getSku()));
    }

    public JsonPriceEntry getPrice(String skuId, JsonPriceUnit unit){
        return jsonPrices.get(skuId).stream().filter(p -> p.getUnit()==unit).findFirst().orElseThrow(() -> new EntityNotFoundEcxeptiom("price not found"));
    }
}
