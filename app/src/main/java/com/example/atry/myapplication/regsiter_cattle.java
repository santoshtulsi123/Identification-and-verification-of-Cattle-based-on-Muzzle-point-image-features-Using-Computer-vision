package com.example.atry.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

public class regsiter_cattle extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private Bitmap mImageBitmap;
    private String mCurrentPhotoPath;
    private ImageView mImageView;
    public static int RESULT_LOAD_CATTLE_FACE_REGISTER = 2, RESULT_IMAGE_NOSE_1 = 3, RESULT_IMAGE_NOSE_2 = 4, RESULT_IMAGE_NOSE_3 = 5, RESULT_IMAGE_NOSE_4 = 6;
    EditText cattle_name, cattle_breed, cattle_color, cattle_dob, cattle_horn_size;
    Button upload_image_cow_face, regsiter_cattle, image1_nose, image2_nose, image3_nose, image4_nose;
    Bitmap bitmap;
    String cow_image, nose_image1, nose_image2, nose_image3, nose_image4;
    regsiter_cattle cattle;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            // this case will occur when taking a picture with a camera
            try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                this.bitmap = mImageBitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            //compress file

            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, baos);
            byte[] imageBytes = baos.toByteArray();
            if (requestCode == RESULT_LOAD_CATTLE_FACE_REGISTER) {
                cow_image = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(getApplicationContext(), "Cow image selected", Toast.LENGTH_SHORT).show();
            } else if (requestCode == RESULT_IMAGE_NOSE_1) {
                nose_image1 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(getApplicationContext(), "Nose image 1 selected", Toast.LENGTH_SHORT).show();

            } else if (requestCode == RESULT_IMAGE_NOSE_2) {
                nose_image2 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(getApplicationContext(), "Nose image 2 selected", Toast.LENGTH_SHORT).show();

            } else if (requestCode == RESULT_IMAGE_NOSE_3) {
                nose_image3 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(getApplicationContext(), "Nose image 3 selected", Toast.LENGTH_SHORT).show();

            } else if (requestCode == RESULT_IMAGE_NOSE_4) {
                nose_image4 = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                Toast.makeText(getApplicationContext(), "Nose image 4 selected", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regsiter_cattle);

        cattle_name = (EditText) findViewById(R.id.cattle_name_register);
        cattle_breed = (EditText) findViewById(R.id.cattle_breed_register);
        cattle_color = (EditText) findViewById(R.id.cattle_color_register);
        cattle_dob = (EditText) findViewById(R.id.dob_cattle_register);
        upload_image_cow_face = (Button) findViewById(R.id.btn_upload_image_cattle);
        regsiter_cattle = (Button) findViewById(R.id.register_cattle_send);
        cattle_horn_size = (EditText) findViewById(R.id.cattle_horn_size);
        this.cattle = this;
        image1_nose = (Button) findViewById(R.id.btn_upload_nose_image1);
        image2_nose = (Button) findViewById(R.id.btn_upload_nose_image2);
        image3_nose = (Button) findViewById(R.id.btn_upload_nose_image3);
        image4_nose = (Button) findViewById(R.id.btn_upload_nose_image4);
        upload_image_cow_face.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent;
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        //Log.i("TAG", ex.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(regsiter_cattle.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_LOAD_CATTLE_FACE_REGISTER);
                    }
                }

            }
        });
        image1_nose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent;
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        //Log.i("TAG", ex.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(regsiter_cattle.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_1);
                    }
                }
            }
        });
        image2_nose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent;
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        //Log.i("TAG", ex.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(regsiter_cattle.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_2);
                    }
                }
            }
        });
        image3_nose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent;
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        //Log.i("TAG", ex.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(regsiter_cattle.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_3);
                    }
                }
            }
        });
        image4_nose.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cameraIntent;
                cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (cameraIntent.resolveActivity(getPackageManager()) != null) {
                    // Create the File where the photo should go
                    File photoFile = null;
                    try {
                        photoFile = createImageFile();
                    } catch (IOException ex) {
                        // Error occurred while creating the File
                        //Log.i("TAG", ex.toString());
                    }
                    // Continue only if the File was successfully created
                    if (photoFile != null) {
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(regsiter_cattle.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_4);
                    }
                }
            }
        });

        regsiter_cattle.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new register_cattle_data_send(getApplicationContext(), cattle).execute();

            }
        });
    }


    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  // prefix
                ".jpg",         // suffix
                storageDir      // directory
        );

        // Save a file: path for use with ACTION_VIEW intents
        mCurrentPhotoPath = "file:" + image.getAbsolutePath();
        return image;
    }


}


class register_cattle_data_send extends AsyncTask<String, Void, String> {
    Context context;
    regsiter_cattle cattle;
    String cattle_name, cattle_breed, cattle_dob, cattle_color, image, uid, horn, nose1, nose2, nose3, nose4;

    public register_cattle_data_send(Context context, regsiter_cattle cattle) {
        this.context = context;
        this.cattle = cattle;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        SharedPreferences prefs = context.getSharedPreferences("user", MODE_PRIVATE);
        uid = prefs.getString("uid", null);
        this.cattle_name = cattle.cattle_name.getText().toString();
        this.cattle_breed = cattle.cattle_breed.getText().toString();
        this.cattle_dob = cattle.cattle_dob.getText().toString();
        this.cattle_color = cattle.cattle_color.getText().toString();
        this.image = cattle.cow_image;
        this.horn = cattle.cattle_horn_size.getText().toString();
        this.nose1 = cattle.nose_image1;
        this.nose2 = cattle.nose_image2;
        this.nose3 = cattle.nose_image3;
        this.nose4 = cattle.nose_image4;
        //Log.d("Image", image);
        //Toast.makeText(context.getApplicationContext(), image, Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context.getApplicationContext(), s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... strings) {
        try {
            String link = context.getResources().getString(R.string.link) + "reg_cattle/";

            URL url = new URL(link);
            URLConnection con = url.openConnection();

            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            JSONObject add = new JSONObject();
            //add.put("", cattle_name);
            add.put("color", cattle_color);
            add.put("breed", cattle_breed);
            //add.put(, cattle_dob);
            add.put("face", image);
            add.put("uid", uid);
            add.put("horn", horn);
            add.put("muzzle1", nose1);
            add.put("muzzle2", nose2);
            add.put("muzzle3", nose3);
            add.put("muzzle4", nose4);

            wr.write(add.toString());
            wr.flush();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {

                sb.append(line);
            }
            return sb.toString();

        } catch (Exception e) {
            Log.d("ERROR", e.getMessage());
            return "Exception: " + e.getMessage();
        }
    }
}