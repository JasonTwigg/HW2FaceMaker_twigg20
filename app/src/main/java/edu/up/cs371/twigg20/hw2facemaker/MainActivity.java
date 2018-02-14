package edu.up.cs371.twigg20.hw2facemaker;

import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //setup the three seekbars for each color element
    private SeekBar redSeekBar;
    private SeekBar greenSeekBar;
    private SeekBar blueSeekBar;


    //create the randombutton
    private Button randomButton;


    //create the three radio buttons for selecting the type changed
    private RadioButton radioButtonHair;
    private RadioButton radioButtonEyes;
    private RadioButton radioButtonSkin;


    //create the spinner for choosing the hair types
    private Spinner typeSpinner;

    //create a board to allow us to call on the face instance later on
    private Board board;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // instantiate the board object in order to change the values of the face
        board = (Board)findViewById(R.id.Board);


        /*
        *  reference the spinner in the GUI and set its values to the
        *  various hair types using an array adapter (similar to a list):
        *  Fro - Spiked - Long Hair
         */
        typeSpinner = (Spinner)findViewById(R.id.spinnerSelector);

        ArrayAdapter<String> optionAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item, Face.hairStyles);
        typeSpinner.setAdapter(optionAdapter);

        //create a lister for the spinner that waits for a new item to be selected
        typeSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //when a new item is selected, change the hairStyle int in the face object and update the board
                board.faceObject.setHairStyle(position);
                board.update();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        /*
        * create a new instance of the listener class, this is to help reduce the amount of code in
        * the main class and practice good format to begin we give it the red value text view for the
        * red seekbar
        */
        ListenerClass myListener = new ListenerClass(
                (TextView)findViewById(R.id.textViewRedValue),
                board);
        /*
        * add the other two text views for green and blue values to the listener class object
        */
        myListener.addTV(
                (TextView)findViewById(R.id.textViewGreenValue));
        myListener.addTV(
                (TextView)findViewById(R.id.textViewBlueValue));

        // pass the hair spinner into the listener class for future use
        myListener.setHairSpinner(typeSpinner);


        /*
        * reference the three seekbars in order to call upon their values aswell as listen to actions
        * preformed on them
        */
        redSeekBar = (SeekBar)findViewById(R.id.seekBarRed);
        greenSeekBar = (SeekBar)findViewById(R.id.seekBarGreen);
        blueSeekBar = (SeekBar)findViewById(R.id.seekBarBlue);

        /*
        * instantiat the random button aswell as the three radio buttons in the radio group, for picking
        * the body part to have its color changed
        */
        randomButton = (Button)findViewById(R.id.buttonRandom);
        radioButtonHair = (RadioButton)findViewById(R.id.radioButtonHair);
        radioButtonSkin = (RadioButton)findViewById(R.id.radioButtonSkin);
        radioButtonEyes = (RadioButton)findViewById(R.id.radioButtonEyes);

        /*
        * add the three radio button into the listener so they can be called upon and changed by the
        * listener class
        */
        myListener.addRadioButton(radioButtonEyes);
        myListener.addRadioButton(radioButtonHair);
        myListener.addRadioButton(radioButtonSkin);

        /*
        * Create a radio group, this is necessary to use a listener to check for actions on each of
        * the radio button
        *
        * External Citation
        *   Date: 12 February 2018
        *   Problem: Could not get the listener for a radioButton to work
        *
        *   Resource : https://stackoverflow.com/questions/8323778/how-to-set-onclicklistener-on-a-
        *   radiobutton-in-android
        *   Solution: I created a radioGroup and then used a listener for the whole group
        */
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radioGroupOptions);
        radioGroup.setOnCheckedChangeListener(myListener);



        /*
        * Step 3 to a listener:
        * Set all of the button's and seekbar's listener to myListener
        * Without this step the listener would not work
        */
        randomButton.setOnClickListener(myListener);
        redSeekBar.setOnSeekBarChangeListener(myListener);
        greenSeekBar.setOnSeekBarChangeListener(myListener);
        blueSeekBar.setOnSeekBarChangeListener(myListener);

        /*
        * Add the three seekbars to the listener class, this is so they can be called on and so
        * their values can be changed in the listener class
        */
        myListener.addSeekBar(redSeekBar);
        myListener.addSeekBar(greenSeekBar);
        myListener.addSeekBar(blueSeekBar);




    }
}
