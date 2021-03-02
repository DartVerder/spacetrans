package com.company.st.web.screens.waybillitem;

import com.company.st.entity.Waybill;
import com.company.st.service.WaybillService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.components.HasValue;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.WaybillItem;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UiController("st_WaybillItem.edit")
@UiDescriptor("waybill-item-edit.xml")
@EditedEntityContainer("waybillItemDc")
@LoadDataBeforeShow
public class WaybillItemEdit extends StandardEditor<WaybillItem> {
    @Inject
    private InstanceContainer<WaybillItem> waybillItemDc;
    @Inject
    private TextField<BigDecimal> chargeField;
    @Inject
    private InstanceContainer<Waybill> waybillDc;
    @Inject
    private CollectionContainer<WaybillItem> waybillItemsDc;
    @Inject
    private TextField<Integer> numberField;

    @Subscribe("dimLengthField")
    public void onDimLengthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(AppBeans.get(WaybillService.class).charge(waybillItemDc.getItem()));
    }

    @Subscribe("dimWidthField")
    public void onDimWidthFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(AppBeans.get(WaybillService.class).charge(waybillItemDc.getItem()));
    }

    @Subscribe("dimHeightField")
    public void onDimHeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(AppBeans.get(WaybillService.class).charge(waybillItemDc.getItem()));
    }

    @Subscribe("weightField")
    public void onWeightFieldValueChange(HasValue.ValueChangeEvent<Double> event) {
        chargeField.setValue(AppBeans.get(WaybillService.class).charge(waybillItemDc.getItem()));
    }



}