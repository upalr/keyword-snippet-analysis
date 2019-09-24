import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * BufferedIOStream is responsible for read and write a file using Buffered
 * Stream IO NIO method.
 *
 * @author Upal Roy
 * @version 1.0.0
 */

public class BufferedIOStream extends FileNIOMethod {

	/**
	 * Constructor for object class BufferedIOStream
	 */
	public BufferedIOStream(String inFileString, String ouString, int bufferSize, ArrayList<String> keywords) {
		super(inFileString, ouString, bufferSize, keywords);
	}

	/**
	 * Read TextFile using Buffered Stream IO NIO method and save as chank in a
	 * ArrayList
	 *
	 */
	@Override
	public void ReadTextFile() {
		// Using Buffered Stream I/O
		try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(inFileStr))) {
			byte[] contents = new byte[bufferSize];
			startTime = System.nanoTime();
			int bytesCount;
			while ((bytesCount = in.read(contents)) != -1) {
				snippets.add(new String(contents));
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Write searched keyword's snippet in a TextFile using Buffered Stream IO NIO
	 * method
	 *
	 */
	@Override
	public void WriteToTextFile() {
		// Using Buffered Stream I/O
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outFileStr, true))) {
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
	 * Write response time of keyword snippet search in a TextFile using Buffered
	 * Stream IO NIO method
	 *
	 */
	@Override
	public void WriteResponseTime() {
		try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(responseTimeFileStr, true))) {
			String responseStr = "Using Buffered Stream with a  Byte Size of " + bufferSize + "\n";
			responseStr += "Response Time: " + elapsedTime / 1000000.0 + " msec\n\n";
			out.write(responseStr.getBytes());
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
