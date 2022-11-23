package Server;

import java.io.Serializable;

public class Question implements Serializable {
    String question;
    String wrongAlternative1;
    String wrongAlternative2;
    String wrongAlternative3;
    String rightAlternative;
    String questionType;

    public Question(String question, String wrongAlternative1, String wrongAlternative2,
                    String wrongAlternative3, String rightAlternative, String questionType) {
        this.question = question;
        this.wrongAlternative1 = wrongAlternative1;
        this.wrongAlternative2 = wrongAlternative2;
        this.wrongAlternative3 = wrongAlternative3;
        this.rightAlternative = rightAlternative;
        this.questionType = questionType;
    }

    public String getQuestion() {
        return question;
    }

    public String getWrongAlternative1() {
        return wrongAlternative1;
    }

    public String getWrongAlternative2() {
        return wrongAlternative2;
    }

    public String getWrongAlternative3() {
        return wrongAlternative3;
    }

    public String getRightAlternative() {
        return rightAlternative;
    }

    public String getQuestionType() {
        return questionType;
    }
    public static Question getTestQuestion(){
        Question testQuestion = new Question("Hur många ben har en myra?", "2",
                "4", "8", "6", "Natur");
        return testQuestion;
    }
}