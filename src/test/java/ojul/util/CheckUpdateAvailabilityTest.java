/**
 * OJUL(Oracle Java Updater for Linux) is free software: you can redistribute it and/or
 * modify it under the terms of the GNU General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * OJUL(Oracle Java Updater for Linux) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with
 * OJUL(Oracle Java Updater for Linux). If not, see <http://www.gnu.org/licenses/>.
 */
package ojul.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CheckUpdateAvailabilityTest {
	
	private CheckUpdateAvailability checkUpdateAvailability;
	
	private final JDKDownloadableURL jdkDownloadableURL = new JDKDownloadableURL();
	
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
	
	@Before
	public void setUp() throws Exception {
		
		this.checkUpdateAvailability = new CheckUpdateAvailability(
		        this.jdkDownloadableURL);
	}
	
	@After
	public void tearDown() throws Exception {
	
	}
}
