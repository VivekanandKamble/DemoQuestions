package com.app.demoquestions;

import android.support.design.widget.TabLayout;
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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
{
    static String questionsList="http://demo.myfeedbackq.com/api/feedback_question_list";
    TabLayout tabLayout_FeedbackCategory;
    ViewPager viewPager_FeedbackQuestions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabLayout_FeedbackCategory = findViewById(R.id.tabLayout_FeedbackCategory);
        viewPager_FeedbackQuestions = findViewById(R.id.viewPager_FeedbackQuestions);


        jsonFeedbackAllQuestions("18","1");
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

                        System.out.println("json Array Category List Length :- "+jsonArrayCategoryList.length() +" and Array :- "+ jsonArrayCategoryList.toString());

                        for (int i=0;i<jsonArrayCategoryList.length();i++)
                        {
                            JSONObject jsonObjectCategory = jsonArrayCategoryList.getJSONObject(i);
                            tabLayout_FeedbackCategory.addTab(tabLayout_FeedbackCategory.newTab().setText(jsonObjectCategory.getString("qc_name")));
                        }

                        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),tabLayout_FeedbackCategory.getTabCount());
                        viewPager_FeedbackQuestions.setAdapter(viewPagerAdapter);
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
