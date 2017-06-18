package pl.lodz.p.zzpwj.basic.unittest;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import pl.lodz.p.zzpwj.userinterface.controller.MainPanelController;


@RunWith(value = Parameterized.class)
public class MainPanelControllerPriceTest {

    private BigDecimal price;
    private boolean isProper;
    private String message;
    private MainPanelController mainPanelController;


    public MainPanelControllerPriceTest(double priceValue, boolean isProper, String message) {
        this.price = new BigDecimal(priceValue);
        this.isProper = isProper;
        this.message = message;
    }

    @Parameters(name="{2}: isValid({0})={1}")
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {1, true, "Wartość ceny miesząca się w zakresie 1-99"},
                {50, true, "Wartość ceny miesząca się w zakresie 1-99"},
                {99, true, "Wartość ceny miesząca się w zakresie 1-99"},
                {-12, false, "Wartość ceny mniejsza niż dopuszczalne minimum"},
                {100, false, "Wartość ceny większa niż dopuszczalne maksimum"},
                {87.32, true, "Poprawny format ceny"}
        });
    }

    @Test
    public void test_setNewPrice() {
        mainPanelController = new MainPanelController();
        assertEquals(isProper, mainPanelController.checkNewPrice(price));
    }
}
