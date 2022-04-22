package edu.andrews.cptr252.anastasiap.finalproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuestionDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuestionDetailsFragment extends Fragment {

    /** Tag for logging fragment messages */
    public static final String TAG = "BugDetailsFragment";
    /** Bug that is being viewed/edited */
    private Question mQuestion;
    /** Reference to title field for bug*/
    private EditText mTitleField;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuestionDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuestionDetailsFragment newInstance(String param1, String param2) {
        QuestionDetailsFragment fragment = new QuestionDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        mQuestion = new Question(); // create new bug

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_question_details, container, false);


        // get reference to EditText box for bug title
        mTitleField = v.findViewById(R.id.question_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                // left intentionally blank
            }

            @Override
            public void onTextChanged(CharSequence s,int start,int before,int count) {
                // user typed text, update the bug title
                mQuestion.setTitle(s.toString());
                // write the new title to the message log for debugging
                Log.d(TAG, "Title changed to " + mQuestion.getTitle());
            }
            @Override
            public void afterTextChanged(Editable s) {
                // left intentionally blank
            }

        });
        return v;


    }
}