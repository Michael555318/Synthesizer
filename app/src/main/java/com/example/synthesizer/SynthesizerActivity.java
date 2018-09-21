package com.example.synthesizer;

import android.graphics.Color;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.annotation.ColorLong;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SynthesizerActivity extends AppCompatActivity implements View.OnClickListener{

    private SoundPool soundPool;
    //Save Function
    private Button save1;
    private Boolean saved1 = false;
    private ArrayList<Button> saveSong1;
    private ArrayList<Integer> saveDelay1;
    private ArrayList<Boolean> saveLow1;
    //Notes
    private Map<Integer, Integer> noteMap;
    private Button scroll;
    private Boolean low = true;
    private Button buttonA;
    private int noteA;
    private int hnoteA;
    private Button buttonBb;
    private int noteBb;
    private int hnoteBb;
    private Button buttonB;
    private int noteB;
    private int hnoteB;
    private Button buttonC;
    private int noteC;
    private int hnoteC;
    private Button buttonCs;
    private int noteCs;
    private int hnoteCs;
    private Button buttonD;
    private int noteD;
    private int hnoteD;
    private Button buttonDs;
    private int noteDs;
    private int hnoteDs;
    private Button buttonE;
    private int noteE;
    private int hnoteE;
    private Button buttonF;
    private int noteF;
    private int hnoteF;
    private Button buttonFs;
    private int noteFs;
    private int hnoteFs;
    private Button buttonG;
    private int noteG;
    private int hnoteG;
    private Button buttonGs;
    private int noteGs;
    private int hnoteGs;
    //Create Function
    private Button buttonPlayScale;
    private Button buttonCreate;
    private Button buttonCreatePlay;
    private Button buttonCreateDel;
    private Button buttonCreateReset;
    private TextView createNotes;
    private Boolean createMode = false;
    private ArrayList<Button> createNoteSequence;
    private ArrayList<Integer> createDelaySequence;
    private ArrayList<Boolean> createLowSequence;
    private String addNotes = "";
    private CheckBox speedxhalf;
    private boolean halfSpeed = false;
    private CheckBox speedxwhole;
    private boolean wholeSpeed = true;
    private CheckBox speedxdouble;
    private boolean doubleSpeed = false;
    private CheckBox speedxquarter;
    private Boolean quarterSpeed = false;

    public static final float DEFAULT_VOLUME = 1.0f;
    public static final float DEFAULT_RATE = 1.0f;
    public static final int DEFAULT_PRIORITY = 1;
    private static final int WHOLE_NOTE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_synthesizer);

        wireWidgets();
        initializeSoundPool();
        initializeNoteMap();
        setListeners();
        updateCheckBox();
    }

    private void initializeNoteMap() {
        noteMap = new HashMap<>();
        //in a map, you store a key: value pair
        //the key is the buttonId, the value is the noteId
        noteMap.put(R.id.button_synth_a, noteA);
        noteMap.put(R.id.button_synth_bb, noteBb);
        noteMap.put(R.id.button_synth_b, noteB);
        noteMap.put(R.id.button_synth_c, noteC);
        noteMap.put(R.id.button_synth_cs, noteCs);
        noteMap.put(R.id.button_synth_d, noteD);
        noteMap.put(R.id.button_synth_ds, noteDs);
        noteMap.put(R.id.button_synth_e, noteE);
        noteMap.put(R.id.button_synth_f, noteF);
        noteMap.put(R.id.button_synth_fs, noteFs);
        noteMap.put(R.id.button_synth_g, noteG);
        noteMap.put(R.id.button_synth_gs, noteGs);

    }

    private void wireWidgets() {
        scroll = findViewById(R.id.button_main_scroll);
        buttonA = findViewById(R.id.button_synth_a);
        buttonBb = findViewById(R.id.button_synth_bb);
        buttonB = findViewById(R.id.button_synth_b);
        buttonC = findViewById(R.id.button_synth_c);
        buttonCs = findViewById(R.id.button_synth_cs);
        buttonD = findViewById(R.id.button_synth_d);
        buttonDs = findViewById(R.id.button_synth_ds);
        buttonE = findViewById(R.id.button_synth_e);
        buttonF = findViewById(R.id.button_synth_f);
        buttonFs = findViewById(R.id.button_synth_fs);
        buttonG = findViewById(R.id.button_synth_g);
        buttonGs = findViewById(R.id.button_synth_gs);
        buttonPlayScale = findViewById(R.id.button_main_playscale);
        buttonCreate = findViewById(R.id.button_main_create);
        buttonCreatePlay = findViewById(R.id.button_main_createplay);
        buttonCreateDel = findViewById(R.id.button_main_createdelete);
        createNotes = findViewById(R.id.textView_main_createnotes);
        buttonCreateReset = findViewById(R.id.button_main_createreset);
        speedxdouble = findViewById(R.id.checkbox_main_createdouble);
        speedxhalf = findViewById(R.id.checkbox_main_createhalf);
        speedxwhole = findViewById(R.id.checkbox_main_createwhole);
        speedxquarter = findViewById(R.id.checkBox_main_createquarter);
        save1 = findViewById(R.id.button_main_save1);
    }

    private void initializeSoundPool() {
        soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
        noteA = soundPool.load(this, R.raw.scalea, 1);
        noteBb = soundPool.load(this, R.raw.scalebb, 1);
        noteB = soundPool.load(this, R.raw.scaleb, 1);
        noteC = soundPool.load(this, R.raw.scalec, 1);
        noteCs = soundPool.load(this, R.raw.scalecs, 1);
        noteD = soundPool.load(this, R.raw.scaled, 1);
        noteDs = soundPool.load(this, R.raw.scaleds, 1);
        noteE = soundPool.load(this, R.raw.scalee, 1);
        noteF = soundPool.load(this, R.raw.scalef, 1);
        noteFs = soundPool.load(this, R.raw.scalefs, 1);
        noteG = soundPool.load(this, R.raw.scaleg, 1);
        noteGs = soundPool.load(this, R.raw.scalegs, 1);

        hnoteA = soundPool.load(this, R.raw.scalehigha, 1);
        hnoteBb = soundPool.load(this, R.raw.scalehighbb, 1);
        hnoteB = soundPool.load(this, R.raw.scalehighb, 1);
        hnoteC = soundPool.load(this, R.raw.scalehighc, 1);
        hnoteCs = soundPool.load(this, R.raw.scalehighcs, 1);
        hnoteD = soundPool.load(this, R.raw.scalehighd, 1);
        hnoteDs = soundPool.load(this, R.raw.scalehighds, 1);
        hnoteE = soundPool.load(this, R.raw.scalehighe, 1);
        hnoteF = soundPool.load(this, R.raw.scalehighf, 1);
        hnoteFs = soundPool.load(this, R.raw.scalehighfs, 1);
        hnoteG = soundPool.load(this, R.raw.scalehighg, 1);
        hnoteGs = soundPool.load(this, R.raw.scalehighgs, 1);
    }

    private void setListeners() {
        KeyboardNoteListener noteListener = new KeyboardNoteListener();
        scroll.setOnClickListener(this);
        buttonA.setOnClickListener(noteListener);
        buttonBb.setOnClickListener(noteListener);
        buttonB.setOnClickListener(noteListener);
        buttonC.setOnClickListener(noteListener);
        buttonCs.setOnClickListener(noteListener);
        buttonD.setOnClickListener(noteListener);
        buttonDs.setOnClickListener(noteListener);
        buttonE.setOnClickListener(noteListener);
        buttonF.setOnClickListener(noteListener);
        buttonFs.setOnClickListener(noteListener);
        buttonG.setOnClickListener(noteListener);
        buttonGs.setOnClickListener(noteListener);
        buttonPlayScale.setOnClickListener(this);
        buttonCreate.setOnClickListener(this);
        buttonCreatePlay.setOnClickListener(this);
        buttonCreateDel.setOnClickListener(this);
        buttonCreateReset.setOnClickListener(this);
        speedxwhole.setOnClickListener(this);
        speedxhalf.setOnClickListener(this);
        speedxdouble.setOnClickListener(this);
        speedxquarter.setOnClickListener(this);
        save1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // one method to handle the clicks of all the buttons
        // but don't forget to tell the buttons who is doing the listening
        switch(v.getId()) {

            case R.id.button_main_playscale:
                playScale();
                break;

            case R.id.button_main_create:
                if (createMode == false) {
                    createMode = true;
                    buttonCreateDel.setVisibility(View.VISIBLE);
                    buttonCreatePlay.setVisibility(View.VISIBLE);
                    createNotes.setVisibility(View.VISIBLE);
                    buttonCreateReset.setVisibility(View.VISIBLE);
                    speedxwhole.setVisibility(View.VISIBLE);
                    speedxhalf.setVisibility(View.VISIBLE);
                    speedxdouble.setVisibility(View.VISIBLE);
                    speedxquarter.setVisibility(View.VISIBLE);
                    createNoteSequence = new ArrayList<>();
                    createDelaySequence = new ArrayList<>();
                    createLowSequence = new ArrayList<>();
                    createNotes.setText("your notes");
                    addNotes = "";

                } else {
                    createMode = false;
                    buttonCreateDel.setVisibility(View.INVISIBLE);
                    buttonCreatePlay.setVisibility(View.INVISIBLE);
                    createNotes.setVisibility(View.INVISIBLE);
                    buttonCreateReset.setVisibility(View.INVISIBLE);
                    speedxwhole.setVisibility(View.INVISIBLE);
                    speedxhalf.setVisibility(View.INVISIBLE);
                    speedxdouble.setVisibility(View.INVISIBLE);
                    speedxquarter.setVisibility(View.INVISIBLE);
                }
                break;

            case R.id.button_main_createdelete:
                if (createNoteSequence.size() > 0) {
                    deleteNote();
                }
                break;

            case R.id.button_main_createplay:
                playCreate();
                break;

            case R.id.button_main_createreset:
                if (createNoteSequence.size() > 0) {
                    reset();
                }
                break;

            case R.id.checkbox_main_createwhole:
                if (!wholeSpeed) {
                    wholeSpeed = true;
                    doubleSpeed = false;
                    halfSpeed = false;
                    quarterSpeed = false;
                }
                updateCheckBox();
                break;

            case R.id.checkbox_main_createdouble:
                if (!doubleSpeed) {
                    wholeSpeed = false;
                    doubleSpeed = true;
                    halfSpeed = false;
                    quarterSpeed = false;
                }
                updateCheckBox();
                break;

            case R.id.checkbox_main_createhalf:
                if (!halfSpeed) {
                    wholeSpeed = false;
                    doubleSpeed = false;
                    halfSpeed = true;
                    quarterSpeed = false;
                }
                updateCheckBox();
                break;

            case R.id.checkBox_main_createquarter:
                if (!quarterSpeed) {
                    wholeSpeed = false;
                    doubleSpeed = false;
                    halfSpeed = false;
                    quarterSpeed = true;
                }
                updateCheckBox();
                break;

            case R.id.button_main_save1:
                if (createMode) {
                    saveSong1 = createNoteSequence;
                    saveDelay1 = createDelaySequence;
                    saveLow1 = createLowSequence;
                    saved1 = true;
                    Toast.makeText(this, "Song saved!", Toast.LENGTH_SHORT).show();
                } else {
                    if (saved1) {
                        playSong(saveSong1, saveDelay1, saveLow1);
                    } else if (!saved1) {
                        Toast.makeText(this, "Save 1 is empty.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

            case R.id.button_main_scroll:
                if (low) {
                    low = false;
                    scroll.setText("↑ LOW");
                } else {
                    low = true;
                    scroll.setText("↓ HIGH");
                }
                changeScale(low);
                updateNoteMap(low);
                break;
        }
    }

    private void updateNoteMap(Boolean low) {
        if (!low) {
            noteMap.put(R.id.button_synth_a, hnoteA);
            noteMap.put(R.id.button_synth_bb, hnoteBb);
            noteMap.put(R.id.button_synth_b, hnoteB);
            noteMap.put(R.id.button_synth_c, hnoteC);
            noteMap.put(R.id.button_synth_cs, hnoteCs);
            noteMap.put(R.id.button_synth_d, hnoteD);
            noteMap.put(R.id.button_synth_ds, hnoteDs);
            noteMap.put(R.id.button_synth_e, hnoteE);
            noteMap.put(R.id.button_synth_f, hnoteF);
            noteMap.put(R.id.button_synth_fs, hnoteFs);
            noteMap.put(R.id.button_synth_g, hnoteG);
            noteMap.put(R.id.button_synth_gs, hnoteGs);
        } else {
            noteMap.put(R.id.button_synth_a, noteA);
            noteMap.put(R.id.button_synth_bb, noteBb);
            noteMap.put(R.id.button_synth_b, noteB);
            noteMap.put(R.id.button_synth_c, noteC);
            noteMap.put(R.id.button_synth_cs, noteCs);
            noteMap.put(R.id.button_synth_d, noteD);
            noteMap.put(R.id.button_synth_ds, noteDs);
            noteMap.put(R.id.button_synth_e, noteE);
            noteMap.put(R.id.button_synth_f, noteF);
            noteMap.put(R.id.button_synth_fs, noteFs);
            noteMap.put(R.id.button_synth_g, noteG);
            noteMap.put(R.id.button_synth_gs, noteGs);
        }

    }
//todo: fix playCreate higher scale
    private void changeScale(Boolean low) {
        if (low == true) {
            buttonA.setTextColor(Color.rgb(0, 0, 0));
            buttonBb.setTextColor(Color.rgb(0, 0, 0));
            buttonB.setTextColor(Color.rgb(0, 0, 0));
            buttonC.setTextColor(Color.rgb(0, 0, 0));
            buttonCs.setTextColor(Color.rgb(0, 0, 0));
            buttonD.setTextColor(Color.rgb(0, 0, 0));
            buttonDs.setTextColor(Color.rgb(0, 0, 0));
            buttonE.setTextColor(Color.rgb(0, 0, 0));
            buttonF.setTextColor(Color.rgb(0, 0, 0));
            buttonFs.setTextColor(Color.rgb(0, 0, 0));
            buttonG.setTextColor(Color.rgb(0, 0, 0));
            buttonGs.setTextColor(Color.rgb(0, 0, 0));
        } else {
            buttonA.setTextColor(Color.rgb(0, 0, 250));
            buttonBb.setTextColor(Color.rgb(0, 0, 250));
            buttonB.setTextColor(Color.rgb(0, 0, 250));
            buttonC.setTextColor(Color.rgb(0, 0, 250));
            buttonCs.setTextColor(Color.rgb(0, 0, 250));
            buttonD.setTextColor(Color.rgb(0, 0, 250));
            buttonDs.setTextColor(Color.rgb(0, 0, 250));
            buttonE.setTextColor(Color.rgb(0, 0, 250));
            buttonF.setTextColor(Color.rgb(0, 0, 250));
            buttonFs.setTextColor(Color.rgb(0, 0, 250));
            buttonG.setTextColor(Color.rgb(0, 0, 250));
            buttonGs.setTextColor(Color.rgb(0, 0, 250));
        }
    }

    private void playSong(ArrayList<Button> song, ArrayList<Integer> delays, ArrayList<Boolean> low) {
        for (int i = 0; i < song.size(); i++) {
            playNote(getNotes(song.get(i), low.get(i)).getNoteId());
            delay(delays.get(i));
        }
    }

    private void updateCheckBox() {
        speedxdouble.setChecked(doubleSpeed);
        speedxhalf.setChecked(halfSpeed);
        speedxwhole.setChecked(wholeSpeed);
        speedxquarter.setChecked(quarterSpeed);
    }

    private void playScale() {
        //play all the notes one at a time
        playNote(noteA);
        delay(WHOLE_NOTE);
        playNote(noteBb);
        delay(WHOLE_NOTE);
        playNote(noteB);
        delay(WHOLE_NOTE);
        playNote(noteC);
        delay(WHOLE_NOTE);
        playNote(noteCs);
        delay(WHOLE_NOTE);
        playNote(noteD);
        delay(WHOLE_NOTE);
        playNote(noteDs);
        delay(WHOLE_NOTE);
        playNote(noteE);
        delay(WHOLE_NOTE);
        playNote(noteF);
        delay(WHOLE_NOTE);
        playNote(noteFs);
        delay(WHOLE_NOTE);
        playNote(noteG);
        delay(WHOLE_NOTE);
        playNote(noteGs);
        delay(WHOLE_NOTE);
        playNote(hnoteA);
        delay(WHOLE_NOTE);
        playNote(hnoteBb);
        delay(WHOLE_NOTE);
        playNote(hnoteB);
        delay(WHOLE_NOTE);
        playNote(hnoteC);
        delay(WHOLE_NOTE);
        playNote(hnoteCs);
        delay(WHOLE_NOTE);
        playNote(hnoteD);
        delay(WHOLE_NOTE);
        playNote(hnoteDs);
        delay(WHOLE_NOTE);
        playNote(hnoteE);
        delay(WHOLE_NOTE);
        playNote(hnoteF);
        delay(WHOLE_NOTE);
        playNote(hnoteFs);
        delay(WHOLE_NOTE);
        playNote(hnoteG);
        delay(WHOLE_NOTE);
        playNote(hnoteGs);
    }

    private void playCreate() {
        for (int i = 0; i < createNoteSequence.size(); i++) {
            playNote(getNotes(createNoteSequence.get(i), createLowSequence.get(i)).getNoteId());
            delay(createDelaySequence.get(i));
        }
    }

    private Note getNotes(Button button, Boolean low) {
        if (low) {
            if (button.equals(buttonA)) { return new Note(noteA); }
            if (button.equals(buttonBb)) { return new Note(noteBb); }
            if (button.equals(buttonB)) { return new Note(noteB); }
            if (button.equals(buttonC)) { return new Note(noteC); }
            if (button.equals(buttonCs)) { return new Note(noteCs); }
            if (button.equals(buttonD)) { return new Note(noteD); }
            if (button.equals(buttonDs)) { return new Note(noteDs); }
            if (button.equals(buttonE)) { return new Note(noteE); }
            if (button.equals(buttonF)) { return new Note(noteF); }
            if (button.equals(buttonFs)) { return new Note(noteFs); }
            if (button.equals(buttonG)) { return new Note(noteG); }
            if (button.equals(buttonGs)) { return new Note(noteGs); }
        } else {
            if (button.equals(buttonA)) { return new Note(hnoteA); }
            if (button.equals(buttonBb)) { return new Note(hnoteBb); }
            if (button.equals(buttonB)) { return new Note(hnoteB); }
            if (button.equals(buttonC)) { return new Note(hnoteC); }
            if (button.equals(buttonCs)) { return new Note(hnoteCs); }
            if (button.equals(buttonD)) { return new Note(hnoteD); }
            if (button.equals(buttonDs)) { return new Note(hnoteDs); }
            if (button.equals(buttonE)) { return new Note(hnoteE); }
            if (button.equals(buttonF)) { return new Note(hnoteF); }
            if (button.equals(buttonFs)) { return new Note(hnoteFs); }
            if (button.equals(buttonG)) { return new Note(hnoteG); }
            if (button.equals(buttonGs)) { return new Note(hnoteGs); }
        }
        return null;
    }

    private void reset() {
        createNoteSequence.clear();
        createDelaySequence.clear();
        createLowSequence.clear();
        addNotes = "";
        createNotes.setText(addNotes);
    }

    private void playNote(int note, int loop) {
        soundPool.play(note, DEFAULT_VOLUME, DEFAULT_VOLUME, DEFAULT_PRIORITY, loop, DEFAULT_RATE);
    }

    private void playNote(int note) {
        playNote(note, 0);
    }

    private void delay(int duration){
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int getDelay() {
        if (halfSpeed) {
            return WHOLE_NOTE/2;
        } else if (wholeSpeed) {
            return WHOLE_NOTE;
        } else if (doubleSpeed) {
            return WHOLE_NOTE*2;
        } else if (quarterSpeed) {
            return WHOLE_NOTE/4;
        }
        return 0;
    }

    private void addNote(Button button)  {
        if (createNoteSequence.size() <= 80) {
            createDelaySequence.add(getDelay());
            createNoteSequence.add(button);
            createLowSequence.add(low);
            String addition = createNoteSequence.get(createNoteSequence.size()-1).getText() + " ";
            addNotes = addNotes + addition;
            createNotes.setText(addNotes);
        } else {
            Toast.makeText(this, "Maximum is 80 notes!", Toast.LENGTH_SHORT).show();
        }
    }

    private void deleteNote() {
        if (createNoteSequence.size() > 1) {
            createNoteSequence.remove(createNoteSequence.size()-1);
            createDelaySequence.remove(createDelaySequence.size()-1);
            createLowSequence.remove(createDelaySequence.size()-1);
        } else {
             createNoteSequence.clear();
             createLowSequence.clear();
             createDelaySequence.clear();
        }

        addNotes = "";
        for (Button s : createNoteSequence) {
            addNotes = addNotes + s.getText() + " ";
        }
        createNotes.setText(addNotes);
    }

    //make an inner class to handle the button clicks for the individual notes
    private class KeyboardNoteListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            // get the id of the button that was clicked
            int id = v.getId();
            //use the map to determine which note to play
            int note = noteMap.get(id);
            // play the note
            playNote(note);
            Button noteButton = findViewById(v.getId());
            if (createMode) { addNote(noteButton); }
        }
    }
}

//♭  ♯    ↑    ↓