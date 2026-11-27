import java.util.*;
import java.io.*;

public class GradeManager {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Student> students = new ArrayList<>();

    static void addStudent() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.println("Roll Number Already Exists!");
                return;
            }
        }

        System.out.print("Enter Mark 1: ");
        int m1 = sc.nextInt();

        System.out.print("Enter Mark 2: ");
        int m2 = sc.nextInt();

        System.out.print("Enter Mark 3: ");
        int m3 = sc.nextInt();
        sc.nextLine();

        if (m1 < 0 || m1 > 100 || m2 < 0 || m2 > 100 ||
            m3 < 0 || m3 > 100) {
            System.out.println("Marks must be between 0 and 100!");
            return;
        }

        Student s = new Student(name, roll, m1, m2, m3);
        students.add(s);

        saveStudents();

        System.out.println("Student Added Successfully!");
    }

    static void viewStudents() {

        if (students.isEmpty()) {
            System.out.println("No Student Records Found!");
            return;
        }

        System.out.println("\n--- ALL STUDENT RECORDS ---");

        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {

        System.out.print("Enter Roll Number: ");
        int roll = sc.nextInt();
        sc.nextLine();

        for (Student s : students) {
            if (s.rollNo == roll) {
                System.out.println("\nStudent Found!");
                s.display();
                return;
            }
        }

        System.out.println("Student Not Found!");
    }

    static void saveStudents() {

        try (PrintWriter writer =
             new PrintWriter(new FileWriter("students.txt"))) {

            for (Student s : students) {
                writer.println(
                    s.name + "|" + s.rollNo + "|" +
                    s.mark1 + "|" + s.mark2 + "|" + s.mark3
                );
            }

        } catch (IOException e) {
            System.out.println("Error Saving Records!");
        }
    }

    static void loadStudents() {

        File file = new File("students.txt");

        if (!file.exists()) {
            return;
        }

        try (Scanner reader = new Scanner(file)) {

            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("\\|", -1);

                if (data.length == 5) {
                    try {
                        Student s = new Student(
                            data[0],
                            Integer.parseInt(data[1]),
                            Integer.parseInt(data[2]),
                            Integer.parseInt(data[3]),
                            Integer.parseInt(data[4])
                        );

                        students.add(s);

                    } catch (NumberFormatException e) {
                        System.out.println("Invalid record skipped.");
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("Error Loading Records!");
        }
    }

    public static void main(String[] args) {

        loadStudents();

        int choice;

        do {
            System.out.println("\n===== STUDENT GRADE MANAGEMENT =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. View Saved Records");
            System.out.println("5. Exit");

            System.out.print("Enter Your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    searchStudent();
                    break;

                case 4:
                    viewStudents();
                    break;

                case 5:
                    saveStudents();
                    System.out.println("Thank You!");
                    break;

                default:
                    System.out.println("Invalid Choice!");
            }

        } while (choice != 5);

        sc.close();
    }
}