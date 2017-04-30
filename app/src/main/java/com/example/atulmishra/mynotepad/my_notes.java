package com.example.atulmishra.mynotepad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Atul Mishra on 30-04-17.
 */

public class my_notes extends AppCompatActivity {
    EditText notestext;
    Button btnsettings;
    Button clearbtn;
    private static final int SETTINGS_INFO=1;

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        outState.putString("NOTES",notestext.getText().toString());
        super.onSaveInstanceState(outState);
    }

    private void savesettings(){
        SharedPreferences.Editor speditor = getPreferences(Context.MODE_PRIVATE).edit();
        speditor.putString("NOTES",notestext.getText().toString());
        speditor.commit();
    }


    @Override
    protected void onStop() {
        savesettings();
        super.onStop();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == SETTINGS_INFO){
            updateNoteText();
        }
    }

    private void updateNoteText()
    {
     SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        if(sharedPreferences.getBoolean("pref_text_bold",false)){
            notestext.setTypeface(null, Typeface.BOLD);
        }
        else{
            notestext.setTypeface(null , Typeface.NORMAL);
        }
        String txtsize = sharedPreferences.getString("pref_text_size","16");
        float txtsizefloat = Float.parseFloat(txtsize);
        notestext.setTextSize(txtsizefloat);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_notes);
        notestext=(EditText)findViewById(R.id.noteseditText);
        clearbtn = (Button)findViewById(R.id.button2);
        if(savedInstanceState != null)
        {
            String notes = savedInstanceState.getString("NOTES");
            notestext.setText(notes);
        }
        String spnotes = getPreferences(Context.MODE_PRIVATE).getString("NOTES","EMPTY");
        if(!spnotes.equals("EMPTY"))
        {
            notestext.setText(spnotes);
        }
        btnsettings = (Button)findViewById(R.id.button);
        btnsettings.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intentpref = new Intent(getApplicationContext(), my_settings.class);

                startActivityForResult(intentpref,SETTINGS_INFO);
            }
        });

        clearbtn.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                notestext.setText("");
            }

    });
    }







}
