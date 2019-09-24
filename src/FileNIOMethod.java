import java.util.ArrayList;
import java.util.Hashtable;

/**
 * FileNIOMethod is responsible for maintaining the common characteristics and
 * functionality of different NIO methods.
 *
 * @author Upal Roy
 * @version 1.0.0
 */
public abstract class FileNIOMethod {
	protected String inFileStr;
	protected String outFileStr;
	protected String responseTimeFileStr = "ResponseTime.txt";
	protected int bufferSize;
	protected long startTime, elapsedTime; // for speed benchmarking
	protected ArrayList<String> keywords;
	protected ArrayList<String> snippets;
	protected Hashtable<String, ArrayList<String>> searchResults;

	/**
	 * Constructor for abstract class FileNIOMethod
	 */
	public FileNIOMethod(String inFileString, String outFileStr, int bufferSize, ArrayList<String> keywords) {
		this.inFileStr = inFileString;
		this.outFileStr = outFileStr;
		this.bufferSize = bufferSize;
		this.keywords = keywords;

		snippets = new ArrayList<String>();
		searchResults = new Hashtable<String, ArrayList<String>>();
	}

	/**
	 * Search keywords and save associated snippet/s in HashTable where keyword is
	 * the key and snippet as the value.
	 *
	 */
	public void KeywordSearch() {
		for (String keyword : keywords) {
			for (String snippet : snippets) {
				if (snippet.contains(keyword)) {
					if (searchResults.containsKey(keyword)) {
						searchResults.get(keyword).add(snippet);
					} else {
						ArrayList<String> newSnippet = new ArrayList<String>();
						newSnippet.add(snippet);
						searchResults.put(keyword, newSnippet);
					}
				}
			}
		}
	}

	/**
	 * Read TextFile and save as chank in a ArrayList
	 *
	 */
	public abstract void ReadTextFile();

	/**
	 * Write searched keyword's snippet in a TextFile
	 *
	 */
	public abstract void WriteToTextFile();

	/**
	 * Write response time of keyword snippet search in a TextFile
	 *
	 */
	public abstract void WriteResponseTime();

}
