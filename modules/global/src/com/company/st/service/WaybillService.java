package com.company.st.service;

import com.company.st.entity.Waybill;
import com.company.st.entity.WaybillItem;

import java.math.BigDecimal;

public interface WaybillService {
    String NAME = "st_WaybillService";

    Double totalWeight(Waybill waybill);
    BigDecimal totalCharge(Waybill waybill);
    BigDecimal charge(WaybillItem waybillItem);
}