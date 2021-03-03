package com.company.st.web.screens.moon;

import com.company.st.entity.AstronimicBody;
import com.company.st.entity.Planet;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Moon;

import javax.inject.Inject;
import java.util.List;

@UiController("st_Moon.edit")
@UiDescriptor("moon-edit.xml")
@EditedEntityContainer("moonDc")
@LoadDataBeforeShow
public class MoonEdit extends StandardEditor<Moon> {
    @Inject
    private InstanceContainer<Moon> moonDc;

    @Inject
    private CollectionContainer<Moon> moonsDc;

    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        List<Moon> moons=moonsDc.getItems();
        Moon newMoon=moonDc.getItem();
        for(Moon m:moons)
        {
            if(m.getName().equals(newMoon.getName())&&!m.getId().equals(newMoon.getId()))
                throw new RuntimeException("\n" +
                        "Please change the name of the Moon. This name already exists");
        }
    }
}