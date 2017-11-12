package ojul.util;

public interface ResourcePath {

	public static final String OJUL_HOME = System.getProperty("user.home") + "/.ojul/";
	public static final String JAVA_HOME = OJUL_HOME + "oracle-java/";
	public static final String TEMP_HOME = OJUL_HOME + "temp/";
	public static final String JAVA_DIRECTORY = JAVA_HOME + "java/";
}
