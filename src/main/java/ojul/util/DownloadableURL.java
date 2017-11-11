package ojul.util;

import java.io.Serializable;
import java.util.List;

import ojul.util.models.JavaDetails;

public interface DownloadableURL extends Serializable {

	public abstract List<JavaDetails> getJdkDetails();

	public abstract String getDownloadURL();

	public abstract String getCurrentJDKVersion();
}
