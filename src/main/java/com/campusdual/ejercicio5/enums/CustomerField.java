package com.campusdual.ejercicio5.enums;

public enum CustomerField {
    NAME(true),
    SURNAME(true),
    WEIGHT(false),
    HEIGHT(false),
    AGE(false),
    GENDER(true);

    private boolean text;

    CustomerField(boolean text) {
        this.text = text;
    }

    public boolean isText(){
        return text;
    }
}
