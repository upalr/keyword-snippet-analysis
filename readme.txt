Name: Upal Roy
ID: 101918586
Subject ID: COS80007 

Instructipons on how to run the assignment:
	Unsing the batch file:
		1. Go to the "KeywordSearch" project folder.
		2. Click the run.bat file. already some instructiuons is written there. "Sample.txt file is used as a default file 
		   for searching snippets and "coasts", "Malta" and "geology" are used as default keywords.
		   user can edit the file and test with different method name, buffer size and text file name. 
		   The command format is: java programName methodName bufferSize textFileName.
		   Example: java -classpath bin KeywordSearch dirChannel 1024 Sample.txt
		   		   
    Unsing Eclipse IDE:
		1. Open the project in Eclipse
		2. Run the project providing the arguments as above mentioned order.
		
Bugs:
	No known bugs

	
Source Codes:
	1. Lecture 4 slides.
	2. "Lab4-FileChannelCopy" (available on Canvas ) project's from the lecture 4.

	
Assessment Items:
1. Design and implementation of different methods. (Used four different methods)
2. Use of batch file containing java commands to do the experiments. (Created run.bat file )
3. Use NIO and advanced java features as far as possible. (Used FileChannel, ByteBuffer, CharBuffer and StandardCharsets from NIO 
   and encapsulation, Iinheritance and polymorphism java features has been used)
4. Use advanced data structure for indexing and exception handling. (Hashtable and ArrayList has been used)
5. Recording the time for each method and printing the results in a text file. (ResponseTime.text file recored the time)
6. Include reasonable documentation according to the Javadoc standards. (commens are Done according to the Javadoc standards)
7. A readme.txt file explaining features completed, expected mark and locating presence of codes from other sources. (Done)

Expected Mark:
  I have completed all the requirements for this project using the advance Java features and NIO methods. Therefore, I would like to get 
  HD for this assignment.
