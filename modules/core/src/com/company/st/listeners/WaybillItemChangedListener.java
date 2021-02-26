package com.company.st.listeners;

import com.company.st.entity.WaybillItem;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.util.UUID;

@Component("st_WaybillItemChangedListener")
public class WaybillItemChangedListener {

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void beforeCommit(EntityChangedEvent<WaybillItem, UUID> event) {

    }
}