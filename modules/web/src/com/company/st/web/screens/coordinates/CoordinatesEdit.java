package com.company.st.web.screens.coordinates;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Coordinates;

@UiController("st_Coordinates.edit")
@UiDescriptor("coordinates-edit.xml")
@EditedEntityContainer("coordinatesDc")
@LoadDataBeforeShow
public class CoordinatesEdit extends StandardEditor<Coordinates> {
}