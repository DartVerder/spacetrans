package com.company.st.web.screens.waybill;

import com.company.st.entity.*;
import com.company.st.service.WaybillService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.CollectionPropertyContainer;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.App;

import javax.inject.Inject;
import java.math.BigDecimal;
import java.util.*;

@UiController("st_Waybill.edit")
@UiDescriptor("waybill-edit.xml")
@EditedEntityContainer("waybillDc")
@LoadDataBeforeShow
public class WaybillEdit extends StandardEditor<Waybill> {

    @Inject
    private RadioButtonGroup shipperRadio;
    @Inject
    private CollectionContainer<Company> companiesDc;
    @Inject
    private CollectionContainer<Individual> individualsDc;
    @Inject
    private LookupField<Customer> shipperField;
    @Inject
    private CollectionContainer<Customer> customersDc;
    @Inject
    private RadioButtonGroup consigneeRadio;
    @Inject
    private CollectionContainer<Spaceport> spaceportsDc;
    @Inject
    private LookupField planetMoonDep;
    @Inject
    private LookupField planetMoonDes;
    @Inject
    private PickerField destinationMoon;
    @Inject
    private PickerField destinationPlanet;
    @Inject
    private PickerField departureMoon;
    @Inject
    private PickerField departurePlanet;
    @Inject
    private PickerField<Spaceport> destinationPortField;
    @Inject
    private PickerField<Spaceport> departurePortField;
    @Inject
    private CollectionContainer<Carrier> carriersDc;
    @Inject
    private TextField<BigDecimal> totalChargeField;
    @Inject
    private TextField<Double> totalWeightField;
    @Inject
    private CollectionPropertyContainer<WaybillItem> itemsDc;
    @Inject
    private InstanceContainer<Waybill> waybillDc;
    @Inject
    private PickerField<Customer> consigneeField;
    @Inject
    private PickerField consigneeField1;
    @Inject
    private PickerField consigneeField0;
    @Inject
    private Notifications notifications;


    @Subscribe("shipperRadio")
    public void onShipperRadioValueChange(HasValue.ValueChangeEvent event) {
        List<Customer> customers = customersDc.getItems();
        List<Customer> res = new ArrayList<>();

        shipperField.setVisible(true);
        if((int)shipperRadio.getValue()==0)
        {
            List<Company> companies = companiesDc.getItems();
            for(Company c: companies)
            {
                for(Customer e:customers)
                {

                    if(c.getId().equals(e.getId()))
                    {
                        res.add(e); }
                }
            }

        }
        else if((int)shipperRadio.getValue()==1)
        {
            List<Individual> individual = individualsDc.getItems();
            for(Individual i: individual)
            {
                for(Customer e:customers)
                {
                    if(i.getId().equals(e.getId()))
                    {res.add(e); }
                }
            }

        }
        shipperField.setOptionsList(res);
    }

    @Subscribe("consigneeRadio")
    public void onConsigneeRadioValueChange(HasValue.ValueChangeEvent event) {
        if((int)consigneeRadio.getValue()==0)
        {
            consigneeField0.setVisible(true);
            consigneeField1.setVisible(false);
            setConsignee(consigneeField0,consigneeField,shipperField);
        }
        else if ((int)consigneeRadio.getValue()==1)
        {
            consigneeField1.setVisible(true);
            consigneeField0.setVisible(false);
            setConsignee(consigneeField1,consigneeField,shipperField);
        }
    }

    @Subscribe
    protected void onInit(InitEvent event) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Company", 0);
        map.put("Individual", 1);
        shipperRadio.setOptionsMap(map);
        consigneeRadio.setOptionsMap(map);

