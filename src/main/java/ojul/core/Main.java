package ojul.core;

import java.util.Scanner;

import ojul.util.CheckUpdateAvailability;
import ojul.util.DownloadableURL;
import ojul.util.Downloader;
import ojul.util.JDKDownloadableURL;

public class Main {

	private static Scanner scanner;

	public static void main(String[] args) {

		try {

			DownloadableURL downloadableURL = new JDKDownloadableURL();
			CheckUpdateAvailability checkUpdateAvailability = new CheckUpdateAvailability(downloadableURL);
			if (checkUpdateAvailability.isUpdateAvailable() > 0) {

				System.out.println("New JAVA update is available!");
				System.out.println("Installed JAVA version is : " + checkUpdateAvailability.getLocalJavaVersion());
				System.out.println("Latest JAVA version is: " + downloadableURL.getCurrentJDKVersion());

				Downloader downloader = new Downloader(downloadableURL);
				System.out.println(
						"You will need to download " + downloader.getJavaDetails().getSize() + " of archive file.");
				System.out.println("Make sure not to terminate the program by force before finished automatically!!!");
				System.out.print("Do you want to download and update your JAVA version ? (Y/n) : ");
				scanner = new Scanner(System.in);
				String token = scanner.next().trim().toLowerCase();
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
				System.out.println("Installed JAVA version is : " + checkUpdateAvailability.getLocalJavaVersion());
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}