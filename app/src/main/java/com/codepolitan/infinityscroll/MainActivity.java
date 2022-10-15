package com.codepolitan.infinityscroll;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.LinearLayout;

import com.codepolitan.infinityscroll.adapter.ContactAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//isscroll untuk data bisa discroll
    private boolean isScroll =true;
    private ContactAdapter contactAdapter = new ContactAdapter();
    private ArrayList<String> dataContacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData(0);
        RecyclerView rcContact =findViewById(R.id.rcContact);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        
        rcContact.setLayoutManager(layoutManager);
        rcContact.setHasFixedSize(true);
        rcContact.setAdapter(contactAdapter);
//        menambahkan menu scroll
        rcContact.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
//                jumlah data yang ada direcycleView
                final int coutItems = layoutManager.getItemCount();
//                jumlah data yang terlihat dilayar
                int currenItem = layoutManager.getChildCount();
//                jumlah data yang terlihat pertama kali kalau vertikal data keatas
                int firsVisibleposition = layoutManager.findFirstVisibleItemPosition();
                int totalScrollItem = currenItem +firsVisibleposition;

                Log.d("coba","count items:" +coutItems);
                Log.d("coba","corrent item:" +currenItem);
                Log.d("coba","corrent item:" +currenItem);
                Log.d("coba","firsh Visible Position:" +firsVisibleposition);

                if (isScroll && totalScrollItem >= coutItems){
                    isScroll = false ;
                    contactAdapter.addDataLoading();

                    new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            contactAdapter.removeDataLoading();
                            fetchData(coutItems);
                            isScroll = true;
                        }
                    },2000);
                }

            }
        });
    }

    private void fetchData(int countItems) {
        if (dataContacts.size() > 0){
            dataContacts.clear();
        }
//        coding untuk mengompail data 15 kali
        for (int i =countItems; i < countItems + 15; i++){
            dataContacts.add(i + ".dimas sandy yolanda");
        }
//        untuk memasukan data contact dari contact adapter
        contactAdapter.addDataContact(dataContacts);
    }
}