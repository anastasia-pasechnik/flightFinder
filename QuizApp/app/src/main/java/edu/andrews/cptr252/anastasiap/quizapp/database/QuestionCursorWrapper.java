package edu.andrews.cptr252.anastasiap.quizapp.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import edu.andrews.cptr252.anastasiap.quizapp.Question;

public class QuestionCursorWrapper extends CursorWrapper {

    public QuestionCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    /**
     * Extract question information from a query.
     * @return Question that resulted from the query.
     */
    public Question getQuestion() {
        String uuidString = getString(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.UUID));
        String title = getString(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.TITLE));
        String description = getString(getColumnIndex(QuestionDbSchema.QuestionTable.Cols.DESCRIPTION));

        Question question = new Question(UUID.fromString(uuidString));
        question.setTitle(title);
        question.setDescription(description);

        return question;
    }

}
