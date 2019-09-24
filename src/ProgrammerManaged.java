import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ProgrammerManaged is responsible for read and write a file using
 * Programmer-managed Byte-Array NIO method.
 *
 * @author Upal Roy
 * @version 1.0.0
 */
public class ProgrammerManaged extends FileNIOMethod {

	/**
	 * Constructor for object class ProgrammerManaged
	 */
	public ProgrammerManaged(String inFileString, String ouString, int bufferSize, ArrayList<String> keywords) {
		super(inFileString, ouString, bufferSize, keywords);
	}

	/**
	 * Read TextFile using Programmer-managed Byte-Array NIO method and save as
	 * chank in a ArrayList
	 *
	 */
	@Override
	public void ReadTextFile() {
		// Using a programmer-managed byte-array
		try (FileInputStream in = new FileInputStream(inFileStr)) {
			startTime = System.nanoTime();
			byte[] byteArray = new byte[bufferSize];
			int bytesCount;
			while ((bytesCount = in.read(byteArray)) != -1) {
				snippets.add(new String(byteArray));
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Write searched keyword's snippet in a TextFile using Byte-Array NIO method
	 * NIO method
	 *
	 */
	@Override
	public void WriteToTextFile() {
		// Using a programmer-managed byte-array
		try (FileOutputStream out = new FileOutputStream(outFileStr, true)) {
			out.write(("\n\nBufferSize: " + bufferSize + "\n").getBytes());
			for (String key : searchResults.keySet()) {
				String keyName = "KeyWord:" + key + "\n";
				out.write(keyName.getBytes());

				for (String searchResult : searchResults.get(key)) {
					searchResult = searchResult + "\n\n\n";
					out.write(searchResult.getBytes());
				}
			}
			out.write(
					("-------------------------------------------------------- END OF Search Result--------------------------------------------------------")
							.getBytes());
			elapsedTime = System.nanoTime() - startTime;
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Write response time of keyword snippet search in a TextFile using Byte-Array
	 * NIO method NIO method
	 *
	 */
	@Override
	public void WriteResponseTime() {
		try (FileOutputStream out = new FileOutputStream(responseTimeFileStr, true)) {
			String responseStr = "Using a programmer-managed byte-array of " + bufferSize + "\n";
			responseStr += "Response Time: " + elapsedTime / 1000000.0 + " msec\n\n";
			out.write(responseStr.getBytes());
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
