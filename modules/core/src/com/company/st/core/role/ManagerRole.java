package com.company.st.core.role;

import com.company.st.entity.*;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.entity.User;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = ManagerRole.NAME)
public class ManagerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "manager";

    @ScreenAccess(screenIds = {"st_Moon.browse", "application-st", "st_Planet.browse", "st_Spaceport.browse", "st_Carrier.browse", "st_Discount.browse", "st_Company.browse", "st_Waybill.browse", "st_Individual.browse", "administration", "st_WaybillItem.edit", "st_WaybillItem.browse", "st_Waybill.edit", "st_Spaceport.edit", "st_Planet.edit", "st_Moon.edit", "st_Individual.edit", "st_Gas.edit", "st_Gas.browse", "st_Discount.edit", "st_Customer.edit", "st_Customer.browse", "st_Company.edit", "st_Carrier.edit", "st_AtmosphericGas.edit", "st_Atmosphere.edit", "st_AtmosphericGas.browse", "st_Atmosphere.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = FileDescriptor.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = User.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = WaybillItem.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Waybill.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Spaceport.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Planet.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Moon.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Individual.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Gas.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Discount.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Dimensions.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Customer.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Coordinates.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Company.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Carrier.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = AtmosphericGas.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @EntityAccess(entityClass = Atmosphere.class, operations = {EntityOp.CREATE, EntityOp.UPDATE, EntityOp.READ, EntityOp.DELETE})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = FileDescriptor.class, modify = "*")
    @EntityAttributeAccess(entityClass = User.class, view = "*")
    @EntityAttributeAccess(entityClass = WaybillItem.class, modify = "*")
    @EntityAttributeAccess(entityClass = Waybill.class, modify = "*")
    @EntityAttributeAccess(entityClass = Spaceport.class, modify = "*")
    @EntityAttributeAccess(entityClass = Planet.class, modify = "*")
    @EntityAttributeAccess(entityClass = Moon.class, modify = "*")
    @EntityAttributeAccess(entityClass = Individual.class, modify = "*")
    @EntityAttributeAccess(entityClass = Gas.class, modify = "*")
    @EntityAttributeAccess(entityClass = Discount.class, modify = "*")
    @EntityAttributeAccess(entityClass = Dimensions.class, modify = "*")
    @EntityAttributeAccess(entityClass = Customer.class, modify = "*")
    @EntityAttributeAccess(entityClass = Coordinates.class, modify = "*")
    @EntityAttributeAccess(entityClass = Company.class, modify = "*")
    @EntityAttributeAccess(entityClass = Carrier.class, modify = "*")
    @EntityAttributeAccess(entityClass = AtmosphericGas.class, modify = "*")
    @EntityAttributeAccess(entityClass = Atmosphere.class, modify = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = {"cuba.gui.appFolder.global", "cuba.gui.bulkEdit", "cuba.gui.loginToClient", "cuba.gui.presentations.global", "cuba.gui.searchFolder.global", "cuba.gui.showExceptionDetails", "cuba.gui.showInfo", "cuba.gui.filter.customConditions", "cuba.gui.filter.global", "cuba.gui.filter.edit", "cuba.gui.filter.maxResults"})
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
