package com.sw;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Main> mains;
    public MainAdapter(){

    }
    public void setMains(List<Main>mains){
        this.mains = mains;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.holder_main,parent,false);
        return new MainHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder,int position){
        if(holder instanceof MainHolder)
            ((MainHolder)holder).bind(mains.get(position));
    }
    @Override
    public int getItemCount(){
        if(mains == null)
            return 0;

        return mains.size();
    }
}

