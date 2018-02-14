package edu.up.cs371.twigg20.hw2facemaker;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

/**
 * Created by Jason on 2/7/2018.
 */

public class Face {


    public static String[] hairStyles = {"Fro","Spiked","Long Hair"};

    private int skinColor;

    private int eyeColor;

    private int hairColor;

    private int hairStyle;

    private Paint hairPaint = new Paint();
    private Paint eyePaint = new Paint();
    private Paint skinPaint = new Paint();
    private Paint whitePaint = new Paint();
    private Paint blackPaint = new Paint();



    public Face(){

        randomize();
        whitePaint.setColor(Color.WHITE);
        blackPaint.setColor(Color.BLACK);

    }


    public void randomize(){


        skinColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        hairColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));
        eyeColor = Color.rgb((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256));

        hairStyle = (int)(Math.random() * 3);


        return;

    }

    public void onDraw(Canvas canvas){




        drawSkin(canvas);

        drawExtras(canvas);

        drawEyes(canvas);
        drawHair(canvas);





    }

    /*

        @parameters
        int color - 0-255 int value for the specific part of an RGB color
        int type - 0: Hair 1: Skin 2: Eyes
        int colorType - 0:Red 1:Green 2:Blue

     */
    public void setColor(int color, int type, int colorType ){

        if( type == 0 ){

            if( colorType == 0 ){

                eyeColor = Color.rgb(color, Color.green(eyeColor),Color.blue(eyeColor));

            } else if ( colorType == 1){

                eyeColor = Color.rgb(Color.red(eyeColor), color ,Color.blue(eyeColor));

            } else if ( colorType == 2){

                eyeColor = Color.rgb(Color.red(eyeColor), Color.green(eyeColor),color);

            }

        } else if ( type == 1 ){

            if( colorType == 0 ){

                hairColor = Color.rgb(color, Color.green(hairColor),Color.blue(hairColor));

            } else if ( colorType == 1){

                hairColor = Color.rgb(Color.red(hairColor), color,Color.blue(hairColor));

            } else if ( colorType == 2){

                hairColor = Color.rgb(Color.red(hairColor), Color.green(hairColor),color);

            }
        } else if ( type == 2 ){

            if( colorType == 0 ){

                skinColor = Color.rgb(color, Color.green(skinColor),Color.blue(skinColor));

            } else if ( colorType == 1){

                skinColor = Color.rgb(Color.red(skinColor), color,Color.blue(skinColor));

            } else if ( colorType == 2){

                skinColor = Color.rgb(Color.red(skinColor), Color.green(skinColor),color);

            }
        }

    }

    public void setHairStyle( int i ){
        if( i >= 0 && i < hairStyles.length ) {
            hairStyle = i;
        }

    }

    public int getEyesColor(){

        return eyeColor;
    }

    public int getHairColor(){

        return hairColor;

    }

    public int getSkinColor(){

        return skinColor;

    }

    public int getHairStyle(){

        return hairStyle;
    }

    public void drawSkin( Canvas canvas ){

        skinPaint.setColor(skinColor);
        //Log.i("ran","ran");

        //canvas.drawRect(650,500,850,700,skinPaint);
        canvas.drawCircle(750,600,250,skinPaint);

    }

    public void drawHair( Canvas canvas ){

        hairPaint.setColor(hairColor);
        int cx = 750;
        int cy = 600;

        int diameter = 500;

        if( hairStyle == 0 ) {

            hairPaint.setStyle(Paint.Style.FILL);

            for( int x = 0; x < 10; x++){
                for( int y = 0; y < 4; y++){
                    if( y == 0 ) {
                        if( x >= 2 && x < 8 ) {
                            canvas.drawCircle(525 + 50 * x, 300 + 50 * y, 50, hairPaint);
                        }
                    } else if( y == 1 ){
                        if( x >= 1 && x < 9 ) {
                            canvas.drawCircle(525 + 50 * x, 300 + 50 * y, 50, hairPaint);
                        }
                    } else if( y == 2 ){
                        if( x >= 0 && x < 10 ) {
                            canvas.drawCircle(525 + 50 * x, 300 + 50 * y, 50, hairPaint);
                        }
                    } else {
                        if( x == 0 || x == 9 ) {
                            canvas.drawCircle(525 + 50 * x, 300 + 50 * y, 50, hairPaint);
                        }
                    }
                }
            }


        } else if (hairStyle == 1) {

            hairPaint.setStyle(Paint.Style.STROKE);
            hairPaint.setStrokeWidth(10f);



            for( double i = 0; i <= 3.15; i+=.05){

                double difference = Math.abs((1.57 - i) * (.35/1.57));

                int x1 = cx - (int)(diameter/2 * Math.cos(i));
                int x2 = cx - (int)(diameter/(1.65+difference) * Math.cos(i));
                int y1 = cy - (int)(diameter/2 * Math.sin(i));
                int y2 = cy - (int)(diameter/(1.65+difference) * Math.sin(i));

                canvas.drawLine(x1, y1, x2, y2, hairPaint);
            }






        } else if( hairStyle == 2 ){

            hairPaint.setStyle(Paint.Style.STROKE);
            hairPaint.setStrokeWidth(50f);



            for( double i = 0; i <= 3.15; i+=.05){


                int x1 = cx - (int)(diameter/2 * Math.cos(i));
                int x2 = cx - (int)(diameter/2 * Math.cos(i+.075));
                int y1 = cy - (int)(diameter/2 * Math.sin(i));
                int y2 = cy - (int)(diameter/2 * Math.sin(i+.075));

                canvas.drawLine(x1, y1, x2, y2, hairPaint);
            }
            canvas.drawLine(500, 575 , 500,1200, hairPaint);
            canvas.drawLine(1000, 575 , 1000,1200, hairPaint);

            hairPaint.setStrokeWidth(1f);
            for( double i = 3.14; i <= 6.28; i+=.03){


                int x1 = cx - (int)(diameter/2 * Math.cos(i));
                int x2 = x1;
                int y1 = cy - (int)(diameter/2 * Math.sin(i));
                int y2 = 1200;

                canvas.drawLine(x1, y1, x2, y2, hairPaint);
            }

        }



    }


    public void drawEyes( Canvas canvas ){

        eyePaint.setColor(eyeColor);

        canvas.drawCircle(650,500,50,whitePaint);
        canvas.drawCircle(650,500,20,eyePaint);
        canvas.drawCircle(650,500,5,whitePaint);
        canvas.drawCircle(850,500,50,whitePaint);
        canvas.drawCircle(850,500,20,eyePaint);
        canvas.drawCircle(850,500,5,whitePaint);

    }

    public void drawExtras( Canvas canvas ){

        canvas.drawCircle(750,700,75,blackPaint);
        canvas.drawRect(675,625,825,680,skinPaint);
        canvas.drawRect(700,680,740,720,whitePaint);
        canvas.drawRect(755,680,795,720,whitePaint);

        canvas.drawCircle(725,600,15,blackPaint);
        canvas.drawCircle(775,600,15,blackPaint);

    }


}
