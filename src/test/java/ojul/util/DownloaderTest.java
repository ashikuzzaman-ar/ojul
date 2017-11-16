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
