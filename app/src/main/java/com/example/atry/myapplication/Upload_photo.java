package com.example.atry.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static com.example.atry.myapplication.regsiter_cattle.RESULT_IMAGE_NOSE_1;
import static com.example.atry.myapplication.regsiter_cattle.RESULT_IMAGE_NOSE_2;
import static com.example.atry.myapplication.regsiter_cattle.RESULT_IMAGE_NOSE_3;
import static com.example.atry.myapplication.regsiter_cattle.RESULT_IMAGE_NOSE_4;
import static com.example.atry.myapplication.regsiter_cattle.RESULT_LOAD_CATTLE_FACE_REGISTER;

// Class file for uploading images of both ocw face and cow nose after login
public class Upload_photo extends AppCompatActivity {
    private static final int RESULT_LOAD_CATTLE_FACE_UPLOAD = 1;
    private static final int RESULT_IMAGE_NOSE_1_UPLOAD = 2, RESULT_IMAGE_NOSE_2_UPLOAD = 3, RESULT_IMAGE_NOSE_3_UPLOAD = 4, RESULT_IMAGE_NOSE_4_UPLOAD = 5;
    Button face_image, muzzle_image1, muzzle_image2, muzzle_image3, muzzle_image4, send_image, register_cow;
    public static int RESULT_LOAD_FACE = 1;
    ImageView img1;
    Bitmap bitmap;
    Upload_photo upload;
    TextView color, breed, horn, status;
    TableLayout cattle;
    String faceImage, noseImage;
    private Bitmap mImageBitmap;
    String cow_image, nose_image1, nose_image2, nose_image3, nose_image4;
    private String mCurrentPhotoPath;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_photo);
        muzzle_image1 = (Button) findViewById(R.id.nose_upload_image1);
        muzzle_image2 = (Button) findViewById(R.id.nose_upload_image2);
        muzzle_image3 = (Button) findViewById(R.id.nose_upload_image3);
        muzzle_image4 = (Button) findViewById(R.id.nose_upload_image4);
        send_image = (Button) findViewById(R.id.send_image);
        register_cow = (Button) findViewById(R.id.register_cow_btn);
        cattle = (TableLayout) findViewById(R.id.cattle_details_table);
        color = (TextView) findViewById(R.id.color_cattle);
        breed = (TextView) findViewById(R.id.breed_cattle);
        horn = (TextView) findViewById(R.id.horn_size_cattle);
        status = (TextView) findViewById(R.id.status_cattle);
        face_image = (Button) findViewById(R.id.upload_image);
        upload = this;
        muzzle_image1.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_IMAGE_NOSE_1);
                /*Intent cameraIntent;
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
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Upload_photo.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_1);
                    }
                }
           */

            }
        });
        muzzle_image2.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent;
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
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Upload_photo.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_2);
                    }
                }*/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_IMAGE_NOSE_2);

            }
        });
        muzzle_image3.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent;
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
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Upload_photo.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_3);
                    }
                }*/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_IMAGE_NOSE_3);

            }
        });
        muzzle_image4.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent;
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
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Upload_photo.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_IMAGE_NOSE_4);
                    }
                }*/
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_IMAGE_NOSE_4);

            }
        });
        send_image.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                new get_request(getApplicationContext(), cow_image, upload, nose_image1, nose_image2, nose_image3, nose_image4).execute();
            }
        });

        face_image.setOnClickListener(new Button.OnClickListener(

        ) {
            @Override
            public void onClick(View view) {
                /*Intent cameraIntent;
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
                        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(Upload_photo.this,
                                BuildConfig.APPLICATION_ID + ".provider",
                                photoFile));
                        startActivityForResult(cameraIntent, RESULT_LOAD_FACE);
                    }
                }
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_FACE);
                */
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, RESULT_LOAD_FACE);
            }
        });
        register_cow.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent register = new Intent(getApplicationContext(), regsiter_cattle.class);
                register.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(register);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE) {
            //TODO: action
            final Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap img = extras.getParcelable("data");
            }
        }

        if (resultCode == RESULT_OK) {
            try {
                final Uri imageUri = data.getData();
                final InputStream imageStream = getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(imageStream);

            } catch (FileNotFoundException e) {
                e.printStackTrace();

            }

        }
        // this case will occur when taking a picture with a camera
            /*final Bundle extras = data.getExtras();
            if (extras != null)
            {
                bitmap = extras.getParcelable("data");
            }*/
         /*try {
                mImageBitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), Uri.parse(mCurrentPhotoPath));
                this.bitmap = mImageBitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }*/

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //compress file

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        if (requestCode == RESULT_LOAD_CATTLE_FACE_UPLOAD) {
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

//class for uploading photos to server
class get_request extends AsyncTask<String, Void, String> {
    String uid;
    Context context;
    String encoded, nose1, nose2, nose3, nose4;
    Upload_photo upload;

    public get_request(Context context, String encoded, Upload_photo upload, String nose1, String nose2, String nose3, String nose4) {
        this.context = context;
        this.upload = upload;
        this.encoded = encoded;
        this.nose1 = nose1;
        this.nose2 = nose2;
        this.nose3 = nose3;
        this.nose4 = nose4;

    }

    @Override
    protected void onPostExecute(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
        Log.d("Data", s);
        try {
            JSONObject object = new JSONObject(s);
            String result = object.getString("result");
            if (result.equals("True")) {
                JSONObject data = object.getJSONObject("data");
                upload.color.setText(data.getString("color"));
                upload.breed.setText(data.getString("breed"));
                upload.horn.setText(data.getString("horn_size"));
                upload.status.setText(data.getString("cattle_status"));
                upload.cattle.setVisibility(View.VISIBLE);
            } else {
                upload.cattle.setVisibility(View.GONE);

                Toast.makeText(context.getApplicationContext(), "Upload Another Image Match Failed", Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        SharedPreferences prefs = context.getSharedPreferences("user", MODE_PRIVATE);
        uid = prefs.getString("uid", null);
        Log.d("UID", uid);
    }

    @Override

    protected String doInBackground(String... params) {

        try {
            String link = context.getResources().getString(R.string.link) + "name/";

            //String data= "{'user_name':'name','user_password':'pass','user_email':'email'}";


            //Toast.makeText(context.getApplicationContext(),"LLLasfsdfdOLLL",Toast.LENGTH_LONG).show();
            URL url = new URL(link);
            URLConnection con = url.openConnection();
            //Toast.makeText(context.getApplicationContext(),"LLLasfdOLLL",Toast.LENGTH_LONG).show();
            con.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());

            JSONObject json = new JSONObject();
            JSONObject add = new JSONObject();
            add.put("uid", uid);
            add.put("face", encoded);
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
