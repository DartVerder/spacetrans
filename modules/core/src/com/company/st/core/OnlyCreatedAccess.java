package com.company.st.core;

import com.company.st.entity.Waybill;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.app.group.AnnotatedAccessGroupDefinition;
import com.haulmont.cuba.security.app.group.annotation.AccessGroup;
import com.haulmont.cuba.security.app.group.annotation.Constraint;
import com.haulmont.cuba.security.app.group.annotation.JpqlConstraint;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.group.ConstraintsContainer;

import javax.inject.Inject;

@AccessGroup(name = "OnlyCreatedAccess")
public class OnlyCreatedAccess  extends AnnotatedAccessGroupDefinition {
    @JpqlConstraint(target = Waybill.class, where = "{E}.createdBy = :session$userLogin")
    @Override
    public ConstraintsContainer accessConstraints() {
        return super.accessConstraints();
    }

    @Inject
    private UserSessionSource userSessionSource;

    @Constraint(operations = {EntityOp.CREATE, EntityOp.READ, EntityOp.UPDATE, EntityOp.DELETE})
    public boolean waybillConstraints(Waybill waybill) {
        return waybill.getCreator().equals(userSessionSource.getUserSession().getUser());
    }
}