package ojul.util;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DownloaderTest implements ResourcePath {

	private Downloader downloader;

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
		while (!this.downloader.isDownloadComplete()) {

			try {

				Thread.sleep(1000L);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
		System.out.println("Download is completed : " + this.downloader.isDownloadComplete());
	}
}
