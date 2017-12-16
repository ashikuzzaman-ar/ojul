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

import java.io.Serializable;
import java.util.List;

import ojul.util.models.JavaDetails;

public interface DownloadableURL extends Serializable {
	
	public abstract String getCurrentJDKVersion();
	
	public abstract String getDownloadURL();
	
	public abstract List<JavaDetails> getJdkDetails();
}
