package com.company.st.web.screens.coordinates;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Coordinates;

@UiController("st_Coordinates.browse")
@UiDescriptor("coordinates-browse.xml")
@LookupComponent("coordinatesesTable")
@LoadDataBeforeShow
public class CoordinatesBrowse extends StandardLookup<Coordinates> {
}