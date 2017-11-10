package ojul.util;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ojul.util.models.JDKDetails;

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
		JDKDetails jdkDetails1 = new JDKDetails();
		jdkDetails1.setFileName("jdk-9.0.1_linux-x64_bin.rpm");
		jdkDetails1.setFilepath("http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_linux-x64_bin.rpm");
		jdkDetails1.setSHA256("e93ddb7de5f03b1713ca4161517a24baf8c61e11bd735f52967ca45f996d9b8e");
		jdkDetails1.setSize("304.99 MB");
		jdkDetails1.setTitle("Linux");
		jdkDetails1.setVersion("9.0.1");

		Assert.assertTrue(this.jdkDownloadableURL.getJdkDetails().get(0).equals(jdkDetails1));

		JDKDetails jdkDetails2 = new JDKDetails();
		jdkDetails2.setFileName("jdk-9.0.1_linux-x64_bin.tar.gz");
		jdkDetails2.setFilepath("http://download.oracle.com/otn-pub/java/jdk/9.0.1+11/jdk-9.0.1_linux-x64_bin.tar.gz");
		jdkDetails2.setSHA256("2cdaf0ff92d0829b510edd883a4ac8322c02f2fc1beae95d048b6716076bc014");
		jdkDetails2.setSize("338.11 MB");
		jdkDetails2.setTitle("Linux");
		jdkDetails2.setVersion("9.0.1");

		Assert.assertTrue(this.jdkDownloadableURL.getJdkDetails().get(1).equals(jdkDetails2));
	}
}
