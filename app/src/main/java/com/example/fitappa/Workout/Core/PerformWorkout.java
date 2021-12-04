package com.example.fitappa.Workout.Core;

import com.example.fitappa.Exercise.Exercise.CreatableExercise;
import com.example.fitappa.Exercise.Exercise.PerformExercise;
import com.example.fitappa.Exercise.Exercise.ExerciseTemplate;
import com.example.fitappa.Exercise.Set.SetFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a workout that can be performed.
 *
 * @author abdullah
 * @version 0.1
 * @layer 2
 */
public class PerformWorkout {
    private String name;
    private final List<PerformExercise<?>> exercises;
    private final SetFactory setFactory;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Creates a new Workout
     * @param template represents the workout template that this workout is based off of
     */
    public PerformWorkout(WorkoutTemplate template) {
        this.name = template.getName();
        this.exercises = new ArrayList<>();
        this.setFactory = new SetFactory();
        // Dependency inversion to ensure no violation of clean :-)
        for(CreatableExercise exerciseTemplate : template.getExercises()) {
            this.exercises.add(exerciseTemplate.create());
        }
    }


    public List<PerformExercise<?>> getExercises() {
        return this.exercises;
    }

    /**
     * Getter for this.name
     * @return the name of the workout
     */
    public String getName() {
        return this.name;
    }

    /**
     * Setter for this.name
     * @param name the name of the workout
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Adds an exercise to the workout
     * @param exerciseTemplate represents the exercise to add
     */
    public void addExercise(ExerciseTemplate exerciseTemplate) {
        this.exercises.add(exerciseTemplate.create());
    }

    /**
     * This method returns if a workout is being tracked
     * @return true iff this.startTime != null
     */
    public boolean isTracking() {
        return this.startTime != null;
    }



    /**
     * This method starts a workout. A workout can be started iff it wasn't started before
     *
     */
    public void start() {
        this.startTime = LocalDateTime.now();
    }


    /**
     * Precondition: 1) 0 <= i < this.currWorkout.length(),
     *               2) the exercise at position <i> is of type RepExercise
     * @param i represents the position of the exercise
     * @param numReps represents the number of reps to add in the set
     */
    public void addSet(int i, int numReps) {
        this.exercises.get(i).addSet(setFactory.buildSet(numReps));
    }

    /**
     * Precondition: 1) 0 <= i < this.currWorkout.length(),
     *               2) the exercise at position <i> is of type WeightedExercise
     * @param i represents the position of the exercise
     * @param numReps represents the number of reps to add in the set
     * @param weight represents the weight to add in the set
     */
    public void addSet(int i, int numReps, double weight) {
        this.exercises.get(i).addSet(setFactory.buildSet(numReps, weight));
    }

    /**
     * Precondition: 1) 0 <= i < this.currWorkout.length(),
     *               2) the exercise at position <i> is of type TimedExercise
     * @param i represents the position of the exercise
     * @param time represents the time in seconds to add to the set
     */
    public void addSet(int i, double time) {
        this.exercises.get(i).addSet(setFactory.buildSet(time));
    }


    /**
     *
     * A workout is defined to be "finished" if there exists a endTime
     *
     * @return True if the workout is finished
     */
    public boolean isFinished() {
        return !(endTime == null);
    }

    /**
     * This method return the volume generated by all exercises in this
     * workout
     * @return the volume generated by all exercises in the workout
     */
    public double volume() {
        double vol = 0;
        for(PerformExercise<?> e : exercises) {
            vol += e.volume();
        }
        return vol;
    }

    /**
     * This method ensures that the workout is finished.
     *
     * A workout is finished when this.isFinished() == true
     *
     */
    public void finish() {
        this.endTime = LocalDateTime.now();
    }
}
