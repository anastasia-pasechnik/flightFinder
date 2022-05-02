package edu.andrews.cptr252.anastasiap.quizapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionListFragment extends Fragment {

    /** RecyclerView that displays list of questions */
    private RecyclerView mRecyclerView;
    /** Adapter that generates/reuses views to display questions */
    private QuestionAdapter mQuestionAdapter;

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

        // use our custom bug adapter for generating views for each bug
        mQuestionAdapter = new QuestionAdapter(mQuestions);

        // for now, list bugs in log
        for (Question question: mQuestions){
            Log.d(TAG, question.getTitle());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_question_list, container, false);

        mRecyclerView = v.findViewById(R.id.question_list_recyclerView);
        // RecyclerView will use the QuestionAdapter to create views for questions
        mRecyclerView.setAdapter(mQuestionAdapter);
        // Use a linear layout when displaying questions
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));



        return v;

    }


    /**
     * Question list fragment was paused (user was most likely editing a question).
     * Notify the adapter that the data set (Question list) may have changed.
     * The adapter should update the views.
     */
    @Override
    public void onResume() {
        super.onResume(); // first execute parent's onResume method
        mQuestionAdapter.notifyDataSetChanged();
    }

}
