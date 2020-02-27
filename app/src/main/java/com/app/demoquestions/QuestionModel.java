package com.app.demoquestions;

import java.util.ArrayList;

public class QuestionModel
{
    String str_que_id,str_gue_title,str_que_type,str_que_category_id,str_que_fk_id;
    ArrayList<OptionsModel> optionsModels;


    public QuestionModel(String str_que_id, String str_gue_title, String str_que_type,
                         String str_que_category_id, ArrayList<OptionsModel> optionsModels)
    {
        this.str_que_id = str_que_id;
        this.str_gue_title = str_gue_title;
        this.str_que_type = str_que_type;
        this.str_que_category_id = str_que_category_id;
        this.optionsModels = optionsModels;
    }

    public QuestionModel(String str_que_id, String str_gue_title, String str_que_type,
                         String str_que_category_id, String str_que_fk_id, ArrayList<OptionsModel> optionsModels)
    {
        this.str_que_id = str_que_id;
        this.str_gue_title = str_gue_title;
        this.str_que_type = str_que_type;
        this.str_que_category_id = str_que_category_id;
        this.str_que_fk_id = str_que_fk_id;
        this.optionsModels = optionsModels;
    }

    public String getStr_que_category_id() {
        return str_que_category_id;
    }

    public void setStr_que_category_id(String str_que_category_id) {
        this.str_que_category_id = str_que_category_id;
    }

    public String getStr_que_fk_id() {
        return str_que_fk_id;
    }

    public void setStr_que_fk_id(String str_que_fk_id) {
        this.str_que_fk_id = str_que_fk_id;
    }

    public ArrayList<OptionsModel> getOptionsModels() {
        return optionsModels;
    }

    public void setOptionsModels(ArrayList<OptionsModel> optionsModels) {
        this.optionsModels = optionsModels;
    }

    public String getStr_que_id() {
        return str_que_id;
    }

    public void setStr_que_id(String str_que_id) {
        this.str_que_id = str_que_id;
    }

    public String getStr_gue_title() {
        return str_gue_title;
    }

    public void setStr_gue_title(String str_gue_title) {
        this.str_gue_title = str_gue_title;
    }

    public String getStr_que_type() {
        return str_que_type;
    }

    public void setStr_que_type(String str_que_type) {
        this.str_que_type = str_que_type;
    }
}
