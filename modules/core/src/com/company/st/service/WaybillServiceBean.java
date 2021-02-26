package com.company.st.service;

import com.company.st.entity.Waybill;
import com.company.st.entity.WaybillItem;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service(WaybillService.NAME)
public class WaybillServiceBean implements WaybillService {

    @Override
     public BigDecimal charge(WaybillItem waybillItem)
     {
         if(waybillItem!=null&&waybillItem.getDim()!=null&&waybillItem.getDim().getHeight()!=null
                 &&waybillItem.getDim().getLength()!=null&&waybillItem.getDim().getWidth()!=null
                 &&waybillItem.getWeight()!=null)

         {
             Double height = waybillItem.getDim().getHeight();
             Double length = waybillItem.getDim().getLength();
             Double width = waybillItem.getDim().getWidth();
             Double weight = waybillItem.getWeight();
             double volume = height*length*width;
             Double a = 100+50*volume+10*weight;
             BigDecimal b = new BigDecimal(100+50*volume+10*weight);
             return BigDecimal.valueOf(100+50*volume+10*weight);
         }
        return BigDecimal.valueOf(0);
     }
     @Override
    public BigDecimal totalCharge(Waybill waybill)
    {
        BigDecimal total = new BigDecimal(0);
        if(waybill!=null&&waybill.getItems()!=null)
        {
            for(WaybillItem w: waybill.getItems())
            {
                total = total.add(w.getCharge());
            }
        }
        return total;
    }

    @Override
    public Double totalWeight(Waybill waybill)
    {
        Double total = 0.0;
        if(waybill!=null&&waybill.getItems()!=null)
        {
            for(WaybillItem w: waybill.getItems())
            {
                total+=w.getWeight();
            }
        }
        return total;
    }
}