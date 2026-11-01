package Backend;

public class StudentManager {

    private static final int MIN_STUDENT_ID = 1;
    private static final int MIN_GRADE = 0;
    private static final int MAX_GRADE = 100;
    private static final int PASS_GRADE = 60;
    private static final int GOOD_GRADE = 70;
    private static final int VERY_GOOD_GRADE = 80;
    private static final int EXCELLENT_GRADE = 90;

    private static final int NOT_FOUND = -1;

    int[] studentIDs;
    String[] studentNames;
    int[] grades;
    int size;
    ArrayBag<Integer> gradesBag;

    public StudentManager(int capacity) {
        studentIDs = new int[capacity];
        studentNames = new String[capacity];
        grades = new int[capacity];
        size = 0;
        gradesBag = new ArrayBag<Integer>(capacity);
    }

    public boolean IsFull() {
        return size == studentIDs.length;
    }

    public boolean IsEmpty() {
        return size == 0;
    }

    public boolean isValidGrade(int grade) {
        return grade >= MIN_GRADE && grade <= MAX_GRADE;
    }

    public boolean isValidStudentID(int id) {
        if (id < MIN_STUDENT_ID) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (studentIDs[i] == id) {
                return false;
            }
        }

        return true;
    }

    public boolean isValidName(String name) {
        return name != null && !name.isBlank();
    }

    public boolean addStudent(int id, String name, int grade) {
        if (IsFull()) {
            return false;
        }

        if (isValidName(name) && isValidGrade(grade) && isValidStudentID(id)) {

            studentIDs[size] = id;
            studentNames[size] = name;
            grades[size] = grade;
            gradesBag.Add(grade);
            size++;
            return true;
        }
        return false;
    }

    public int searchStudent(int id) {
        for (int i = 0; i < size; i++) {
            if (studentIDs[i] == id) {
                return i;
            }
        }
        return NOT_FOUND;
    }

    public void removeStudent(int id) {
        int deleteId = searchStudent(id);

        if (deleteId == NOT_FOUND) {
            return;
        }

        gradesBag.remove(grades[deleteId]);

        for (int i = deleteId; i < size - 1; i++) {
            studentIDs[i] = studentIDs[i + 1];
            studentNames[i] = studentNames[i + 1];
            grades[i] = grades[i + 1];
        }

        size--;

        studentIDs[size] = 0;
        studentNames[size] = null;
        grades[size] = 0;
    }

    public void displayStudents() {
        if (IsEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.println("\n--- Student ---");
            System.out.println("Student : " + studentNames[i]);
            System.out.println("ID : " + studentIDs[i]);
            System.out.println("Grade : " + grades[i]);
        }
    }

    public void clearStudents() {
        if (IsEmpty()) {
            return;
        }

        for (int i = 0; i < size; i++) {
            gradesBag.remove(grades[i]);

            studentIDs[i] = 0;
            studentNames[i] = null;
            grades[i] = 0;
        }

        size = 0;
    }

    public int getTotalStudents() {
        return size;
    }

    public double calculateAverage() {
        if (IsEmpty()) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < size; i++) {
            sum += grades[i];
        }

        return (double) sum / size;
    }

    public int getHighestGrade() {
        if (IsEmpty()) {
            return NOT_FOUND;
        }

        int highGrade = grades[0];

        for (int i = 1; i < size; i++) {
            if (highGrade < grades[i]) {
                highGrade = grades[i];
            }
        }

        return highGrade;
    }

    public int getLowestGrade() {
        if (IsEmpty()) {
            return NOT_FOUND;
        }

        int lowestGrade = grades[0];

        for (int i = 1; i < size; i++) {
            if (lowestGrade > grades[i]) {
                lowestGrade = grades[i];
            }
        }

        return lowestGrade;
    }

    public int countFail() {
        int fail = 0;

        for (int i = 0; i < size; i++) {
            if (grades[i] < PASS_GRADE) {
                fail++;
            }
        }

        return fail;
    }

    public int countPass() {
        int pass = 0;

        for (int i = 0; i < size; i++) {
            if (grades[i] >= PASS_GRADE && grades[i] < GOOD_GRADE) {
                pass++;
            }
        }

        return pass;
    }

    public int countGood() {
        int good = 0;

        for (int i = 0; i < size; i++) {
            if (grades[i] >= GOOD_GRADE && grades[i] < VERY_GOOD_GRADE) {
                good++;
            }
        }

        return good;
    }

    public int countVeryGood() {
        int veryGood = 0;

        for (int i = 0; i < size; i++) {
            if (grades[i] >= VERY_GOOD_GRADE && grades[i] < EXCELLENT_GRADE) {
                veryGood++;
            }
        }

        return veryGood;
    }

    public int countExcellent() {
        int excellent = 0;

        for (int i = 0; i < size; i++) {
            if (grades[i] >= EXCELLENT_GRADE && grades[i] <= MAX_GRADE) {
                excellent++;
            }
        }

        return excellent;
    }

    public int getGradeFrequency(int grade) {
        return gradesBag.frequencyOf(grade);
    }

    public boolean containsGrade(int grade) {
        return gradesBag.Contins(grade);
    }
}
