package pl.lodz.p.zzpwj.controller.unittest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;

import pl.lodz.p.zzpwj.facade.interfaces.implementations.MovieFacade;
import pl.lodz.p.zzpwj.userinterface.controller.NewMoviePanelController;

public class NewMoviePanelControllerTest {


    @Mock
    private MovieFacade mockedMovieFacade;

    @InjectMocks
    private NewMoviePanelController sut = new NewMoviePanelController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldPassTestCheckDuration() {
        boolean result = sut.checkDuration("20");
        assertThat(result).isTrue();
    }

    @Test
    public void shouldPassTestCheckTitle() throws Exception {
        boolean result = sut.checkTitle("Film1");
        assertThat(result).isTrue();
    }

    @Test
    public void shouldPassTestCheckCategory() {
        boolean result = sut.checkCategory("Komedia");
        assertThat(result).isTrue();
    }

    @Test
    public void shouldFailTestCheckDurationNotInRange() {
        boolean result = sut.checkDuration("-56");
        assertThat(result).isFalse();
    }

    @Test
    public void shouldFailTestCheckDurationWrongFormat() {
        boolean result = sut.checkDuration("12.98");
        assertThat(result).isFalse();
    }

    @Test
    public void shouldFailTestCheckTitleEmptyTitle(){
        boolean result = sut.checkTitle("");
        assertThat(result).isFalse();
    }

    @Test
    public void shouldFailCheckCategoryNotChosenCategory() {
        boolean result = sut.checkCategory("");
        assertThat(result).isFalse();
    }

}
