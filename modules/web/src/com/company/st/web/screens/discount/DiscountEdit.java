package com.company.st.web.screens.discount;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Discount;

@UiController("st_Discount.edit")
@UiDescriptor("discount-edit.xml")
@EditedEntityContainer("discountDc")
@LoadDataBeforeShow
public class DiscountEdit extends StandardEditor<Discount> {
}