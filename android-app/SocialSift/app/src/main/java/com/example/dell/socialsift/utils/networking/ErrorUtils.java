package com.example.dell.socialsift.utils.networking;



import com.example.dell.socialsift.beans.common.ErrorMessageResponse;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;

import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * Created by Replete Android on 10/12/2016.
 */
public class ErrorUtils {


    public static ErrorMessageResponse parseError(Response<?> response, Retrofit retrofit) {

        Converter<ResponseBody, ErrorMessageResponse> converter =
                retrofit.responseBodyConverter(ErrorMessageResponse.class, new Annotation[0]);

        ErrorMessageResponse error;

        try {
            error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ErrorMessageResponse();
        }

        return error;
    }

}
