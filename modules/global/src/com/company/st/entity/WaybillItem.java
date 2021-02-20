package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "ST_WAYBILL_ITEM")
@Entity(name = "st_WaybillItem")
@NamePattern("%s|name")
public class WaybillItem extends StandardEntity {
    private static final long serialVersionUID = 5178883424732340774L;

    @Column(name = "NUMBER_")
    private Integer number;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @Column(name = "WEIGHT")
    private Double weight;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "length", column = @Column(name = "DIM_LENGTH")),
            @AttributeOverride(name = "width", column = @Column(name = "DIM_WIDTH")),
            @AttributeOverride(name = "height", column = @Column(name = "DIM_HEIGHT"))
    })
    private Dimensions dim;

    @Column(name = "CHARGE")
    private BigDecimal charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAYBILL_ID")
    private Waybill waybill;

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public BigDecimal getCharge() {
        return charge;
    }

    public void setCharge(BigDecimal charge) {
        this.charge = charge;
    }

    public Dimensions getDim() {
        return dim;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}