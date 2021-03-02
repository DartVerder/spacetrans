package com.company.st.web.screens.waybillitem;

import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.WaybillItem;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@UiController("st_WaybillItem.browse")
@UiDescriptor("waybill-item-browse.xml")
@LookupComponent("waybillItemsTable")
@LoadDataBeforeShow
public class WaybillItemBrowse extends StandardLookup<WaybillItem> {
    @Inject
    private CollectionContainer<WaybillItem> waybillItemsDc;


}