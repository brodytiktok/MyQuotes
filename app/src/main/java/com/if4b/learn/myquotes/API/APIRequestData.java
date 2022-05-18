package com.if4b.learn.myquotes.API;

import com.if4b.learn.myquotes.Model.QuoteModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIRequestData {
    @GET("quotes")
    Call<List<QuoteModel>> ardGet();
}
