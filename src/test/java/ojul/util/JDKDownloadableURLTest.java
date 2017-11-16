/**
 * OJUL(Oracle Java Updater for Linux) is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    OJUL(Oracle Java Updater for Linux) is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with OJUL(Oracle Java Updater for Linux).  If not, see <http://www.gnu.org/licenses/>.
 */
package ojul.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ojul.util.models.JavaDetails;

public class JDKDownloadableURLTest {

	private JDKDownloadableURL jdkDownloadableURL;

	@Before
	public void setUp() throws Exception {

		this.jdkDownloadableURL = new JDKDownloadableURL();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetDownloadURL() {

		/**
		 * While performing this test, oracle-jdk version is 9.0.1 and download url is
		 * 'http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html'
		 */
		String expected = "http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html";
		Assert.assertEquals(expected, this.jdkDownloadableURL.getDownloadURL());
	}

	@Test
	public void testGetCurrentJDKVersion() {

		/**
		 * While performing this test, oracle-jdk version is 9.0.1 and download url is
		 * 'http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html'
		 */
		String expected = "9.0.1";
		Assert.assertEquals(expected, this.jdkDownloadableURL.getCurrentJDKVersion());
	}

	@Test
	public void testJDKDetails() {

		/**
		 * While performing this test, oracle-jdk version is 9.0.1 and download url is
		 * 'http://www.oracle.com/technetwork/java/javase/downloads/jdk9-downloads-3848520.html'
		 * and all other information are same like below
		 */
		JavaDetails jdkDetails1 = new JavaDetails();
		jdkDetails1.setFileName("jdk-9.0.1_linux-x64_bin.rpm");
		jdkDetails1.setFilepath("http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_linux-x64_bin.rpm");
		jdkDetails1.setSHA256("e93ddb7de5f03b1713ca4161517a24baf8c61e11bd735f52967ca45f996d9b8e");
		jdkDetails1.setSize("304.99 MB");
		jdkDetails1.setTitle("Linux");
		jdkDetails1.setVersion("9.0.1");

		Assert.assertTrue(this.jdkDownloadableURL.getJdkDetails().get(0).equals(jdkDetails1));

		JavaDetails jdkDetails2 = new JavaDetails();
		jdkDetails2.setFileName("jdk-9.0.1_linux-x64_bin.tar.gz");
		jdkDetails2.setFilepath("http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_linux-x64_bin.tar.gz");
		jdkDetails2.setSHA256("2cdaf0ff92d0829b510edd883a4ac8322c02f2fc1beae95d048b6716076bc014");
		jdkDetails2.setSize("338.11 MB");
		jdkDetails2.setTitle("Linux");
		jdkDetails2.setVersion("9.0.1");

		Assert.assertTrue(this.jdkDownloadableURL.getJdkDetails().get(1).equals(jdkDetails2));
	}
}
