package edu.andrews.cptr252.anastasiap.quizapp.database;

public class QuestionDbSchema {

    public static final class QuestionTable {
        public static final String NAME = "questions";
        /**
         * DB column names.
         */
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String DESCRIPTION = "description";
        }
    }


}
