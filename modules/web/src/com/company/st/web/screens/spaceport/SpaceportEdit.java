package com.company.st.web.screens.spaceport;

import com.company.st.entity.Planet;
import com.haulmont.cuba.client.AttributeAccessUpdater;
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
    private Button commitAndCloseBtn;
    @Inject
    private AttributeAccessUpdater attributeAccessUpdater;
    @Inject
    private DataComponents dataComponents;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        Spaceport spaceport = spaceportDc.getItem();
        //check that used only Planet or only Moon

        if (spaceport.getMoon() != null && spaceport.getPlanet() != null) {
            throw new RuntimeException("Please select only one: Moon OR Planet");
        }

        //check that isDefault=true is the only one
        if (spaceportsDc != null) {
            if (spaceport.getIsDefault() != null&& spaceport.getIsDefault()) {
                List<Spaceport> allSpaceports = spaceportsDc.getMutableItems();
                for (Spaceport tmp : allSpaceports) {
                    if (tmp.getIsDefault()!=null&& !tmp.getName().equals(spaceport.getName()) && tmp.getIsDefault()){
                        tmp.setIsDefault(null);
                        break;
                    }
                }
            }
        }
    }
}