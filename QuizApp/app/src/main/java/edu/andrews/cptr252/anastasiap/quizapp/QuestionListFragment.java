package edu.andrews.cptr252.anastasiap.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.ItemTouchHelper;
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

    /** Reference to list of questions in display */
    private ArrayList<Question> mQuestions;

    public QuestionListFragment() {
        // Required empty public constructor
    }



    /** Create a new question, add it to the list and launch question editor. */
    private void addQuestion() {
        // create new question
        Question question = new Question();
        // add question to the list
        QuestionList.getInstance(getActivity()).addQuestion(question);
        // create an intent to send to QuestionDetailsActivity
        // add the question Id as an extra so QuestionDetailsFragment can edit it.
        Intent intent = new Intent(getActivity(), QuestionDetailsActivity.class);
        intent.putExtra(QuestionDetailsFragment.EXTRA_QUESTION_ID, question.getId());
        // launch QuestionDetailsActivity which will launch QuestionDetailsFragment
        startActivityForResult(intent, 0);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_question_list, menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_add_question:
                // new question icon clicked
                addQuestion();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);


        getActivity().setTitle(R.string.question_list_label);

        mQuestions = QuestionList.getInstance(getActivity()).getQuestions();

        mQuestionAdapter = new QuestionAdapter(mQuestions, getActivity());

        // for now, list questions in log
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


        // Create and attach our new touch helper for question swipes
        QuestionSwiper questionSwiper = new QuestionSwiper(mQuestionAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(questionSwiper);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);


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
