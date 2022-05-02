package edu.andrews.cptr252.anastasiap.quizapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.UUID;

public class QuestionList {


    public void addQuestion(Question question) {

        mQuestions.add(question);
        saveQuestions();
    }

    /** Instance variable for QuestionList **/
    private static QuestionList sOurInstance;

    /** List of Questions **/
    private ArrayList<Question> mQuestions;


    /** Tag for message log */
    private static final String TAG = "QuestionList";
    /** name of JSON file containing list of questions */
    private static final String FILENAME = "questions.json";
    /** Reference to JSON serializer for a list of questions */
    private QuestionJSONSerializer mSerializer;


    /**
     * Write question list to JSON file.
     * @return true if successful, false otherwise.
     */
    public boolean saveQuestions() {
        try {
            mSerializer.saveQuestions(mQuestions);
            Log.d(TAG, "Questions saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving questions: " + e);
            return false;
        }
    }



    /** Reference to information about app environment */
    private Context mAppContext;
    /** Private constructor. */
    private QuestionList(Context appContext) {

        mAppContext = appContext;

        // create our serializer to load and save questions
        mSerializer = new QuestionJSONSerializer(mAppContext, FILENAME);
        try {
            // load questions from JSON file
            mQuestions = mSerializer.loadQuestions();
        } catch (Exception e) {
            // Unable to load from file, so create empty list.
            // Either file does not exist (okay)
            // Or file contains error (not great)
            mQuestions = new ArrayList<>();
            Log.e(TAG, "Error loading questions: " + e);
        }

    }


    /**
     * Return one and only instance of the question list.
     * (If it does not exist, create it).
     * @param c is the Application context
     * @return Reference to the question list
     */
    public static QuestionList getInstance(Context c) {
        if (sOurInstance == null){
            sOurInstance = new QuestionList(c.getApplicationContext());
        }
        return sOurInstance;
    }
    /**
     * Return list of questions.
     * @return Array of Question objects.
     */
    public ArrayList<Question> getQuestions() { return mQuestions; }


    public Question getQuestion (UUID id) {
        for (Question question : mQuestions) {
            if (question.getId().equals(id))
                return question;
        }
        return null;
    }


    /**
     * Add a question to the list at given position.
     * @param position is the index for the question to add.
     * @param question is the question to add.
     */
    public void addQuestion(int position, Question question) {
        mQuestions.add(position, question);
        saveQuestions();
    }
    /**
     * Delete a given question from list of questions.
     * @param position is the index of question to delete.
     */
    public void deleteQuestion(int position) {
        mQuestions.remove(position);
        saveQuestions();
    }



}
