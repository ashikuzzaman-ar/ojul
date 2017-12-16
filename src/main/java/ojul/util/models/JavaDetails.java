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
package ojul.util.models;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author ashik
 *
 */
public class JavaDetails implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String fileName;
	
	private String filepath;
	
	@JsonProperty
	private String SHA256;
	
	private String size;
	
	private String title;
	
	private String version;
	
	public JavaDetails() {
	
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		
		this.trimmer();
		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final JavaDetails other = (JavaDetails) obj;
		if (this.SHA256 == null) {
			if (other.SHA256 != null) {
				return false;
			}
		} else if (!this.SHA256.equals(other.SHA256)) {
			return false;
		}
		if (this.fileName == null) {
			if (other.fileName != null) {
				return false;
			}
		} else if (!this.fileName.equals(other.fileName)) {
			return false;
		}
		if (this.filepath == null) {
			if (other.filepath != null) {
				return false;
			}
		} else if (!this.filepath.equals(other.filepath)) {
			return false;
		}
		if (this.size == null) {
			if (other.size != null) {
				return false;
			}
		} else if (!this.size.equals(other.size)) {
			return false;
		}
		if (this.title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!this.title.equals(other.title)) {
			return false;
		}
		if (this.version == null) {
			if (other.version != null) {
				return false;
			}
		} else if (!this.version.equals(other.version)) {
			return false;
		}
		return true;
	}
	
	public String getFileName() {
		
		return this.fileName.trim();
	}
	
	public String getFilepath() {
		
		return this.filepath.trim();
	}
	
	public String getSHA256() {
		
		return this.SHA256.trim();
	}
	
	public String getSize() {
		
		return this.size.trim();
	}
	
	public String getTitle() {
		
		return this.title.trim();
	}
	
	public String getVersion() {
		
		return this.version.trim();
	}
	
	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = (prime * result) + ((this.SHA256 == null) ? 0 : this.SHA256.hashCode());
		result = (prime * result)
		        + ((this.fileName == null) ? 0 : this.fileName.hashCode());
		result = (prime * result)
		        + ((this.filepath == null) ? 0 : this.filepath.hashCode());
		result = (prime * result) + ((this.size == null) ? 0 : this.size.hashCode());
		result = (prime * result) + ((this.title == null) ? 0 : this.title.hashCode());
		result = (prime * result)
		        + ((this.version == null) ? 0 : this.version.hashCode());
		return result;
	}
	
	public void setFileName(String fileName) {
		
		this.fileName = fileName;
	}
	
	public void setFilepath(String filepath) {
		
		this.filepath = filepath;
	}
	
	public void setSHA256(String SHA256) {
		
		this.SHA256 = SHA256;
	}
	
	public void setSize(String size) {
		
		this.size = size;
	}
	
	public void setTitle(String title) {
		
		this.title = title;
	}
	
	public void setVersion(String version) {
		
		this.version = version;
	}
	
	@Override
	public String toString() {
		
		return "title = " + this.title + ", size = " + this.size + ", filePath = "
		        + this.filepath + ", fileName = " + this.fileName + ", version = "
		        + this.version + ", SHA256 = " + this.SHA256;
	}
	
	private void trimmer() {
		
		this.title = this.title.trim();
		this.size = this.size.trim();
		this.filepath = this.filepath.trim();
		this.SHA256 = this.SHA256.trim();
		this.fileName = this.fileName.trim();
		this.version = this.version.trim();
	}
}
