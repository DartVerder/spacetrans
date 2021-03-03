package com.company.st.web.screens.planet;

import com.company.st.entity.AstronimicBody;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Planet;

import javax.inject.Inject;
import java.util.List;

@UiController("st_Planet.edit")
@UiDescriptor("planet-edit.xml")
@EditedEntityContainer("planetDc")
@LoadDataBeforeShow
public class PlanetEdit extends StandardEditor<Planet> {
    @Inject
    private InstanceContainer<Planet> planetDc;
    @Inject
    private CollectionContainer<Planet> planetsDc;


    @Subscribe
    public void onBeforeCommitChanges(BeforeCommitChangesEvent event) {
        List<Planet> planets=planetsDc.getItems();
        Planet newPlanet=planetDc.getItem();
        for(Planet planet:planets)
        {
            if(planet.getName().equals(newPlanet.getName()))
                throw new RuntimeException("\n" +
                        "Please change the name of the Planet. This name already exists");
        }
    }
}