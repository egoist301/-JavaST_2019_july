package by.training.composite.bean;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * Symbol test.
 */
public class SymbolTest {
    /**
     * Testing symbol compose.
     *
     * @param componentNew actual component.
     * @param sNew         result string.
     */
    @Test(groups = {"Component group"}, dataProvider = "Compose")
    public void testCompose(final Component componentNew, final String sNew) {
        assertEquals(componentNew.compose(), sNew);
    }

    /**
     * Provider.
     *
     * @return actual component and result string.
     */
    @DataProvider(name = "Compose")
    public Object[][] testComposeProvider() {
        Component component = new Symbol('a');
        Component component1 = new Symbol('2');
        Component component2 = new Symbol('.');
        Component component3 = new Symbol('w');
        Component component4 = new Symbol('\u0000');
        return new Object[][]{
                {component, "a"},
                {component1, "2"},
                {component2, "."},
                {component3, "w"},
                {component4, "\u0000"}
        };
    }
}
