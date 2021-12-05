package com.example.fitappa.Exercise.Exercise;
import com.example.fitappa.Exercise.Set.RepSet;
import com.example.fitappa.Exercise.Set.TimedSet;
import com.example.fitappa.Exercise.Set.WeightedSet;

import java.io.Serializable;

/**
 *
 * This class stores a template of an exercise.
 *
 * The methods create a template which can create an Exercise object using the
 * factory design pattern.
 *
 * The documentation in this class give a specification on what the methods do
 *
 * @author abdullah
 * @version 0.1
 * @layer 1.
 */
public class ExerciseTemplate implements Serializable, CreatableExercise {
    protected String name;
    protected int numSets;
    /* These values must be constant later
    * Accepted values: REP, WEIGHTED, TIMED
    * */
    protected String category;

    /**
     * Constructor for a User class, takes in all necessary variables needed to make a User
     *
     * @param name   The String name referring to the name of the exercise
     * @param sets   The int represents the number of sets
     */
    public ExerciseTemplate(String name, int sets, String category) {
        this.name = name;
        this.numSets = sets;
        this.category = category;
    }

    /**
     * Factory Design Pattern for Exercise objects.
     * Note that this method was implemented due to the CreatableExercise interface
     * @return the appropriate Exercise object based on this.category
     */
    public PerformExercise<?> create() {
        if (this.category.equals("REP"))
            return new PerformExercise<RepSet>(this.name, this.category);
        else if (this.category.equals("WEIGHTED"))
            return new PerformExercise<WeightedSet>(this.name, this.category);
        else
            return new PerformExercise<TimedSet>(this.name, this.category);
    }

    /**
     * Empty constructor needed by firebase
     */
    @SuppressWarnings("unused")
    public ExerciseTemplate() {
        numSets = 0;
    }

    /**
     * Creates an Exercise object given the name.
     *
     * @param name - name of the exercise
     */
    public ExerciseTemplate(String name) {
        this.name = name;
        this.numSets = 0;
    }

    /**
     * returns the name of the exercise
     *
     * @return the string name
     */
    public String getName() {
        return name;
    }

    /**
     * return the number of sets
     *
     * @return the int numSets
     */
    public int getNumSets() {
        return numSets;
    }

    /**
     * Necessary method for Firebase
     */
    @SuppressWarnings("unused")
    public String getCategory() {
        return category;
    }
}
