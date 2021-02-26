package com.company.st.entity;

import com.haulmont.chile.core.datatypes.impl.EnumClass;

import javax.annotation.Nullable;


public enum CustomerGrade implements EnumClass<Integer> {

    BRONZE(10),
    SILVER(20),
    GOLD(30);

    private Integer id;

    CustomerGrade(Integer value) {
        this.id = value;
    }

    public Integer getId() {
        return id;
    }

    @Nullable
    public static CustomerGrade fromId(Integer id) {
        for (CustomerGrade at : CustomerGrade.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }

}