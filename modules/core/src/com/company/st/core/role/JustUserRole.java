package com.company.st.core.role;

import com.company.st.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.*;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;
import com.haulmont.cuba.security.role.SpecificPermissionsContainer;

@Role(name = JustUserRole.NAME, isDefault = true)
public class JustUserRole extends AnnotatedRoleDefinition {
    public final static String NAME = "justUser";

    @ScreenAccess(screenIds = {"st_Moon.browse", "application-st", "st_Planet.browse", "st_Spaceport.browse", "st_Carrier.browse", "st_Discount.browse"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }

    @EntityAccess(entityClass = Spaceport.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Planet.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Moon.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Gas.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Discount.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Dimensions.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Coordinates.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Carrier.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = AtmosphericGas.class, operations = EntityOp.READ)
    @EntityAccess(entityClass = Atmosphere.class, operations = EntityOp.READ)
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }

    @EntityAttributeAccess(entityClass = WaybillItem.class, view = "*")
    @EntityAttributeAccess(entityClass = Waybill.class, view = "*")
    @EntityAttributeAccess(entityClass = Spaceport.class, view = "*")
    @EntityAttributeAccess(entityClass = Planet.class, view = "*")
    @EntityAttributeAccess(entityClass = Moon.class, view = "*")
    @EntityAttributeAccess(entityClass = Gas.class, view = "*")
    @EntityAttributeAccess(entityClass = Discount.class, view = "*")
    @EntityAttributeAccess(entityClass = Dimensions.class, view = "*")
    @EntityAttributeAccess(entityClass = Coordinates.class, view = "*")
    @EntityAttributeAccess(entityClass = Company.class, view = "*")
    @EntityAttributeAccess(entityClass = Carrier.class, view = "*")
    @EntityAttributeAccess(entityClass = AtmosphericGas.class, view = "*")
    @EntityAttributeAccess(entityClass = Atmosphere.class, view = "*")
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @SpecificAccess(permissions = {"cuba.gui.showInfo", "cuba.gui.loginToClient"})
    @Override
    public SpecificPermissionsContainer specificPermissions() {
        return super.specificPermissions();
    }
}
