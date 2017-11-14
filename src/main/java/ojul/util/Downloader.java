package ojul.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

import ojul.util.models.JavaDetails;

/**
 * 
 * @author ashik
 *
 */
public class Downloader implements Runnable, ResourcePath {

	private DownloadableURL downloadableURL;
	private JavaDetails javaDetails;
	private File file;
	private URL url;
	private HttpURLConnection httpURLConnection;
	private InputStream inputStream;
	private DownloadInformation information;

	/**
	 * This class will be used for tracking basic download information
	 * 
	 * @author ashik
	 *
	 */
	private class DownloadInformation {

		private long timeBeforeStartingDownload;
		private long timeAfterCompletingDownload;
		private long downloadedSize;
		private boolean isDownloadComplete;

		public DownloadInformation() {

			this.timeBeforeStartingDownload = System.currentTimeMillis();
			this.timeAfterCompletingDownload = System.currentTimeMillis();
			this.downloadedSize = -1L;
			this.isDownloadComplete = false;
		}
	}

	/**
	 * 
	 * @param downloadableURL
	 */
	public Downloader(DownloadableURL downloadableURL) {
		super();
		this.downloadableURL = downloadableURL;
		this.checkDirectories();
		this.chooseJavaDetails();
	}

	/**
	 * This method will be used for running downloading in standalone mode rather
	 * creating thread object
	 */
	public void startDownload() {

		new Thread(this).start();
	}

	/**
	 * @return the javaDetails
	 */
	public JavaDetails getJavaDetails() {
		return this.javaDetails;
	}

	/**
	 * This method will download the current java(jdk/jre)
	 * 
	 * @return
	 */
	private boolean download() {

		try {

			// Creating URL from oracle's download page url
			this.url = new URL(this.javaDetails.getFilepath());

			// Checking weather the original url is redirected or not
			// If the original url is redirected creating new url using that
			while (true) {

				this.httpURLConnection = (HttpURLConnection) this.url.openConnection();

				// Sometime page forwarding happens
				// This line will allow the connection to handle them
				this.httpURLConnection.setInstanceFollowRedirects(true);

				// Oracle license accepting is hard coded here
				this.httpURLConnection.setRequestProperty("Cookie", "oraclelicense=accept-securebackup-cookie");

				int responseCode = this.httpURLConnection.getResponseCode();
				if (responseCode != HttpURLConnection.HTTP_MOVED_PERM
						&& responseCode != HttpURLConnection.HTTP_MOVED_TEMP && responseCode != 307) {

					break;
				}

				this.url = new URL(this.httpURLConnection.getHeaderField("Location"));
			}

			/**
			 * If any error occurs while connecting the actual url then the downloading
			 * process will be terminated and the error log will be printed
			 */
			if (this.httpURLConnection.getErrorStream() != null) {

				this.inputStream = this.httpURLConnection.getErrorStream();
				BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(this.inputStream));
				String error = null;
				while ((error = bufferedReader.readLine()) != null) {

					System.err.println(error);
				}

				return false;
			}

			this.inputStream = this.httpURLConnection.getInputStream();

			this.information = new DownloadInformation();

			/**
			 * This thread will independently calculate some basic information while
			 * downloading the file
			 */
			new Thread(new Runnable() {

				@Override
				public void run() {

					try {

						File file = new File(TEMP_HOME + javaDetails.getFileName());
						information.timeBeforeStartingDownload = System.currentTimeMillis();

						// Wait 2 seconds to start
						Thread.sleep(2000L);

						// This loop will continue until the downloading process stops
						while (!information.isDownloadComplete) {

							// If and only if the file exists then it can calculate
							// the file size
							if (file.exists()) {

								information.downloadedSize = Files
										.size(Paths.get(TEMP_HOME + javaDetails.getFileName()));
							}

							information.timeAfterCompletingDownload = System.currentTimeMillis();
							Thread.sleep(100L);
						}
					} catch (Exception e) {

						e.printStackTrace();
					}
				}
			}).start();

			// Downloading the file
			Files.copy(this.inputStream, Paths.get(TEMP_HOME + this.javaDetails.getFileName()));

			// Downloading trigger will be shut after completing the download
			this.information.isDownloadComplete = true;
		} catch (Exception e) {

			e.printStackTrace();
		}

