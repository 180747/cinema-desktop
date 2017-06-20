package pl.lodz.p.zzpwj.controller.unittest;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;


import pl.lodz.p.zzpwj.entity.Category;
import pl.lodz.p.zzpwj.facade.interfaces.implementations.CategoryFacade;
import pl.lodz.p.zzpwj.userinterface.controller.NewCategoryPanelController;
import static org.mockito.Mockito.when;

public class NewCategoryPanelControllerTest {


    private Category helpCategory;
    private Category testCategory;
    private List<Category> categoryList;



    @Mock
    private CategoryFacade mockedCategoryFacade;

    @InjectMocks
    public NewCategoryPanelController sut = new NewCategoryPanelController();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        // general mocks initialization
        helpCategory = new Category();
        helpCategory.setName("Komedia");

        testCategory = new Category();
        categoryList = new ArrayList<Category>();
        categoryList.add(helpCategory);

        sut.setExistingCategoryList(categoryList);
    }

    @Test
    public void shouldFailCategoryNameTestCategoryNameEmpty() {
        helpCategory.setName("");
        when(mockedCategoryFacade.findAll()).thenReturn(categoryList);
        boolean result = sut.validateCategory(helpCategory.getName());
        assertThat(result).isFalse();
    }

    @Test
    public void shouldPassCategoryNameTest() {
        testCategory.setName("Sencacja");
        boolean result = sut.validateCategory(testCategory.getName());
        assertThat(result).isTrue();
    }

    @Test
    public void shouldFailCategoryTestNameCategoryNameRepeated() {
        when(mockedCategoryFacade.findAll()).thenReturn(categoryList);
        boolean result = sut.validateCategory("Komedia");
        assertThat(result).isFalse();
    }
}
