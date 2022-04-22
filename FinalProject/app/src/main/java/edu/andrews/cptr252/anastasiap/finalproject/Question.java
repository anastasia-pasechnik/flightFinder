package edu.andrews.cptr252.anastasiap.finalproject;
import java.util.UUID;

public class Question {
    /** Unique Id for the question*/
    private UUID mId;

    /** Title of bug */
    private String mTerm;
    /**
     * Create and initialize a new Bug.
     */
    public Question() {
        // Generate unique identifier for the new bug
        mId = UUID.randomUUID();
    }
    /**
     * Return unique id for Question.
     * @return Question Id
     */
    public UUID getId() {
        return mId;
    }
    /**
     * Return the title for the bug.
     * @return Title of Question
     */
    public String getTitle() {
        return mTerm;
    }
    /**
     * Provide a new title for the Question.
     * @param term New title
     */
    public void setTitle(String term) {
        mTerm = term;
    }
}




