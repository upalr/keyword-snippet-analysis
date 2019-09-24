import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Hashtable;

/**
 * DirectByteBuffer is responsible for read and write a file using Direct Byte
 * Buffer NIO method.
 *
 * @author Upal Roy
 * @version 1.0.0
 */

public class DirectByteBuffer extends FileNIOMethod {

	/**
	 * Constructor for object class DirectByteBuffer
	 */
	public DirectByteBuffer(String inFileString, String ouString, int bufferSize, ArrayList<String> keywords) {
		super(inFileString, ouString, bufferSize, keywords);
	}

	/**
	 * Read TextFile using Direct Byte Buffer NIO method and save as chank in a
	 * ArrayList
	 *
	 */
	@Override
	public void ReadTextFile() {
		// Using FileChannel with direct ByteBuffer
		try (FileChannel in = new FileInputStream(inFileStr).getChannel()) {
			ByteBuffer bytebuf = ByteBuffer.allocateDirect(bufferSize); // Allocate a "direct" ByteBuffer

			startTime = System.nanoTime();

			int bytesCount;
			while ((bytesCount = in.read(bytebuf)) > 0) { // Read data from file into ByteBuffer
				bytebuf.flip(); // flip the buffer which set the limit to current position, and position to 0.

				CharBuffer charBuffer = StandardCharsets.UTF_8.decode(bytebuf);
				snippets.add(charBuffer.toString());

				bytebuf.clear(); // For the next read
			}
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Write searched keyword's snippet in a TextFile using Direct Byte Buffer NIO
	 * method
	 *
	 */
	@Override
	public void WriteToTextFile() {
		try (FileChannel out = new FileOutputStream(outFileStr, true).getChannel()) {
			out.write(ByteBuffer.wrap(("\n\nBufferSize: " + bufferSize + "\n").getBytes()));
			for (String key : searchResults.keySet()) {
				String keyName = "KeyWord:" + key + "\n";
				ByteBuffer buffer = ByteBuffer.wrap(keyName.getBytes());
				out.write(buffer);

				for (String searchResult : searchResults.get(key)) {
					searchResult = searchResult + "\n\n\n";
					byte[] inputBytes = searchResult.getBytes();
					buffer = ByteBuffer.wrap(inputBytes);
					out.write(buffer); // Write data from ByteBuffer to file
				}
			}
			out.write(ByteBuffer.wrap(
					("-------------------------------------------------------- END OF Search Result--------------------------------------------------------")
							.getBytes()));
			elapsedTime = System.nanoTime() - startTime;
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Write response time of keyword snippet search in a TextFile using Direct Byte
	 * Buffer NIO method
	 *
	 */
	@Override
	public void WriteResponseTime() {
		try (FileChannel out = new FileOutputStream(responseTimeFileStr, true).getChannel()) {
			String responseStr = "Using FileChannel with a direct ByteBuffer of " + bufferSize + "\n";
			responseStr += "Response Time: " + elapsedTime / 1000000.0 + " msec\n\n";
			out.write(ByteBuffer.wrap(responseStr.getBytes())); // Write response time
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
