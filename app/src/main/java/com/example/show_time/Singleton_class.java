package com.example.show_time;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class Singleton_class {

    private   RequestQueue requestqueue;
    private  static Singleton_class object;


    //create the constructor
    private Singleton_class(Context context) {

        requestqueue = Volley.newRequestQueue(context);


    }

    public static synchronized  Singleton_class getInstance(Context context){

        if(object==null){
            //create the object of the class
            object=new Singleton_class(context.getApplicationContext());
        }

        return  object;
    }

    public RequestQueue getRequestqueue(){
        return requestqueue;
    }

}
