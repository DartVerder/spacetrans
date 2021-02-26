package com.company.st.web.screens.planet;

import com.company.st.service.CsvService;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
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
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.UUID;


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
    @Inject
    private Image planetImage;

    private void processfile(File file)
    {
        int defLength, newLength;
        List<Planet> defaultPlanetList= planetsDc.getMutableItems();
        List<Planet> planetList = null;
        try {
            planetList=csvService.getPlanets(file);
        } catch (IOException ioException) {
            log.error("Error", ioException);
        }
        assert planetList != null;
        newLength = planetList.size();
        Planet newPlanet, defPlanet;
        boolean change=false;
        for(int i =0;i<newLength; i++)
        {
            defLength = defaultPlanetList.size();
            newPlanet = planetList.get(i);

            for(int j=0;j<defLength;j++)
            {
                defPlanet = defaultPlanetList.get(j);
                change=newPlanet.getName().equals(defPlanet.getName());
                if(change) {
                    defPlanet=dataContext.merge(defPlanet);
                    planetsDc.getMutableItems().set(j,newPlanet);
                    dataContext.setModified(defPlanet,true);
                    break;
                }
            }
            if(!change) {
                newPlanet = dataContext.merge(newPlanet);
                planetsDc.getMutableItems().add(newPlanet);
            }
            else
                change=false;
        }

        dataContext.commit();
        getScreenData().loadAll();
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