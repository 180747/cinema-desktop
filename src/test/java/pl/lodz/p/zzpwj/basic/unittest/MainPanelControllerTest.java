package pl.lodz.p.zzpwj.basic.unittest;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;
import pl.lodz.p.zzpwj.userinterface.controller.MainPanelController;

import static org.assertj.core.api.Assertions.assertThat;

public class MainPanelControllerTest {

    private MainPanelController sut;

    @Before
    public void setUp() throws Exception {
        sut = new MainPanelController();
    }

    @Test
    public void shouldPassTestCheckNewPrice() {
        boolean result = sut.checkNewPrice(new BigDecimal(25.08));
        assertThat(result).isTrue();
    }

    @Test
    public void shouldFailTestCheckNewPriceNegativeNumber() {
        boolean result = sut.checkNewPrice(new BigDecimal(-25.08));
        assertThat(result).isFalse();
    }

    @Test(expected = NumberFormatException.class)
    public void shouldFailTestCheckNewPriceWrongFormat() {
        sut.checkNewPrice(new BigDecimal("we"));
    }
}
