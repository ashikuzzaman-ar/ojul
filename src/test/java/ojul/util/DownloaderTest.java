package ojul.util;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DownloaderTest {

	private Downloader downloader;
	private static final String OJUL_HOME = System.getProperty("user.home") + "/.ojul/";
	private static final String JAVA_HOME = OJUL_HOME + "oracle-java/";
	private static final String TEMP_HOME = OJUL_HOME + "temp/";
	// private File file;

	@Before
	public void setUp() throws Exception {

		this.downloader = new Downloader(new JDKDownloadableURL());
	}

	@After
	public void tearDown() throws Exception {

		try {

			new File(JAVA_HOME).delete();
			new File(TEMP_HOME).delete();
			new File(OJUL_HOME).delete();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	@Test
	public void testcheckDirectories_1() {

		Assert.assertTrue(new File(OJUL_HOME).exists());
		Assert.assertTrue(new File(JAVA_HOME).exists());
		Assert.assertTrue(new File(TEMP_HOME).exists());
	}

	@Test
	public void testDownloading() {

		this.downloader.startDownload();
		while (!downloader.isDownloadComplete()) {

			try {

				System.out.println("Downloaded : " + this.downloader.getDownloadedSize());
				System.out.println("Time Passed: " + this.downloader.getElapsedTime() / 1000);
				System.out.println("Download Percentage: " + this.downloader.getDownloadedPercentage() + " %");
				System.out.println("Average Download Speed : " + this.downloader.getAverageDownloadSpeed());
				System.out.println("Remaining Size : " + this.downloader.getRemainingDownloadableSize());
				System.out.println("Remaining Time : " + this.downloader.getRequiredTimeToCompleteDownload());
				System.out.println("\n\n\n\n");
				Thread.sleep(1000L);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	}
}
