package com.company.st.entity;

import com.company.st.service.WaybillService;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.EmbeddedParameters;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;
import com.haulmont.cuba.core.global.AppBeans;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@PublishEntityChangedEvents
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

    @Column(name = "WEIGHT", nullable = false)
    @NotNull
    private Double weight;

    @Embedded
    @EmbeddedParameters(nullAllowed = false)
    @AttributeOverrides({
            @AttributeOverride(name = "length", column = @Column(name = "DIM_LENGTH")),
            @AttributeOverride(name = "width", column = @Column(name = "DIM_WIDTH")),
            @AttributeOverride(name = "height", column = @Column(name = "DIM_HEIGHT"))
    })
    private @NotNull Dimensions dim;

    @Column(name = "CHARGE")
    private BigDecimal charge;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WAYBILL_ID")
    private Waybill waybill;

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Dimensions getDim() {
        return dim;
    }

    public void setDim(Dimensions dim) {
        this.dim = dim;
    }

    public Waybill getWaybill() {
        return waybill;
    }

    public void setWaybill(Waybill waybill) {
        this.waybill = waybill;
    }

    public BigDecimal getCharge() {
        return AppBeans.get(WaybillService.class).charge(this);
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

}