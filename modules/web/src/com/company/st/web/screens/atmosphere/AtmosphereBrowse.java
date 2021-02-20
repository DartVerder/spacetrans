package com.company.st.web.screens.atmosphere;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Atmosphere;

@UiController("st_Atmosphere.browse")
@UiDescriptor("atmosphere-browse.xml")
@LookupComponent("atmospheresTable")
@LoadDataBeforeShow
public class AtmosphereBrowse extends StandardLookup<Atmosphere> {
}