//package com.example.dell.socialsift.sample;
//
//import io.socket.IOAcknowledge;
//import io.socket.IOCallback;
//import io.socket.SocketIO;
//import io.socket.SocketIOException;
//
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.Serializable;
//import java.io.UnsupportedEncodingException;
//import java.net.MalformedURLException;
//import java.security.InvalidAlgorithmParameterException;
//import java.security.InvalidKeyException;
//import java.security.NoSuchAlgorithmException;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Locale;
//
//import javax.crypto.BadPaddingException;
//import javax.crypto.IllegalBlockSizeException;
//import javax.crypto.NoSuchPaddingException;
//
//import org.apache.http.NameValuePair;
//import org.java_websocket.util.Base64;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.ActivityNotFoundException;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.database.Cursor;
//import android.graphics.Bitmap;
//import android.graphics.Bitmap.CompressFormat;
//import android.graphics.BitmapFactory;
//import android.graphics.drawable.BitmapDrawable;
//import android.media.MediaPlayer;
//import android.media.MediaRecorder;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Environment;
//import android.provider.DocumentsContract;
//import android.provider.MediaStore;
//import android.support.annotation.Nullable;
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentActivity;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentTransaction;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageButton;
//import android.widget.LinearLayout.LayoutParams;
//import android.widget.PopupWindow;
//import android.widget.RelativeLayout;
//import android.widget.Toast;
//
//import com.ipaulpro.afilechooser.utils.FileUtils;
//import com.surveysignal.adapters.ActivityAdapter;
//import com.surveysignal.database.DatabaseHelper;
//import com.surveysignal.encode.CryptLib;
//import com.surveysignal.main.GCMIntentService;
//import com.surveysignal.main.MainScreen;
//import com.surveysignal.main.R;
//import com.surveysignal.maplocation.DemoLocation;
//import com.surveysignal.researchdatamodel.ResearchChatModel;
//import com.surveysignal.researchdatamodel.ResearchListModel;
//
//@SuppressLint("NewApi")
//public class DemoChat extends Fragment {
//
//    public static DatabaseHelper db;
//    Context context;
//    ImageButton attachment, send;
//    EditText yourText;
//    Button loadButton;
//    List<NameValuePair> list;
//    LayoutInflater inflater;
//    private PopupWindow mpopup;
//    ImageButton camera, video, audio, gallery, location, file;
//    RelativeLayout main_layout;
//    View popUpView;
//    java.util.Date date;
//    String timeStamp;
//    MediaPlayer mediaPlayer;
//
//    //camera & video variables
//    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
//    private static final int CAMERA_CAPTURE_VIDEO_REQUEST_CODE = 200;
//    private static final int MEDIA_TYPE_IMAGE = 1;
//    private static final int MEDIA_TYPE_VIDEO = 2;
//    private static final int PICK_IMAGE = 1;
//    File mediaFile;
//    String capturedFile;
//    Uri fileUri;
//
//    //gallery variables
//    Bitmap bitmap;
//    String imageFile;
//    String filePath = "";
//
//    //Audio record variables
//    private MediaRecorder myAudioRecorder;
//    private int currentFormat = 0;
//    private int output_formats[] = {MediaRecorder.OutputFormat.MPEG_4, MediaRecorder.OutputFormat.THREE_GPP};
//
//    File audiofile;
//    String outputfile;
//    int count = 0;
//
//    //	location variables
//    View locationView;
//    double latitude = 0.0, longitude = 0.0;
//    String lat_lng;
//    String[] splitted_latlng;
//    String locSurvey, locRName;
//    int flag;
//
//    //File variables
//    String fileExtension, fileExtensionVal;
//    String fileExtensiondoc, fileExtensionValdoc;
//
//
//    private static final int REQUEST_CODE = 0;
//    File document;
//    String documentextension;
//    //Socket data
//    SocketIO socket = null;
//    Calendar calender;
//    String currentDate;
//    String message, msgDate;
//    String url;
//    String mms, surveyId, deviceId, ddate;
//
//    //	Lists
//    ResearchListModel researchModel;
//    ResearchChatModel chatModel;
//    List<ResearchChatModel> chatModelList;
//    public static List<ResearchChatModel> chatSaved;
//
//    List<ResearchChatModel> savedlist;
//    ResearchChatModel savedModel;
//
//    //	public static ListView chatListView;
//    //	DemoAdapter adapterDemo;
//
//    //Recycler view
//    private LinearLayoutManager mLayoutManager;
//    public static RecyclerView chatListView;
//    ResearchChatModel modelRec;
//    ActivityAdapter activityAdapter;
//    long chatId;
//
//    ////Scroll States
//    int mLastFirstVisibleItem;
//    boolean mIsScrollingUp = false, mIsScrollingDown = false;
//    String notification_survey;
//
//    //Request data
//    String reqmms, reqsurveyId, reqdate, reqcontent, reqdeviceId, reqlat, reqlng;
//
//    //CryptLib
//
//    CryptLib cryptLib, cryptLibloc;
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater,
//                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//
//        mediaPlayer = new MediaPlayer();
//        View rootView = inflater.inflate(R.layout.demochat_recycler, container, false);
//        chatListView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
//        yourText = (EditText) rootView.findViewById(R.id.your_text);
//        send = (ImageButton) rootView.findViewById(R.id.send);
//
//        loadButton = (Button) rootView.findViewById(R.id.load_button);
//        return rootView;
//
//    }
//
//    @Override
//    public void onActivityCreated(Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//        chatListView.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(getActivity());
//        mLayoutManager.setStackFromEnd(true);
//        chatListView.setLayoutManager(mLayoutManager);
//
//        chatSaved = new ArrayList<ResearchChatModel>();
//        savedlist = new ArrayList<ResearchChatModel>();
//        db = new DatabaseHelper(getActivity());
//        db.createResearchChat();
//
//
//        chatListView.setOnScrollListener(new RecyclerView.OnScrollListener() {
//
//            @Override
//            public void onScrolled(int dx, int dy) {
//
//
//                int firstVisibleItem = ((LinearLayoutManager) chatListView.getLayoutManager()).findFirstVisibleItemPosition();
//
//                if (firstVisibleItem == 0) {
//
//                    if (chatSaved.size() >= 0) {
//                        loadButton.setVisibility(View.VISIBLE);
//                    }
//
//                } else {
//                    loadButton.setVisibility(View.GONE);
//                }
//            }
//
//            @Override
//            public void onScrollStateChanged(int newState) {
//
//            }
//
//        });
//
//        loadButton.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                try {
//                    socket = new SocketIO(MainScreen.url);
//
//                    socket.connect(new IOCallback() {
//
//                        @Override
//                        public void onDisconnect() {
//                            System.out.println("disconnected");
//                        }
//
//                        @Override
//                        public void onConnect() {
//                            System.out.println("connected");
//                        }
//
//                        @Override
//                        public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                            System.out.println("Server said data: " + data);
//                            System.out.println("Data ====>>>> " + data);
//
//                        }
//
//                        @Override
//                        public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                            try {
//                                System.out.println("Server Json string 2 ====>>>>" + jsonObject.toString(2));
//                                System.out.println("Server Json ====>>>>" + jsonObject.toString());
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//
//
//                        @Override
//                        public void onError(SocketIOException socketIOException) {
//                            System.out.println("an Error occured");
//                            socketIOException.printStackTrace();
//
//                        }
//
//                        @Override
//                        public void on(String arg0, IOAcknowledge arg1,
//                                       Object... Objects) {
//                            System.out.println("Server Response" + Objects[0].toString());
//
//                            try {
//                                JSONObject jsonObj = new JSONObject(Objects[0].toString());
//                                JSONArray jsonArray = jsonObj.getJSONArray("msg");
//                                for (int i = 0; i <= jsonArray.length(); i++) {
//                                    JSONObject jsonInnerObj = jsonArray.getJSONObject(i);
//                                    reqmms = jsonInnerObj.getString("mms");
//                                    reqsurveyId = jsonInnerObj.getString("SurveyID");
//                                    reqdate = jsonInnerObj.getString("SurWindowSignalDate");
//                                    reqcontent = jsonInnerObj.getString("SignalContent");
//                                    reqdeviceId = jsonInnerObj.getString("DeviceId");
//                                    reqlat = jsonInnerObj.getString("longitude");
//                                    reqlng = jsonInnerObj.getString("latitude");
//
//                                    System.out.println("Request data" + reqmms + "" + reqsurveyId + "" + reqdate + "" + reqcontent + "" + reqdeviceId + "" + reqlat + "" + reqlng);
//                                    modelRec = new ResearchChatModel();
//                                    modelRec.setMms(reqmms);
//                                    modelRec.setSurveyId(reqsurveyId);
//                                    modelRec.setTimestamp(reqdate);
//                                    modelRec.setMessage(reqcontent);
//                                    modelRec.setDeviceId(reqdeviceId);
//                                    chatSaved.add(modelRec);
//
//                                }
//
//                                activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                                chatListView.setAdapter(activityAdapter);
//                            } catch (JSONException e) {
//                                // TODO Auto-generated catch block
//                                e.printStackTrace();
//                            }
//                        }
//                    });
//
//                    try {
//
//                        Calendar c = Calendar.getInstance();
//                        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
//                        String currentDate = df.format(c.getTime());
//                        String beforeDate = (c.get(Calendar.DATE) - 2) + "-"
//                                + (c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.YEAR);
//
//                        System.out.println("Sending message to server.");
//                        JSONObject list = new JSONObject();
//                        list.put("startDate", beforeDate);
//                        list.put("endDate", currentDate);
//                        list.put("deviceId", MainScreen.uniqueDeviceId);
//                        if (researchModel == null) {
//                            list.put("surveyId", locSurvey);
//                        } else {
//                            list.put("surveyId", researchModel.getSurveyId());
//                        }
//                        socket.emit("requestData", list);
//
//                    } catch (Exception e) {
//
//                        e.printStackTrace();
//                    }
//
//                } catch (MalformedURLException e1) {
//
//                    e1.printStackTrace();
//                }
//
//                //				calender = Calendar.getInstance();
//                //				currentDate = ""+calender.get(Calendar.DATE)+" "+ calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH)+" "+calender.get(Calendar.YEAR);
//                //
//                //				modelRec = new ResearchChatModel();
//                //
//                //				if(researchModel == null){
//                //					modelRec.setSurveyId(locSurvey);
//                //				}else{
//                //					modelRec.setSurveyId(researchModel.getSurveyId());
//                //				}
//                //				modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                //				modelRec.setMms("message");
//                //				modelRec.setMessage(yourText.getText().toString());
//                //				modelRec.setTimestamp(currentDate);
//                //				modelRec.setFlag("1");
//
//                //				if(researchModel == null){
//                //					chatId = db.updateChat(locSurvey,MainScreen.uniqueDeviceId,"message",yourText.getText().toString(),currentDate,"1");
//                //
//                //				}else{
//                //					chatId  = db.updateChat(researchModel.getSurveyId(),MainScreen.uniqueDeviceId,"message",yourText.getText().toString(),currentDate,"1");
//                //				}
//                //				modelRec.setChat_id(chatId);
//                //				yourText.setText("");
//                //				chatSaved.add(modelRec);
//                //				activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                //				chatListView.setAdapter(activityAdapter);
//                ////				db.getChatsCount(modelRec.getSurveyId());
//                ////				db.getChatsCount2(modelRec);
//
//                loadButton.setVisibility(View.GONE);
//            }
//        });
//
//
//        if (getArguments() != null) {
//
//            flag = getArguments().getInt("flag");
//            if (flag == 0) {
//                latitude = getArguments().getDouble("lat");
//                longitude = getArguments().getDouble("lng");
//                locSurvey = getArguments().getString("locSurvey");
//                locRName = getArguments().getString("locRname");
//                chatSaved = (List<ResearchChatModel>) getArguments().getSerializable("chatSaved");
//
//                if (latitude == 0.0 && longitude == 0.0) {
//                    Toast.makeText(getActivity(), "No location Found", Toast.LENGTH_SHORT).show();
//                } else {
//
//                    lat_lng = latitude + "," + longitude;
//                    splitted_latlng = lat_lng.split(",");
//
//                    calender = Calendar.getInstance();
//                    currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                    modelRec = new ResearchChatModel();
//                    modelRec.setSurveyId(locSurvey);
//                    modelRec.setMms("location");
//                    modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                    modelRec.setMessage(lat_lng);
//                    modelRec.setTimestamp(currentDate);
//                    modelRec.setFlag("1");
//
//                    if (researchModel == null) {
//                        chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "location", lat_lng, currentDate, "1");
//
//                    } else {
//                        chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "location", lat_lng, currentDate, "1");
//                    }
//                    modelRec.setChat_id(chatId);
//                    chatSaved.add(modelRec);
//                    activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                    chatListView.setAdapter(activityAdapter);
//
//                    db.getChatsCount2(modelRec);
//
//                    try {
//                        socket = new SocketIO(MainScreen.url);
//
//                        socket.connect(new IOCallback() {
//
//                            @Override
//                            public void onDisconnect() {
//                                System.out.println("disconnected");
//                            }
//
//                            @Override
//                            public void onConnect() {
//                                System.out.println("connected");
//                            }
//
//                            @Override
//                            public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                                System.out.println("Server said data: " + data);
//                                System.out.println("Data ====>>>> " + data);
//
//                            }
//
//                            @Override
//                            public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                                try {
//                                    System.out.println("Server Json string 2 ====>>>>" + jsonObject.toString(2));
//                                    System.out.println("Server Json ====>>>>" + jsonObject.toString());
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//                            @Override
//                            public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//                            }
//
//                            @Override
//                            public void onError(SocketIOException socketIOException) {
//                                System.out.println("an Error occured");
//                                socketIOException.printStackTrace();
//
//                            }
//                        });
//
//                        System.out.println("Sending message to server.");
//                        JSONObject list = new JSONObject();
//                        try {
//                            list.put("latitude", latitude);
//                            list.put("longitude", longitude);
//                            if ((MainScreen.regId).equals("")) {
//                                list.put("GCMId", GCMIntentService.regID);
//                            } else {
//                                list.put("GCMId", MainScreen.regId);
//                            }
//                            list.put("deviceId", MainScreen.uniqueDeviceId);
//                            list.put("surveyId", locSurvey);
//                            list.put("mms", "location");
//                            list.put("date", currentDate);
//
//                            cryptLibloc = new CryptLib();
//
//                            String Currentlocation = cryptLibloc.encrypt(list.toString(), CryptLib.key, CryptLib.iv);
//
//                            System.out.println("List Currentlocation" + Currentlocation);
//
//                            socket.emit("location", Currentlocation);
////							socket.emit("location",list);
//
//
//                            System.out.println("List size" + list.length());
//                            System.out.println("List data current location" + list);
//                        } catch (JSONException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (NoSuchAlgorithmException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (NoSuchPaddingException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (InvalidKeyException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (UnsupportedEncodingException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (InvalidAlgorithmParameterException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (IllegalBlockSizeException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        } catch (BadPaddingException e) {
//                            // TODO Auto-generated catch block
//                            e.printStackTrace();
//                        }
//
//                    } catch (MalformedURLException e1) {
//
//                        e1.printStackTrace();
//                    }
//                }
//            } else if (flag == 1) {
//                researchModel = (ResearchListModel) getArguments().getSerializable(getActivity().getPackageName());
//                chatSaved = db.getAllChatsSurvey(researchModel.getSurveyId());
//            } else {
//                researchModel = (ResearchListModel) getArguments().getSerializable(getActivity().getPackageName());
//                chatSaved = db.getAllChatsSurvey(researchModel.getSurveyId());
//            }
//        }
//
//
//        date = new java.util.Date();
//        timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date.getTime());
//
//        if (researchModel == null) {
//            MainScreen.title.setText(locRName);
//        } else {
//            MainScreen.title.setText(researchModel.getName());
//        }
//        MainScreen.back_button.setVisibility(View.VISIBLE);
//        MainScreen.backLayout.setVisibility(View.VISIBLE);
//
//        if (MainScreen.action_icon.getVisibility() == View.GONE) {
//            MainScreen.action_icon.setVisibility(View.VISIBLE);
//        }
//        if (MainScreen.bottomTabLayout.getVisibility() == View.GONE) {
//            MainScreen.bottomTabLayout.setVisibility(View.VISIBLE);
//        }
//
//
//        MainScreen.back_button.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                MyStudiesFragment fragment = new MyStudiesFragment();
//                fragmentTransaction.add(R.id.realtabcontent, fragment, "MY_FRAG");
//
//                fragmentTransaction.commit();
//
//            }
//        });
//
//        MainScreen.backLayout.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//                FragmentManager fragmentManager = ((FragmentActivity) getActivity()).getSupportFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                MyStudiesFragment fragment = new MyStudiesFragment();
//                fragmentTransaction.add(R.id.realtabcontent, fragment, "MY_FRAG");
//                fragmentTransaction.commit();
//
//            }
//        });
//
//
//        if (chatSaved.size() == 0) {
//            Toast.makeText(getActivity(), "No Chats", Toast.LENGTH_SHORT).show();
//        } else {
//
//            activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//            chatListView.setAdapter(activityAdapter);
//        }
//
//
//        send.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                if (yourText.getText().toString().equals("")) {
//                    Toast.makeText(getActivity(), "Can't send empty text", Toast.LENGTH_SHORT).show();
//                } else {
//                    try {
//                        socket = new SocketIO(MainScreen.url);
//
//                        socket.connect(new IOCallback() {
//
//                            @Override
//                            public void onDisconnect() {
//                                System.out.println("disconnected");
//                            }
//
//                            @Override
//                            public void onConnect() {
//                                System.out.println("connected");
//                            }
//
//                            @Override
//                            public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                                System.out.println("Server said data: " + data);
//                                System.out.println("Data ====>>>> " + data);
//
//                            }
//
//
//                            @Override
//                            public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                                try {
//                                    System.out.println("Server Json string 2 ====>>>>" + jsonObject.toString(2));
//                                    System.out.println("Server Json ====>>>>" + jsonObject.toString());
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
//                            }
//
//
//                            @Override
//                            public void onError(SocketIOException socketIOException) {
//                                System.out.println("an Error occured");
//                                socketIOException.printStackTrace();
//
//                            }
//
//                            @Override
//                            public void on(String arg0, IOAcknowledge arg1,
//                                           Object... arg2) {
//                                // TODO Auto-generated method stub
//
//                            }
//                        });
//
//                        try {
//
//                            calender = Calendar.getInstance();
//                            currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                            System.out.println("Sending message to server.");
//                            JSONObject list = new JSONObject();
//                            list.put("message", yourText.getText().toString());
//
//                            if ((MainScreen.regId).equals("")) {
//                                list.put("GCMId", GCMIntentService.regID);
//                            } else {
//                                list.put("GCMId", MainScreen.regId);
//                            }
//
//                            list.put("deviceId", MainScreen.uniqueDeviceId);
//                            if (researchModel == null) {
//                                list.put("surveyId", locSurvey);
//                            } else {
//                                list.put("surveyId", researchModel.getSurveyId());
//                            }
//                            list.put("mms", "message");
//                            list.put("date", currentDate);
//
//                            cryptLib = new CryptLib();
//
//                            System.out.println("Sending msgeny msgeny msgeny.=Encrypted==>" + list.toString());
//                            String msgeny = cryptLib.encrypt(list.toString(), CryptLib.key, CryptLib.iv);
//
//                            System.out.println("Sending msgeny msgeny msgeny.=Encrypted==>" + msgeny);
//
//                            String msgdec = cryptLib.decrypt(msgeny, CryptLib.key, CryptLib.iv);
//
//                            System.out.println("Sending msgeny msgeny msgeny.=Decrypted==>" + msgdec);
//
////							socket.emit("sendMessage",msgeny);
////							socket.emit("demo",list);
//                            socket.emit("message", msgeny);
//
//                        } catch (Exception e) {
//
//                            e.printStackTrace();
//                        }
//
//                    } catch (MalformedURLException e1) {
//
//                        e1.printStackTrace();
//                    }
//
//                    calender = Calendar.getInstance();
//                    currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                    modelRec = new ResearchChatModel();
//
//                    if (researchModel == null) {
//                        modelRec.setSurveyId(locSurvey);
//                    } else {
//                        modelRec.setSurveyId(researchModel.getSurveyId());
//                    }
//                    modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                    modelRec.setMms("message");
//                    modelRec.setMessage(yourText.getText().toString());
//                    modelRec.setTimestamp(currentDate);
//                    modelRec.setFlag("1");
//
//                    if (researchModel == null) {
//                        chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "message", yourText.getText().toString(), currentDate, "1");
//
//                    } else {
//                        chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "message", yourText.getText().toString(), currentDate, "1");
//                    }
//                    modelRec.setChat_id(chatId);
//                    yourText.setText("");
//                    chatSaved.add(modelRec);
//
//                    activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                    chatListView.setAdapter(activityAdapter);
//
//                    //					db.getChatsCount(modelRec.getSurveyId());
//                    db.getChatsCount2(modelRec);
//
//
//                    //					if(db.getChatsCount(modelRec.getSurveyId()) > 20)
//                    //					{
//                    //
//                    //						//					chatSaved.remove(0);
//                    //						System.out.println("***************************** DemoChat Row delete ********************************");
//                    //						db.deleteRow(modelRec);
//                    //						System.out.println(" Table chat Count after delete in Chat==>>" +db.getChatsCount(modelRec.getSurveyId()));
//                    //						System.out.println("chatSaved Size after delete " +chatSaved.size());
//                    //
//                    //					}
//
//                }
//            }
//        });
//
//
//        attachment = (ImageButton) getActivity().findViewById(R.id.attachment);
//
//        attachment.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                inflater = (LayoutInflater) getActivity().getSystemService(getActivity().LAYOUT_INFLATER_SERVICE);
//                popUpView = inflater.inflate(R.layout.attachment_popup, null);
//                popupItems();
//                mpopup = new PopupWindow(popUpView, LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT, true);
//                mpopup.setWindowLayoutMode(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
//                mpopup.setAnimationStyle(android.R.style.Animation_Dialog);
//                mpopup.showAtLocation(popUpView, Gravity.BOTTOM, -280, 300);
//                mpopup.setBackgroundDrawable(new BitmapDrawable());
//                mpopup.setOutsideTouchable(true);
//                mpopup.setFocusable(true);
//
//                camera.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        mpopup.dismiss();
//                        captureImage();
//
//                    }
//
//                    private void captureImage() {
//
//                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//                        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
//
//                    }
//
//                });
//
//
//                if (!isDeviceSupportCamera()) {
//                    Toast.makeText(getActivity(), "Sorry! Your device doesn't support camera", Toast.LENGTH_LONG).show();
//                    getActivity().finish();
//                }
//
//                video.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        mpopup.dismiss();
//                        recordVideo();
//                    }
//
//                    private void recordVideo() {
//
//                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
//                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_VIDEO);
//                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
//                        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//                        startActivityForResult(intent, CAMERA_CAPTURE_VIDEO_REQUEST_CODE);
//                    }
//
//                });
//
//                audio.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//
//                        if (v.isSelected()) {
//
//                            startRecording();
//                            audio.setSelected(!audio.isSelected());
//
//                        } else {
//                            stopRecording();
//                            audio.setSelected(!audio.isSelected());
//                            getFilename();
//
//
//                            String filenameArray[] = mediaFile.toString().split("\\.");
//                            fileExtension = filenameArray[filenameArray.length - 1];
//                            fileExtensionVal = '"' + fileExtension + '"';
//                            //							fileExtensionVal = "'" +fileExtension+ "'";
//                            calender = Calendar.getInstance();
//                            currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                            if (researchModel == null) {
//                                chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "audio", mediaFile.toString(), currentDate, "1");
//                            } else {
//                                chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "audio", mediaFile.toString(), currentDate, "1");
//                            }
//
//                            encodeAudio(mediaFile);
//
//                            modelRec = new ResearchChatModel();
//                            if (researchModel == null) {
//                                modelRec.setSurveyId(locSurvey);
//                            } else {
//                                modelRec.setSurveyId(researchModel.getSurveyId());
//                            }
//                            modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                            modelRec.setMms("audio");
//                            modelRec.setMessage(mediaFile.toString());
//                            modelRec.setTimestamp(currentDate);
//                            modelRec.setFlag("1");
//                            modelRec.setChat_id(chatId);
//                            chatSaved.add(modelRec);
//
//                            activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                            chatListView.setAdapter(activityAdapter);
//
//                            db.getChatsCount2(modelRec);
//
//                        }
//
//                    }
//
//                    private void stopRecording() {
//
//                        if (myAudioRecorder != null) {
//                            myAudioRecorder.reset();
//                            myAudioRecorder.release();
//                            myAudioRecorder = null;
//                        }
//
//                        Toast.makeText(getActivity(), "Audio recorded successfully", Toast.LENGTH_LONG).show();
//
//                    }
//
//                    private void startRecording() {
//
//
//                        myAudioRecorder = new MediaRecorder();
//                        myAudioRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
//                        myAudioRecorder.setOutputFormat(output_formats[currentFormat]);
//                        myAudioRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                        myAudioRecorder.setOutputFile(getFilename().toString());
//                        myAudioRecorder.setOnErrorListener(errorListener);
//                        myAudioRecorder.setOnInfoListener(infoListener);
//                        try {
//                            myAudioRecorder.prepare();
//                            myAudioRecorder.start();
//                        } catch (IllegalStateException e) {
//                            e.printStackTrace();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        Toast.makeText(getActivity(), "Recording started", Toast.LENGTH_LONG).show();
//
//                    }
//
//                    private File getFilename() {
//
//                        audiofile = new File(Environment.getExternalStoragePublicDirectory(
//                                Environment.DIRECTORY_DCIM), "SurveySignalAudios");
//
//                        if (!audiofile.exists()) {
//                            audiofile.mkdirs();
//                        }
//
//                        mediaFile = new File(audiofile.getPath() + File.separator + "AUD_" + timeStamp + ".wav");
//                        System.out.println("ChatScreenFragment.getOutputMediaFile()" + mediaFile.toString());
//                        return mediaFile;
//
//                    }
//
//
//                    private MediaRecorder.OnErrorListener errorListener = new MediaRecorder.OnErrorListener() {
//                        @Override
//                        public void onError(MediaRecorder mr, int what, int extra) {
//                            Toast.makeText(getActivity(), "Error: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
//                        }
//                    };
//
//                    private MediaRecorder.OnInfoListener infoListener = new MediaRecorder.OnInfoListener() {
//                        @Override
//                        public void onInfo(MediaRecorder mr, int what, int extra) {
//                            Toast.makeText(getActivity(), "Warning: " + what + ", " + extra, Toast.LENGTH_SHORT).show();
//                        }
//                    };
//
//                });
//
//
//                location.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//                        Intent intent = new Intent(getActivity(), DemoLocation.class);
//                        if (researchModel == null) {
//                            intent.putExtra("surveyId", locSurvey);
//                            intent.putExtra("researchName", locRName);
//                        } else {
//                            intent.putExtra("surveyId", researchModel.getSurveyId());
//                            intent.putExtra("researchName", researchModel.getName());
//                        }
//
//                        intent.putExtra("chatSaved", (Serializable) chatSaved);
//                        getActivity().startActivity(intent);
//
//                        mpopup.dismiss();
//                    }
//
//                });
//
//                gallery.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(getActivity(), "gallery", Toast.LENGTH_SHORT).show();
//                        mpopup.dismiss();
//                        selectImageFromGallery();
//                    }
//
//                    private void selectImageFromGallery() {
//
//                        Intent intent = new Intent();
//                        intent.setType("image/*");
//                        intent.setAction(Intent.ACTION_GET_CONTENT);
//                        startActivityForResult(Intent.createChooser(intent, "Select Picture"),
//                                PICK_IMAGE);
//
//                    }
//
//                });
//
//                file.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//
//                        mpopup.dismiss();
//                        showChooser();
//
//                    }
//
//                    private void showChooser() {
//
//                        Intent target = FileUtils.createGetContentIntent();
//                        Intent intent = Intent.createChooser(target, "Choose File");
//
//                        try {
//                            startActivityForResult(intent, REQUEST_CODE);
//                        } catch (ActivityNotFoundException e) {
//                            // The reason for the existence of aFileChooser
//                        }
//
//                    }
//                });
//
//                main_layout.setOnClickListener(new OnClickListener() {
//
//                    @Override
//                    public void onClick(View v) {
//
//                        mpopup.dismiss();
//
//                    }
//                });
//            }
//
//
//            private boolean isDeviceSupportCamera() {
//                if (getActivity().getPackageManager().hasSystemFeature(
//                        PackageManager.FEATURE_CAMERA)) {
//                    return true;
//                } else {
//                    return false;
//                }
//            }
//
//            private void popupItems() {
//                main_layout = (RelativeLayout) popUpView.findViewById(R.id.main_layout);
//                camera = (ImageButton) popUpView.findViewById(R.id.camera);
//                video = (ImageButton) popUpView.findViewById(R.id.video);
//                audio = (ImageButton) popUpView.findViewById(R.id.audio);
//                audio.setSelected(!audio.isSelected());
//                gallery = (ImageButton) popUpView.findViewById(R.id.gallery);
//                location = (ImageButton) popUpView.findViewById(R.id.location);
//                file = (ImageButton) popUpView.findViewById(R.id.file);
//
//            }
//
//        });
//
//    }
//
//
//    public void encodeCameraImage(File mediaFile) {
//
//        capturedFile = null;
//
//        try {
//
//            capturedFile = com.surveysignal.encode.Base64.encodeFromFile(mediaFile.toString());
//            System.out.println("ChatScreenFragment.decodeMediaFile()" + capturedFile);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        try {
//            socket = new SocketIO(MainScreen.url);
//            socket.connect(new IOCallback() {
//
//                @Override
//                public void onDisconnect() {
//                    System.out.println("disconnected");
//                }
//
//                @Override
//                public void onConnect() {
//                    System.out.println("connected");
//                }
//
//                @Override
//                public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                    System.out.println("Server said: " + data);
//
//                }
//
//                @Override
//                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                    try {
//                        System.out.println("Server said:" + jsonObject.toString(2));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//
//                }
//
//                @Override
//                public void onError(SocketIOException socketIOException) {
//                    System.out.println("an Error occured");
//                    socketIOException.printStackTrace();
//
//                }
//            });
//
//            try {
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//                System.out.println("Sending message to server.");
//                JSONObject imageObj = new JSONObject();
//                imageObj.put("image", capturedFile);
//                if ((MainScreen.regId).equals("")) {
//                    imageObj.put("GCMId", GCMIntentService.regID);
//                } else {
//                    imageObj.put("GCMId", MainScreen.regId);
//                }
//                imageObj.put("deviceId", MainScreen.uniqueDeviceId);
//                if (researchModel == null) {
//                    imageObj.put("surveyId", locSurvey);
//                } else {
//                    imageObj.put("surveyId", researchModel.getSurveyId());
//                }
//
//                imageObj.put("mms", "image");
//                imageObj.put("date", currentDate);
//
////				System.out.println("Encrypted image by cam==String=>"+imageObj.toString());
//
//                cryptLib = new CryptLib();
//
//                String imgeny = cryptLib.encrypt(imageObj.toString(), CryptLib.key, CryptLib.iv);
//
//                System.out.println("Encrypted image by cam==imgeny=>" + imgeny);
////
//                String imgdec = cryptLib.decrypt(imgeny, CryptLib.key, CryptLib.iv);
////
//                System.out.println("Encrypted image by cam=imgdec=>" + imgdec);
//                socket.emit("image", imgeny);
////				socket.emit("demo",imageObj);
////				socket.emit("image",imageObj);
//                System.out.println("List size" + imageObj.length());
////				System.out.println("List data" +imageObj);
//                System.out.println("Sending message to server.");
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//        } catch (MalformedURLException e1) {
//
//            e1.printStackTrace();
//        }
//
//    }
//
//
//    public void encodeVideo(File mediaFile) {
//
//        capturedFile = null;
//
//        try {
//
//            capturedFile = com.surveysignal.encode.Base64.encodeFromFile(mediaFile.toString());
//            System.out.println("ChatScreenFragment.decodeMediaFile()" + capturedFile);
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//
//        try {
//            socket = new SocketIO(MainScreen.url);
//
//            socket.connect(new IOCallback() {
//
//                @Override
//                public void onDisconnect() {
//                    System.out.println("disconnected");
//                }
//
//                @Override
//                public void onConnect() {
//                    System.out.println("connected");
//                }
//
//                @Override
//                public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                    System.out.println("Server said: " + data);
//
//                }
//
//                @Override
//                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                    try {
//                        System.out.println("Server said:" + jsonObject.toString(2));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//
//                }
//
//                @Override
//                public void onError(SocketIOException socketIOException) {
//                    System.out.println("an Error occured");
//                    socketIOException.printStackTrace();
//
//                }
//            });
//
//            try {
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//                System.out.println("Sending message to server.");
//                JSONObject videoObj = new JSONObject();
//                videoObj.put("video", capturedFile);
//                if ((MainScreen.regId).equals("")) {
//                    videoObj.put("GCMId", GCMIntentService.regID);
//                } else {
//                    videoObj.put("GCMId", MainScreen.regId);
//                }
//                videoObj.put("deviceId", MainScreen.uniqueDeviceId);
//
//                if (researchModel == null) {
//                    videoObj.put("surveyId", locSurvey);
//                } else {
//                    videoObj.put("surveyId", researchModel.getSurveyId());
//                }
//
//                videoObj.put("mms", "video");
//                videoObj.put("date", currentDate);
//
//                cryptLib = new CryptLib();
//
//                String videoeny = cryptLib.encrypt(videoObj.toString(), CryptLib.key, CryptLib.iv);
//                socket.emit("video", videoeny);
//
////				socket.emit("video",videoObj);
//                System.out.println("List size" + videoObj.length());
//                System.out.println("List data video new" + videoObj);
//                System.out.println("Sending message to server.");
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//        } catch (MalformedURLException e1) {
//
//            e1.printStackTrace();
//        }
//
//    }
//
//
//    public void encodeAudio(File mediaFile) {
//
//        capturedFile = null;
//
//        try {
//
//            capturedFile = com.surveysignal.encode.Base64.encodeFromFile(mediaFile.toString());
//            System.out.println("ChatScreenFragment.decodeMediaFile()" + capturedFile);
//        } catch (IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//
//        try {
//            socket = new SocketIO(MainScreen.url);
//
//            socket.connect(new IOCallback() {
//
//                @Override
//                public void onDisconnect() {
//                    System.out.println("disconnected");
//                }
//
//                @Override
//                public void onConnect() {
//                    System.out.println("connected");
//                }
//
//                @Override
//                public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                    System.out.println("Server said: " + data);
//
//                }
//
//                @Override
//                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                    try {
//                        System.out.println("Server said:" + jsonObject.toString(2));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//
//                }
//
//                @Override
//                public void onError(SocketIOException socketIOException) {
//                    System.out.println("an Error occured");
//                    socketIOException.printStackTrace();
//
//                }
//            });
//
//            try {
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//                System.out.println("Sending message to server.");
//                JSONObject audioObj = new JSONObject();
//                audioObj.put("audio", capturedFile);
//                if ((MainScreen.regId).equals("")) {
//                    audioObj.put("GCMId", GCMIntentService.regID);
//                } else {
//                    audioObj.put("GCMId", MainScreen.regId);
//                }
//                audioObj.put("deviceId", MainScreen.uniqueDeviceId);
//
//                if (researchModel == null) {
//                    audioObj.put("surveyId", locSurvey);
//                } else {
//                    audioObj.put("surveyId", researchModel.getSurveyId());
//                }
//
//                audioObj.put("mms", "audio");
//                audioObj.put("date", currentDate);
//                audioObj.put("extension", fileExtension);
//
//                cryptLib = new CryptLib();
//
//                String audioeny = cryptLib.encrypt(audioObj.toString(), CryptLib.key, CryptLib.iv);
//
//                socket.emit("audio", audioeny);
//
////				socket.emit("audio",audioObj);
//                System.out.println("List size" + audioObj.length());
//                System.out.println("List data" + audioObj);
//                System.out.println("Sending message to server.");
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//        } catch (MalformedURLException e1) {
//
//            e1.printStackTrace();
//        }
//
//    }
//
//
//    public void encodeDocument(File document) throws IOException {
//
//        capturedFile = null;
//        FileInputStream fis = new FileInputStream(document);
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        byte[] buf = new byte[1024];
//        try {
//            for (int readNum; (readNum = fis.read(buf)) != -1; ) {
//                bos.write(buf, 0, readNum);
//                System.out.println("read " + readNum + " bytes,");
//            }
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        byte[] bytes = bos.toByteArray();
//        capturedFile = com.surveysignal.encode.Base64.encodeBytes(bytes);
//        System.out.println("Bytes" + capturedFile);
//
//
//        try {
//            socket = new SocketIO(MainScreen.url);
//
//            socket.connect(new IOCallback() {
//
//                @Override
//                public void onDisconnect() {
//                    System.out.println("disconnected");
//                }
//
//                @Override
//                public void onConnect() {
//                    System.out.println("connected");
//                }
//
//                @Override
//                public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                    System.out.println("Server said: " + data);
//
//                }
//
//                @Override
//                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                    try {
//                        System.out.println("Server said:" + jsonObject.toString(2));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//                @Override
//                public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//
//                }
//
//                @Override
//                public void onError(SocketIOException socketIOException) {
//                    System.out.println("an Error occured");
//                    socketIOException.printStackTrace();
//
//                }
//            });
//
//            try {
//
//                System.out.println("Sending message to server.");
//                JSONObject list = new JSONObject();
//                //				list.put("file", capturedFile); capturedFile
////				list.put("file", "aGlpaSBldmVyeW9uZQ==");
//                list.put("file", capturedFile);
//                //				list.put("extension", fileExtensiondoc);
//                list.put("extension", documentextension);
//                if ((MainScreen.regId).equals("")) {
//                    list.put("GCMId", GCMIntentService.regID);
//                } else {
//                    list.put("GCMId", MainScreen.regId);
//                }
//                list.put("deviceId", MainScreen.uniqueDeviceId);
//                if (researchModel == null) {
//                    list.put("surveyId", locSurvey);
//                } else {
//                    list.put("surveyId", researchModel.getSurveyId());
//                }
//
//                list.put("mms", "document");
//
//                cryptLib = new CryptLib();
//
//                String documenteny = cryptLib.encrypt(list.toString(), CryptLib.key, CryptLib.iv);
//                System.out.println("Document And Extension =documenteny=>>" + documenteny.toString());
//
//                String documentDec = cryptLib.decrypt(documenteny, CryptLib.key, CryptLib.iv);
////				System.out.println("Document And Extension =documentDec=>>" +documentDec);
//
//                if (documenteny != null) {
//                    System.out.println("System =documentDec=>>" + documenteny.toString());
//                    socket.emit("document", documenteny);
//                }
//
//
////				socket.emit("document",list);
////				System.out.println("Document And Extension ==>>" +list.toString());
////				System.out.println("List size" +list.length());
////				System.out.println("List data" +list);
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//        } catch (MalformedURLException e1) {
//
//            e1.printStackTrace();
//        }
//
//    }
//
//
//    public void encodeGalleryImage(String filePath) {
//
//        BitmapFactory.Options options = new BitmapFactory.Options();
//        options.inSampleSize = 3;
//        bitmap = BitmapFactory.decodeFile(filePath, options);
//
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//        bitmap.compress(CompressFormat.JPEG, 100, bos);
//        byte[] data = bos.toByteArray();
//        imageFile = Base64.encodeBytes(data);
//        System.out.println("ChatScreenFragment.decodeFile()" + imageFile);
//
//        try {
//            socket = new SocketIO(MainScreen.url);
//
//            socket.connect(new IOCallback() {
//
//                @Override
//                public void onDisconnect() {
//                    System.out.println("disconnected");
//                }
//
//                @Override
//                public void onConnect() {
//                    System.out.println("connected");
//                }
//
//                @Override
//                public void onMessage(String data, IOAcknowledge ioAcknowledge) {
//
//                    System.out.println("Server said: " + data);
//
//                }
//
//                @Override
//                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
//
//                    try {
//                        System.out.println("Server said:" + jsonObject.toString(2));
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//
//                @Override
//                public void on(String event, IOAcknowledge ioAcknowledge, Object... objects) {
//
//
//                }
//
//                @Override
//                public void onError(SocketIOException socketIOException) {
//                    System.out.println("an Error occured");
//                    socketIOException.printStackTrace();
//
//                }
//            });
//
//            try {
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//                System.out.println("Sending message to server.");
//                JSONObject imageObj = new JSONObject();
//                imageObj.put("image", imageFile);
//                if ((MainScreen.regId).equals("")) {
//                    imageObj.put("GCMId", GCMIntentService.regID);
//                } else {
//                    imageObj.put("GCMId", MainScreen.regId);
//                }
//                imageObj.put("deviceId", MainScreen.uniqueDeviceId);
//                if (researchModel == null) {
//                    imageObj.put("surveyId", locSurvey);
//                } else {
//                    imageObj.put("surveyId", researchModel.getSurveyId());
//                }
//                imageObj.put("surveyId", researchModel.getSurveyId());
//                imageObj.put("mms", "image");
//                imageObj.put("date", currentDate);
//
//                cryptLib = new CryptLib();
//
//                String gallaryimgeny = cryptLib.encrypt(imageObj.toString(), CryptLib.key, CryptLib.iv);
//                socket.emit("image", gallaryimgeny);
//
////				socket.emit("image",imageObj);
//                System.out.println("List size" + imageObj.length());
//                System.out.println("List data" + imageObj);
//
//
//            } catch (Exception e) {
//
//                e.printStackTrace();
//            }
//
//        } catch (MalformedURLException e1) {
//
//            e1.printStackTrace();
//        }
//
//    }
//
//
//    public Uri getOutputMediaFileUri(int type) {
//        return Uri.fromFile(getOutputMediaFile(type));
//    }
//
//    private File getOutputMediaFile(int type) {
//        if (type == MEDIA_TYPE_VIDEO) {
//
//            File videoStorage = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DCIM), "SurveySignalVideos");
//
//            if (!videoStorage.exists()) {
//                if (!videoStorage.mkdirs()) {
//                    Toast.makeText(getActivity(), "Failed to create Video directory .", Toast.LENGTH_LONG).show();
//                    Log.d("SurveySignalVideos", "Failed " +
//                            "to create Video directory .");
//                    return null;
//                }
//
//            }
//            mediaFile = new File(videoStorage.getPath() + File.separator + "VID_" + timeStamp + ".mp4");
//            System.out.println("ChatScreenFragment.getOutputMediaFile()" + mediaFile.toString());
//
//
//        } else if (type == MEDIA_TYPE_IMAGE) {
//
//            File audioStorage = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM), "SurveySignalImages");
//
//            if (!audioStorage.exists()) {
//                if (!audioStorage.mkdirs()) {
//                    Toast.makeText(getActivity(), "Failed to create Audio directory.", Toast.LENGTH_LONG).show();
//                    Log.d("SurveySignalImages", "Failed to create Audio directory.");
//                    return null;
//                }
//            }
//
//            mediaFile = new File(audioStorage.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
//            System.out.println("ChatScreenFragment.getOutputMediaFile()" + mediaFile.toString());
//        } else {
//            return null;
//        }
//        return mediaFile;
//    }
//
//    @Override
//    public void onSaveInstanceState(Bundle outState) {
//        // TODO Auto-generated method stub
//        super.onSaveInstanceState(outState);
//        outState.putParcelable("file_uri", fileUri);
//    }
//
//
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        super.onActivityResult(requestCode, resultCode, data);
//
//        final String picturePath;
//        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK
//                && null != data) {
//            //			Uri selectedImage = data.getData();
//            //			String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            //
//            //			Cursor cursor = getActivity().getContentResolver().query(selectedImage,
//            //					filePathColumn, null, null, null);
//            //			cursor.moveToFirst();
//            //
//            //			int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            //			picturePath = cursor.getString(columnIndex);
//            //			cursor.close();
//
//            ////////////////////////////////////////////////////////////////////////////////
//            Uri selectedImage = data.getData();
//            String wholeID = DocumentsContract.getDocumentId(selectedImage);
//            String id = wholeID.split(":")[1];
//            String[] column = {MediaStore.Images.Media.DATA};
//            String sel = MediaStore.Images.Media._ID + "=?";
//
//            Cursor cursor = getActivity().getContentResolver().
//                    query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                            column, sel, new String[]{id}, null);
//
//            int columnIndex = cursor.getColumnIndex(column[0]);
//
//            if (cursor.moveToFirst()) {
//                filePath = cursor.getString(columnIndex);
//            }
//            cursor.close();
//            ////////////////////////////////////////////////////////////////////////////////////
//            //			System.out.println("Data Getdata == >>" + data.getData());
//            //		        try {
//            //					InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
//            //
//            //					BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//            //				  StringBuilder sb = new StringBuilder();
//            //
//            //				    String line = null;
//            //				    try {
//            //				        while ((line = reader.readLine()) != null) {
//            //				            sb.append(line).append('\n');
//            //				        }
//            //				    } catch (IOException e) {
//            //				        e.printStackTrace();
//            //				    } finally {
//            //				        try {
//            //				        	inputStream.close();
//            //				        } catch (IOException e) {
//            //				            e.printStackTrace();
//            //				        }
//            //				    }
//            //				    filePath = sb.toString();
//            //				} catch (FileNotFoundException e) {
//            //					// TODO Auto-generated catch block
//            //					e.printStackTrace();
//            //				}
//            //Now you can do whatever you want with your inpustream, save it as file, upload to a server, decode a bitmap...
//
//
//            ////////////////////////////////////////////////////////////////////////////////////
//            calender = Calendar.getInstance();
//            currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//            if (researchModel == null) {
//                chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "image", filePath, currentDate, "1");
//            } else {
//                chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "image", filePath, currentDate, "1");
//            }
//
//            encodeGalleryImage(filePath);
//
//            modelRec = new ResearchChatModel();
//            if (researchModel == null) {
//                modelRec.setSurveyId(locSurvey);
//            } else {
//                modelRec.setSurveyId(researchModel.getSurveyId());
//            }
//            modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//            modelRec.setMms("image");
//            modelRec.setMessage(filePath);
//            modelRec.setTimestamp(currentDate);
//            modelRec.setFlag("1");
//            modelRec.setChat_id(chatId);
//
//            chatSaved.add(modelRec);
//            activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//            chatListView.setAdapter(activityAdapter);
//
//            db.getChatsCount2(modelRec);
//
//        }
//
//
//        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK
//                && null != data) {
//            // Get the URI of the selected file
//            final Uri uri = data.getData();
//            document = new File(uri.getPath());
//            System.out.println("ChatScreenFragment File browser uri" + uri.toString());
//            System.out.println("ChatScreenFragment File browser file" + document.toString());
//
//
//            calender = Calendar.getInstance();
//            currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//            if (researchModel == null) {
//                chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "document", document.toString(), currentDate, "1");
//            } else {
//                chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "document", document.toString(), currentDate, "1");
//            }
//
//
//            try {
//                // Get the file path from the URI
//                final String path = FileUtils.getPath(getActivity(), uri);
//                documentextension = path.substring(path.lastIndexOf("."));
//
//                String filenameArray[] = path.split("\\.");
//                fileExtensiondoc = filenameArray[filenameArray.length - 1];
//                fileExtensionValdoc = "'" + fileExtension + "'";
//
//
//            } catch (Exception e) {
//                Log.e("FileSelectorTestActivity", "File select error", e);
//            }
//
//            try {
//                encodeDocument(document);
//            } catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//            modelRec = new ResearchChatModel();
//            if (researchModel == null) {
//                modelRec.setSurveyId(locSurvey);
//            } else {
//                modelRec.setSurveyId(researchModel.getSurveyId());
//            }
//            modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//            modelRec.setMms("document");
//            modelRec.setMessage(document.toString());
//            modelRec.setTimestamp(currentDate);
//            modelRec.setFlag("1");
//            modelRec.setChat_id(chatId);
//
//            chatSaved.add(modelRec);
//            activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//            chatListView.setAdapter(activityAdapter);
//
//            db.getChatsCount2(modelRec);
//
//        }
//
//
//        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//
//                Toast.makeText(getActivity(), "Image saved successfully", Toast.LENGTH_SHORT).show();
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                if (researchModel == null) {
//                    chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "image", mediaFile.toString(), currentDate, "1");
//                } else {
//                    chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "image", mediaFile.toString(), currentDate, "1");
//                }
//
//                encodeCameraImage(mediaFile);
//
//                modelRec = new ResearchChatModel();
//                if (researchModel == null) {
//                    modelRec.setSurveyId(locSurvey);
//                } else {
//                    modelRec.setSurveyId(researchModel.getSurveyId());
//                }
//                modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                modelRec.setMms("image");
//                modelRec.setMessage(mediaFile.toString());
//                modelRec.setTimestamp(currentDate);
//                modelRec.setFlag("1");
//                modelRec.setChat_id(chatId);
//
//                chatSaved.add(modelRec);
//                activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                chatListView.setAdapter(activityAdapter);
//
//                db.getChatsCount2(modelRec);
//
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(getActivity(), "User cancelled image capture", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getActivity(), "Sorry! Failed to capture image", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//
//        if (requestCode == CAMERA_CAPTURE_VIDEO_REQUEST_CODE) {
//            if (resultCode == Activity.RESULT_OK) {
//                Toast.makeText(getActivity(), "Video saved successfully", Toast.LENGTH_SHORT).show();
//
//                calender = Calendar.getInstance();
//                currentDate = "" + calender.get(Calendar.DATE) + " " + calender.getDisplayName(Calendar.MONTH, Calendar.SHORT, Locale.ENGLISH) + " " + calender.get(Calendar.YEAR);
//
//                if (researchModel == null) {
//                    chatId = db.updateChat(locSurvey, MainScreen.uniqueDeviceId, "video", mediaFile.toString(), currentDate, "1");
//                } else {
//                    chatId = db.updateChat(researchModel.getSurveyId(), MainScreen.uniqueDeviceId, "video", mediaFile.toString(), currentDate, "1");
//                }
//
//                encodeVideo(mediaFile);
//
//                modelRec = new ResearchChatModel();
//                if (researchModel == null) {
//                    modelRec.setSurveyId(locSurvey);
//                } else {
//                    modelRec.setSurveyId(researchModel.getSurveyId());
//                }
//                modelRec.setDeviceId(MainScreen.uniqueDeviceId);
//                modelRec.setMms("video");
//                modelRec.setMessage(mediaFile.toString());
//                modelRec.setTimestamp(currentDate);
//                modelRec.setFlag("1");
//                modelRec.setChat_id(chatId);
//
//                chatSaved.add(modelRec);
//                activityAdapter = new ActivityAdapter(getActivity(), chatSaved);
//                chatListView.setAdapter(activityAdapter);
//
//                db.getChatsCount2(modelRec);
//
//
//            } else if (resultCode == Activity.RESULT_CANCELED) {
//                Toast.makeText(getActivity(), "User cancelled video recording", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getActivity(), "Sorry! Failed to record video", Toast.LENGTH_SHORT).show();
//            }
//        }
//
//    }
//
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
