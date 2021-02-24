package com.company.st.web.screens.waybillitem;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.WaybillItem;

@UiController("st_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
@LoadDataBeforeShow
public class WaybillItemEdit extends StandardEditor<WaybillItem> {
}