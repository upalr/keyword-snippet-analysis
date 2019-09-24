import java.util.ArrayList;

/**
 * Mange to start the KeyWord search application
 *
 * @author Upal Roy
 * @version 1.0.0
 */

public class KeywordSearch {

	public static void main(String[] args) {

		String methodName = args[0];
		int bufferSize = Integer.parseInt(args[1]);
		String inFileStr = args[2];
		ArrayList<String> keywords = new ArrayList<String>() {
			{
				add("coasts");
				add("Malta");
				add("geology");
			}
		};
		FileNIOMethod fnio = null;

		if (methodName.equals("dirChannel")) {
			fnio = new DirectByteBuffer(inFileStr, "Sampleout-DirectByteBuffer.txt", bufferSize, keywords);
		} else if (methodName.equals("indirChannel")) {
			fnio = new IndirectByteBuffer(inFileStr, "Sampleout-IndirectByteBuffer.txt", bufferSize, keywords);
		} else if (methodName.equals("buffStream")) {
			fnio = new BufferedIOStream(inFileStr, "Sampleout-BufferedIOStream.txt", bufferSize, keywords);
		} else if (methodName.equals("progArray")) {
			fnio = new ProgrammerManaged(inFileStr, "Sampleout-ProgrammerManaged.txt", bufferSize, keywords);
		} else {
			System.out.println("Please choose the right method for keyword search.");
		}
		
		fnio.ReadTextFile();
		fnio.KeywordSearch();
		fnio.WriteToTextFile();
		fnio.WriteResponseTime();

	}
}
