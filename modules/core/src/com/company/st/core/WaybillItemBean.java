package com.company.st.core;

import com.company.st.entity.Waybill;
import com.company.st.entity.WaybillItem;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Component(WaybillItemBean.NAME)
public class WaybillItemBean {
    public static final String NAME = "st_WaybillItemBean";
    @Inject
    private Persistence persistence;

    public void a (WaybillItem waybillItem)
    {
        List<Waybill> result;
        // start transaction
        try (Transaction tx = persistence.createTransaction()) {
            // get EntityManager for the current transaction
            EntityManager em = (EntityManager) persistence.getEntityManager();
            // create and execute Query
            Query query = em.createQuery(
                    "select e from st_Waybills e");
                result = (List<Waybill>) query.getResultList();
            // commit transaction
            tx.commit();
        }

    }
}