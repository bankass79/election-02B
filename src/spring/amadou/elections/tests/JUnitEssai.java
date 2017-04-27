package spring.amadou.elections.tests;

import junit.framework.TestCase;

public class JUnitEssai extends TestCase {
	protected void setUp() throws Exception {
		super.setUp();
		System.out.println("tearUp");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		System.out.println("tearDown");
	}

	public void test1() {
		System.out.println("test1");
		assertEquals(1, 1);
	}

	public void test2() {
		System.out.println("test2");
		assertEquals(2, 3);
	}
}