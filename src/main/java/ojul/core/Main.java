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

package ojul.core;

import java.util.Scanner;

import ojul.util.CheckUpdateAvailability;
import ojul.util.DownloadableURL;
import ojul.util.Downloader;
import ojul.util.JDKDownloadableURL;

/**
 *
 * @author ashik
 *
 */
public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {

		try {

			final DownloadableURL downloadableURL = new JDKDownloadableURL();
			final CheckUpdateAvailability checkUpdateAvailability = new CheckUpdateAvailability(
			        downloadableURL);
			if (checkUpdateAvailability.isUpdateAvailable() > 0) {

				System.out.println("New JAVA update is available!");
				System.out.println("Installed JAVA version is : "
				        + checkUpdateAvailability.getLocalJavaVersion());
				System.out.println("Latest JAVA version is: "
				        + downloadableURL.getCurrentJDKVersion());

				final Downloader downloader = new Downloader(downloadableURL);
				System.out.println("You will need to download "
				        + downloader.getJavaDetails().getSize() + " of archive file.");
				System.out.println(
				        "Make sure not to terminate the program by force before finished automatically!!!");
				System.out.print(
				        "Do you want to download and update your JAVA version ? (Y/n) : ");
				Main.scanner = new Scanner(System.in);
				final String token = Main.scanner.next().trim().toLowerCase();
				if (token.equals("y")) {

					System.out.println("Starting download!");
					downloader.startDownload();
					downloader.showDownloadDetails();
				} else if (token.equals("n")) {

					System.out.println("JAVA updating process has beed terminated!");
				} else {

					System.out.println("Invalid Token!");
				}
			} else {

				System.out.println("JAVA is updated!");
				System.out.println("Installed JAVA version is : "
				        + checkUpdateAvailability.getLocalJavaVersion());
			}
		} catch (final Exception e) {

			e.printStackTrace();
		}
	}
}
