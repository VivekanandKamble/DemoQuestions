package com.app.demoquestions;

import java.util.ArrayList;

public class CategoryModel
{
    String str_category_id,str_category_name;
    ArrayList<QuestionModel> questionModels;

    public CategoryModel(String str_category_id, String str_category_name)
    {
        this.str_category_id = str_category_id;
        this.str_category_name = str_category_name;
    }

    public CategoryModel(String str_category_id, String str_category_name, ArrayList<QuestionModel> questionModels)
    {
        this.str_category_id = str_category_id;
        this.str_category_name = str_category_name;
        this.questionModels = questionModels;
    }

    public String getStr_category_id() {
        return str_category_id;
    }

    public void setStr_category_id(String str_category_id) {
        this.str_category_id = str_category_id;
    }

    public String getStr_category_name() {
        return str_category_name;
    }

    public void setStr_category_name(String str_category_name) {
        this.str_category_name = str_category_name;
    }

    public ArrayList<QuestionModel> getQuestionModels() {
        return questionModels;
    }

    public void setQuestionModels(ArrayList<QuestionModel> questionModels) {
        this.questionModels = questionModels;
    }
}
