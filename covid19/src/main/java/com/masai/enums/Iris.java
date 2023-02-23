package com.masai.enums;

public enum Iris {
    Present("Present"),
    Absent("Absent"),
    ;


    private String irres;

    private Iris(String ire) {
        this.irres = ire;
    }

    public String getIre() {
        return irres;
    }
}
