package com.company.st.web.screens.spaceport;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Spaceport;

import javax.inject.Inject;

@UiController("st_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
@LoadDataBeforeShow
public class SpaceportBrowse extends StandardLookup<Spaceport> {
    @Inject
    private CollectionLoader<Spaceport> spaceportsDl;




    @Subscribe
    public void onInit(InitEvent event) {
        getScreenData().loadAll();
    }


}