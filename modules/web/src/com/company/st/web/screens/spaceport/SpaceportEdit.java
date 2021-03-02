package com.company.st.web.screens.spaceport;

import com.company.st.entity.Planet;
import com.haulmont.cuba.client.AttributeAccessUpdater;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.PickerField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Spaceport;

import javax.inject.Inject;
import java.util.List;

@UiController("st_Spaceport.edit")
@UiDescriptor("spaceport-edit.xml")
@EditedEntityContainer("spaceportDc")
@LoadDataBeforeShow
public class SpaceportEdit extends StandardEditor<Spaceport> {
    @Inject
    private InstanceContainer<Spaceport> spaceportDc;
    @Inject
    private CollectionContainer<Spaceport> spaceportsDc;
    @Inject
    private Notifications notifications;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {

    }
}