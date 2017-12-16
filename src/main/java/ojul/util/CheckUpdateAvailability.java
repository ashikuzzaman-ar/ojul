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

import java.io.File;
import java.io.FileFilter;
import java.util.Scanner;

/**
 *
 * @author ashik
 *
 */
public class CheckUpdateAvailability implements ResourcePath {
	
	private final DownloadableURL downloadableURL;
	
	private File file;
	
	private String LOCAL_JAVA_VERSION;
	
	public CheckUpdateAvailability(DownloadableURL downloadableURL) {
		
		super();
		this.downloadableURL = downloadableURL;
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
					 * Installed java version can be found in java root directory having
					 * name release at line number 3
					 */
					this.file = this.file.listFiles((FileFilter) arg0 -> arg0.isFile()
					        && arg0.getName().equals("release"))[0];
					
					final Scanner scanner = new Scanner(this.file);
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
			} catch (final Exception e) {
				
				e.printStackTrace();
			}
		}
		
		return this.LOCAL_JAVA_VERSION;
	}
	
	/**
	 * This method will return 0 if no update is available and greater than 0 if any
	 * update found
	 *
	 * @return
	 */
	public int isUpdateAvailable() {
		
		return this.downloadableURL.getCurrentJDKVersion()
		        .compareTo(this.getLocalJavaVersion());
	}
	
	/**
	 * This method will return if source and destination are ok else false
	 *
	 * @return
	 */
	public boolean pathValidation() {
		
		this.file = new File(ResourcePath.JAVA_DIRECTORY);
		return this.file.exists();
	}
}
