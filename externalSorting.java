import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class externalSorting {
    public static void main(String[] args) {
        String inputFileName = "numbers.txt"; 
        String outputFileName = "sorted_numbers.txt"; 

        ArrayList<Integer> numbers = readNumbersFromFile(inputFileName);
        if (numbers != null) {
            Collections.sort(numbers);
            if (writeNumbersToFile(outputFileName, numbers)) {
                System.out.println("Sorted numbers written to " + outputFileName);
            } else {
                System.out.println("Failed to write sorted numbers to file.");
            }
        } else {
            System.out.println("Failed to read numbers from file.");
        }
    }

    private static ArrayList<Integer> readNumbersFromFile(String fileName) {
        ArrayList<Integer> numbers = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                try {
                    int number = Integer.parseInt(line);
                    numbers.add(number);
                } catch (NumberFormatException e) {
                    System.err.println("Invalid number found: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return null;
        }

        return numbers;
    }

    private static boolean writeNumbersToFile(String fileName, ArrayList<Integer> numbers) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (int number : numbers) {
                bw.write(Integer.toString(number));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
            return false;
        }

        return true;
    }
}