package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@Table(name = "ST_COMPANY")
@Entity(name = "st_Company")
@NamePattern("%s|name")
public class Company extends Customer {
    private static final long serialVersionUID = 8159731181693766843L;

    @Column(name = "REGISTRATION_ID", nullable = false)
    @NotNull
    private String registrationId;

    @Column(name = "COMPANY_TYPE", nullable = false)
    @NotNull
    private String companyType;

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getRegistrationId() {
        return registrationId;
    }

    public void setRegistrationId(String registrationId) {
        this.registrationId = registrationId;
    }
}