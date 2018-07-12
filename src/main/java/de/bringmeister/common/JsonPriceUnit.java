package de.bringmeister.common;

import com.google.gson.annotations.SerializedName;

public enum JsonPriceUnit {
    @SerializedName("piece")
    PIECE("piece"),
    @SerializedName("package")
    PACKAGE("package");

    private final String value;

    JsonPriceUnit(String value){
        this.value = value;
    }
}
