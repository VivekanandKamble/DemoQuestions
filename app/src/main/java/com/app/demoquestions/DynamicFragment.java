package com.app.demoquestions;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class DynamicFragment extends Fragment {
    View view;
    RecyclerView recyclerView_QuestionFragment;
    Button button_feedback_next,button_feedback_back;
    MultiViewTypeAdapter multiViewTypeAdapter;
    ArrayList<QuestionModel> questionModels = new ArrayList<QuestionModel>();

    public static Fragment newInstance() {
        DynamicFragment dynamicFragment = new DynamicFragment();
        return dynamicFragment;
    }

    public void setQuestionaries(ArrayList<QuestionModel> questionaries) {
        if (questionaries != null && !questionaries.isEmpty()) {
            if (questionModels != null && !questionModels.isEmpty()) {
                questionModels.clear();
            }

            questionModels.addAll(questionaries);
            multiViewTypeAdapter = new MultiViewTypeAdapter(getContext(), questionModels);
            recyclerView_QuestionFragment.setAdapter(multiViewTypeAdapter);
        } else {
            System.out.println("Hello");
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dynamic_fragment, container, false);
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView_QuestionFragment = view.findViewById(R.id.recyclerView_QuestionFragment);
        button_feedback_next = view.findViewById(R.id.button_feedback_next);
        button_feedback_back = view.findViewById(R.id.button_feedback_back);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), OrientationHelper.VERTICAL, false);
        recyclerView_QuestionFragment.setLayoutManager(linearLayoutManager);
        recyclerView_QuestionFragment.setItemAnimator(new DefaultItemAnimator());

        button_feedback_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getContext(), "Fragment Next", Toast.LENGTH_SHORT).show();
            }
        });

        button_feedback_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Fragment Back", Toast.LENGTH_SHORT).show();
            }
        });

    }
}