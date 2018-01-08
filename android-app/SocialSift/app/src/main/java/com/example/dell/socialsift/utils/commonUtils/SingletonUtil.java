package com.example.dell.socialsift.utils.commonUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.support.design.widget.Snackbar;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dell.socialsift.beans.common.ErrorMessageResponse;

import java.io.ByteArrayOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;



/**
 * Created by MR JOSHI on 14-Mar-16.
 */
public class SingletonUtil {

    private static SingletonUtil singletonUtil;

    private String TAG = "SingletonUtil";

    public String convert24To12Hr(String dateStr) {

        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
        Date _24HourDt = null;
        try {
            _24HourDt = _24HourSDF.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println(_24HourDt);
        return _12HourSDF.format(_24HourDt);

    }

    public interface OKClickListenerSnackbar {
        void onItemClick();
    }

    private OKClickListenerSnackbar okClickListenerSnackbar;


    private SingletonUtil() {
    }

    public static String formatDateforSort(String date) throws ParseException {

        Date initDate = new SimpleDateFormat("dd-MM-yyyy").parse(date);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String parsedDate = formatter.format(initDate);
        System.out.println("Date IS* " + parsedDate);

        return parsedDate;
    }


    public Boolean calculateDifference(String startDate, String endDate) {
        Boolean value = null;
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("yyyy-MM-dd");

        try {

            Date date1 = simpleDateFormat.parse(startDate);
            Date date2 = simpleDateFormat.parse(endDate);


            long different = date2.getTime() - date1.getTime();

            System.out.println("different : " + different);
            if (different >= 0) {
                value = true;
            } else {
                value = false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return value;
    }


    public static SingletonUtil getSingletonConfigInstance() {
        if (singletonUtil == null)
            singletonUtil = new SingletonUtil();
        return singletonUtil;
    }

    /**
     * display message in snackbar
     *
     * @param message Message to show in snackbar
     * @param view    Layout in which Snackbar is to be shown
     */
    public Snackbar showSnackBar(String message, View view) {
        Snackbar snackbar = null;

        if (view != null) {

            snackbar = Snackbar
                    .make(view, message, Snackbar.LENGTH_INDEFINITE)
                    .setAction("OK", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                        }
                    });
            // Changing message text color
            snackbar.setActionTextColor(Color.WHITE);
            // Changing action button text color
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        }
        return snackbar;
    }

    public String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public Snackbar showSnackBarWithClickListener(String message, View view, OKClickListenerSnackbar onItemClickListener) {
        this.okClickListenerSnackbar = onItemClickListener;
        Snackbar snackbar = Snackbar
                .make(view, message, Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        okClickListenerSnackbar.onItemClick();
                    }
                });
        // Changing message text color
        snackbar.setActionTextColor(Color.WHITE);
        // Changing action button text color
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.WHITE);
        snackbar.show();
        return snackbar;
    }


    public boolean isConnectedToInternet(Context mContext) {
        ConnectivityManager cm = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected() == true) {
            return true;
        }
        return false;
    }

    // convert from bitmap to byte array
    public byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // convert from byte array to bitmap
    public Bitmap getByteToBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    //convert String to Bitmap
    public Bitmap getBitmapFromString(String galImage) {
        byte[] imageAsBytes = Base64.decode(galImage.getBytes(), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
        return bitmap;
    }

    public String getEncodedString(Bitmap bitmap) {

        bitmap = Bitmap.createScaledBitmap(bitmap, 200, 200, true);

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 40, stream);
        byte[] byteFormat = stream.toByteArray();
        // get the base 64 string
        String encodedString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);

        return encodedString;
    }

    public boolean checkGPS(Context context) {
        boolean isGPSON = false;
        LocationManager service = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        boolean enabled = service.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // Check if enabled and if not send user to the GPS settings
        if (!enabled) {
            isGPSON = false;
        } else
            isGPSON = true;
        return isGPSON;
    }


    public String setTime(int hourOfDay, int minute) {

        String hourString = hourOfDay < 10 ? "0" + hourOfDay : "" + hourOfDay;
        String minuteString = minute < 10 ? "0" + minute : "" + minute;
        String mode = null;

        if (hourOfDay > 12) {
            hourString = (hourOfDay - 12) + "";
            mode = "PM";
        } else if (hourOfDay == 12) {
            hourString = "" + hourOfDay;
            mode = "PM";
        } else {
            mode = "AM";
        }

        if (hourOfDay < 12 && hourOfDay >= 0) {
            mode = "AM";
        } else {
            if (hourOfDay != 12)
                hourString = (hourOfDay - 12) + "";
            mode = "PM";
        }
        String time = "" + hourString + ":" + minuteString + " " + mode;
        return time;
    }


    /*public void showErrorMessage(ErrorMessageResponse error, View view, Context context) {
        if(error!= null && error.getError() != null) {

            if (error.getError() != null && error.getError().equals("invalid_token")) {


                DatabaseHandler db = new DatabaseHandler(context);
                db.updateTokennfo("bearer","244e112a-0a2d-4397-8cfc-5fef2a6856fa");


//                Activity currentActivity = ((MyApp) context.getApplicationContext()).getCurrentActivity();
//                Intent intent = new Intent(currentActivity, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                currentActivity.startActivity(intent);
//                currentActivity.finish();


            } else if (error.getError() != null) {
//                if (error.getResponseMessage().getMessage() != null) {
//                    singletonUtil.showSnackBar(error.getResponseMessage().getMessage(),
//                            view);
//                    Log.d("error message", error.getResponseMessage().getMessage());
//                } else
//                    singletonUtil.showSnackBar(
//                            "Unable to connect to server at this moment!! Please try again later!!", view);
            }
        }
        else
            singletonUtil.showSnackBar(
                    "Unable to connect to server at this moment!! Please try again later!!", view);


    }*/


    public boolean showErrorMessage(ErrorMessageResponse error, View view, Context context) {
        boolean status = false;
        if (error != null && error.getError() != null) {

            if (error.getError() != null && error.getError().equals("invalid_token")) {


                DatabaseHandler db = new DatabaseHandler(context);
                status = db.updateTokenInfo("bearer", "3e3ab123-e332-4fbc-a362-66085527ccb6");
                System.out.println("status  " + status);

//                Activity currentActivity = ((MyApp) context.getApplicationContext()).getCurrentActivity();
//                Intent intent = new Intent(currentActivity, LoginActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                currentActivity.startActivity(intent);
//                currentActivity.finish();


            } else if (error.getError() != null) {
                if (error.getError() != null) {
                    singletonUtil.showSnackBar(error.getErrorDescription(),
                            view);
                    Log.d("error message", error.getError());
                } else
                    singletonUtil.showSnackBar(
                            "Unable to connect to server at this moment!! Please try again later!!", view);
            }
        } else
            singletonUtil.showSnackBar(
                    "Unable to connect to server at this moment!! Please try again later!!", view);

        return status;
    }


    public String compressImage(Bitmap bitmap) {
//        BitmapFactory.Options options = null;
//        options = new BitmapFactory.Options();
//        options.inSampleSize = 8;
//        bitmap = BitmapFactory.decodeFile(filePath,
//                options);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        // Must compress the Image to reduce image size to make upload easy
        bitmap.compress(Bitmap.CompressFormat.PNG, 50, stream);
        byte[] byte_arr = stream.toByteArray();

        // Encode Image to String
        String encodedString = Base64.encodeToString(byte_arr, 0);
        return encodedString;
    }


}
