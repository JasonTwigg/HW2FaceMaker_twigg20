package edu.up.cs371.twigg20.hw2facemaker;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jason on 2/8/2018.
 */

public class ListenerClass implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, AdapterView.OnItemSelectedListener, RadioGroup.OnCheckedChangeListener {

    //create an arraylist for all of the text views, this is so they can easily been cycled through and changed throughout the listener class
    private ArrayList<TextView> allTVs = new ArrayList<TextView>();

    //keep track of the board object so we can make changes to the face
    private Board boardObject;

    //create array of 3 booleans to keep track of what radio button is being pressed
    boolean[] checks = {false, false, false};

    //create an arraylist to hold each of the radio buttons from the selection
    private ArrayList<RadioButton> radioButtons = new ArrayList<>();

    //create an arraylist to hold all of the seekbars and allow us to loop through all of them
    private ArrayList<SeekBar> seekBars = new ArrayList<>();

    //create a spinner variable to hold the hair spinner to be referred upon
    private Spinner hairSpinner;


    public ListenerClass( TextView initialTV, Board boardObject ){

        //when instantiated, add the first text view to the array and obtain the board object from the main activity
        addTV(initialTV);
        this.boardObject = boardObject;

    }


    /*
    * adds a Text View to the text view array list
    *
    * @param anotherTV the text view to be added
    *
    * @return void
    */
    public void addTV( TextView anotherTV ){
        allTVs.add(anotherTV);
    }

    /*
    * adds a radio button to the radio button array list
    *
    * @param rbutton radio button to be added
    *
    * @return void
    */
    public void addRadioButton(RadioButton rButton ){

        radioButtons.add(rButton);

    }

    /* adds a seek bar to the seekbar array list
    *
    * @param seekBar seekbar to be added
    *
    * @return void
    */
    public void addSeekBar( SeekBar seekBar ){

        seekBars.add(seekBar);

    }

    /* sets a spinner to the hairSpinner value
    *
    * @param hs spinner to be set
    *
    * @return void
    */
    public void setHairSpinner( Spinner hs ){

        hairSpinner = hs;

    }





    // listener for the clicking of a button
    @Override
    public void onClick(View wasClicked) {

        // reference the button that was clicked
        Button buttonWasClicked = ((Button)wasClicked);

        //retrieve the label of the button so we can make sure its the right button
        String buttonLabel = (String)(buttonWasClicked.getText());


        // if the random button was pressed
        if( buttonWasClicked.getId() == R.id.buttonRandom ){

            //reference the face and randomize the values and then draw the
            //updated version
            boardObject.getFace().randomize();
            boardObject.update();

            //change the progresses of the seekbars to match the selected type
            //of the new random variables
            if( checks[0]  ) {
                setProgresses(0);
            } else if( checks[1] ){
                setProgresses(1);
            } else if( checks[2] ){
                setProgresses(2);
            }

            //updates the spinner aswell for the new random hair type
            setProgresses(3);

        }



    }

