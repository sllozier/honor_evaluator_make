package main;

import java.net.MalformedURLException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import java.util.Scanner;

public class GenerateStudentData {

    public static void generateStudentDataFile(String fileName, int numberOfStudents) {
        FileWriter fileWriter = null;
        if (numberOfStudents <= 0) {
            System.err.println("Invalid number of students. File not created.");
            return;
        }
        try {
            // Create a FileWriter to write to the specified text file
            // FileWriter is a Java standard class for writing data to text files
            fileWriter = new FileWriter(fileName);

            // Random number generator
            Random random = new Random();

            // Generate student data
            for (int i = 0; i < numberOfStudents; i++) {
                // Generate random first name and last name using the uinames.com API
                String[] randomName = generateRandomName();
                String firstName = randomName[0];
                String lastName = randomName[1];

                int creditHours = random.nextInt(61) + 20; // Random credit hours between 20 and 80
                int qualityPoints = creditHours * (random.nextInt(3) + 2); // Random quality points
                String yearOrDegree = generateYearOrDegree(random);

                // Write student data to the file
                fileWriter.write(lastName + "," + firstName + " " + creditHours + " " + qualityPoints + " "
                        + yearOrDegree + "\n");
            }

            System.out.println("Student data generated successfully in '" + fileName + "'");
        } catch (IOException e) {
            System.err.println("Failed to write data to file: " + fileName);
            e.printStackTrace();
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                System.err.println("Failed to close the file writer.");
                e.printStackTrace();
            }
        }
    }

    private static String[] generateRandomName() {
        try {
            // Create a URL object to access the uinames.com API
            // URL is a Java class used to represent and manipulate Uniform Resource
            // Locators (URLs)
            URL url = new URL("https://randomuser.me/api/?nat=us,gb,au"); // API to generate names
            // (https://github.com/thm/uinames)

            // Open a connection to the URL
            // HttpURLConnection is a Java class for making HTTP requests and handling HTTP
            // connections
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the API
            Scanner scanner = new Scanner(connection.getInputStream());
            StringBuilder response = new StringBuilder();
            while (scanner.hasNextLine()) {
                response.append(scanner.nextLine());
            }
            scanner.close();

            // JSON (JavaScript Object Notation) is a lightweight data interchange format
            // JSON parsing is required to extract specific data from the JSON response
            // received from the API
            // Parse the JSON response to extract the first name and last name
            String json = response.toString();
            String firstName = json.split("\"first\":\"")[1].split("\"")[0];
            String lastName = json.split("\"last\":\"")[1].split("\"")[0];

            return new String[] { firstName, lastName };
        } catch (MalformedURLException e) {
            System.err.println("URL format is incorrect.");
            e.printStackTrace();
            return new String[] { "John", "Doe" };
        } catch (IOException e) {
            System.err.println("Error occurred while calling the randomuser.me API.");
            e.printStackTrace();
            return new String[] { "John", "Doe" };
        }
    }

    private static String generateYearOrDegree(Random random) {
        // Generate random year for undergraduate or "Masters" for graduate
        if (random.nextBoolean()) {
            String[] years = { "Freshman", "Sophomore", "Junior", "Senior" };
            return years[random.nextInt(years.length)];
        } else {
            return "Masters";
        }
    }

    public static void main(String[] args) {
        // Example usage:
        // Generate student data and save it to a text file named "students.txt" with
        // 100 students
        // Get the directory of the running JAR file or the root project directory
        String basePath = new File("").getAbsolutePath();

        // Define the path to the target/generated-sources directory
        String targetDirPath = basePath + File.separator + "docs";

        // Ensure the directory exists
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs(); // Create the directory if it does not exist
        }

        // Define the path to the students.txt file within the target/generated-sources
        // directory
        String filePath = targetDirPath + File.separator + "students.txt";

        // Generate student data and save it to the specified file
        generateStudentDataFile(filePath, 100);
    }
}
