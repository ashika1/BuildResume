package com.example.jntuh.buildresume.fragments;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.jntuh.buildresume.R;
import com.example.jntuh.buildresume.adapter.OthersListview;
import com.example.jntuh.buildresume.model.OthersModel;

import java.util.ArrayList;


public class Other extends Fragment implements View.OnClickListener{
    public Button addskills,addach,addhobbys,addlan;
    public ListView addSkillslv,addAchlv,addHobbyslv,addLanlv;
    public  ArrayList<OthersModel> detailModels ;
    public  ArrayList<OthersModel> detailModels1 ;
    public  ArrayList<OthersModel> detailModels2 ;
    public  ArrayList<OthersModel> detailModels3 ;
    OthersListview othersListview;
    OthersListview othersListview1;
    OthersListview othersListview2;
    OthersListview othersListview3;
    public Other() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.fragment_other, container, false);
        addskills = (Button)itemView.findViewById(R.id.addskills);
        addskills.setOnClickListener(this);
        addach = (Button)itemView.findViewById(R.id.addachivements);
        addach.setOnClickListener(this);
        addhobbys = (Button)itemView.findViewById(R.id.addhobbys);
        addhobbys.setOnClickListener(this);
        addlan = (Button)itemView.findViewById(R.id.addlan);
        addlan.setOnClickListener(this);

        addSkillslv = (ListView)itemView.findViewById(R.id.addskillslistview);
        addAchlv = (ListView)itemView.findViewById(R.id.addachivementslistview);
        addHobbyslv = (ListView)itemView.findViewById(R.id.addhobbyslistview);
        addLanlv = (ListView)itemView.findViewById(R.id.addlanlistview);
        detailModels = new ArrayList<OthersModel>();
        detailModels1 = new ArrayList<OthersModel>();
        detailModels2 = new ArrayList<OthersModel>();
        detailModels3 = new ArrayList<OthersModel>();

        othersListview = new OthersListview(getActivity(),detailModels);
        othersListview1 = new OthersListview(getActivity(),detailModels1);
        othersListview2 = new OthersListview(getActivity(),detailModels2);
        othersListview3 = new OthersListview(getActivity(),detailModels3);

        return itemView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.addskills:
                addOthers("Add Skills","Skill");
                break;
            case R.id.addachivements:
                addOthers("Add Achivements","Achivement");
                break;
            case R.id.addhobbys:
                addOthers("Add Hobbys","Hobby");
                break;
            case R.id.addlan:
                addOthers("Add Language","Language");
                break;
        }
    }

    private void addOthers(String s, final String s1) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.addvalue, null);
        builder.setTitle(s);
        builder.setView(dialogView);
        final AlertDialog alertDialog = builder.create();

        final TextInputLayout valueOthers = (TextInputLayout)dialogView.findViewById(R.id.valueother);
        valueOthers.getEditText().setHint(s1);

        Button save = (Button)dialogView.findViewById(R.id.save_value);
        Button cancel = (Button)dialogView.findViewById(R.id.cancel_value);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    String qualification = valueOthers.getEditText().getText().toString();

                    if(qualification==null||qualification==""){
                        Toast.makeText(getContext(),"Should Be Fill All Fields",Toast.LENGTH_LONG).show();
                    }else if(s1.equals("Skill")){
                        otherssavelan(qualification);
                        alertDialog.dismiss();

                    }
                    else if(s1.equals("Achivement")){
                        otherssavehobbys(qualification);
                        alertDialog.dismiss();

                    }
                    else if(s1.equals("Hobby")){
                        otherssaveache(qualification);
                        alertDialog.dismiss();
                    }
                    else if(s1.equals("Language")){
                        otherssaveskills(qualification);
                        alertDialog.dismiss();
                    }
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
            }


        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

    private void otherssaveskills(String qualification) {
        OthersModel othersModel = new OthersModel(qualification);
        detailModels.add(othersModel);
        addLanlv.setAdapter(othersListview);
        othersListview.notifyDataSetInvalidated();
    }
    private void otherssaveache(String qualification) {
        OthersModel othersModel = new OthersModel(qualification);
        detailModels1.add(othersModel);
        addHobbyslv.setAdapter(othersListview1);
        othersListview1.notifyDataSetInvalidated();
    }
    private void otherssavehobbys(String qualification) {
        OthersModel othersModel = new OthersModel(qualification);
        detailModels2.add(othersModel);
        addAchlv.setAdapter(othersListview2);
        othersListview2.notifyDataSetInvalidated();
    }
    private void otherssavelan(String qualification) {
        OthersModel othersModel = new OthersModel(qualification);
        detailModels3.add(othersModel);
        addSkillslv.setAdapter(othersListview3);
        othersListview3.notifyDataSetInvalidated();
    }

}
