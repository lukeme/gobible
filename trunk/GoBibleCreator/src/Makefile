
GoBibleCreator.jar: *.class Manifest
	jar cfm GoBibleCreator.jar Manifest *.class jolon.jar

*.class: *.java jolon.jar
	javac -cp jolon.jar *.java

