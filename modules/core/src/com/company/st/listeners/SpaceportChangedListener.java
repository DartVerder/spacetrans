package com.company.st.listeners;

import com.company.st.entity.Spaceport;
import com.haulmont.cuba.core.EntityManager;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.listener.BeforeInsertEntityListener;
import com.haulmont.cuba.core.listener.BeforeUpdateEntityListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Component("st_SpaceportChangedListener")
public class SpaceportChangedListener implements
        BeforeUpdateEntityListener<Spaceport>,
        BeforeInsertEntityListener<Spaceport>

{
    @Inject
    private DataManager dataManager;

    @Override
    public void onBeforeInsert(Spaceport entity, EntityManager entityManager) {
        if ((entity.getMoon() != null && entity.getPlanet() != null)||(entity.getMoon() == null && entity.getPlanet() == null)) {
            throw new RuntimeException("Please select one: Moon OR Planet");
        }
        defaultPort(entity,entityManager);
    }

    @Override
    public void onBeforeUpdate(Spaceport entity, EntityManager entityManager) {

        if ((entity.getMoon() != null && entity.getPlanet() != null)||(entity.getMoon() == null && entity.getPlanet() == null)) {
            throw new RuntimeException("Please select one: Moon OR Planet");
        }
        defaultPort(entity,entityManager);

    }
    private void defaultPort(Spaceport entity, EntityManager entityManager)
    {
        if (entity.getIsDefault() != null&& entity.getIsDefault()) {
            List<Spaceport> spaceports = (List<Spaceport>) entityManager.createQuery(
                    "select s from st_Spaceport s where s.planet = :planet and s.moon = :moon and s.id <> :id ")
                    .setParameter("planet", entity.getPlanet())
                    .setParameter("id", entity.getId())
                    .setParameter("moon", entity.getMoon())
                    .getResultList();
            //check that isDefault=true is the only one
            if (!spaceports.isEmpty()) {
                for (Spaceport tmp : spaceports) {
                    if (tmp.getIsDefault() != null && !tmp.getName().equals(entity.getName()) && tmp.getIsDefault()) {
                        tmp.setIsDefault(false);
                        entityManager.persist(tmp);
                        break;
                    }
                }
            }
        }
    }
}