import java.util.Scanner;

public class GradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numSubjects = Integer.parseInt(scanner.nextLine());

        int totalMarks = 0;
        for (int i = 1; i <= numSubjects; i++) {
            int marks;
            do {
                System.out.print("Enter marks obtained in subject " + i + ": ");
                marks = Integer.parseInt(scanner.nextLine());
                if (marks < 0 || marks > 100) {
                    System.out.println("Marks should be between 0 and 100.");
                }
            } while (marks < 0 || marks > 100);

            totalMarks += marks;
        }

        double averagePercentage = (double) totalMarks / numSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else if (averagePercentage >= 50) {
            grade = 'E';
        } else {
            grade = 'F';
        }

        System.out.println("\nResults:");
        System.out.println("Total Marks: " + totalMarks);
        System.out.println("Average Percentage: " + averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
