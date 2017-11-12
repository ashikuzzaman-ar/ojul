package ojul.util;

import java.io.IOException;

import org.junit.Test;

public class DecompressJavaTest implements ResourcePath {

	@Test
	public void test_1() throws InterruptedException, IOException {

		DecompressJava decompressJava = new DecompressJava(TEMP_HOME + "jdk-9.0.1_linux-x64_bin.tar.gz", JAVA_HOME);
		decompressJava.startDecompression();
		String out = null;
		while ((out = decompressJava.getOutputBuffer().readLine()) != null) {

			System.out.println(out);
		}
	}
}
