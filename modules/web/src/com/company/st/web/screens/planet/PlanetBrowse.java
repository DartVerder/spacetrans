package com.company.st.web.screens.planet;

import com.company.st.service.CsvService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import com.haulmont.cuba.gui.components.FileUploadField;
import com.haulmont.cuba.gui.components.LayoutClickNotifier;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataContext;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Planet;
import com.haulmont.cuba.gui.upload.FileUploadingAPI;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;


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

    private void processfile(File file)
    {
        int defLength=0, newLength=0;
        List<Planet> defaultPlanetList= planetsDc.getMutableItems();
        List<Planet> planetList = null;
        dataContext.merge(defaultPlanetList);
        try {
            planetList=csvService.getPlanets(file);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

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
                    defaultPlanetList.set(j, newPlanet);
                    break;
                }
            }
            if(!change)
                defaultPlanetList.add(newPlanet);
            else
                change=false;
        }

        dataContext.commit();
    }



    @Subscribe("uploadField")
    public void onUploadFieldFileUploadSucceed(FileUploadField.FileUploadSucceedEvent event) {
        UUID fileId = uploadField.getFileId();
        File file = fileUploadingAPI.getFile(fileId);
        processfile(file);

        try {
            fileUploadingAPI.deleteFile(fileId);
        } catch (FileStorageException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Inject
    private CollectionLoader<Planet> planetsDl;

    @Subscribe(id = "planetsDc", target = Target.DATA_CONTAINER)
    public void onPlanetsDcCollectionChange(CollectionContainer.CollectionChangeEvent<Planet> event) {

    }
}