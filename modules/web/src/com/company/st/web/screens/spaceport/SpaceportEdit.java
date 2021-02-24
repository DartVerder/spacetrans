package com.company.st.web.screens.spaceport;

import com.haulmont.cuba.gui.model.CollectionContainer;
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
    private CollectionContainer<Spaceport> spaceportsDc;
    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Spaceport spaceport = spaceportDc.getItem();
        if (spaceport.getIsDefault() !=null) {
            if (spaceportsDc != null) {
                List<Spaceport> allSpaceports = spaceportsDc.getMutableItems();
                for (Spaceport tmp : allSpaceports) {
                    if (tmp.getIsDefault()) {
                        tmp.setIsDefault(false);
                        break;
                    }
                }
            }
        }
    }
}