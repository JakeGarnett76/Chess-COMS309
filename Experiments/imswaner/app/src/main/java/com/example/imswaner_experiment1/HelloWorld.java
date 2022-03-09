package com.example.imswaner_experiment1;

import android.util.Log;

public class HelloWorld {

    public static void main(String args[])
    {
        fizbuzz();
    }
    public static void fizbuzz()
    {
        for(int i = 0;i<100;i++)
        {
            String out = Integer.toString(i);
            if(i%5==0 && i%3 == 0)
                Log.d(out,"Hello World");
            else if(i%5 ==0)
                Log.d(out,"World");
            else if(i%3 == 0)
                Log.d(out,"Hello");
        }
    }

    public void onCreate()
    {

    }

}
