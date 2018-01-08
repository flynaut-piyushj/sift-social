package com.example.dell.socialsift.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.dell.socialsift.R;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;

/**
 * Created by DELL on 9/14/2017.
 */

public class SocialIntegrationActivity extends ParentActivity {
private int REQUEST_TAKE_GALLERY_VIDEO = 99;
    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private File file;
    private ShareDialog shareDialog;
    private Button postOntwitter, postOnFb;
    private AccessTokenTracker accessTokenTracker;
    private String accessToken;
    Twitter twitter;
    // share button
    private ShareButton shareButton;
    //image
    private Button button_twitter;
    private Bitmap image;
    SharedPreferences pref;
    //counter
    private int counter = 0;
//    private UiLifecycleHelper uiHelper;
    //    private FacebookSdk fb = new FacebookSdk("445145832506275");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_social_integration);
        FacebookSdk.sdkInitialize(getApplicationContext());
        //share button
        pref = this.getPreferences(0);
        button_twitter = (Button) findViewById(R.id.button_twitter);
        button_twitter.setOnClickListener(this);
        twitter = new TwitterFactory().getInstance();
        twitter.setOAuthConsumer(pref.getString("CONSUMER_KEY", ""), pref.getString("CONSUMER_SECRET", ""));
        shareButton = (ShareButton) findViewById(R.id.share_btn);
        shareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                System.out.println("IN NEW SHARE");
                postPicture();
            }
        });

        shareDialog = new ShareDialog(this);
        postOntwitter = (Button) findViewById(R.id.postOntwitter);
        postOntwitter.setOnClickListener(this);
        postOnFb = (Button) findViewById(R.id.postOnFb);
        postOnFb.setOnClickListener(this);





        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setReadPermissions(Arrays.asList(
                "public_profile", "email", "user_birthday", "user_friends","user_posts","user_photos"));

        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(
                    AccessToken oldAccessToken,
                    AccessToken currentAccessToken) {
                // Set the access token using
                // currentAccessToken when it's loaded or set.
            }
        };
        // If the access token is available already assign it.
        accessToken = AccessToken.getCurrentAccessToken().getToken().toString();
