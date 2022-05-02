package edu.andrews.cptr252.anastasiap.quizapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionDetailsFragment extends Fragment {


    /** key used to pass the id of a bug */
    public static final String EXTRA_QUESTION_ID
            = "edu.andrews.cptr252.rlsummerscales.bugtracker.bug_id";

    /** Tag for logging fragment messages */
    public static final String TAG = "QuestionDetailsFragment";


    /** Reference to question answer field */
    private EditText mAnswerField;




    /** Question that is being viewed/edited */
    private Question mQuestion;
    /** Reference to title field for question*/
    private EditText mTitleField;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuestionDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * A new instance of fragment QuestionDetailsFragment.
     */

    // TODO: Rename and change types and number of parameters

    public static QuestionDetailsFragment newInstance(UUID questionId) {


        QuestionDetailsFragment fragment = new QuestionDetailsFragment();

        Bundle args = new Bundle();

        args.putSerializable(EXTRA_QUESTION_ID, questionId);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Extract question id from Bundle
        UUID questionId = (UUID)getArguments().getSerializable(EXTRA_QUESTION_ID);

        // Get the question with the id from the Bundle.
        // This will be the question that the fragment displays.
        mQuestion = QuestionList.getInstance(getActivity()).getQuestion(questionId);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_details, container, false);

        // get reference to EditText box for bug title
        mTitleField = v.findViewById(R.id.question_hint);
        mTitleField.setText(mQuestion.getTitle());
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // user typed text, update the question title
                mQuestion.setTitle(s.toString());
                // write the new title to the message log for debugging
                Log.d(TAG, "Question title changed to " + mQuestion.getTitle());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // left intentionally blank
            }

        });


        // get reference to EditText box for bug description
        mAnswerField = v.findViewById(R.id.answer_hint);
        mAnswerField.setText(mQuestion.getDescription());
        mAnswerField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // left intentionally blank
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                mQuestion.setDescription(s.toString());
                Log.d(TAG, "Change answer to "+ s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // left intentionally blank
            }
        });


        return v;

    }
}