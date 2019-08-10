package by.training.task18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class RunLearning {
    public static void main(String[] args) {
        final int NUMBER_TASKS_1 = 5;
        Student student1 = new Student(322801, NUMBER_TASKS_1);
        for (int i = 0; i < NUMBER_TASKS_1; ++i) {
            Task t = new Task("Task #" + i);
            student1.addTask(t);
        }
        final int NUMBER_TASKS_2 = 4;
        Student student2 = new Student(322924, NUMBER_TASKS_2);
        for (int i = 0; i < NUMBER_TASKS_2; ++i) {
            Task t = new Task("Task ##" + i);
            student2.addTask(t);
        }
        List<Student> lst = new ArrayList<>();
        lst.add(student1);
        lst.add(student2);
        Tutor tutor = new Tutor(lst);
        student1.start();
        student2.start();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
        tutor.start();
    }
}
