package com.if4b.learn.myquotes.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.if4b.learn.myquotes.API.APIRequestData;
import com.if4b.learn.myquotes.API.RetroServer;
import com.if4b.learn.myquotes.Adapter.AdapterQuote;
import com.if4b.learn.myquotes.Model.QuoteModel;
import com.if4b.learn.myquotes.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvQuote;
    private ProgressBar pbQuote;
    private List<QuoteModel> listQuote;
    private AdapterQuote adapterQuote;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvQuote = findViewById(R.id.rv_quote);
        pbQuote = findViewById(R.id.pb_quote);

        linearLayoutManager = new LinearLayoutManager(MainActivity.this);

        rvQuote.setLayoutManager(linearLayoutManager);

        retrieveQuote();
    }

    private void retrieveQuote() {
        pbQuote.setVisibility(View.VISIBLE);

        APIRequestData ardData = RetroServer.connectRetrofit().create(APIRequestData.class);
        Call<List<QuoteModel>> retrieveProcess = ardData.ardGet();

        retrieveProcess.enqueue(new Callback<List<QuoteModel>>() {
            @Override
            public void onResponse(Call<List<QuoteModel>> call, Response<List<QuoteModel>> response) {
                listQuote = response.body();
                adapterQuote = new AdapterQuote(listQuote);
                rvQuote.setAdapter(adapterQuote);

                pbQuote.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<QuoteModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Gagak Menghubungi Server"+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("CEK", t.getMessage());
                pbQuote.setVisibility(View.INVISIBLE);
            }
        });
    }
}