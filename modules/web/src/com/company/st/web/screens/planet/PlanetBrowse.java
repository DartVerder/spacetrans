package com.company.st.web.screens.planet;

import com.company.st.service.CsvService;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.core.global.FluentValueLoader;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.*;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Planet;
import com.haulmont.cuba.gui.screen.LookupComponent;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.*;


@UiController("st_Planet.browse")
@UiDescriptor("planet-browse.xml")
@LookupComponent("planetsTable")
@LoadDataBeforeShow
public class PlanetBrowse extends StandardLookup<Planet> {
    @Inject
    private FileUploadField uploadField;
    @Inject
    private FileUploadingAPI fileUploadingAPI;
    @Inject
    private CsvService csvService;
    @Inject
    private CollectionContainer<Planet> planetsDc;
    @Inject
    private DataManager dataManager;
    @Inject
    private DataContext dataContext;
    @Inject
    private Logger log;
    @Inject
    private DataComponents dataComponents;
    @Inject
    private GroupTable<Planet> planetsTable;


    private void processfile(File file) {
        int defLength, newLength;
        List<Planet> defaultPlanetList = dataManager.load(Planet.class).list();
        List<Planet> planetList = new ArrayList<>();
        try {
            planetList = csvService.getPlanets(file);
        } catch (IOException ioException) {
            log.error("Error", ioException);
        }
        if (planetList != null) {
            Planet planet;
            boolean change = false;
                 for(Planet newPlanet:planetList) {
                     for(Planet defPlanet:defaultPlanetList) {
                         if (newPlanet.getName().equals(defPlanet.getName())) {
                             planetField(defPlanet,newPlanet);
                             change=true;
                             dataManager.commit(defPlanet);
                             break;
                         }
                     }
                     if(!change) {
                         planet = dataManager.create(Planet.class);
                         planetField(planet,newPlanet);
                         dataManager.commit(planet);
                     }
                     change=false;
                    }
                    dataManager.commit();
                }
            getScreenData().loadAll();

    }


    private void planetField(Planet planet,Planet newPlanet) {
        planet.setName(newPlanet.getName());
        planet.setMass(newPlanet.getMass());
        planet.setSemiMajorAxis(newPlanet.getSemiMajorAxis());
        planet.setOrbitalPeriod(newPlanet.getOrbitalPeriod());
        planet.setRotationPeriod(newPlanet.getRotationPeriod());
        planet.setRings(newPlanet.getRings());
    }

    @Subscribe("uploadField")
    public void onUploadFieldFileUploadSucceed(FileUploadField.FileUploadSucceedEvent event) {
        UUID fileId = uploadField.getFileId();
        File file = fileUploadingAPI.getFile(fileId);
        processfile(file);

        try {
            fileUploadingAPI.deleteFile(fileId);
        } catch (FileStorageException ex) {
            log.error("Error", ex);
        }
    }

    @Inject
    private CollectionLoader<Planet> planetsDl;

    @Subscribe(id = "planetsDc", target = Target.DATA_CONTAINER)
    public void onPlanetsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Planet> event) {

    }

    @Subscribe(id = "planetsDc", target = Target.DATA_CONTAINER)
    private void onPlanetDcCollectionChange(CollectionContainer.CollectionChangeEvent<Planet> event) {
        CollectionChangeType changeType = event.getChangeType();
        Collection<? extends Planet> changes = event.getChanges();
        // ...
    }


}