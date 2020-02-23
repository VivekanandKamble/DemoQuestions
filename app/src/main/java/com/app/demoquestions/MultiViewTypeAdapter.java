package com.app.demoquestions;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;
    private ArrayList<QuestionModel> questionModels;

    public MultiViewTypeAdapter(Context mContext, ArrayList<QuestionModel> questionModels)
    {
        this.mContext = mContext;
        this.questionModels = questionModels;
    }

    public static class TextTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_textViewQuestions;
        EditText editText_textViewAnswer;

        public TextTypeViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView_textViewQuestions=itemView.findViewById(R.id.textView_textViewQuestions);
            editText_textViewAnswer = itemView.findViewById(R.id.editText_textViewAnswer);

        }
    }

    public static class LongTextTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_long_textViewQuestions;
        EditText editText_long_textViewAnswer;

        public LongTextTypeViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView_long_textViewQuestions=itemView.findViewById(R.id.textView_long_textViewQuestions);
            editText_long_textViewAnswer = itemView.findViewById(R.id.editText_long_textViewAnswer);

        }
    }

    public static class RadioTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_radioQuestions;
        LinearLayout linearLayout_RadioButton;

        public RadioTypeViewHolder(@NonNull View itemView)
        {
            super(itemView);
            textView_radioQuestions=itemView.findViewById(R.id.textView_radioQuestions);
            linearLayout_RadioButton = itemView.findViewById(R.id.linearLayout_RadioButton);
        }
    }

    public static class RatingTypeViewHolder extends RecyclerView.ViewHolder
    {
        RatingBar ratingBar_Answer;
        TextView textView_ratingBarQuestions;

        public RatingTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            ratingBar_Answer=itemView.findViewById(R.id.ratingBar_Answer);
            textView_ratingBarQuestions=itemView.findViewById(R.id.textView_ratingBarQuestions);
        }
    }

    public static class CheckTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_checkBoxQuestions;
        LinearLayout linearLayout_CheckBox;

        public CheckTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_checkBoxQuestions=itemView.findViewById(R.id.textView_checkBoxQuestions);
            this.linearLayout_CheckBox = itemView.findViewById(R.id.linearLayout_CheckBox);
        }
    }

    public static class ImageTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_imageQuestions;
        ImageView imageView_ImageAnswer;

        public ImageTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_imageQuestions=itemView.findViewById(R.id.textView_imageQuestions);
            this.imageView_ImageAnswer = itemView.findViewById(R.id.imageView_ImageAnswer);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view;

        if (questionModels.get(i).getStr_que_type().matches("Check Box"))
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_check, viewGroup, false);
            return new CheckTypeViewHolder(view);
        }
//        else if (questionModels.get(i).getStr_que_type().matches("Check Box"))


        return null;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position)
    {
        QuestionModel questionModel = questionModels.get(position);

        if (questionModel!=null)
        {
            if (questionModels.get(position).getStr_que_type().matches("Check Box"))
            {
                ((CheckTypeViewHolder) viewHolder).textView_checkBoxQuestions.setText("Q : "+questionModels.get(position).str_gue_title);

                int count = questionModels.get(position).optionsModels.size();

                System.out.println("Options Array Count :- "+ count);

                for (int k=0;k<count;k++)
                {
                    final CheckBox checkBox = new CheckBox(mContext);
                    checkBox.setId(Integer.parseInt(questionModels.get(position).optionsModels.get(k).str_opt_id));
                    checkBox.setText(questionModels.get(position).optionsModels.get(k).str_opt_name);
                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(mContext, "ID is :- "+checkBox.getId(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    ((CheckTypeViewHolder) viewHolder).linearLayout_CheckBox.addView(checkBox);
                }
            }
            else if (questionModels.get(position).getStr_que_type().matches("Radio Button"))
            {
                ((RadioTypeViewHolder) viewHolder).textView_radioQuestions.setText("Q : "+questionModels.get(position).str_gue_title);
                RadioGroup radioGroup = new RadioGroup(mContext);
                radioGroup.setOrientation(RadioGroup.VERTICAL);
                int count = questionModels.get(position).optionsModels.size();
                for (int j=0;j<count;j++)
                {
                    final RadioButton radioButton = new RadioButton(mContext);
                    radioButton.setId(Integer.parseInt(questionModels.get(position).optionsModels.get(j).str_opt_id));
                    radioButton.setText(questionModels.get(position).optionsModels.get(j).str_opt_name);
                    radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                        @Override
                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                            Toast.makeText(mContext, "ID is :- "+radioButton.getId(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    radioGroup.addView(radioButton);
                }
                ((RadioTypeViewHolder) viewHolder).linearLayout_RadioButton.addView(radioGroup);
            }
            else if (questionModels.get(position).getStr_que_type().matches("Ratings"))
            {
                ((RatingTypeViewHolder) viewHolder).textView_ratingBarQuestions.setText("Q : "+questionModels.get(position).str_gue_title);
                final int count = questionModels.get(position).optionsModels.size();
                ((RatingTypeViewHolder) viewHolder).ratingBar_Answer.setNumStars(count);
                ((RatingTypeViewHolder) viewHolder).ratingBar_Answer.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
                    {
                        if (rating<count)
                        {
                            Toast.makeText(mContext, "ID is :- "+Integer.parseInt(questionModels.get(position).optionsModels.get(Math.round(rating)).str_opt_id +" And Values :- "+questionModels.get(position).optionsModels.get(Math.round(rating)).str_opt_name), Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
            else if (questionModels.get(position).getStr_que_type().matches("TextArea"))
            {
                ((LongTextTypeViewHolder) viewHolder).textView_long_textViewQuestions.setText("Q : "+questionModels.get(position).str_gue_title);
                String answer = ((LongTextTypeViewHolder) viewHolder).editText_long_textViewAnswer.getText().toString();
            }
            else if (questionModels.get(position).getStr_que_type().matches("Image"))
            {
                ((ImageTypeViewHolder) viewHolder).textView_imageQuestions.setText("Q : "+questionModels.get(position).str_gue_title);
                ((ImageTypeViewHolder) viewHolder).imageView_ImageAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "CLICK PHOTO", Toast.LENGTH_SHORT).show();
                    }
                });
            }

        }

    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }


}
