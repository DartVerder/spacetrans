package com.company.st.core;

import com.company.st.StTestContainer;
import com.company.st.entity.*;
import com.company.st.service.WaybillServiceBean;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.Persistence;
import com.haulmont.cuba.core.Transaction;
import com.haulmont.cuba.core.TypedQuery;
import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.CommitContext;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.security.entity.User;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.RegisterExtension;
import com.company.st.service.WaybillService;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SampleIntegrationTest {

    @Inject
    WaybillServiceBean waybillServiceBean;

    @RegisterExtension
    public static StTestContainer cont = StTestContainer.Common.INSTANCE;

    private static Metadata metadata;
    private static Persistence persistence;
    private static DataManager dataManager;

    @BeforeAll
    public static void beforeAll() throws Exception {
        metadata = cont.metadata();
        persistence = cont.persistence();
        dataManager = AppBeans.get(DataManager.class);
    }

    @AfterAll
    public static void afterAll() throws Exception {

    }

    @Test
    public void testLoadUser() {
        try (Transaction tx = persistence.createTransaction()) {
            EntityManager em = persistence.getEntityManager();
            TypedQuery<User> query = em.createQuery(
                    "select u from sec$User u where u.login = :userLogin", User.class);
            query.setParameter("userLogin", "admin");
            List<User> users = query.getResultList();
            tx.commit();
            Assertions.assertEquals(1, users.size());
        }
    }

    @Test
    public void testWaybillCreate()
    {
        CommitContext commitContext=new CommitContext();
        List<Customer> customers= dataManager.load(Customer.class).list();
        List<Spaceport> spaceports = dataManager.load(Spaceport.class).list();
        List<User> users = dataManager.load(User.class).list();
        List<Carrier> carriers = dataManager.load(Carrier.class).list();
        Waybill waybill = dataManager.create(Waybill.class);
        waybill.setCreator(users.get(0));
        waybill.setConsignee(customers.get(0));
        waybill.setShipper(customers.get(1));
        waybill.setDeparturePort(spaceports.get(0));
        waybill.setDestinationPort(spaceports.get(1));
        waybill.setReference("Waybill Create test");
        waybill.setCarrier(carriers.get(0));

        List<WaybillItem> waybillItems=new ArrayList<>();
        for(int i = 1; i<=5;i++)
        {
            WaybillItem waybillItem = dataManager.create(WaybillItem.class);
            waybillItem.setWaybill(waybill);
            fieldItem(i,waybillItem);

            waybillItems.add(waybillItem);
            commitContext.addInstanceToCommit(waybillItem);
        }
        waybill.setItems(waybillItems);
        commitContext.addInstanceToCommit(waybill);
        dataManager.commit(commitContext);
        Assertions.assertEquals(15,waybill.getTotalWeight());

        double discount = 0.8;
        Assertions.assertEquals(new BigDecimal(11900*discount).stripTrailingZeros(),waybill.getTotalCharge().stripTrailingZeros());

        Assertions.assertEquals(new BigDecimal(160.0).stripTrailingZeros(),waybill.getItems().get(0).getCharge().stripTrailingZeros());
        Assertions.assertEquals(new BigDecimal(520).stripTrailingZeros(),waybill.getItems().get(1).getCharge().stripTrailingZeros());
        Assertions.assertEquals(new BigDecimal(1480).stripTrailingZeros(),waybill.getItems().get(2).getCharge().stripTrailingZeros());
        Assertions.assertEquals(new BigDecimal(3340).stripTrailingZeros(),waybill.getItems().get(3).getCharge().stripTrailingZeros());
        Assertions.assertEquals(new BigDecimal(6400).stripTrailingZeros(),waybill.getItems().get(4).getCharge().stripTrailingZeros());
        dataManager.remove(waybill);
    }

    @Test
    public void testWaybillUpdateAddItem()
    {
        CommitContext commitContext=new CommitContext();
        CommitContext c = new CommitContext();
        Waybill waybill= dataManager.load(Waybill.class).one();
        waybill = dataManager.reload(waybill,"waybill-view");

        BigDecimal totalCharge=waybill.getTotalCharge();
        Double totalWeight= waybill.getTotalWeight();
        List<WaybillItem> old = new ArrayList<>(waybill.getItems());
        List<WaybillItem> items = waybill.getItems();
        for(int i = 1; i<=5;i++)
        {
            WaybillItem waybillItem = dataManager.create(WaybillItem.class);
            waybillItem.setWaybill(waybill);
            fieldItem(i,waybillItem);
            items.add(waybillItem);
            commitContext.addInstanceToCommit(waybillItem);
            c.addInstanceToCommit(waybillItem);
        }
        waybill.setItems(items);
        Collection<Entity> en =commitContext.getCommitInstances();
        commitContext.addInstanceToCommit(waybill);
        dataManager.commit(commitContext);
        waybill = dataManager.reload(waybill,"waybill-view");
        double discount = 0.8;
        Assertions.assertEquals(totalWeight+15,waybill.getTotalWeight());
        Assertions.assertEquals(new BigDecimal(11900*discount).add(totalCharge).stripTrailingZeros(),waybill.getTotalCharge().stripTrailingZeros());
        for(Entity e:c.getCommitInstances())
        {
            dataManager.remove(e);
        }
    }

    @Test
    public void testWaybillUpdate()
    {
        CommitContext commitContext=new CommitContext();
        Waybill waybill= dataManager.load(Waybill.class).one();
        waybill = dataManager.reload(waybill,"waybill-view");
        BigDecimal totalCharge=waybill.getTotalCharge();
        Double totalWeight= waybill.getTotalWeight();
        WaybillItem waybillItem = dataManager.create(WaybillItem.class);
        fieldItem(1,waybillItem);
        waybillItem.setWaybill(waybill);
        List<WaybillItem> items= waybill.getItems();
        items.add(waybillItem);
        commitContext.addInstanceToCommit(waybillItem);
        waybill.setItems(items);
        commitContext.addInstanceToCommit(waybill);
        dataManager.commit(commitContext);
        double discount = 0.7;
        Assertions.assertEquals(totalWeight+1,waybill.getTotalWeight());
        Assertions.assertEquals(new BigDecimal(160*discount).add(totalCharge).stripTrailingZeros(),waybill.getTotalCharge().stripTrailingZeros());
        dataManager.remove(waybillItem);

    }

    public void fieldItem(int i, WaybillItem waybillItem)
    {
        waybillItem.setNumber(i);
        waybillItem.setName("Test Item "+i);
        waybillItem.setWeight((double) i);
        Dimensions dimensions = dataManager.create(Dimensions.class);
        dimensions.setHeight((double) i);
        dimensions.setLength((double) i);
        dimensions.setWidth((double) i);
        waybillItem.setDim(dimensions);
    }
}