System.out.println("accessToken :  " + accessToken);

        Collection<String> publishPermissions = new ArrayList<>();
        publishPermissions.add("publish_actions");
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {


                GraphRequest request = GraphRequest.newMeRequest(
                        loginResult.getAccessToken(),
                        new GraphRequest.GraphJSONObjectCallback() {
                            @Override
                            public void onCompleted(JSONObject object, GraphResponse response) {
                                Log.v("LoginActivity", response.toString());
                                AccessToken token = AccessToken.getCurrentAccessToken();
                                Log.v("token   ", "" + token.getToken() + " * " + token);
//                                V/token: EAAGU25c4Q6MBADXGZAIfA9dzwnxyRl6OqKNIp6F2bdYhGL3N1BjeR4TFitCr17qj1L2z6orR2akMAtegHnnm6lLXLD9tiGGZApt6pDBMZADHWKIZCZCh3TYQbVz5ZAfLYbzkAM985cOHVkvNZAOtoBL18kPcqZCKqOtiJkQ88JeDU9g14DS50wbENZCOjYuG6WrNO33UJXsk6J0Tt5zmbhRqZBwedsxCDKCCfoqI6lzyIvof0laUd17DeD * {AccessToken token:ACCESS_TOKEN_REMOVED permissions:[user_birthday, public_profile, user_friends, email]}
                                // Application code
                                try {
                                    String email = object.getString("email");
                                    String birthday = object.getString("birthday"); // 01/31/1980 format
//                                    {"id":"853284754832636","name":"Shradha Bele","email":"shradhabele16@gmail.com","gender":"female","birthday":"08\/16\/1994"}, error: null}
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();



            }

            @Override
            public void onCancel() {
                System.out.println("onCancel " + "");
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError " + exception.getMessage());
                // App code
            }
        });
//        LoginManager.getInstance().logInWithPublishPermissions(SocialIntegrationActivity.this,publishPermissions);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        accessTokenTracker.stopTracking();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.button_twitter:
//                uploadVideo();
              /*

                String dataMsg = "Your video description here.";
                Bundle param;
//    facebook = new Facebook(FB_APP_ID);
                AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);


                    param = new Bundle();
                    param.putString("message", dataMsg);
                    param.putString("filename", "mp4");
                    param.putByteArray("video", data);
                    mAsyncRunner.request("me/videos", param, "POST", new fbRequestListener(), null);
                }
                catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }*/


///storage/emulated/0/document/video:38994
        String dataPath = "storage/emulated/0/DCIM/qwert.mp4";
                byte[] data = null;
                InputStream is = null;
        try {
            is = new FileInputStream(dataPath);
            data = readBytes(is);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
                String path = "me/videos";
                AccessToken at = AccessToken.getCurrentAccessToken();
                Bundle parameters = new Bundle();
                parameters.putString("message", "Hello!!!");
                parameters.putByteArray("video", data);
                parameters.putString("filename", "mp4");
                HttpMethod method = HttpMethod.POST;
                GraphRequest.Callback cb = new GraphRequest.Callback() {

                    @Override
                    public void onCompleted(GraphResponse graphResponse) {

                        //check graphResponse for success or failure
                        if(graphResponse.getError()==null){
                            Toast.makeText(SocialIntegrationActivity.this, "Successfully posted to Facebook", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SocialIntegrationActivity.this, "Facebook: There was an error, Please Try Again", Toast.LENGTH_SHORT).show();

                        }
                    }
                };

                GraphRequest request = new GraphRequest(at,path,parameters,method,cb);
                request.setParameters(parameters);
                request.executeAsync();




//                selectVideo();

                /*Bundle params = new Bundle();
                params.putString("with", "picture");
//                params.putString("url", "{image-url}");
*//* make the API call *//*
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/feed",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                System.out.println("response1111===> " + response.toString());
                            }
                        }
                ).executeAsync();*/



                /*Bundle params = new Bundle();
                params.putString("url", "{image-url}");
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/853284754832636_835706453257133",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {
                                System.out.println("response1111===> " + response.toString());
                            }
                        }
                ).executeAsync();*/
               /* Intent startCustomCameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                file = new File(Environment.getExternalStorageDirectory(),
                        "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
                Uri uri = Uri.fromFile(file);
                startCustomCameraIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);
                startCustomCameraIntent.putExtra("return-data", true);
                startActivityForResult(startCustomCameraIntent, 0);*/
                break;
            case R.id.postOntwitter:

                uploadVideo();

/*

                try {
                    uploadPic();
                } catch (Exception e) {
                    e.printStackTrace();
                }

*/

/*


//
//                public Uri getImageUri(Context inContext, Bitmap inImage) {
                image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                image.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getApplicationContext().getContentResolver(), image, "Title", null);
                Uri file = Uri.parse(path);
//            }
//            Uri file = Uri.parse("android.resource://com.code2care.thebuddhaquotes/"+R.drawable.app_logo);
                Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                shareIntent.setType("image*/
/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM,file);
                shareIntent.putExtra(Intent.EXTRA_TITLE, "YOUR TEXT HERE");
                shareIntent.setPackage("com.instagram.android");
                startActivity(shareIntent);
*/


               /* String msg = "Shradha";
                Uri uri = Uri
                        .parse("android.resource://com.code2care.example.sharetextandimagetwitter/drawable/mona");
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, msg);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_STREAM, uri);
                intent.setType("image/jpeg");
                intent.setPackage("com.instagram.android");
                startActivity(intent);*/


             /*   new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/853284754832636/feed",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {

                                System.out.println("response===> " + response.toString());
                            }
                        }
                ).executeAsync();
*/

                break;

            case R.id.postOnFb:
                Bundle params = new Bundle();
                params.putString("with", "location");
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/853284754832636/posts",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {


                                System.out.println("response===> " + response.getRawResponse());
/*
                                GraphObject go  = response.getGraphObject();
                                JSONObject  jso = go.getInnerJSONObject();
                                JSONArray arr = jso.getJSONArray( "data" );*/

                               /* JSONObject graphResponse = response.getRawResponse().getInnerJSONObject();
                                String postId = null;
                                try {
                                    postId = graphResponse.getString("id");
                                } catch (JSONException e) {
                                    Log.i("Facebook error", "JSON error " + e.getMessage());
                                }*/


                            }
                        }
                ).executeAsync();
