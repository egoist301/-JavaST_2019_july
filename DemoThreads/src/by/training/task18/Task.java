package by.training.task18;

public class Task {
    private String content;
    private String answer;
    private int mark;

    public Task(final String contentNew) {
        content = contentNew;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String contentNew) {
        content = contentNew;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(final String answerNew) {
        answer = answerNew;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(final int markNew) {
        mark = markNew;
    }
}
