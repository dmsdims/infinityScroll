package com.codepolitan.infinityscroll.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepolitan.infinityscroll.R;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
//TEKAN LAMPU MERAH DAN BIKIM METHODNYA

public class ContactAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<String>dataContact = new ArrayList<>();
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_LOADING = 2;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == TYPE_ITEM){
            return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact, parent,false));
        }
        return new ProgressViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_ITEM){
//            buat class bind kalau merah alt enter pilih createn method
            ((ItemViewHolder) holder).bind(dataContact.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return dataContact.size();
    }

    @Override
//    bertugas mengecek data yang ada diarraylist ,memunculkanya dengan mengetik getItemView pilih yang public int
    public int getItemViewType(int position) {
        return (dataContact.get(position) != null) ? TYPE_ITEM : TYPE_LOADING;
    }

    public void addDataContact (List<String> contact){
        if (contact != null){
            dataContact.addAll(contact);
            notifyDataSetChanged();
        }
    }
    public void addDataLoading(){
        dataContact.add(null);
        notifyDataSetChanged();
    }
    public void removeDataLoading(){
        dataContact.remove(dataContact.size()-1);
        notifyDataSetChanged();
    }

//    start memanggil nama variable
    class ItemViewHolder extends RecyclerView.ViewHolder{

        TextView tvName;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
        }
//        selesai
//ganti variable menjadi name
        public void bind(String name) {
            tvName.setText(name);
        }
    }
    class  ProgressViewHolder extends RecyclerView.ViewHolder{
        public ProgressViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
