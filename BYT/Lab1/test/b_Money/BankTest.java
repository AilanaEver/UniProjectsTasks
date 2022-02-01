package b_Money;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);
		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	// test case is written
	//it should be equal to DKK
	@Test
	public void testGetName() {
		assertEquals("DKK", DKK.getName());
	}

	// test case is written
	//it should be DKK
	@Test
	public void testGetCurrency() {
		assertEquals(DKK, DanskeBank.getCurrency());
	}

	// test case is written
	// checks if acc has already exist it would not open it again
	// if it is a new acc it will open acc
	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("1");
		boolean exist = false;
		try {
			SweBank.openAccount("1");
		} catch (AccountExistsException e) {
			exist = true;
		}
		assertTrue("account '1' exists in  SweBank", exist);
	}

	// test case is written
	//deposit transaction system
	// You can see some messages
	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		assertEquals("Ulrika before", 0, SweBank.getBalance("Ulrika").intValue());
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		assertEquals("Ulrika after", 10000, SweBank.getBalance("Ulrika").intValue());
	}

	// test case is written
	//withdraw transaction system
	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		assertEquals((Integer) 0, SweBank.getBalance("Ulrika"));
		SweBank.deposit("Ulrika", new Money(10000, DKK));
		SweBank.withdraw("Ulrika", new Money(10000, DKK));
		assertEquals((Integer) 0, SweBank.getBalance("Ulrika"));
	}

	// test case is written
	//get balance of acc Ulrika
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		assertEquals("Ulrika", 0, SweBank.getBalance("Ulrika").intValue());
	}

	// test case is written
	//transfer system between Ulrika(from) and Bob(to)
	@Test
	public void testTransfer() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(10000, SEK));
		SweBank.transfer("Ulrika", Nordea, "Bob", new Money(5000, SEK));
		assertEquals("Ulrika", 5000, SweBank.getBalance("Ulrika").intValue());
		assertEquals("Bob", 5000, Nordea.getBalance("Bob").intValue());
	}

	// test case is written
	//changed using add TimedPayment, tick by id with account Ulrika, Bob
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(20000, SEK));
		SweBank.addTimedPayment("Ulrika", "1884", 2, 1, new Money(5000, SEK), Nordea, "Bob");
		assertEquals(20000, SweBank.getBalance("Ulrika").intValue());
		assertEquals(0, Nordea.getBalance("Bob").intValue());
		SweBank.tick();
		assertEquals(20000, SweBank.getBalance("Ulrika").intValue());
	}
}