		return true;
	}

	public void showDownloadDetails() {

		try {

			while (!this.isDownloadComplete()) {

				Thread.sleep(1000L);

				for (int i = 0; i < 100; i++) {

					System.out.println();
				}

				System.out.println(
						"Downloaded               : " + (this.getDownloadedSize() / (1.0 * 1024 * 1024)) + " MB");
				System.out.println("Time Passed              : " + (this.getElapsedTime() / (1.0 * 1000)) + " Sec.");
				System.out.println("Downloaded Percentage    : " + this.getDownloadedPercentage() + " %");
				System.out.println("Average Download Speed   : " + (this.getAverageDownloadSpeed() / 1.0) + " KB/S");
				System.out.println("Remaining Size           : "
						+ (this.getRemainingDownloadableSize() / (1.0 * 1024 * 1024)) + " MB");
				System.out.println("Remaining Time           : "
						+ (this.getRequiredTimeToCompleteDownload() / (1.0 * 1000)) + " (Approx.)");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * Returns the remaining time to complete the download in millisecond
	 * 
	 * @return
	 */
	public long getRequiredTimeToCompleteDownload() {

		return (long) (this.information == null ? -1
				: this.getRemainingDownloadableSize() / this.getAverageDownloadSpeed());
	}

	/**
	 * Returns the remaining volumes to download in byte unit
	 * 
	 * @return
	 */
	public long getRemainingDownloadableSize() {

		return (long) (this.information == null ? -1L
				: (Double.parseDouble(this.javaDetails.getSize().split(" ")[0]) * 1024 * 1024)
						- this.getDownloadedSize());
	}

	/**
	 * Returns the average download speed per second in KBPS
	 * 
	 * @return
	 */
	public double getAverageDownloadSpeed() {

		return this.information == null ? 0.0D : this.getDownloadedSize() / (1.0 * this.getElapsedTime());
	}

	/**
	 * Returns the percentage of already downloaded volume
	 * 
	 * @return
	 */
	public double getDownloadedPercentage() {

		return this.information == null ? 0
				: (this.getDownloadedSize() * 100.0)
						/ (Double.parseDouble(this.javaDetails.getSize().split(" ")[0]) * 1024 * 1024);
	}

	/**
	 * Returns the passed time from starting the download to the present time
	 * 
	 * @return
	 */
	public long getElapsedTime() {

		return this.information == null ? 0L
				: this.information.timeAfterCompletingDownload - this.information.timeBeforeStartingDownload;
	}

	/**
	 * Returns true if download is completed successfully else false
	 * 
	 * @return
	 */
	public boolean isDownloadComplete() {

		return this.information == null ? false : this.information.isDownloadComplete;
	}

	/**
	 * Returns the already downloaded volume in byte unit
	 * 
	 * @return
	 */
	public long getDownloadedSize() {

		return this.information == null ? 0 : this.information.downloadedSize;
	}

	/**
	 * This method will choose which java version is going to download
	 */
	private void chooseJavaDetails() {

		try {

			if (this.downloadableURL != null) {

				// Default java version if no suitable file found to download
				this.javaDetails = this.downloadableURL.getJdkDetails().get(0);
			}

			this.downloadableURL.getJdkDetails().forEach(jdkDetails -> {

				// This line is going to choose a file having extension of tar
				if (jdkDetails.getFileName().contains("tar")) {

					this.javaDetails = jdkDetails;
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * This method will check the basic configurations and directory patterns
	 * required for this application
	 */
	private void checkDirectories() {

		try {

			this.file = new File(OJUL_HOME);
			if (!this.file.exists()) {

				this.file.mkdirs();
			}

			this.file = new File(JAVA_HOME);
			if (!this.file.exists()) {

				this.file.mkdirs();
			}

			this.file = new File(TEMP_HOME);
			if (!this.file.exists()) {

				this.file.mkdirs();
			}

			this.downloadableURL.getJdkDetails().forEach(details -> {

				this.file = new File(TEMP_HOME + details.getFileName());
				if (this.file.exists()) {

					this.file.delete();
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	@Override
	public void run() {

		this.download();
	}
}