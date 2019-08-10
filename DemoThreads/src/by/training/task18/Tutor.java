package by.training.task18;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tutor extends Thread {
    private Integer idTutor;
    private List<Student> list;

    public Tutor() {
        list = new ArrayList<>();
    }

    public Tutor(List<Student> listNew) {
        list = listNew;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(final Integer idTutorNew) {
        idTutor = idTutorNew;
    }

    @Override
    public void run() {
        for (Student st : list) {
            List<Task> tasks = st.getTaskList();
            for (Task current : tasks) {
                int mark = 3 + new Random().nextInt(7);
                current.setMark(mark);
                System.out.println(mark + " for student N " + st.getIdStudent());
                st.getCountDown().countDown();
            }
            System.out.println("All estimates made for " + st.getIdStudent());
        }
    }
}
