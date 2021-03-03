package com.company.st.web.screens.waybill;

import com.company.st.entity.*;
import com.company.st.service.WaybillService;
import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.ScreenBuilders;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.*;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.web.App;
import com.sun.el.stream.Stream;

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
    private InstanceContainer<Waybill> waybillDc;
    @Inject
    private PickerField<Customer> consigneeField;
    @Inject
    private PickerField consigneeField1;
    @Inject
    private PickerField consigneeField0;
    @Inject
    private LookupField<Carrier> carrierField;
    @Inject
    private CollectionPropertyContainer<WaybillItem> itemsDc;
    @Inject
    private Button Up;
    @Inject
    private Button Down;
    @Inject
    private Table<WaybillItem> itemsTable;
    @Inject
    private UserSessionSource userSessionSource;

    @Subscribe
    public void onInit(InitEvent event) {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Company", 0);
        map.put("Individual", 1);
        shipperRadio.setOptionsMap(map);
        shipperRadio.setValue(0);
        consigneeRadio.setOptionsMap(map);
        consigneeRadio.setValue(0);

        Map<String, Integer> planetmoon = new LinkedHashMap<>();
        planetmoon.put("Planet",0);
        planetmoon.put("Moon",1);
        planetMoonDep.setOptionsMap(planetmoon);
        planetMoonDes.setOptionsMap(planetmoon);

    }

    @Subscribe
    public void onInitEntity(InitEntityEvent<Waybill> event) {
        Waybill waybill = event.getEntity();
        waybill.setCreator(userSessionSource.getUserSession().getUser());
    }



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

    private void carriersList (Spaceport one, Spaceport two)
    {
        if (carriersDc != null) {
            List<Carrier> res=new ArrayList<>();
            List<Carrier> carriers= carriersDc.getItems();
            for(Carrier c:carriers) {
                if (c.getPorts().contains(one)) {
                    if (c.getPorts().contains(two)) {
                        res.add(c);
                    }
                }
            }
            carrierField.setOptionsList(res);
            carrierField.setEditable(true);
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
            if(departurePortField.getValue()!=null)
            {
                if(departurePortField.getValue().equals(destinationPortField.getValue()))
                {
                    destinationPortField.setValue(null);
                    throw new RuntimeException("The destination port is equal to the departure port! Please choose different values for departure and destination");
                }
                else
                {
                    carriersList(destinationPortField.getValue(),departurePortField.getValue());
                }
            }
        }

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
            if(destinationPortField.getValue()!=null)
            {
                if(departurePortField.getValue().equals(destinationPortField.getValue()))
                {
                    departurePortField.setValue(null);
                    throw new RuntimeException("The destination port is equal to the department port! Please choose different values for department and destination");
                }
                else
                {
                    carriersList(destinationPortField.getValue(),departurePortField.getValue());
                }

            }
        }

    }


    @Subscribe(id = "itemsDc", target = Target.DATA_CONTAINER)
    public void onItemsDcCollectionChange(CollectionContainer.CollectionChangeEvent<WaybillItem> event) {
        if(itemsDc.getItems().size()!=0) {
            totalChargeField.setValue(AppBeans.get(WaybillService.class).totalCharge(waybillDc.getItem()));
            totalWeightField.setValue(AppBeans.get(WaybillService.class).totalWeight(waybillDc.getItem()));
            if (itemsDc.getItems().size() > 1) {
                Up.setEnabled(true);
                Down.setEnabled(true);
            } else {
                Up.setEnabled(false);
                Down.setEnabled(false);
            }
            List<WaybillItem> items = itemsDc.getMutableItems();
            for (WaybillItem item : items) {
                item.setNumber(items.indexOf(item)+1);
            }
        }
    }

    private void setConsignee(PickerField tmp, PickerField main, LookupField shipper)
    {
        if(shipper!=null&&tmp!=null&&shipper.getValue()!=null&&tmp.getValue()!=null&&shipper.getValue().equals(tmp.getValue()))
        {
            tmp.setValue(null);
            throw new RuntimeException("The shipper is equal to the consignee! Please choose different values for shipper and consignee");
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
            throw new RuntimeException("The shipper is equal to the consignee! Please choose different values for shipper and consignee");
        }


    }

    @Subscribe("Up")
    public void onUpClick(Button.ClickEvent event) {
        List<WaybillItem> newWaybillItems = new ArrayList<>(itemsDc.getItems());
        WaybillItem selected = itemsTable.getSingleSelected();
        int id = newWaybillItems.indexOf(selected);
        if (id > 0){
            WaybillItem prev = newWaybillItems.get(id-1);
            newWaybillItems.set(id, prev);
            prev.setNumber(id+1);
            newWaybillItems.set(id-1, selected);
            selected.setNumber(id);
        }
        itemsDc.setItems(newWaybillItems);
    }

    @Subscribe("Down")
    public void onDownClick(Button.ClickEvent event) {
        List<WaybillItem> newWaybillItems = new ArrayList<>(itemsDc.getItems());
        WaybillItem selected = itemsTable.getSingleSelected();
        int id = newWaybillItems.indexOf(selected);
        if (id < newWaybillItems.size()-1){
            WaybillItem next = newWaybillItems.get(id+1);
            newWaybillItems.set(id, next);
            next.setNumber(id+1);
            newWaybillItems.set(id+1, selected);
            selected.setNumber(id+2);
        }
        itemsDc.setItems(newWaybillItems);
    }


    @Subscribe
    public void onAfterShow(AfterShowEvent event) {
        if(waybillDc.getItem().getShipper()!=null) {
            shipperField.setValue(waybillDc.getItem().getShipper());
            if(waybillDc.getItem().getShipper().getClass().equals(Company.class))
                shipperRadio.setValue(0);
            else if(waybillDc.getItem().getShipper().getClass().equals(Individual.class))
                shipperRadio.setValue(1);
        }
        if(waybillDc.getItem().getConsignee()!=null)
        {
            if(waybillDc.getItem().getConsignee().getClass().equals(Company.class))
            {
                consigneeField0.setValue(waybillDc.getItem().getConsignee());
                consigneeRadio.setValue(0);
            }
            else if(waybillDc.getItem().getConsignee().getClass().equals(Individual.class)) {
                consigneeField1.setValue(waybillDc.getItem().getConsignee());
                consigneeRadio.setValue(1);
            }
        }
    }








}