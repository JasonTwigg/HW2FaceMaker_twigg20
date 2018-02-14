package edu.up.cs371.twigg20.hw2facemaker;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Jason on 2/7/2018.
 */

public class Board extends SurfaceView {


    Face faceObject;

    private Canvas c = null;

    private SurfaceHolder sh;


    public Board(Context context) {
        super(context);

        generalInit();

    }

    public Board(Context context, AttributeSet attrs) {
        super(context, attrs);

        generalInit();

    }

    public Board(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        generalInit();

    }


    public void generalInit(){


        faceObject = new Face();
        setWillNotDraw(false);



    }

    public Face getFace(){

        return faceObject;

    }



    @Override
    public void onDraw(Canvas canvas){

        if( c == null ) {
            c = canvas;
        }


        faceObject.onDraw(canvas);





    }




    public void update(){
        invalidate();
        postInvalidate();

        /*

            https://stackoverflow.com/questions/18607335/how-to-update-a-surfaceview

        */
    }




}
