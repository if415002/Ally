package com.kp.pembekalan.ally;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.kp.pembekalan.ally.interfaces.APIService;
import com.kp.pembekalan.ally.models.ImageResponse;
import com.kp.pembekalan.ally.networking.CaptureRetforitClient;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import pub.devrel.easypermissions.EasyPermissions;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import pub.devrel.easypermissions.EasyPermissions;
public class CaptureImageActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int REQUEST_GALLERY_CODE = 200;
    private static final int READ_REQUEST_CODE = 300;
    ImageView imageView;
    Button photoButton, uploadPhoto;
    String mediaPath;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_capture_image );

        imageView = (ImageView) this.findViewById( R.id.imageView1 );
        Button photoButton = (Button) this.findViewById( R.id.photoButton );

        photoButton.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent( android.provider.MediaStore.ACTION_IMAGE_CAPTURE );
                startActivityForResult( cameraIntent, CAMERA_REQUEST );
            }
        } );

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            if (requestCode == CAMERA_REQUEST) {
                Bitmap photo = (Bitmap) data.getExtras().get( "data" );
                imageView.setImageBitmap( photo );
                uploadFile();
            }
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            }
        }
    }
    private void faceRecognize(){

    }
    private void uploadFile() {
        // Map is used to multipart the file using okhttp3.RequestBody
        File file = new File(mediaPath);

        // Parsing any Media type file
        RequestBody requestBody = RequestBody.create(MediaType.parse("*/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), requestBody);
        RequestBody filename = RequestBody.create(MediaType.parse("text/plain"), file.getName());

        APIService getResponse = CaptureRetforitClient.getRetrofit().create(APIService.class);
        Call<ImageResponse> imageResponseCall = getResponse.uploadImage(fileToUpload, filename);
        imageResponseCall.enqueue(new Callback<ImageResponse>() {
            @Override
            public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                ImageResponse imageResponse = response.body();
                if(imageResponse!=null){
                    if(imageResponse.getSuccess()){
                        uploadPhoto.setOnClickListener( new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent( getApplicationContext(), KatalogActivity.class );
                                startActivity( intent );
                            }
                        } );
                        //Toast.makeText( getApplicationContext(), imageResponse.getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                    else {
                        Toast.makeText( getApplicationContext(), imageResponse.getMessage(), Toast.LENGTH_SHORT ).show();
                    }
                }
                else {
                    assert imageResponse != null;
                    Log.v("Response", imageResponse.toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}