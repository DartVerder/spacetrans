package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.NumberFormat;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "ST_DISCOUNT")
@Entity(name = "st_Discount")
@NamePattern("%s|id")
public class Discount extends StandardEntity {
    private static final long serialVersionUID = -2810378743385215723L;

    @NumberFormat(pattern = "##.00", decimalSeparator = ".")
    @NotNull
    @Column(name = "VALUE_", nullable = false)
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}