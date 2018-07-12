package de.bringmeister.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlProductEntry {
    @XmlAttribute
    private String id;
    
    @XmlElement(name = "Name", required = true)
    String name;

    @XmlElement(name = "Description", required = true)
    String description;

    String sku;
}
