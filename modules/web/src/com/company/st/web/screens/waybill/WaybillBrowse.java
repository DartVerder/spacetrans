package com.company.st.web.screens.waybill;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Waybill;

@UiController("st_Waybill.browse")
@UiDescriptor("waybill-browse.xml")
@LookupComponent("waybillsTable")
@LoadDataBeforeShow
public class WaybillBrowse extends StandardLookup<Waybill> {
}