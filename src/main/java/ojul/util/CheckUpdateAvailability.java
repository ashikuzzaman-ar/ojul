package ojul.util;

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

/**
 * 
 * @author ashik
 *
 */
public class CheckUpdateAvailability {

	private static final String OJUL_HOME = System.getProperty("user.home") + "/.ojul/";
	private static final String JAVA_HOME = OJUL_HOME + "oracle-java/";
	// private static final String TEMP_HOME = OJUL_HOME + "temp/";
	private static final String JAVA_DIRECTORY = JAVA_HOME + "java/";
	private File file;
	private String LOCAL_JAVA_VERSION;
	private DownloadableURL downloadableURL;

	public CheckUpdateAvailability(DownloadableURL downloadableURL) {
		super();
		this.downloadableURL = downloadableURL;
	}

	/**
	 * This method will return 0 if no update is available and greater than 0 if any
	 * update found
	 * 
	 * @return
	 */
	public int isUpdateAvailable() {

		return this.downloadableURL.getCurrentJDKVersion().compareTo(this.getLocalJavaVersion());
	}

	/**
	 * This method will return the installed JAVA version
	 * 
	 * @return
	 */
	public String getLocalJavaVersion() {

		if (this.LOCAL_JAVA_VERSION == null) {

			try {

				if (this.pathValidation()) {

					/**
					 * Installed java version can be found in java root directory having name
					 * release at line number 3
					 */
					this.file = this.file.listFiles(new FileFilter() {

						@Override
						public boolean accept(File arg0) {

							// Filtering a file having name 'release'
							return arg0.isFile() && arg0.getName().equals("release");
						}
					})[0];

					Scanner scanner = new Scanner(this.file);
					String line = null;

					// Reading from release file for java version
					while (scanner.hasNext()) {

						line = scanner.nextLine();
						if (line.contains("JAVA_VERSION")) {
							break;
						}
					}
					scanner.close();

					// Splitting text and removing all unnecessary strings
					this.LOCAL_JAVA_VERSION = line.split("=")[1].replaceAll("\"", "");
				}
			} catch (Exception e) {

				e.printStackTrace();
			}
		}

		return this.LOCAL_JAVA_VERSION;
	}

	/**
	 * This method will return if source and destination are ok else false
	 * 
	 * @return
	 */
	public boolean pathValidation() {

		this.file = new File(JAVA_DIRECTORY);
		return file.exists();
	}
}
