package com.company.st.web.screens.discount;

import com.haulmont.cuba.gui.screen.*;
import com.company.st.entity.Discount;

@UiController("st_Discount.browse")
@UiDescriptor("discount-browse.xml")
@LookupComponent("discountsTable")
@LoadDataBeforeShow
public class DiscountBrowse extends StandardLookup<Discount> {
}