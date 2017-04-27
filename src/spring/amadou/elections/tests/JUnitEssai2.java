package spring.amadou.elections.tests;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JUnitEssai2 {

	@Before
	public void avant() throws Exception {
		System.out.println("tearUp");
	}

	@After
	public void après() throws Exception {
		System.out.println("tearDown");
	}

	@Test
	public void t1() {
		System.out.println("test1");
		Assert.assertEquals(1, 1);
	}

	@Test
	public void t2() {
		System.out.println("test2");
		Assert.assertEquals(1, 2);
	}

}
