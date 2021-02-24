package com.company.st.web.screens.waybillitem;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.WaybillItem;

@UiController("st_WaybillItem.browse")
@UiDescriptor("waybill-item-browse.xml")
@LookupComponent("waybillItemsTable")
@LoadDataBeforeShow
public class WaybillItemBrowse extends StandardLookup<WaybillItem> {
}