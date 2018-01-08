//package com.example.dell.socialsift.ui;
//
//
//import com.example.dell.socialsift.R;
//
//import static com.facebook.FacebookSdk.getApplicationContext;
//
///**
// * Created by DELL on 9/25/2017.
// */
//
//public class PostActivity2 extends ActionBarActivity {
//
//    // share button
//    private ShareButton shareButton;
//    //image
//    private Bitmap image;
//    //counter
//    private int counter = 0;
//
//    Toast t;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        //initialize facebook sdk
//        FacebookSdk.sdkInitialize(getApplicationContext());
//        setContentView(R.layout.activity_main25);
//
////        callbackManager = CallbackManager.Factory.create();
////        shareDialog = new ShareDialog(this);
////        // this part is optional
////        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
////
////        });
//
//
//        //share button
//        shareButton = (ShareButton) findViewById(R.id.share_btn);
//        shareButton.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                postPicture();
//            }
//        });
//    }
//
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
//
//        return super.onOptionsItemSelected(item);
//    }
//
//    public void postPicture() {
//        //check counter
//        if(counter == 0) {
//            //save the screenshot
//            View rootView = findViewById(android.R.id.content).getRootView();
//            rootView.setDrawingCacheEnabled(true);
//            // creates immutable clone of image
//            image = Bitmap.createBitmap(rootView.getDrawingCache());
//            // destroy
//            rootView.destroyDrawingCache();
//
//            //share dialog
//            AlertDialog.Builder shareDialog = new AlertDialog.Builder(this);
//            shareDialog.setTitle("Share Screen Shot");
//            shareDialog.setMessage("Share image to Facebook?");
//            shareDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    //share the image to Facebook
//                    SharePhoto photo = new SharePhoto.Builder().setBitmap(image).build();
//                    SharePhotoContent content = new SharePhotoContent.Builder().addPhoto(photo).build();
//                    shareButton.setShareContent(content);
//
//
////                    sendButton.registerCallback(callbackManager, new Fa
//                    counter = 1;
//                    shareButton.performClick();
//                }
//            });
//            shareDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int which) {
//                    dialog.cancel();
//                }
//            });
//            shareDialog.show();
//        }
//        else {
//            counter = 0;
//            shareButton.setShareContent(null);
//        }
//    }
//}