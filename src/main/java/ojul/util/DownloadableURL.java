package ojul.util;

import java.io.Serializable;
import java.util.List;

import ojul.util.models.JDKDetails;

public interface DownloadableURL extends Serializable {

	public abstract List<JDKDetails> getJdkDetails();

	public abstract String getDownloadURL();

	public abstract String getCurrentJDKVersion();
}
