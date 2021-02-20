package com.company.st.entity;

import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.chile.core.annotations.MetaProperty;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import com.haulmont.cuba.security.entity.User;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Table(name = "ST_WAYBILL")
@Entity(name = "st_Waybill")
@NamePattern("%s|reference")
public class Waybill extends StandardEntity {
    private static final long serialVersionUID = -5842090535415841578L;

    @Column(name = "REFERENCE")
    private String reference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREATOR_ID")
    private User creator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SHIPPER_ID")
    private Customer shipper;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONSIGNEE_ID")
    private Customer consignee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DEPARTURE_PORT_ID")
    private Spaceport departurePort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DESTINATION_PORT_ID")
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
        return totalCharge;
    }

    public void setTotalCharge(BigDecimal totalCharge) {
        this.totalCharge = totalCharge;
    }

    public List<WaybillItem> getItems() {
        return items;
    }

    public void setItems(List<WaybillItem> items) {
        this.items = items;
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
    @MetaProperty(related = "items")
    public Double getTotalWeight() {
        Double res = 0.0;
        for (WaybillItem e:items) {
            res+=e.getWeight();
        }
        return res;
    }
}