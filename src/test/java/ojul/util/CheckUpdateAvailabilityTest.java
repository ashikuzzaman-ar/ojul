package ojul.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckUpdateAvailabilityTest {

	private CheckUpdateAvailability checkUpdateAvailability;
	private JDKDownloadableURL jdkDownloadableURL = new JDKDownloadableURL();

	@Before
	public void setUp() throws Exception {

		this.checkUpdateAvailability = new CheckUpdateAvailability(this.jdkDownloadableURL);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void getLocalJavaVersion_1() {

		Assert.assertEquals("9.0.1", this.checkUpdateAvailability.getLocalJavaVersion());
	}

	@Test
	public void isUpdateAvailable_1() {

		Assert.assertEquals(this.checkUpdateAvailability.getLocalJavaVersion(),
				this.jdkDownloadableURL.getCurrentJDKVersion());
	}

	@Test
	public void isUpdateAvailable_2() {

		Assert.assertEquals(0, this.checkUpdateAvailability.isUpdateAvailable());
	}
}
