package com.app.demoquestions;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    static String questionsList="http://demo.myfeedbackq.com/api/feedback_question_list";
    TabLayout tabLayout_FeedbackCategory;
    ViewPager viewPager_FeedbackQuestions;

    ArrayList<QuestionModel> QuestionList;
    ArrayList<OptionsModel> OptionsList;
    ArrayList<CategoryModel> CategoryList;

    String [] str_que_id,str_que_title,str_que_type,str_que_category_id;
    String [] str_opt_id,str_opt_name;
    String [] str_category_id,str_category_name;

    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout_FeedbackCategory = findViewById(R.id.tabLayout_FeedbackCategory);
        viewPager_FeedbackQuestions = findViewById(R.id.viewPager_FeedbackQuestions);


        jsonFeedbackAllQuestions("18","1");

        viewPager_FeedbackQuestions.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i)
            {
                getCategory(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void getCategory(int a )
    {
        CategoryModel categoryModel = CategoryList.get(a);
        Fragment fragment = viewPagerAdapter.getItem(a);
        if (fragment instanceof DynamicFragment)
        {
            ((DynamicFragment) fragment).setQuestionaries(categoryModel.getQuestionModels());
        }
    }

    private void jsonFeedbackAllQuestions(final String str_user_id, final String str_feedback_id)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, questionsList, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                System.out.println("Response Question List" + response);
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.getString("Status").toString().matches("Success"))
                    {
                        JSONObject jsonObjectData = new JSONObject(jsonObject.getString("Data"));
                        System.out.println("Questions Objects :- "+jsonObjectData);

                        JSONArray jsonArrayCategoryList = new JSONArray(jsonObjectData.getString("category_list"));

                        System.out.println("JSON Array Category List Length :- "+jsonArrayCategoryList.length() +" and Array :- "+ jsonArrayCategoryList.toString());

                        CategoryList = new ArrayList<CategoryModel>();
                        str_category_id = new String[jsonArrayCategoryList.length()];
                        str_category_name = new String[jsonArrayCategoryList.length()];

                        for (int i=0;i<jsonArrayCategoryList.length();i++)
                        {
                            JSONObject jsonObjectCategory = jsonArrayCategoryList.getJSONObject(i);
                            str_category_id[i] = jsonObjectCategory.getString("qc_pk_id");
                            str_category_name [i]= jsonObjectCategory.getString("qc_name");

                            System.out.println("Question Category ID : " + str_category_id[i]);
                            System.out.println("Question Category Name : " + str_category_name[i]);

                            tabLayout_FeedbackCategory.addTab(tabLayout_FeedbackCategory.newTab().setText(jsonObjectCategory.getString("qc_name")));

                            JSONArray jsonArrayQuestion = new JSONArray(jsonObjectCategory.getString("question_list"));
                            System.out.println("Question List :- "+ jsonArrayQuestion);

                            QuestionList = new ArrayList<QuestionModel>();
                            str_que_id = new String[jsonArrayQuestion.length()];
                            str_que_title = new String[jsonArrayQuestion.length()];
                            str_que_type = new String[jsonArrayQuestion.length()];
                            str_que_category_id = new String[jsonArrayQuestion.length()];

                            for (int j= 0; j<jsonArrayQuestion.length();j++)
                            {
                                JSONObject jsonObjectQuestionList = jsonArrayQuestion.getJSONObject(j);
                                str_que_id[j] = jsonObjectQuestionList.getString("id");
                                str_que_title[j] = jsonObjectQuestionList.getString("question_title");
                                str_que_type[j] = jsonObjectQuestionList.getString("q_type");
                                str_que_category_id[j] = jsonObjectQuestionList.getString("question_category");

                                System.out.println("Question ID : " + str_que_id[j]);
                                System.out.println("Question Title : " + str_que_title[j]);
                                System.out.println("Question Type : " + str_que_type[j]);
                                System.out.println("Question Cate ID : " + str_que_category_id[j]);

                                JSONArray jsonArrayQuestionOptions = new JSONArray(jsonObjectQuestionList.getString("options"));
                                OptionsList = new ArrayList<OptionsModel>();
                                str_opt_id = new String[jsonArrayQuestionOptions.length()];
                                str_opt_name = new String[jsonArrayQuestionOptions.length()];

                                for (int l = 0; l < jsonArrayQuestionOptions.length(); l++) {
                                    JSONObject jsonObjectQuestionOptions = jsonArrayQuestionOptions.getJSONObject(l);
                                    str_opt_id[l] = jsonObjectQuestionOptions.getString("id");
                                    str_opt_name[l] = jsonObjectQuestionOptions.getString("question_option");

                                    System.out.println("Option ID : " + str_opt_id[l]);
                                    System.out.println("Option Name : " + str_opt_name[l]);

                                    OptionsModel optionsModel = new OptionsModel(str_opt_id[l], str_opt_name[l]);
                                    OptionsList.add(optionsModel);

                                }

                                System.out.println("Options List :- " + OptionsList.size());

                                QuestionModel questionModel = new QuestionModel(str_que_id[j], str_que_title[j], str_que_type[j], str_que_category_id[j], OptionsList);
                                QuestionList.add(questionModel);
                            }

                            CategoryModel categoryModel =new CategoryModel(str_category_id[i],str_category_name[i]);
                            CategoryList.add(categoryModel);
                        }

                        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout_FeedbackCategory.getTabCount());
                        viewPager_FeedbackQuestions.setAdapter(viewPagerAdapter);
                        getCategory(0);


                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        System.out.println("Error.Response" + error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("feedback_id", str_feedback_id);
                params.put("user_id", str_user_id);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
