public class Student {
    String name;
    int rollNo;
    int mark1, mark2, mark3;

    Student(String name, int rollNo, int m1, int m2, int m3) {
        this.name = name;
        this.rollNo = rollNo;
        this.mark1 = m1;
        this.mark2 = m2;
        this.mark3 = m3;
    }

    int calculateTotal() {
        return mark1 + mark2 + mark3;
    }

    double calculateAverage() {
        return calculateTotal() / 3.0;
    }

    String calculateGrade() {
        double avg = calculateAverage();

        if (avg >= 90)
            return "A";
        else if (avg >= 75)
            return "B";
        else if (avg >= 50)
            return "C";
        else
            return "Fail";
    }

    void display() {
        System.out.println("\nName: " + name);
        System.out.println("Roll Number: " + rollNo);
        System.out.println("Total: " + calculateTotal());
        System.out.println("Average: " + calculateAverage());
        System.out.println("Grade: " + calculateGrade());
    }
}