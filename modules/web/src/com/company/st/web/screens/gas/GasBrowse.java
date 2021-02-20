package com.company.st.web.screens.gas;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Gas;

@UiController("st_Gas.browse")
@UiDescriptor("gas-browse.xml")
@LookupComponent("gasesTable")
@LoadDataBeforeShow
public class GasBrowse extends StandardLookup<Gas> {
}