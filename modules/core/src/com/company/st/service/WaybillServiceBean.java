package com.company.st.service;

import com.company.st.entity.CustomerGrade;
import com.company.st.entity.Discount;
import com.company.st.entity.Waybill;
import com.company.st.entity.WaybillItem;
import com.haulmont.cuba.core.global.DataManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.math.BigDecimal;

@Service(WaybillService.NAME)
public class WaybillServiceBean implements WaybillService {

    @Inject
    private DataManager dataManager;

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
            if(waybill.getShipper()!=null&&waybill.getShipper().getGrade()!=null)
            {
                CustomerGrade grade  =waybill.getShipper().getGrade();
                BigDecimal discount =dataManager.loadValue("select e.value from st_Discount e where e.grade = :grade", BigDecimal.class)
                        .parameter("grade", grade.getId())
                        .one();
                discount=discount.subtract(BigDecimal.valueOf(100)).negate().multiply(BigDecimal.valueOf(0.01));
                total = total.multiply(discount);
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