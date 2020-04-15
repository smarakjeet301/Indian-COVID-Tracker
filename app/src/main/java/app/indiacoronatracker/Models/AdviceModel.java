package app.indiacoronatracker.Models;

public class AdviceModel {

    private String question,answer;

    public AdviceModel(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public AdviceModel() {
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
