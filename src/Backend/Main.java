package Backend;

import java.util.Scanner;

public class Main {
    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        int capacity = getValidCapacity();
        StudentManager manager = new StudentManager(capacity);

        boolean UserSelect = true;
        while (UserSelect) {
            System.out.println("---------- Student Operation -----------");
            System.out.println("1. Add Student");
            System.out.println("2. Display Students");
            System.out.println("3. Search Student");
            System.out.println("4. Remove Student");
            System.out.println("5. Clear Students");
            System.out.println("6. Display Statistics");
            System.out.println("7. Search for a Grade / Frequency");
            System.out.println("8. Exit");
            System.out.print("Enter Selected Operation Value : ");

            int UserSelectList = input.nextInt();

            switch (UserSelectList) {
                case 1:
                    addStudent(manager);
                    break;
                case 2:
                    displayStudents(manager);
                    break;
                case 3:
                    SearchStudent(manager);
                    break;
                case 4:
                    RemoveStudent(manager);
                    break;
                case 5:
                    ClearStudents(manager);
                    break;
                case 6:
                    DisplayStatistics(manager);
                    break;
                case 7:
                    SearchForAGradeAndFrequency(manager);
                    break;
                case 8:
                    UserSelect = false;
                    System.out.println("Goodbye!  |^_^| ");
                    break;
                default:
                    System.out.println("Invalid Input |^_^|");
            }
        }
    }

    public static int getValidCapacity() {
        int capacity;

        do {
            System.out.print("Enter Student Capacity : ");
            capacity = input.nextInt();

            if (capacity <= 0) {
                System.out.println("Capacity must be greater than zero!");
            }

        } while (capacity <= 0);

        return capacity;
    }

    public static void addStudent(StudentManager manager){
        System.out.print("Enter Student ID : ");
        int id = input.nextInt();
        System.out.print("Enter Student Name : ");
        String name = input.next();
        System.out.print("Enter Student Grade : ");
        int grade = input.nextInt();

        if (manager.addStudent(id, name, grade)) {
            System.out.println("Student added successfully!");
        } else {
            System.out.println("Failed to add student. Check the ID, name, grade, or capacity.");
        }
    }

    public static void displayStudents(StudentManager manager){
        System.out.println("\n--- Students ---");
        manager.displayStudents();
    }
    public static void SearchStudent(StudentManager manager){
        System.out.print("Enter Student ID To Search : ");
        int searchId = input.nextInt();
        int index = manager.searchStudent(searchId);
        if (index != -1) {
            System.out.println("\n--- Student ---");
            System.out.println("Student : " + manager.studentNames[index]);
            System.out.println("ID : " + manager.studentIDs[index]);
            System.out.println("Grade : " + manager.grades[index]);
        } else {
            System.out.println("Student Not Found ...");
        }
    }
    public static void RemoveStudent(StudentManager manager){
        System.out.print("Enter Student ID To Delete : ");
        int deletedId = input.nextInt();
        int indexToDelete = manager.searchStudent(deletedId);
        if (indexToDelete == -1) {
            System.out.println("Student Not Found");
            return;
        }
        System.out.println("Confirm Remove Student  ");
        System.out.print("By Enter '1' To Delete And '0' To Cancel : ");
        int Confirm = input.nextInt();
        if (Confirm != 1) {
            System.out.println("Delete Cancelled");
            return;
        }
        manager.removeStudent(deletedId);
        System.out.println("Delete Students Succesfuly ...");
    }
    public static void ClearStudents(StudentManager manager){
        System.out.println("Confirm Remove Students   ");
        System.out.print("By Enter '1' To Delete And '0' To Cancel : ");
        int ConfirmAll = input.nextInt();
        if (ConfirmAll != 1) {
            System.out.println("Clear Cancelled");
            return;
        }
        manager.clearStudents();
        System.out.println("\n--- Clear Students Succesfuly---");
    }

    public static void DisplayStatistics(StudentManager manager) {
        System.out.println("\n--- Statistics ---");

        if (manager.IsEmpty()) {
            System.out.println("No Students Available");
            return;
        }

        System.out.println("Total: " + manager.getTotalStudents());
        System.out.println("Average: " + manager.calculateAverage());
        System.out.println("Highest: " + manager.getHighestGrade());
        System.out.println("Lowest: " + manager.getLowestGrade());

        System.out.println("\n--- Grades ---");
        System.out.println("Fail: " + manager.countFail());
        System.out.println("Pass: " + manager.countPass());
        System.out.println("Good: " + manager.countGood());
        System.out.println("Very Good: " + manager.countVeryGood());
        System.out.println("Excellent: " + manager.countExcellent());
    }
    public static void SearchForAGradeAndFrequency(StudentManager manager){
        System.out.println("Search for a Grade and Frequency");
        System.out.print("Enter Grade To Search : ");
        int gradeSearch = input.nextInt();

        System.out.println("Grade: " + gradeSearch + " | Is Found: " + manager.containsGrade(gradeSearch));

        System.out.println("Frequency: " + manager.getGradeFrequency(gradeSearch));
    }

}