//                postToFacebook();
         /* new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/853284754832636_835706453257133",
                        null,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {

                                System.out.println("response1111===> " + response.toString());
                            }
                        }
                ).executeAsync();*/


               /* Bundle params = new Bundle();
                params.putString("with", "location");
                new GraphRequest(
                        AccessToken.getCurrentAccessToken(),
                        "/me/feed",
                        params,
                        HttpMethod.GET,
                        new GraphRequest.Callback() {
                            public void onCompleted(GraphResponse response) {


                                System.out.println("response===> " + response.toString());

                            }
                        }
                ).executeAsync();*/



//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .build();

                /*image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                String VideoURL = "http://www.androidbegin.com/tutorial/AndroidCommercial.3gp";*/
               /* Uri videoFileUri = Uri.parse(VideoURL);
//                Uri videoFileUri = null;
                ShareVideo video = new ShareVideo.Builder()
                        .setLocalUrl(videoFileUri)
                        .build();
                ShareVideoContent content = new ShareVideoContent.Builder()
                        .setVideo(video)
                        .build();*/


//                ShareLinkContent content = new ShareLinkContent.Builder()
//                        .setContentUrl(Uri.parse("https://developers.facebook.com"))
//                        .build();
//        ShareButton shareButton = (ShareButton)findViewById(R.id.share_btn);
                /*image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(image)
                        .build();
                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();
                shareButton.setShareContent(content);
                shareButton.performClick();*/
//                facebookWallPost();
                break;
            case R.id.share_btn:
//                facebookWallPost();
                break;

        }
    }



    void uploadVideo(){

        String dataPath = "storage/emulated/0/DCIM/qwert.mp4";
        byte[] data = null;
        InputStream is = null;
        try {
            is = new FileInputStream(dataPath);
            data = readBytes(is);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams();
        rp.put("access_token","EAAGU25c4Q6MBADXGZAIfA9dzwnxyRl6OqKNIp6F2bdYhGL3N1BjeR4TFitCr17qj1L2z6orR2akMAtegHnnm6lLXLD9tiGGZApt6pDBMZADHWKIZCZCh3TYQbVz5ZAfLYbzkAM985cOHVkvNZAOtoBL18kPcqZCKqOtiJkQ88JeDU9g14DS50wbENZCOjYuG6WrNO33UJXsk6J0Tt5zmbhRqZBwedsxCDKCCfoqI6lzyIvof0laUd17DeD");
        rp.put("name","jhjk");
        rp.put("source",is,dataPath);
        client.post(this, "https://graph-video.facebook.com/me/videos", rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {

            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }



        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        System.out.println("data  " + "");
        callbackManager.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_GALLERY_VIDEO) {
                Uri selectedImageUri = data.getData();

                // OI FILE Manager
                String filemanagerstring = selectedImageUri.getPath();

                // MEDIA GALLERY
//                String selectedImagePath = getPath(selectedImageUri);



                String src = getPath(selectedImageUri);




                File source = new File(src);
                String filename = selectedImageUri.getLastPathSegment();
                File destination = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/CustomFolder/" + filename);

                System.out.println("VideoPath==>  "  +filemanagerstring + " ** " + " * *" + destination);
//                String path = selectedImageUri.uri.getPath(); // "file:///mnt/sdcard/FileName.mp3"
                try {
                    File file = new File(new URI(filename));
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
                System.out.println("VideoPath==>  "  +filemanagerstring + " ** "+ file + " * *" + destination);
            }
        }


    }

    // UPDATED!
    public String getPath(Uri uri) {
        /*String[] projection = { MediaStore.Video.Media.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        if (cursor != null) {
            // HERE YOU WILL GET A NULLPOINTER IF CURSOR IS NULL
            // THIS CAN BE, IF YOU USED OI FILE MANAGER FOR PICKING THE MEDIA
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Video.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } else
            return null;*/

        String path = null;
        String[] projection = { MediaStore.Files.FileColumns.DATA };
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

        if(cursor == null){
            path = uri.getPath();
        }
        else{
            cursor.moveToFirst();
            int column_index = cursor.getColumnIndexOrThrow(projection[0]);
            path = cursor.getString(column_index);
            cursor.close();
        }

        return ((path == null || path.isEmpty()) ? (uri.getPath()) : path);
    }


    //Post to wall function
    public void facebookWallPost() {
        System.out.println("Post  ");
        /*ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse("https://developers.facebook.com"))
                .build();
//        ShareButton shareButton = (ShareButton)findViewById(R.id.share_btn);
        shareButton.setShareContent(content);*/

        if(counter == 0) {
            //save the screenshot
//            View rootView = findViewById(android.R.id.content).getRootView();
//            rootView.setDrawingCacheEnabled(true);
//            // creates immutable clone of image
//            image = Bitmap.createBitmap(rootView.getDrawingCache());
             image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
            // destroy
//            rootView.destroyDrawingCache();

            //share dialog
            AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
            shareDialog.setTitle("Share Screen Shot");
            shareDialog.setMessage("Share image to Facebook?");
            shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //share the image to Facebook
                    SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
                    shareButton.setShareContent(content);
                    counter = 1;
                    shareButton.performClick();
                }
            });
            shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            shareDialog.show();
        }
        else {
            counter = 0;
            shareButton.setShareContent(null);
        }



       /* Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.ic_arrow);
        SharePhoto photo = new SharePhoto.Builder()
                .setBitmap(image)
                .build();
        SharePhotoContent content = new SharePhotoContent.Builder()
                .addPhoto(photo)
                .build();*/

