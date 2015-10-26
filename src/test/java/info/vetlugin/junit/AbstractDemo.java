package info.vetlugin.junit;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class AbstractDemo {
	@Test
	public void testFail() {
		Assert.fail("You shall not pass!");
	}

	@Test
	public void testEquals() {
		Assert.assertEquals("No way!", true, false);
	}

	@Test
	public void testAssertThat() {
		Assert.assertThat("Seriously?", true, CoreMatchers.equalTo(false));
	}
}
