package com.company.st.core;

import com.company.st.entity.Waybill;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.group.ConstraintsContainer;

@AccessGroup(name="FullAccess")
public class FullAccess extends AnnotatedAccessGroupDefinition {
    @JpqlConstraint(target = Waybill.class)
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }
}
