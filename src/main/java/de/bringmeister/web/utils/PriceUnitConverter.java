package de.bringmeister.web.utils;

import de.bringmeister.common.JsonPriceUnit;
import java.beans.PropertyEditorSupport;

public class PriceUnitConverter extends PropertyEditorSupport {
    @Override public void setAsText(final String text) throws IllegalArgumentException
    {
        setValue(JsonPriceUnit.valueOf(text.toUpperCase()));
    }
}
