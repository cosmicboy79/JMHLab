# JMHLab

Learning about Java Microbenchmark Harness (JMH) by doing benchmark exercises.

Different exercises are realized as different Maven modules, as explained in the
following sections.

Overall, this project uses Java 21+ and Maven 3.8.6+.

## `uniqueid`

A simple benchmark to measure the performance of generating unique IDs using different strategies.

### How to run

1. Navigate to the `uniqueid` module directory:
   ```bash
   cd uniqueid
   ```

2. Build the project using Maven:
   ```bash
   mvn clean install
   ```
   
3. Run the benchmark:
   ```bash
   java -jar target/uniqueid-1.0-SNAPSHOT.jar
   ```
