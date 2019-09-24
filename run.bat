@echo off

javac -sourcepath src ./src/KeywordSearch.java -d bin


java -classpath bin KeywordSearch dirChannel 1024 Sample.txt

java -classpath bin KeywordSearch indirChannel 1024 Sample.txt

java -classpath bin KeywordSearch buffStream 1024 Sample.txt

java -classpath bin KeywordSearch progArray 1024 Sample.txt



java -classpath bin KeywordSearch dirChannel 2048 Sample.txt

java -classpath bin KeywordSearch indirChannel 2048 Sample.txt

java -classpath bin KeywordSearch buffStream 2048 Sample.txt

java -classpath bin KeywordSearch progArray 2048 Sample.txt