        Map<String, Integer> planetmoon = new LinkedHashMap<>();
        planetmoon.put("Planet",0);
        planetmoon.put("Moon",1);
        planetMoonDep.setOptionsMap(planetmoon);
        planetMoonDes.setOptionsMap(planetmoon);

    }

    private void carrierY (Spaceport one, Spaceport two)
    {
        one.getId();
        List<Carrier> carriers=new ArrayList<>();
        //..res

    }

    private void carriersList (Spaceport one, Spaceport two, List<Carrier> res)
    {
        if (carriersDc != null) {
            res=new ArrayList<>();
            List<Carrier> carriers= carriersDc.getItems();
            for(Carrier c:carriers) {
                if (c.getPorts().contains(one)) {
                    if (c.getPorts().contains(two)) {
                        res.add(c);
                    }
                }
            }
        }

    }



    private void visible (PickerField var0,PickerField var1,LookupField lookup)
    {
        if((int)lookup.getValue()==0)
        {
            var0.setVisible(true);
            var1.setVisible(false);
        }
        if((int)lookup.getValue()==1)
        {
            var1.setVisible(true);
            var0.setVisible(false);
        }
    }

    private void defaultPort(AstronimicBody planet, PickerField field)
    {
        if(field.getValue()==null) {
            List<Spaceport> spaceports = spaceportsDc.getItems();
            field.setVisible(true);
            field.setValue(null);
            for (Spaceport spaceport : spaceports) {
                if (spaceport.getPlanet() != null) {
                    if (spaceport.getPlanet().getName().equals(planet.getName())) {
                        if (spaceport.getIsDefault() != null && spaceport.getIsDefault()) {
                            field.setValue(spaceport);
                            break;
                        }
                    }
                } else if (spaceport.getMoon() != null) {
                    if (spaceport.getMoon().getName().equals(planet.getName())) {
                        if (spaceport.getIsDefault() != null && spaceport.getIsDefault()) {
                            field.setValue(spaceport);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Subscribe("planetMoonDes")
    public void onPlanetMoonDesValueChange(HasValue.ValueChangeEvent event) {
        visible(destinationPlanet,destinationMoon,planetMoonDes);
    }

    @Subscribe("planetMoonDep")
    public void onPlanetMoonDepValueChange(HasValue.ValueChangeEvent event) {
        visible(departurePlanet,departureMoon,planetMoonDep);
    }

    @Subscribe("destinationPlanet")
    public void onDestinationPlanetValueChange(HasValue.ValueChangeEvent<Planet> event) {
        defaultPort((Planet)destinationPlanet.getValue(),destinationPortField);
    }

    @Subscribe("destinationMoon")
    public void onDestinationMoonValueChange(HasValue.ValueChangeEvent<Moon> event) {
        defaultPort((Moon)destinationMoon.getValue(),destinationPortField);
    }

    @Subscribe("departureMoon")
    public void onDepartureMoonValueChange(HasValue.ValueChangeEvent<Moon> event) {
        defaultPort((Moon)departureMoon.getValue(),departurePortField);
    }

    @Subscribe("departurePlanet")
    public void onDeparturePlanetValueChange(HasValue.ValueChangeEvent<Planet> event) {
        defaultPort((Planet) departurePlanet.getValue(), departurePortField);
    }

    @Subscribe("departurePortField")
    public void onDeparturePortFieldValueChange(HasValue.ValueChangeEvent<Spaceport> event) {

        if(departurePortField.getValue()!=null) {
            if (departurePortField.getValue().getPlanet() != null) {
                planetMoonDep.setValue(0);
                departurePlanet.setValue(departurePortField.getValue().getPlanet());
            } else if (departurePortField.getValue().getMoon() != null) {
                planetMoonDep.setValue(1);
                departureMoon.setValue(departurePortField.getValue().getMoon());
            }
        }

    }

    @Subscribe("destinationPortField")
    public void onDestinationPortFieldValueChange(HasValue.ValueChangeEvent<Spaceport> event) {
        if(destinationPortField.getValue()!=null) {
            if (destinationPortField.getValue().getPlanet() != null) {
                planetMoonDes.setValue(0);
                destinationPlanet.setValue(destinationPortField.getValue().getPlanet());
            } else if (destinationPortField.getValue().getMoon() != null) {
                planetMoonDes.setValue(1);
                destinationMoon.setValue(destinationPortField.getValue().getMoon());
            }
        }

    }
    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    public void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<WaybillItem> event) {
        totalChargeField.setValue(AppBeans.get(WaybillService.class).totalCharge(waybillDc.getItem()));
        totalWeightField.setValue(AppBeans.get(WaybillService.class).totalWeight(waybillDc.getItem()));
    }

    private void setConsignee(PickerField tmp, PickerField main, LookupField shipper)
    {
        if(shipper!=null&&tmp!=null&&shipper.getValue()!=null&&tmp.getValue()!=null&&shipper.getValue().equals(tmp.getValue()))
        {
            tmp.setValue(null);
            throw new RuntimeException("The shipper is equal to the consignee! Please choose different values for sender and consignee");
        }
        else {
            main.setValue(tmp.getValue());
        }
    }


    @Subscribe("consigneeField0")
    public void onConsigneeField0ValueChange(HasValue.ValueChangeEvent event) {
        setConsignee(consigneeField0,consigneeField,shipperField);
    }

    @Subscribe("consigneeField1")
    public void onConsigneeField1ValueChange(HasValue.ValueChangeEvent event) {
        setConsignee(consigneeField1,consigneeField,shipperField);
    }

    @Subscribe("shipperField")
    public void onShipperFieldValueChange(HasValue.ValueChangeEvent<Customer> event) {
        if(consigneeField.getValue()!=null&&consigneeField.getValue().equals(shipperField.getValue()))
        {
            shipperField.setValue(null);
            throw new RuntimeException("The shipper is equal to the consignee! Please choose different values for sender and consignee");
        }


    }










}