    //listener for the changes of the seekbar
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {


        //loop through all of the radiobuttons
        for( RadioButton rb : radioButtons ){

            //get the text of the sellected button
            String s = (String)(rb.getText());

            //runs if the button is eyes and it is selected
            if( s.equalsIgnoreCase("Eyes") && checks[0]){

                //check what seekbar is being moved and based off that set that color to the
                //eyes color in the face class
                if( seekBar.getId() == R.id.seekBarRed ){

                    boardObject.getFace().setColor(progress,0,0);

                } else if ( seekBar.getId() == R.id.seekBarGreen ){

                    boardObject.getFace().setColor(progress,0,1);

                } else if ( seekBar.getId() == R.id.seekBarBlue ){

                    boardObject.getFace().setColor(progress,0,2);
                }

                //runs if the button is hair and it is selected
            } else if ( s.equalsIgnoreCase("Hair") && checks[1]){


                //check what color seekbar is being moved and based off that set that color to the
                //hair color in the face class
                if( seekBar.getId() == R.id.seekBarRed ){

                    boardObject.getFace().setColor(progress,1,0);

                } else if ( seekBar.getId() == R.id.seekBarGreen ){

                    boardObject.getFace().setColor(progress,1,1);

                } else if ( seekBar.getId() == R.id.seekBarBlue){

                    boardObject.getFace().setColor(progress,1,2);
                }

                //runs if the button is skin and it is selected
            } else if ( s.equalsIgnoreCase("Skin") && checks[2]){

                //check what seekbar is being moved and based off that set that color to the
                //skin color in the face class
                if( seekBar.getId() == R.id.seekBarRed ){

                    boardObject.getFace().setColor(progress,2,0);

                } else if ( seekBar.getId() == R.id.seekBarGreen ){

                    boardObject.getFace().setColor(progress,2,1);

                } else if ( seekBar.getId() == R.id.seekBarBlue){
                    boardObject.getFace().setColor(progress,2,2);
                }

            }

            //update the boardObject to draw the new changes
            boardObject.update();

        }


        for(TextView tv: allTVs){

            //set the progress of the new value of the seek bar to its cooresponding textView
            if( seekBar.getId() == R.id.seekBarRed && tv.getId() == R.id.textViewRedValue ){

                tv.setText(progress + "");


            } else if( seekBar.getId() == R.id.seekBarGreen && tv.getId() == R.id.textViewGreenValue ){

                tv.setText(progress + "");

            } else if( seekBar.getId() == R.id.seekBarBlue && tv.getId() == R.id.textViewBlueValue ){

                tv.setText(progress + "");

            }

        }




    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    //listner for the changing of a checked button in the radio group
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        //if eyes radio button are selected
        if( i == radioButtons.get(0).getId()){


            //set the first to true and set the the others to false
            checks[0] = true;
            checks[1] = false;
            checks[2] = false;

            //set the seekbars to the match the values of the colors for the eyes
            setProgresses(0);

        // if the hair radio buttons are selected
        } else if ( i == radioButtons.get(1).getId()){

            //set the second checks value to true and the rest to false
            checks[0] = false;
            checks[1] = true;
            checks[2] = false;

            //set the seekbars to the match the values of the colors for the eyes
            setProgresses(1);

        //
        } else if( i == radioButtons.get(2).getId()){

            checks[0] = false;
            checks[1] = false;
            checks[2] = true;

            setProgresses(2);
        }


    }

    public void setProgresses(int s ){

        if( s == 0){

            if( seekBars.get(0) != null ) {
                seekBars.get(0).setProgress(Color.red(boardObject.getFace().getEyesColor()));
            }
            if( seekBars.get(0) != null ) {
                seekBars.get(1).setProgress(Color.green(boardObject.getFace().getEyesColor()));
            }

            if( seekBars.get(2)!=null) {
                seekBars.get(2).setProgress(Color.blue(boardObject.getFace().getEyesColor()));
            }


        } else if ( s == 1 ){


            if( seekBars.get(0) != null ) {
                seekBars.get(0).setProgress(Color.red(boardObject.getFace().getHairColor()));
            }
            if( seekBars.get(0) != null ) {
                seekBars.get(1).setProgress(Color.green(boardObject.getFace().getHairColor()));
            }
            if( seekBars.get(2)!=null) {
                seekBars.get(2).setProgress(Color.blue(boardObject.getFace().getHairColor()));
            }

        } else if ( s == 2 ) {


            if( seekBars.get(0) != null ) {
                seekBars.get(0).setProgress(Color.red(boardObject.getFace().getSkinColor()));
            }
            if( seekBars.get(0) != null ) {
                seekBars.get(1).setProgress(Color.green(boardObject.getFace().getSkinColor()));
            }
            if( seekBars.get(2)!=null) {
                seekBars.get(2).setProgress(Color.blue(boardObject.getFace().getSkinColor()));
            }
        }

        int i =  boardObject.getFace().getHairStyle();

        Log.i("Number",i+"");

        hairSpinner.setSelection(i);
        //hairSpinner.setSelection(i);


    }
}
