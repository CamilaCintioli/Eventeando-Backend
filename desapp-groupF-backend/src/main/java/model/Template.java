package model;

//import model.Item;
//import model.event.Event;


import model.event.Event;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toList;

public class Template {
    
    private Class<?> eventClass;
    private List<Item> productsNeeded = new ArrayList<Item>();
    private Map<Template, Integer> related = new HashMap<Template, Integer>();
    private String description = "";

    public Template(Class<?> eventClass) {
        this.eventClass = eventClass;
    }

    public Event instantiate() throws IllegalAccessException, InstantiationException {
        Event eventReturn = (Event) eventClass.newInstance();
        eventReturn.addProductsNeeded(this.productsNeeded);
        return eventReturn;
    }

    public void setProductsNeeded(List<Item> productsNeeded) {
        this.productsNeeded = productsNeeded;
    }

    public List<Item> getProductsNeeded() {
        return this.productsNeeded;
    }

    public void relate(Template template) {
        this.related.merge(template, 1, Integer::sum);
    }

    //Return N Templates relateds in decreasing order
    public List<Template> getNRelated(Integer cant) {

        List<Template> sorted = related
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(comparingByValue()))
                .limit(cant)
                .map(Map.Entry::getKey)
                .collect(toList());

        return sorted;
    }

    public String getDescription() {
        return this.description;
    }
}
