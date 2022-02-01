package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CurrencyTest {
    Currency SEK, DKK, NOK, EUR;

    @Before
    public void setUp() throws Exception {
        /* Setup currencies with exchange rates */
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
    }

    // test case is written
    // it should be equal to SEK
    @Test
    public void testGetName() {
        assertEquals("SEK", SEK.getName());
    }

    // test case is written
    // it should be equal to 0.15
    @Test
    public void testGetRate() {
        assertEquals(0.15, SEK.getRate(), 0);
    }

    // test case is written
    // it should be equal to 0.5
    @Test
    public void testSetRate() {
        SEK.setRate(0.5);
        assertEquals(0.5, SEK.getRate(), 0);
    }

    // test case is written
    // 15 is expected in SEK
    @Test
    public void testGlobalValue() {
        assertEquals(15, SEK.universalValue(100).intValue());
    }

    // test case is written
    // value in currency SEK - DKK
    @Test
    public void testValueInThisCurrency() {
        assertEquals(1000, SEK.valueInThisCurrency(750, DKK).intValue());
    }
}