//        shareButton.setShareContent(content);

       /* ShareLinkContent content = new ShareLinkContent.Builder().build();
        shareDialog.show(content);*/
        // Pass null as parameter for setLink method to post status update


    /*    FacebookDialog shareDialog = new FacebookDialog.ShareDialogBuilder(this)
                .setLink(null)
                .build();


        uiHelper.trackPendingDialogCall(shareDialog.present());*/

    }


    public void uploadPic() throws Exception  {
       /* try{

//            File file = new File(Environment.getExternalStorageDirectory(),
//                    "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(CONSUMER_KEY)
                    .setOAuthConsumerSecret(CONSUMER_SECRET)
                    .setOAuthAccessToken(ACCESS_KEY)
                    .setOAuthAccessTokenSecret(ACCESS_SECRET);

            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();
            System.out.println("FILE==>  " + file);
            StatusUpdate status = new StatusUpdate("Hello");
            status.setMedia(file);
            twitter.updateStatus(status);}
        catch(TwitterException e){
            Log.d("TAG", "Pic Upload error" + e.getErrorMessage());
            throw e;
        }*/
        Twitter twitter = TwitterFactory.getSingleton();
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println("TWITTER  " + status.getUser().getName() + ":" +
                    status.getText());
        }
    }




    public void postToFacebook(){

//        final EditText message = (EditText) findViewById(R.id.message);

//        if (isFacebookAuthed()){
        Bitmap image=BitmapFactory.decodeResource(getResources(),R.drawable.ic_arrow);
        ByteArrayOutputStream blob=new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG,0,blob);
        byte[] bitmapdata=blob.toByteArray();


        String path = "me/photos";
            AccessToken at = AccessToken.getCurrentAccessToken();
            Bundle parameters = new Bundle();
            parameters.putString("message", "Hello!!!");
        parameters.putByteArray("picture", bitmapdata);
            HttpMethod method = HttpMethod.POST;
            GraphRequest.Callback cb = new GraphRequest.Callback() {

                @Override
                public void onCompleted(GraphResponse graphResponse) {

                    //check graphResponse for success or failure
                    if(graphResponse.getError()==null){
                        Toast.makeText(SocialIntegrationActivity.this, "Successfully posted to Facebook", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(SocialIntegrationActivity.this, "Facebook: There was an error, Please Try Again", Toast.LENGTH_SHORT).show();

                    }
                }
            };

            GraphRequest request = new GraphRequest(at,path,parameters,method,cb);
            request.setParameters(parameters);
            request.executeAsync();


//        }
//        else
//        {
//
//            Toast.makeText(SocialIntegrationActivity.this,"You are not logged into Facebook", Toast.LENGTH_SHORT).show();
//        }

    }



    public void postPicture() {
        //check counter

        System.out.println("IN SHARE");
        if(counter == 0) {
            //save the screenshot
            View rootView = findViewById(android.R.id.content).getRootView();
            rootView.setDrawingCacheEnabled(true);
            // creates immutable clone of image
            image = Bitmap.createBitmap(rootView.getDrawingCache());
            // destroy
            rootView.destroyDrawingCache();

            //share dialog
            AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
            shareDialog.setTitle("Share Screen Shot");
            shareDialog.setMessage("Share image to Facebook?");
            shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //share the image to Facebook
                    SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
//                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();

                    ShareLinkContent content = new ShareLinkContent.Builder()
                            .setContentUrl(Uri.parse("https://developers.facebook.com"))
                            .build();
                    shareButton.setShareContent(content);
                    counter = 1;
                    shareButton.performClick();
                }
            });
            shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            shareDialog.show();
        }
        else {
            counter = 0;
            shareButton.setShareContent(null);
        }
    }


