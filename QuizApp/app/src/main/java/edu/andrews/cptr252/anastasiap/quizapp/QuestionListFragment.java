package edu.andrews.cptr252.anastasiap.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class QuestionListFragment extends Fragment {

    /** Tag for message log */
    private static final String TAG = "QuestionListFragment";

    /** Reference to list of bugs in display */
    private ArrayList<Question> mQuestions;

    public QuestionListFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.question_list_label);
        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();
        // for now, list bugs in log
        for (Question question: mQuestions){
            Log.d(TAG, question.getTitle());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_list, container, false);

        return v;

    }



}
