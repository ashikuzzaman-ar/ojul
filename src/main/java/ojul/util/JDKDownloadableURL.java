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

import java.util.LinkedList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.fasterxml.jackson.databind.ObjectMapper;

import ojul.util.models.JavaDetails;

/**
 * This class will find out version specific url for latest jdk. This class is
 * needed because oracle does not allow to access direct download links and such
 * links changes dynamically for every new jdk update.
 * 
 * @author ashik
 *
 */
public class JDKDownloadableURL implements DownloadableURL {

	public static final long serialVersionUID = 1L;

	private String oracleURL;
	private String jdkURL;
	private String downloadURL;
	private String currentJDKVersion;
	private List<JavaDetails> javaDetails;

	/**
	 * By default this constructor will initialize oracles base urls.
	 */
	public JDKDownloadableURL() {

		this.oracleURL = "http://www.oracle.com";
		this.jdkURL = this.oracleURL + "/technetwork/java/javase/downloads/index.html";
		this.javaDetails = new LinkedList<JavaDetails>();
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public List<JavaDetails> getJdkDetails() {

		if (this.javaDetails == null || this.javaDetails.size() == 0) {

			this.getLatestJDKVersion();
		}
		return this.javaDetails;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String getDownloadURL() {

		return this.downloadURL != null ? this.downloadURL : this.getDownloadPageURL();
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String getCurrentJDKVersion() {

		return this.currentJDKVersion != null ? this.currentJDKVersion : this.getLatestJDKVersion();
	}

	/**
	 * 
	 * @return
	 */
	private String getDownloadPageURL() {

		try {

			Document document = Jsoup.connect(this.jdkURL).get();
			// Parsing div element having id 'Wrapper_FixedWidth_Centercontent'
			Element element = document.getElementById("Wrapper_FixedWidth_Centercontent");
			// Parsing div elements having class attribute of 'orcl6w3'
			// Getting first element from elements
			element = element.select("div[class$=orcl6w3]").get(0);
			// Parsing all tables from the element where desired link isplaced
			// Getting first element from parsed tables
			element = element.getElementsByTag("table").get(0);
			// Parsing all links from the following table
			// First element is the desired link
			element = element.getElementsByTag("a").get(0);
			// Parsing the href content from the link [a] tag
			this.downloadURL = this.oracleURL + element.attr("href");
		} catch (Exception e) {

			throw new ExceptionInInitializerError(e);
		}

		return this.downloadURL;
	}

	/**
	 * 
	 * @return
	 */
	private String getLatestJDKVersion() {

		try {

			Document document = Jsoup.connect(this.getDownloadURL()).get();
			// Desired jdk version is not present in html document
			// That content is dynamically placed by js and that is
			// defined in 10th number script tag
			Element element = document.getElementsByTag("script").get(9);

			// Jackson's ObjectMapper is used to deserializing json data from
			// the following lines
			ObjectMapper mapper = new ObjectMapper();

			// Splitting to single line so that no other suitable keys found
			// to split from such script
			for (String item : element.toString().split("\n")) {

				// Traversing through lines to find out those lines starting with "downloads"
				// and contains "Linux" so that we need that lines to extract current jdk
				// version
				if (item.startsWith("downloads") && item.contains("Linux")) {

					// Splitting major two parts using "=" and taking 2nd part because
					// 2nd part containing major informations
					JavaDetails details = mapper.readValue(item.split("=")[1], JavaDetails.class);
					// Splitting using "']['" and taking 3rd element cleaning "']" from the
					// last of the line
					details.setFileName(item.split("=")[0].split("'\\]\\['")[2].replaceAll("'\\]", ""));
					this.javaDetails.add(details);
				}
			}

			// Extracting current jdk version from file name
			this.currentJDKVersion = this.javaDetails.get(0).getFileName().split("_")[0].split("-")[1];

			// Setting current jdk version in every jdkDetails object
			for (int i = 0; i < this.javaDetails.size(); i++) {

				this.javaDetails.get(i).setVersion(this.currentJDKVersion);
			}
		} catch (Exception e) {

			throw new ExceptionInInitializerError(e);
		}

		return this.currentJDKVersion;
	}
}
