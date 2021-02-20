package com.company.st.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "ST_CARRIER")
@Entity(name = "st_Carrier")
@NamePattern("%s|name")
public class Carrier extends StandardEntity {
    private static final long serialVersionUID = 1003085135142126097L;

    @NotNull
    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @JoinTable(name = "ST_CARRIER_SPACEPORT_LINK",
            joinColumns = @JoinColumn(name = "CARRIER_ID"),
            inverseJoinColumns = @JoinColumn(name = "SPACEPORT_ID"))
    @ManyToMany
    private List<Spaceport> ports;

    public List<Spaceport> getPorts() {
        return ports;
    }

    public void setPorts(List<Spaceport> ports) {
        this.ports = ports;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}