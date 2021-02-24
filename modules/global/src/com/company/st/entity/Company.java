package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;

@PrimaryKeyJoinColumn(name = "ID", referencedColumnName = "ID")
@DiscriminatorValue("COMPANY")
@Table(name = "ST_COMPANY")
@Entity(name = "st_Company")
@NamePattern("%s|registrationId")
public class Company extends StandardEntity {
    private static final long serialVersionUID = 8159731181693766843L;

    @Column(name = "REGISTRATION_ID")
    private String registrationId;

    @Column(name = "COMPANY_TYPE")
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