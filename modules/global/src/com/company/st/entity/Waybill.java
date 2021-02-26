package com.company.st.entity;

import com.company.st.service.WaybillService;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "ST_WAYBILL")
@Entity(name = "st_Waybill")
@NamePattern("%s|reference")
public class Waybill extends StandardEntity {
    private static final long serialVersionUID = -5842090535415841578L;

    @Column(name = "REFERENCE", nullable = false)
    @NotNull
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SHIPPER_ID")
    @NotNull
    private Customer shipper;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "CONSIGNEE_ID")
    @NotNull
    private Customer consignee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DEPARTURE_PORT_ID")
    @NotNull
    private Spaceport departurePort;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "DESTINATION_PORT_ID")
    @NotNull
    private Spaceport destinationPort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CARRIER_ID")
    private Carrier carrier;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "waybill")
    private List<WaybillItem> items;


    @Column(name = "TOTAL_CHARGE")
    private BigDecimal totalCharge;

    public BigDecimal getTotalCharge() {
        return AppBeans.get(WaybillService.class).totalCharge(this);
    }

    public List<WaybillItem> getItems() {
        return items;
    }

    public Carrier getCarrier() {
        return carrier;
    }

    public void setCarrier(Carrier carrier) {
        this.carrier = carrier;
    }

    public Spaceport getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Spaceport destinationPort) {
        this.destinationPort = destinationPort;
    }

    public Spaceport getDeparturePort() {
        return departurePort;
    }

    public void setDeparturePort(Spaceport departurePort) {
        this.departurePort = departurePort;
    }

    public Customer getConsignee() {
        return consignee;
    }

    public void setConsignee(Customer consignee) {
        this.consignee = consignee;
    }

    public Customer getShipper() {
        return shipper;
    }

    public void setShipper(Customer shipper) {
        this.shipper = shipper;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @MetaProperty
    public Double getTotalWeight() {
        return AppBeans.get(WaybillService.class).totalWeight(this);
    }
}