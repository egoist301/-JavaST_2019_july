package by.training.task18;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class Student extends Thread {
    private Integer idStudent;
    private List<Task> taskList;
    private CountDownLatch countDown;

    public Student(Integer idStudentNew, int numberTasks) {
        idStudent = idStudentNew;
        countDown = new CountDownLatch(numberTasks);
        taskList = new ArrayList<>(numberTasks);
    }

    public Integer getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(final Integer idStudentNew) {
        idStudent = idStudentNew;
    }

    public List<Task> getTaskList() {
        return taskList;
    }

    public CountDownLatch getCountDown() {
        return countDown;
    }

    public void addTask(Task taskNew) {
        taskList.add(taskNew);
    }

    public void run() {
        int i = 0;
        for (Task inWork : taskList) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException eNew) {
                eNew.printStackTrace();
            }
            inWork.setAnswer("Answer #" + ++i);
            System.out.println("Answer #" + i + " from " + idStudent);
        }
        try {
            countDown.await();
        } catch (InterruptedException eNew) {
            eNew.printStackTrace();
        }
        float averageMark = 0;
        for (Task inWork : taskList){
            averageMark +=inWork.getMark();
        }
        averageMark /= taskList.size();
        System.out.println("Student " + idStudent + ": Average mark = " + averageMark);
    }
}
