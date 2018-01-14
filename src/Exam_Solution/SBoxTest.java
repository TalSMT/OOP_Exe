package Exam_Solution;

import static org.junit.Assert.*;

import org.junit.Test;

public class SBoxTest {

	@Test
	public void testSBox() {
		String key = "123";
		SBox s = new SBox(key);
		if(s.isOpen()) {
			fail("Sbox should not be open - ERR");
		}
	}
	@Test
	public void testOpen() {
		String key = "123";
		SBox s = new SBox(key);
		boolean open = s.open(key);
		if(!open) {
			fail("Sbox is should be open - ERR");
		}
		s.lock();
		if(s.isOpen()) {
			fail("Sbox should not be open - ERR");
		}
	}

	@Test
	public void testBalance() {
		String key = "123";
		SBox s = new SBox(key);
		boolean open = s.open(key);
		s.deposit(10);
		s.deposit(10);
		s.withdraw(2);
		s.withdraw(3);
		System.out.println(s);
		double b = s.balance();
		if(b!=15) {
			fail("SBox balance should be 15!");
		}
	}
	@Test
	public void testLock() {
		String key = "123";
		SBox s = new SBox(key);
		s.open(key);
		s.deposit(3);
		s.lock();
		boolean open = s.open(key+"1");
		open = s.open(key+"2");
		open = s.open(key+"3");
		open = s.open(key);
		System.out.println(s.toString());
		if(s.isOpen()) {
			fail("Sbox should not be locked for 15 m' - ERR");
		}
	}
}