/*

    public void postImageToWall() {

        facebook.authorize(
                this,
                new String[] { "user_photos,publish_checkins,publish_actions,publish_stream" },
                new DialogListener() {

                    @Override
                    public void onFacebookError(FacebookError e) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onError(DialogError dialogError) {
                        // TODO Auto-generated method stub
                    }

                    @Override
                    public void onComplete(Bundle values) {
                        postImageonWall();
                    }

                    @Override
                    public void onCancel() {
                        // TODO Auto-generated method stub
                    }
                });
    }

    private void postImageonWall() {
        byte[] data = null;

        Bitmap bi = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bi.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        data = baos.toByteArray();
        Bundle params = new Bundle();
        params.putString(Facebook.TOKEN, facebook.getAccessToken());
        params.putString("method", "photos.upload");
        params.putByteArray("picture", data); // image to post
        params.putString("caption", "My text on wall with Image "); // text to post
        AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);
        mAsyncRunner.request(null, params, "POST", new SampleUploadListener(),
                null);
    }
*/

//    https://api.twitter.com/oauth/authorize?oauth_token=BoiwpQAAAAAA06AkAAABXr4ZQuk



public void postVideo(){

 /*   byte[] data = null;
    String dataPath = "/mnt/sdcard/KaraokeVideos/myvideo.3gp";
    String dataMsg = "Your video description here.";
    Bundle param;
//    facebook = new Facebook(FB_APP_ID);
    AsyncFacebookRunner mAsyncRunner = new AsyncFacebookRunner(facebook);
    InputStream is = null;
    try {
        is = new FileInputStream(dataPath);
        data = readBytes(is);
        param = new Bundle();
        param.putString("message", dataMsg);
        param.putString("filename", dataName);
        param.putByteArray("video", data);
        mAsyncRunner.request("me/videos", param, "POST", new fbRequestListener(), null);
    }
    catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    catch (IOException e) {
        e.printStackTrace();
    }
*/
}


    public byte[] readBytes(InputStream inputStream) throws IOException {
        // This dynamically extends to take the bytes you read.
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

        // This is storage overwritten on each iteration with bytes.
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        // We need to know how may bytes were read to write them to the byteBuffer.
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }

        // And then we can return your byte array.
        return byteBuffer.toByteArray();
    }
public void selectVideo(){

    Intent intent = new Intent();
    intent.setType("video/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent,"Select Video"),REQUEST_TAKE_GALLERY_VIDEO);

}


}
