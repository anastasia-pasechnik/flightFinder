package edu.andrews.cptr252.anastasiap.quizapp;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder>{

    private static final String TAG="QuestionAdapter";
    /** Used to store reference to list of questions*/
    private ArrayList<Question> mQuestions;
    /**
     * Constructor for BugAdapter. Initialize adapter with given list of questions.
     * @param questions list of questions to display.
     */
    public QuestionAdapter(ArrayList<Question> questions) {
        mQuestions = questions;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /** Context hosting the view */
        public Context mContext;

        /** TextView that displays question title */
        public TextView questionTitleTextView;
        /** TextView that displays question answer */
        public TextView questionAnswerTextView;


        /** Create a new view holder for a given view item in the bug list */
        public ViewHolder(View itemView) {
            super(itemView);
            // Store references to the widgets on the view item
            questionTitleTextView = itemView.findViewById(R.id.question_list_item_questionTextView);

            questionAnswerTextView = itemView.findViewById(R.id.question_list_item_answerTextView);

            // Get the context of the view. This will be the activity hosting the view.
            mContext = itemView.getContext();

            itemView.setOnClickListener(this);
        }

        /**
         * OnClick listener for question in the question list.
         * Triggered when user clicks on a question in the list
         * @param /is view for question that was clicked
         */

        @Override
        public void onClick(View view) {
            // Get index of question that was clicked.
            int position = getAdapterPosition();
            // For now, just display the question title.
            // In the future, open the selected question.
            if (position != RecyclerView.NO_POSITION) {
                Question question = mQuestions.get(position);

                // start an instance of QuestionDetailsFragment
                Intent i = new Intent(mContext, QuestionDetailsActivity.class);
                // pass the id of the question as an intent
                i.putExtra(QuestionDetailsFragment.EXTRA_QUESTION_ID, question.getId());
                mContext.startActivity(i);
            }
        }
    } // end ViewHolder


    /**
     * Create a new view to display a question.
     * Return the ViewHolder that stores references to the widgets on the new view.
     */
    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the layout inflater from parent that contains the question view item
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the layout to display a question in the list
        View bugView = inflater.inflate(R.layout.list_item_question, parent, false);
        // Create a view holder to store references to the widgets on the new view.
        ViewHolder viewHolder = new ViewHolder(bugView);

        return viewHolder;
    }

    /**
     * Display given bug in the view referenced by the given ViewHolder.
     * @param viewHolder Contains references to widgets used to display bug.
     * @param position Index of the bug in the list
     */

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder viewHolder, int position) {
        // Get question for given index in question list
        Question question = mQuestions.get(position);
        // Get references to widgets stored in the ViewHolder
        TextView questionTitleTextView = viewHolder.questionTitleTextView;

        TextView questionAnswerTextView = viewHolder.questionAnswerTextView;

        // Update widgets on view with question details
        questionTitleTextView.setText(question.getTitle());

        questionAnswerTextView.setText(question.getDescription());

    }

    /**
     * Get number of questions in list.
     */
    @Override
    public int getItemCount() {
        return mQuestions.size();
    }


} // end of QuestionAdapter class
