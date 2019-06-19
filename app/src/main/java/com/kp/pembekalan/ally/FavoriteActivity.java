package com.kp.pembekalan.ally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.kp.pembekalan.ally.interfaces.APIService;
import com.kp.pembekalan.ally.models.FavoriteModel;
import com.kp.pembekalan.ally.models.FavoriteResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FavoriteActivity extends AppCompatActivity {
    String BASE_URL = "http://127.0.0.1:5000/favorit";
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_katalog );
        listView = findViewById( R.id.listFavorite );
        loadDataFavorit();
    }
    void loadDataFavorit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( new OkHttpClient() )
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<FavoriteResponse> favoriteResponseCall = apiService.getFavorite();
        favoriteResponseCall.enqueue( new Callback<FavoriteResponse>() {
            @Override
            public void onResponse(Call<FavoriteResponse> call, Response<FavoriteResponse> response) {
                if(response.isSuccessful()) {
                    Log.i("onSuccess", response.body().toString());
                    String jsonresponse = response.body().toString();

                    writeGridView(jsonresponse);
                }
                else {
                    Log.i("onEmptyResponse","Returned empty response");
                }
            }

            @Override
            public void onFailure(Call<FavoriteResponse> call, Throwable t) {

            }
        } );
    }
    private void writeGridView(String response) {
        try{
            JSONObject obj = new JSONObject( response );
            if(obj.optString( "status" ).equals( "true" )){
                ArrayList<FavoriteModel> favoriteModelArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray( "data" );

                for (int i=0;i<dataArray.length();i++){
                    FavoriteModel favoriteModel = new FavoriteModel();
                    JSONObject dataObj = dataArray.getJSONObject( i );

                    favoriteModel.setNama( dataObj.getString( "nama" ) );


                    favoriteModelArrayList.add(favoriteModel);
                }
                FavoritAdapter favoritAdapter = new FavoritAdapter(this, favoriteModelArrayList);
                listView.setAdapter( favoritAdapter );
            }
            else {
                Toast.makeText( FavoriteActivity.this, obj.optString( "message" ) + "", Toast.LENGTH_SHORT ).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
