package de.bringmeister.data;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "Products")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProducts {


    @XmlElement(name = "Product")
    List<XmlProductEntry> products;
}
