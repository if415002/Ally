package com.kp.pembekalan.ally;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.kp.pembekalan.ally.interfaces.APIService;
import com.kp.pembekalan.ally.models.KatalogModel;
import com.kp.pembekalan.ally.models.KatalogResponse;

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

public class KatalogActivity extends AppCompatActivity {
    String BASE_URL = "http://127.0.0.1:5000/catalog";
    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_katalog );
        gridView = findViewById( R.id.gridview );
        loadDataKatalog();
    }
    void loadDataKatalog(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( BASE_URL )
                .addConverterFactory( GsonConverterFactory.create() )
                .client( new OkHttpClient() )
                .build();

        APIService apiService = retrofit.create(APIService.class);
        Call<KatalogResponse> katalogResponseCall = apiService.getKatalog();
        katalogResponseCall.enqueue( new Callback<KatalogResponse>() {
            @Override
            public void onResponse(Call<KatalogResponse> call, Response<KatalogResponse> response) {
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
            public void onFailure(Call<KatalogResponse> call, Throwable t) {

            }
        } );
    }
    private void writeGridView(String response) {
        try{
            JSONObject obj = new JSONObject( response );
            if(obj.optString( "status" ).equals( "true" )){
                ArrayList<KatalogModel> katalogModelArrayList = new ArrayList<>();
                JSONArray dataArray = obj.getJSONArray( "data" );

                for (int i=0;i<dataArray.length();i++){
                    KatalogModel katalogModel = new KatalogModel();
                    JSONObject dataObj = dataArray.getJSONObject( i );

                    katalogModel.setNama( dataObj.getString( "nama" ) );
                    katalogModel.setDeskripsi( dataObj.getString( "deskripsi" ) );
                    katalogModel.setHarga( dataObj.getInt( "harga" ) );

                    katalogModelArrayList.add(katalogModel);
                }
                KatalogAdapter katalogAdapter = new KatalogAdapter(this, katalogModelArrayList);
                gridView.setAdapter( katalogAdapter );
            }
            else {
                Toast.makeText( KatalogActivity.this, obj.optString( "message" ) + "", Toast.LENGTH_SHORT ).show();
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
