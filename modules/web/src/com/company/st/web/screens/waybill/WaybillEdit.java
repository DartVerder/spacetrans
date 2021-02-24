package com.company.st.web.screens.waybill;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Waybill;

@UiController("st_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
@LoadDataBeforeShow
public class WaybillEdit extends StandardEditor<Waybill> {
}