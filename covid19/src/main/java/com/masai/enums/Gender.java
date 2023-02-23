package com.masai.enums;

public enum Gender {
    Male("Male"),
    Female("Female"),
    Other("Other");

    private String gen;

    private Gender(String gen) {
        this.gen = gen;
    }

    public String getGen() {
        return gen;
    }
}
