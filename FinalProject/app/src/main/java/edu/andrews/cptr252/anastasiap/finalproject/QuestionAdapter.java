package edu.andrews.cptr252.anastasiap.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class QuestionAdapter extends RecyclerView.Adapter<QuestionAdapter.ViewHolder> {
    private static final String TAG = "QuestionAdapter";

    private ArrayList<Question> mQuestionArrayList;

    public QuestionAdapter(ArrayList<Question> questionArrayList) {
        mQuestionArrayList = questionArrayList;

    }


    /**
     * Class to hold references to widgets on a given view.
     */
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * TextView that displays bug title
         */
        public TextView QuestionTextView;

        public TextView AnswerTextView;

        /**
         * Create a new view holder for a given view item in the bug list
         */
        public ViewHolder(View itemView) {
            super(itemView);
            // Store references to the widgets on the view item
            QuestionTextView = itemView.findViewById(R.id.question_list_item_QuestionTextView);
            AnswerTextView = itemView.findViewById(R.id.question_list_item_QuestionTextView);

        }

        @Override
        public void onClick(View v) {
            // Get index of question that was clicked.
            int position = getAdapterPosition();

            if (position != RecyclerView.NO_POSITION) {
                Question question = mQuestionArrayList.get(position);
                Log.d(TAG, question.getTitle() + " was clicked");
            }
        }

    } // end ViewHolder


    @Override
    public QuestionAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the layout inflater from parent that contains the question view item
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // Inflate the layout to display a question in the list
        View questionView = inflater.inflate(R.layout.list_item_question, parent, false);
        // Create a view holder to store references to the widgets on the new view.
        ViewHolder viewHolder = new ViewHolder(questionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionAdapter.ViewHolder viewHolder, int position) {
        // Get bug for given index
        Question question = mQuestionArrayList.get(position);
        // Get references to widgets stored in the ViewHolder
        TextView QuestionTextView = viewHolder.QuestionTextView;
        TextView AnswerTextView = viewHolder.AnswerTextView;

        // Update widgets on view
        QuestionTextView.setText(question.getTitle());
        AnswerTextView.setText(question.getDescription().toString());

    }
    /**
     * Get number of questions in bug list.
     */
    @Override
    public int getItemCount() {
        return mQuestionArrayList.size();
    }
} //end of QuestionAdapter class
