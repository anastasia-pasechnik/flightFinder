package edu.andrews.cptr252.anastasiap.finalproject;

import java.util.UUID;

public class Answer {

        /** Unique Id for the question*/
        private UUID mId;

        /** Title of bug */
        private String mAnswer;
        /**
         * Create and initialize a new Bug.
         */
        public Answer() {
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
            return mAnswer;
        }
        /**
         * Provide a new title for the Question.
         * @param answer New title
         */
        public void setTitle(String answer) {
            mAnswer= answer;
        
    }
}
