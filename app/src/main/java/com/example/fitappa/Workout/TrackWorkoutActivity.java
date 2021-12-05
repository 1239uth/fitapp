package com.example.fitappa.Workout;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.fitappa.Exercise.Exercise.PerformExercise;
import com.example.fitappa.R;
import com.example.fitappa.Workout.Core.PerformWorkout;

import java.util.Objects;

/**
 *
 * The page responsible for tracking workouts
 * @author abdullah
 * @version 0.1
 * @layer 4
 */
public class TrackWorkoutActivity extends AppCompatActivity implements TrackWorkoutPresenter.View {
    TrackWorkoutPresenter presenter;

    /**
     * This method is called when the activity starts.
     *
     * @param savedInstanceState contains the data it was most recently supplied with by onSaveInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_workout);
        this.presenter = new TrackWorkoutPresenter(this, getIntent().getSerializableExtra(getString(R.string.WorkoutObject)));
    }

    /**
     *
     * Update the app's title bar
     * @param title of the app
     */
    public void updateAppBarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);

    }

    /**
     * Update the title of this page with the workout name
     * @param workoutTitle title of the workout
     */
    public void updateTitle(String workoutTitle) {
        ((TextView) findViewById(R.id.WorkoutNameText)).setText(workoutTitle);
    }

    /**
     *  Populate the layout with the exercises in workout
     */
    public void populateLayout(PerformWorkout workout) {
        LinearLayout exerciseLayout = findViewById(R.id.ExerciseContainer);
        exerciseLayout.removeAllViews();

        for(PerformExercise<?> e : workout.getExercises()) {
            buildExerciseComponent(exerciseLayout, e);
        }
    }

    @Override
    public void setupCancel() {
        Button cancelBtn = findViewById(R.id.CancelWorkoutBtn);
        cancelBtn.setOnClickListener(v -> exit());
    }

    @Override
    public void setupEnd() {
        //Button endBtn = findViewById(R.id.EndWorkoutBtn);
        //endBtn.setOnClickListener(v -> saveWorkouts());
        return;
    }


    public void exit() {
        startActivity(new Intent(this, StartWorkoutActivity.class));
    }

    /**
     * builds an Exercise Component
     * @param exerciseLayout the layout to attach exercise component to
     * @param e the exercise for which the component is created for
     */
    private void buildExerciseComponent(LinearLayout exerciseLayout, PerformExercise<?> e) {
        CardView cardView = new CardView(this);
        cardView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        ((ViewGroup.MarginLayoutParams) cardView.getLayoutParams()).topMargin = 25;

        LinearLayout outerLayer = buildLinearLayout(LinearLayout.VERTICAL);
        outerLayer.setPadding(30,30,30,30);
        cardView.addView(outerLayer);
        outerLayer.addView(buildText(e.getName(), -1));

        LinearLayout exerciseBody = buildExerciseBody(e.getCategory());
        outerLayer.addView(exerciseBody);


        // Attach
        exerciseLayout.addView(cardView);

        Integer numSet = 0;
        exerciseBody.setTag(numSet);

        // Button
        Button addSetBtn = new Button(this);
        addSetBtn.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        ((ViewGroup.MarginLayoutParams) addSetBtn.getLayoutParams()).topMargin = 25;
        addSetBtn.setText(getString(R.string.AddSet));
        addSetBtn.setOnClickListener(v -> addSetToExercise(e.getCategory(), exerciseBody));
        outerLayer.addView(addSetBtn);


    }


    /**
     * Build the exercise body
     * @param category category of the exercise
     * @return ExerciseBody
     */
    private LinearLayout buildExerciseBody(String category) {
        switch (category) {
            case "WEIGHTED":
                return buildWeightedExerciseBody();
            case "TIMED":
                return buildTimedExerciseBody();
            default:
                return buildRepExerciseBody();
        }
    }

    /**
     * This adds a Set view into the exercise's view
     * @param category of the exercise
     * @param exerciseBody the body to attach it to
     */
    private void addSetToExercise(String category, LinearLayout exerciseBody) {
        switch (category) {
            case "WEIGHTED":
                addWeightedSetToExercise(exerciseBody);
            case "TIMED":
                addTimedSetToExercise(exerciseBody);
            default:
                addRepSetToExercise(exerciseBody);
        }
    }

    /**
     * Adds a set to timed exercise body
     * @param exerciseBody the exercise body
     */
    private void addTimedSetToExercise(LinearLayout exerciseBody) {
        // TODO: someone do it on their free time, or else removed timed exercises from the app
        return;
    }

    /**
     *  Adds a set to weighted exercise body
     * @param exerciseBody the exercise body
     */
    private void addWeightedSetToExercise(LinearLayout exerciseBody) {
        // TODO: i will do it
        return;
    }

    /**
     * Adds a set to rep exercise body
     * @param exerciseBody the exercise body
     */
    private void addRepSetToExercise(LinearLayout exerciseBody) {
        LinearLayout setRow = buildLinearLayout(LinearLayout.HORIZONTAL);
        ((ViewGroup.MarginLayoutParams) setRow.getLayoutParams()).topMargin = 10;

        Integer numSet = (Integer) exerciseBody.getTag();
        numSet++;
        exerciseBody.setTag(numSet);

        TextView setText = buildText(numSet.toString(), 900/3);
        setText.setGravity(Gravity.CENTER);
        setRow.addView(setText);

        EditText rep = new EditText(this);
        rep.setInputType(InputType.TYPE_CLASS_NUMBER);
        rep.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        rep.setGravity(Gravity.CENTER);
        rep.setWidth(900/3);
        setRow.addView(rep);

        Button finish = new Button(this);
        finish.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        finish.setOnClickListener(v -> finishSet(setRow));
        finish.setWidth(900/3);
        finish.setText("Done");
        setRow.addView(finish);


        exerciseBody.addView(setRow);
    }

    private void finishSet(LinearLayout setRow) {

    }

    private void finishSetRep(int rep) {

    }

    /**
     * build an exercise body for rep exercise
     * @return LinearLayout
     */
    private LinearLayout buildRepExerciseBody() {

        LinearLayout exerciseBody = buildLinearLayout(LinearLayout.VERTICAL);
        LinearLayout titleRow = buildLinearLayout(LinearLayout.HORIZONTAL);
        exerciseBody.addView(titleRow);


        // Title Row
        ((ViewGroup.MarginLayoutParams) titleRow.getLayoutParams()).topMargin = 25;
        TextView setText = buildText("Set", 900/3);
        setText.setGravity(Gravity.CENTER);
        titleRow.addView(setText);

        TextView repText = buildText("reps", 900/3);
        repText.setGravity(Gravity.CENTER);
        titleRow.addView(repText);

        TextView finishText = buildText("Finish", 900/3);
        finishText.setGravity(Gravity.CENTER);
        titleRow.addView(finishText);

        return exerciseBody;

    }

    /**
     * build an exercise body for weighted exercise
     * @return LinearLayout
     */
    private LinearLayout buildWeightedExerciseBody() {
        LinearLayout exerciseBody = buildLinearLayout(LinearLayout.VERTICAL);
        LinearLayout titleRow = buildLinearLayout(LinearLayout.HORIZONTAL);
        exerciseBody.addView(titleRow);
        // Title Row
        ((ViewGroup.MarginLayoutParams) titleRow.getLayoutParams()).topMargin = 25;
        titleRow.addView(buildText("Set", 900/3));
        titleRow.addView(buildText(getString(R.string.WeightUnit), 900/3));
        titleRow.addView(buildText("reps", 900/3));
        return exerciseBody;
    }

    /**
     * builds the body for a timed exercise
     * @return LinearLayout
     */
    private LinearLayout buildTimedExerciseBody() {
        // TODO: someone do it on their free time, or else removed timed exercises from the app
        return null;
    }

    /**
     *  Helper method to create LinearLayout
     * @param orientation (vertical or horizontal)
     * @return LinearLayout
     */
    private LinearLayout buildLinearLayout(int orientation) {
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(orientation);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1));
        return linearLayout;
    }


    /**
     * Helper method to create TextViews
     * @param title title of the textview
     * @param width of the textview
     * @return TextView
     */
    private TextView buildText(String title, int width) {
       TextView t = new TextView(this);
       t.setText(title);
       t.setLayoutParams(new LinearLayout.LayoutParams(
               ViewGroup.LayoutParams.MATCH_PARENT,
               ViewGroup.LayoutParams.WRAP_CONTENT, 1));

       if(width != -1) {
           t.setWidth(width);
       }

       return t;
    }




}