package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "ST_INDIVIDUAL")
@Entity(name = "st_Individual")
@NamePattern("%s|name")
public class Individual extends Customer {
    private static final long serialVersionUID = 3802235988198926367L;

    @Column(name = "FIRST_NAME", nullable = false)
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME", nullable = false)
    @NotNull
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}