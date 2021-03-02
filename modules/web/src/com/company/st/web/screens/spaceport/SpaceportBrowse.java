package com.company.st.web.screens.spaceport;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.navigation.UrlParamsChangedEvent;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Spaceport;

import javax.inject.Inject;
import java.util.List;

@UiController("st_Spaceport.browse")
@UiDescriptor("spaceport-browse.xml")
@LookupComponent("spaceportsTable")
@LoadDataBeforeShow
public class SpaceportBrowse extends StandardLookup<Spaceport> {
    @Inject
    private CollectionLoader<Spaceport> spaceportsDl;
    @Inject
    private DataManager dataManager;
    @Inject
    private CollectionContainer<Spaceport> spaceportsDc;


    @Subscribe
    public void onInit(InitEvent event) {
        getScreenData().loadAll();
    }

    @Install(to = "spaceportsTable.edit", subject = "afterCloseHandler")
    private void spaceportsTableEditAfterCloseHandler(AfterCloseEvent afterCloseEvent) {

        getScreenData().loadAll();
    }

    @Install(to = "spaceportsTable.create", subject = "afterCloseHandler")
    private void spaceportsTableCreateAfterCloseHandler(AfterCloseEvent afterCloseEvent) {

        getScreenData().loadAll();
    }








}