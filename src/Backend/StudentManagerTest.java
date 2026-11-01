package Backend;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class StudentManagerTest {

    @Test
    void shouldAddValidStudent() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, "Ahmad", 90);

        assertEquals(1, manager.getTotalStudents());
        assertEquals(0, manager.searchStudent(101));
        assertEquals(1, manager.getGradeFrequency(90));
    }

    @Test
    void shouldNotAddStudentWithMissingName() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, "", 90);

        assertEquals(0, manager.getTotalStudents());
    }

    @Test
    void shouldNotAddStudentWithNullName() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, null, 90);

        assertEquals(0, manager.getTotalStudents());
    }

    @Test
    void shouldNotAddStudentWithDuplicateId() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(101, "Ali", 80);

        assertEquals(1, manager.getTotalStudents());
        assertEquals(0, manager.searchStudent(101));
        assertEquals(1, manager.getGradeFrequency(90));
        assertEquals(0, manager.getGradeFrequency(80));
    }

    @Test
    void shouldNotAddGradeBelowZero() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, "Ahmad", -1);

        assertEquals(0, manager.getTotalStudents());
    }

    @Test
    void shouldNotAddGradeAboveOneHundred() {
        StudentManager manager = new StudentManager(3);

        manager.addStudent(101, "Ahmad", 101);

        assertEquals(0, manager.getTotalStudents());
    }

    @Test
    void shouldFindExistingStudent() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);

        assertEquals(0, manager.searchStudent(101));
    }

    @Test
    void shouldReturnMinusOneForNonExistentStudent() {
        StudentManager manager = new StudentManager(3);

        assertEquals(-1, manager.searchStudent(999));
    }

    @Test
    void shouldRemoveExistingStudentAndItsGradeFromBag() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(102, "Ali", 80);

        manager.removeStudent(101);

        assertEquals(1, manager.getTotalStudents());
        assertEquals(-1, manager.searchStudent(101));
        assertEquals(0, manager.searchStudent(102));
        assertEquals(0, manager.getGradeFrequency(90));
        assertEquals(1, manager.getGradeFrequency(80));
    }
    @Test
    void shouldKeepStudentsUnchangedWhenRemovingNonexistentStudent() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);

        manager.removeStudent(999);

        assertEquals(1, manager.getTotalStudents());
        assertEquals(0, manager.searchStudent(101));
        assertEquals(1, manager.getGradeFrequency(90));
    }

    @Test
    void shouldCountDuplicateGrades() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(102, "Ali", 90);
        manager.addStudent(103, "Omar", 80);

        assertTrue(manager.containsGrade(90));
        assertEquals(2, manager.getGradeFrequency(90));
    }

    @Test
    void shouldReturnFalseAndZeroFrequencyWhenBagIsEmpty() {
        StudentManager manager = new StudentManager(3);

        assertFalse(manager.containsGrade(90));
        assertEquals(0, manager.getGradeFrequency(90));
    }

    @Test
    void shouldEmptyBagAfterClearingStudents() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(102, "Ali", 90);

        manager.clearStudents();

        assertEquals(0, manager.getTotalStudents());
        assertFalse(manager.containsGrade(90));
        assertEquals(0, manager.getGradeFrequency(90));
    }

    @Test
    void shouldCalculateAverageHighestAndLowestGrades() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(102, "Ali", 60);
        manager.addStudent(103, "Omar", 80);

        assertEquals(3, manager.getTotalStudents());
        assertEquals(230.0 / 3, manager.calculateAverage(), 0.001);
        assertEquals(90, manager.getHighestGrade());
        assertEquals(60, manager.getLowestGrade());
    }

    @Test
    void shouldCountStudentsInEachGradeCategory() {
        StudentManager manager = new StudentManager(5);
        manager.addStudent(101, "Fail", 59);
        manager.addStudent(102, "Pass", 60);
        manager.addStudent(103, "Good", 70);
        manager.addStudent(104, "VeryGood", 80);
        manager.addStudent(105, "Excellent", 90);

        assertEquals(1, manager.countFail());
        assertEquals(1, manager.countPass());
        assertEquals(1, manager.countGood());
        assertEquals(1, manager.countVeryGood());
        assertEquals(1, manager.countExcellent());
    }

    @Test
    void shouldUpdateStatisticsAndCategoriesAfterRemovingStudent() {
        StudentManager manager = new StudentManager(3);
        manager.addStudent(101, "Ahmad", 90);
        manager.addStudent(102, "Ali", 60);
        manager.addStudent(103, "Omar", 80);

        manager.removeStudent(101);

        assertEquals(2, manager.getTotalStudents());
        assertEquals(70.0, manager.calculateAverage(), 0.001);
        assertEquals(80, manager.getHighestGrade());
        assertEquals(60, manager.getLowestGrade());
        assertEquals(0, manager.countExcellent());
        assertEquals(1, manager.countPass());
        assertEquals(1, manager.countVeryGood());
    }
}
