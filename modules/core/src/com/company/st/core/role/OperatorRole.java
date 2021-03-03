package com.company.st.core.role;

import com.company.st.entity.*;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = OperatorRole.NAME)
public class OperatorRole extends AnnotatedRoleDefinition {
    public final static String NAME = "operator";

    @ScreenAccess(screenIds = {"st_Moon.browse", "application-st", "st_Planet.browse", "st_Spaceport.browse", "st_Carrier.browse", "st_Discount.browse", "st_Company.browse", "st_Individual.browse", "st_Waybill.browse", "st_WaybillItem.edit", "st_WaybillItem.browse", "st_Waybill.edit", "st_Individual.edit", "st_Customer.edit", "st_Customer.browse", "st_Company.edit", "st_Atmosphere.browse", "st_Atmosphere.edit", "st_AtmosphericGas.browse", "st_AtmosphericGas.edit", "st_Carrier.edit", "st_Discount.edit", "st_Gas.edit", "st_Gas.browse", "st_Moon.edit", "st_Planet.edit", "st_Spaceport.edit"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = FileDescriptor.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Atmosphere.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = AtmosphericGas.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Carrier.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Company.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Coordinates.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Dimensions.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Discount.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Gas.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Individual.class, operations = {EntityOp.READ, EntityOp.CREATE, EntityOp.UPDATE, EntityOp.DELETE})
    @EntityAccess(entityClass = Moon.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Planet.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Spaceport.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Waybill.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = WaybillItem.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = FileDescriptor.class, view = "*")
    @EntityAttributeAccess(entityClass = Atmosphere.class, view = "*")
    @EntityAttributeAccess(entityClass = AtmosphericGas.class, view = "*")
    @EntityAttributeAccess(entityClass = Carrier.class, view = "*")
    @EntityAttributeAccess(entityClass = Company.class, modify = "*")
    @EntityAttributeAccess(entityClass = Coordinates.class, view = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Dimensions.class, view = "*")
    @EntityAttributeAccess(entityClass = Discount.class, modify = "*")
    @EntityAttributeAccess(entityClass = Gas.class, view = "*")
    @EntityAttributeAccess(entityClass = Individual.class, modify = "*")
    @EntityAttributeAccess(entityClass = Moon.class, view = "*")
    @EntityAttributeAccess(entityClass = Planet.class, view = "*")
    @EntityAttributeAccess(entityClass = Spaceport.class, view = "*")
    @EntityAttributeAccess(entityClass = Waybill.class, modify = "*")
    @EntityAttributeAccess(entityClass = WaybillItem.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = {"cuba.gui.showExceptionDetails", "cuba.gui.loginToClient", "cuba.gui.showInfo"})
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
