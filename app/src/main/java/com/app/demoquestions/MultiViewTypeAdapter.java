package com.app.demoquestions;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.AppCompatRatingBar;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MultiViewTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    Context mContext;
    private ArrayList<QuestionModel> questionModels;
    JSONObject jsonObjectQASendData;
    HashMap<String,JSONObject> stringJSONObjectHashMap = new HashMap<String, JSONObject>();

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
        AppCompatRatingBar ratingBar_Answer;
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

    public static class DropdownTypeViewHolder extends RecyclerView.ViewHolder
    {
        TextView textView_dropdownQuestions;
        Spinner spinner_Answer;

        public DropdownTypeViewHolder(@NonNull View itemView) {
            super(itemView);

            this.textView_dropdownQuestions=itemView.findViewById(R.id.textView_dropdownQuestions);
            this.spinner_Answer = itemView.findViewById(R.id.spinner_Answer);
        }
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
    {
        View view;

        if (i==0)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_check, viewGroup, false);
            return new CheckTypeViewHolder(view);
        }
        else if (i==1)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_radio, viewGroup, false);
            return new RadioTypeViewHolder(view);
        }
        else if (i==2)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_rating, viewGroup, false);
            return new RatingTypeViewHolder(view);
        }
        else if (i==3)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_long_textview, viewGroup, false);
            return new LongTextTypeViewHolder(view);
        }
        else if (i==4)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_image, viewGroup, false);
            return new ImageTypeViewHolder(view);
        }
        else if (i==5)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_textview, viewGroup, false);
            return new TextTypeViewHolder(view);
        }
        else if (i==6)
        {
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_dropdown, viewGroup, false);
            return new DropdownTypeViewHolder(view);
        }


        return null;
    }

    @Override
    public int getItemViewType(int position)
    {
        if (questionModels.get(position).getStr_que_type().matches("Check Box"))
        {
            return 0;
        }
        else if (questionModels.get(position).getStr_que_type().matches("Radio Button"))
        {
            return 1;
        }
        else if (questionModels.get(position).getStr_que_type().matches("Ratings"))
        {
            return 2;
        }
        else if (questionModels.get(position).getStr_que_type().matches("TextArea"))
        {
            return 3;
        }
        else if (questionModels.get(position).getStr_que_type().matches("Image"))
        {
            return 4;
        }
        else if (questionModels.get(position).getStr_que_type().matches("Textbox"))
        {
            return 5;
        }
        else if (questionModels.get(position).getStr_que_type().matches("Dropdown"))
        {
            return 6;
        }

        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int position)
    {
        QuestionModel questionModel = questionModels.get(position);

        if (questionModel!=null)
        {
            if (viewHolder instanceof CheckTypeViewHolder)
            {
                ((CheckTypeViewHolder) viewHolder).textView_checkBoxQuestions.setText(questionModels.get(position).str_gue_title);

                int count = questionModels.get(position).optionsModels.size();

                System.out.println("Options Array Count :- "+ count);

                for (int k=0;k<count;k++)
                {
                    final CheckBox checkBox = new CheckBox(mContext);
                    checkBox.setId(Integer.parseInt(questionModels.get(position).optionsModels.get(k).str_opt_id));
                    checkBox.setText(questionModels.get(position).optionsModels.get(k).str_opt_name);
                    final int finalK = k;
                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v)
                        {
                            if (checkBox.isChecked())
                            {

                                Toast.makeText(mContext, "ID is :- "+checkBox.getId(), Toast.LENGTH_SHORT).show();
                                try {

                                    jsonObjectQASendData = new JSONObject();
                                    jsonObjectQASendData.put("question_id",questionModels.get(position).str_que_id);
                                    jsonObjectQASendData.put("question_answer",checkBox.getId());
                                    jsonObjectQASendData.put("question_type",questionModels.get(position).str_que_type);
                                    jsonObjectQASendData.put("question_feedback_id",questionModels.get(position).str_que_fk_id);
                                    stringJSONObjectHashMap.put("\""+questionModels.get(position).str_que_id+"_"+checkBox.getId()+"\"",jsonObjectQASendData);

                                    Intent intent = new Intent("custom_message");
                                    intent.putExtra("question_answer", String.valueOf(stringJSONObjectHashMap));
                                    LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                            else
                            {
                                stringJSONObjectHashMap.remove("\""+questionModels.get(position).str_que_id+"_"+checkBox.getId()+"\"");
                                Intent intent = new Intent("custom_message");
                                intent.putExtra("question_answer", String.valueOf(stringJSONObjectHashMap));
                                LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            }


                        }
                    });

                    ((CheckTypeViewHolder) viewHolder).linearLayout_CheckBox.addView(checkBox);
                }

            }
            else if (viewHolder instanceof RadioTypeViewHolder)
            {

                ((RadioTypeViewHolder) viewHolder).textView_radioQuestions.setText(questionModels.get(position).str_gue_title);
                RadioGroup radioGroup = new RadioGroup(mContext);
                radioGroup.setOrientation(RadioGroup.VERTICAL);
                int count = questionModels.get(position).optionsModels.size();
                for (int j=0;j<count;j++)
                {
                    final RadioButton radioButton = new RadioButton(mContext);
                    radioButton.setId(Integer.parseInt(questionModels.get(position).optionsModels.get(j).str_opt_id));
                    radioButton.setText(questionModels.get(position).optionsModels.get(j).str_opt_name);
                    radioGroup.addView(radioButton);
                }
                ((RadioTypeViewHolder) viewHolder).linearLayout_RadioButton.addView(radioGroup);
                radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        Toast.makeText(mContext, "ID is :- "+checkedId, Toast.LENGTH_SHORT).show();
                        try {

                            jsonObjectQASendData = new JSONObject();
                            jsonObjectQASendData.put("question_id",questionModels.get(position).str_que_id);
                            jsonObjectQASendData.put("question_answer",checkedId);
                            jsonObjectQASendData.put("question_type",questionModels.get(position).str_que_type);
                            jsonObjectQASendData.put("question_feedback_id",questionModels.get(position).str_que_fk_id);
                            stringJSONObjectHashMap.put("\""+questionModels.get(position).str_que_id+"\"",jsonObjectQASendData);

                            Intent intent = new Intent("custom_message");
                            intent.putExtra("question_answer", String.valueOf(stringJSONObjectHashMap));
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });


            }
            else if (viewHolder instanceof RatingTypeViewHolder)
            {
                ((RatingTypeViewHolder) viewHolder).textView_ratingBarQuestions.setText(questionModels.get(position).str_gue_title);
                double count = questionModels.get(position).optionsModels.size();
                double value  = 5.0/count;
//                String data = String.format("%.2f",value);
                ((RatingTypeViewHolder) viewHolder).ratingBar_Answer.setStepSize((float) value);
                ((RatingTypeViewHolder) viewHolder).ratingBar_Answer.setNumStars((int) count);
                ((RatingTypeViewHolder) viewHolder).ratingBar_Answer.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                    @Override
                    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser)
                    {
                        System.out.println("Rating Bar Value :- "+ questionModels.get(position).optionsModels.get((int) rating-1).str_opt_id);

                        try {
                            jsonObjectQASendData = new JSONObject();
                            jsonObjectQASendData.put("question_id",questionModels.get(position).str_que_id);
                            jsonObjectQASendData.put("question_answer",questionModels.get(position).optionsModels.get((int) rating-1).str_opt_id);
                            jsonObjectQASendData.put("question_type",questionModels.get(position).str_que_type);
                            jsonObjectQASendData.put("question_feedback_id",questionModels.get(position).str_que_fk_id);

                            stringJSONObjectHashMap.put("\""+questionModels.get(position).str_que_id+"\"",jsonObjectQASendData);

                            Intent intent = new Intent("custom_message");
                            intent.putExtra("question_answer", String.valueOf(stringJSONObjectHashMap));
                            LocalBroadcastManager.getInstance(mContext).sendBroadcast(intent);
                            Toast.makeText(mContext, "Selected Rating Option ID :- "+ questionModels.get(position).optionsModels.get((int) rating-1).str_opt_id, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            else if (viewHolder instanceof LongTextTypeViewHolder)
            {
                jsonObjectQASendData = new JSONObject();
                ((LongTextTypeViewHolder) viewHolder).textView_long_textViewQuestions.setText(questionModels.get(position).str_gue_title);
                final String answerLongText = ((LongTextTypeViewHolder) viewHolder).editText_long_textViewAnswer.getText().toString();


            }
            else if (viewHolder instanceof ImageTypeViewHolder)
            {
                ((ImageTypeViewHolder) viewHolder).textView_imageQuestions.setText(questionModels.get(position).str_gue_title);
                ((ImageTypeViewHolder) viewHolder).imageView_ImageAnswer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(mContext, "CLICK PHOTO", Toast.LENGTH_SHORT).show();
                    }
                });
            }
            else if (viewHolder instanceof TextTypeViewHolder)
            {
                jsonObjectQASendData = new JSONObject();
                ((TextTypeViewHolder) viewHolder).textView_textViewQuestions.setText(questionModels.get(position).str_gue_title);
                final String answerText = ((TextTypeViewHolder) viewHolder).editText_textViewAnswer.getText().toString();

            }
            else if (viewHolder instanceof DropdownTypeViewHolder)
            {
                ((DropdownTypeViewHolder) viewHolder).textView_dropdownQuestions.setText(questionModels.get(position).str_gue_title);

            }


        }

    }

    @Override
    public int getItemCount() {
        return questionModels.size();
    }


}
