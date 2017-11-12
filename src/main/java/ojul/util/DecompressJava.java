package ojul.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

/**
 * 
 * @author ashik
 *
 */
public class DecompressJava implements Runnable {

	private String source;
	private String destination;
	private BufferedReader outputBuffer;
	private BufferedReader errorBuffer;

	public DecompressJava(String source, String destination) {
		super();
		this.source = source;
		this.destination = destination;
	}

	/**
	 * This method will return if source and destination are ok else false
	 * 
	 * @return
	 */
	private boolean pathValidation() {

		try {

			if (this.source == null || this.destination == null) {

				System.err.println("Invalid paths");
				return false;
			}
			File file = new File(this.source);
			if (!file.exists()) {

				System.err.println("Source file does not exist");
				return false;
			}
		} catch (Exception e) {

			e.printStackTrace();
		}

		return true;
	}

	/**
	 * @return the outputBuffer
	 */
	public BufferedReader getOutputBuffer() {
		return this.outputBuffer;
	}

	/**
	 * @return the errorBuffer
	 */
	public BufferedReader getErrorBuffer() {
		return this.errorBuffer;
	}

	/**
	 * This method will run this thread in standalone mode instead of running as a
	 * thread
	 * 
	 * @throws InterruptedException
	 */
	public void startDecompression() throws InterruptedException {

		new Thread(this).start();
		Thread.sleep(1000L);
	}

	@Override
	public void run() {

		try {

			if (this.pathValidation()) {

				String command = "tar -xvf " + this.source + " -C " + this.destination;
				Process process = Runtime.getRuntime().exec(command);
				this.errorBuffer = new BufferedReader(new InputStreamReader(process.getErrorStream()));
				this.outputBuffer = new BufferedReader(new InputStreamReader(process.getInputStream()));
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
