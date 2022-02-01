package b_Money;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoneyTest {
    Currency SEK, DKK, NOK, EUR;
    Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;

    @Before
    public void setUp() throws Exception {
        SEK = new Currency("SEK", 0.15);
        DKK = new Currency("DKK", 0.20);
        EUR = new Currency("EUR", 1.5);
        SEK100 = new Money(10000, SEK);
        EUR10 = new Money(1000, EUR);
        SEK200 = new Money(20000, SEK);
        EUR20 = new Money(2000, EUR);
        SEK0 = new Money(0, SEK);
        EUR0 = new Money(0, EUR);
        SEKn100 = new Money(-10000, SEK);
    }

    // test case is written
    // it should be equal to 10000
    @Test
    public void testGetAmount() {
        assertEquals(10000, SEK100.getAmount().intValue());
    }

    // test case is written
    // it should be equal to SEK
    @Test
    public void testGetCurrency() {
        assertEquals("SEK", SEK100.getCurrency().getName());
    }

    // test case is written
    // it should be equal to expected value
    @Test
    public void testToString() {
        Assert.assertEquals("(10000) (SEK)", this.SEK100.toString());
        Assert.assertEquals("(1000) (EUR)", this.EUR10.toString());
        Assert.assertEquals("(20000) (SEK)", this.SEK200.toString());
    }

    // test case is written
    // it should be equal to 1500
    @Test
    public void testGlobalValue() {
        assertEquals(1500, SEK100.universalValue().intValue());
    }

    // test case is written
    // it should be equal
    @Test
    public void testEqualsMoney() {
        assertTrue(SEK100.equals(SEK100));
    }

    // test case is written
    // it should be equal to 10000
    @Test
    public void testAdd() {
        assertEquals(10000, SEK0.add(SEK100).getAmount().intValue());
    }

    // test case is written
    // it should be equal to -10000
    @Test
    public void testSub() {
        assertEquals(-10000, SEK0.sub(SEK100).getAmount().intValue());
    }

    // test case is written
    // it should be 0 and true
    @Test
    public void testIsZero() {
        assertTrue(SEK0.isZero());
    }

    // test case is written
    // it should be equal to -10000
    @Test
    public void testNegate() {
        assertEquals(-10000, SEK100.negate().getAmount().intValue());
    }

    // test case is written
    //SEKn100 < EUR0
    //EUR20 > SEK100
    @Test
    public void testCompareTo() {
        assertTrue(SEKn100.compareTo(EUR0) < 0);
        assertTrue(EUR20.compareTo(SEK100) > 0);
    }
}