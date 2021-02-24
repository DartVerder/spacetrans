package com.company.st.web.screens.moon;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Moon;

@UiController("st_Moon.browse")
@UiDescriptor("moon-browse.xml")
@LookupComponent("moonsTable")
@LoadDataBeforeShow
public class MoonBrowse extends StandardLookup<Moon> {
}