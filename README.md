# RDF 5424 log file parser


## Task

The application should parse the content of this log file and filter it based on timestamp. The log file should meet standard definition and format of the log message according to IETF-syslog format [(RFC 5424).](https://tools.ietf.org/html/rfc5424)

## Creation

For practical implementation was used Apache Flume [SyslogParser](https://flume.apache.org/releases/content/1.9.0/apidocs/org/apache/flume/source/SyslogParser.html)

```java
public Event parseMessage(String msg, Charset charset, Set<String> keepFields)

Parses a Flume Event out of a syslog message string.
Parameters:
msg - Syslog message, not including the newline character
Returns:
Parsed Flume Event
Throws:
IllegalArgumentException - if unable to successfully parse message
```
For the ```.log``` file reading was created class ```ParserFileReader```.

```java
 public static List<String> parserFileReaderMethod (String filepath, List<String> messages) throws Exception{
        File file = new File(filepath);

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                messages.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return messages;
    }
```

For saving sorted messages to the ```.html``` file, was created method ```write2HTML``` in class ```HTMLfileCreator```.

For sorting messages is used method ```sortParsedRfc5424Messages```.

## Usage

Local path for the ```.log``` file is ```src/main/resources/syslog_example.log```.

Path for the html file is ```src/main/resources/syslog_example_parsed.html```.

For the initial compiling via Maven, was used next command
:
```java
$ mvn clean compile assembly:single
```
To run created Jar is used command:
```java
$ java -jar target/TestTask1-1.0-jar-with-dependencies.jar
```
After the running of the program is opened default web browser with the created html page, with sorted messages.

## Tests
Created tests are using Junit ver. 5.4, and created for testing methods of SyslogParser.

## License
[MIT](https://choosealicense.com/licenses/mit/)
