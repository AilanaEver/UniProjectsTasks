package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;

	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));
		SweBank.deposit("Alice", new Money(1000000, SEK));
	}

	// test case is written
	//changed using remove and add TimedPayment by id with account Alice
	// You can see some messages
	@Test
	public void testAddRemoveTimedPayment() {
		assertFalse("no payment before", testAccount.timedPaymentExists("1"));
		testAccount.addTimedPayment("1", 10, 10, new Money(10000, SEK), SweBank, "Alice");
		assertTrue("payment exists", testAccount.timedPaymentExists("1"));
		testAccount.removeTimedPayment("1");
		assertFalse("no payment after", testAccount.timedPaymentExists("1"));
	}

	// test case is written
	//changed using add TimedPayment, tick by id with account Alice
	// You can see some messages
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		testAccount.addTimedPayment("1", 2, 1, new Money(10_000_00, SEK), SweBank, "Alice");
		assertEquals("before tick1 Hans", 100_000_00, testAccount.getBalance().getAmount().intValue());
		assertEquals("before tick1 Alice", 10_000_00, SweBank.getBalance("Alice").intValue());
		testAccount.tick();
		assertEquals("after tick1 Hans", 100_000_00, testAccount.getBalance().getAmount().intValue());
		assertEquals("after tick1 Alice", 10_000_00, SweBank.getBalance("Alice").intValue());
	}

	// test case is written
	//changed using withdraw
	//after the transfer it should be zero (expected value)
	@Test
	public void testAddWithdraw() {
		testAccount.withdraw(new Money(10000000, SEK));
		assertEquals(0, testAccount.getBalance().getAmount().intValue());
	}

	// test case is written
	//changed using get balance and amount
	//it should be equal to the value specified in the @Before function
	@Test
	public void testGetBalance() {
		assertEquals(10000000, testAccount.getBalance().getAmount().intValue());
	}
}