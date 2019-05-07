package model;

import model.event.Basket;
import model.event.Collect;
import model.event.Party;

import model.Item;
import model.Product;
import org.junit.Test;

import model.Template;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TemplateTest {

    @Test
    public void canInstantiateABasketFromATemplate() throws InstantiationException, IllegalAccessException {
        Template template = new Template(Basket.class);

        assertTrue(template.instantiate() instanceof Basket);
    }

    @Test
    public void canInstantiateACollectFromATemplate() throws InstantiationException, IllegalAccessException {
        Template template = new Template(Collect.class);

        assertTrue(template.instantiate() instanceof Collect);
    }

    @Test
    public void canInstantiateAPartyFromATemplate() throws InstantiationException, IllegalAccessException {
        Template template = new Template(Party.class);

        assertTrue(template.instantiate() instanceof Party);
    }

    @Test
    public void aTemplateCanPredifineTheProductsNeeded() {
        Template template = new Template(Collect.class);

        Product coke = new Product("Coke",new Double(20));
        Product pepsi = new Product("Pepsi", new Double(10));
        List<Item> productsNeeded = Arrays.asList(new Item(pepsi, 2), new Item(coke, 2));

        template.setProductsNeeded(productsNeeded);

        assertEquals(productsNeeded, template.getProductsNeeded());
    }

    @Test
    public void aTemplateCanInstantiateACollectWithTwoCokesAndTwoPepsisAsProductsNeeded() throws InstantiationException, IllegalAccessException {
        Template template = new Template(Collect.class);

        Product coke = new Product("Coke",new Double(20));
        Product pepsi = new Product("Pepsi", new Double(10));
        List<Item> productsNeeded = Arrays.asList(new Item(pepsi, 2), new Item(coke, 2));

        template.setProductsNeeded(productsNeeded);

        Collect collect = (Collect) template.instantiate();

        assertEquals(productsNeeded, collect.getProductsNeeded());
    }

    @Test
    public void aTemplateCanGiveNOfHisTemplatesRelated() {
        Template template1 = new Template(Collect.class);
        Template template2 = new Template(Collect.class);
        Template template3 = new Template(Collect.class);
        Template template4 = new Template(Collect.class);
        Template template5 = new Template(Collect.class);
        template1.relate(template2);
        template1.relate(template2);
        template1.relate(template3);
        template1.relate(template3);
        template1.relate(template3);
        template1.relate(template3);
        template1.relate(template4);
        template1.relate(template4);
        template1.relate(template4);
        template1.relate(template5);

        List<Template> expected = Arrays.asList(template3,template4,template2);

        assertEquals(expected, template1.getNRelated(3));
    }

}
