package com.example.fitappa.constants;

/**
 * Constants to be used for Firebase methods
 * <p>
 * The method in the class return String constants
 * <p>
 * The documentation in this class give a specification on what the methods do
 *
 * @author Uthman
 * @since 0.2
 */
@SuppressWarnings("SameReturnValue")
public class DatabaseConstants {

    /**
     * Get the collection name for users
     *
     * @return String representing collection name for users
     */
    public String getUsers() {
        return "users";
    }

    /**
     * Get the exercises storage name
     *
     * @return String representing the exercises storage name
     */
    public String getExercises() {
        return "exercises";
    }

    /**
     * Get the name for the routines stored
     *
     * @return String representing the name for the routines stored
     */
    public String getRoutines() {
        return "routines";
    }

    /**
     * Get the string that represents the way a name is stored
     *
     * @return string that represents the way a name is stored
     */
    public String getName() {
        return "name";
    }

    /**
     * Get the string that represents the way sets are stored
     *
     * @return string that represents the way sets are stored
     */
    public String getSets() {
        return "sets";
    }

    /**
     * Get the string that represents the way a category is stored
     *
     * @return string that represents the way a category is stored
     */
    public String getCategory() {
        return "category";
    }

    /**
     * Get the string that represents the way a Performed Workouts is stored
     *
     * @return string that represents the way a Performed Workouts is stored
     */
    public String getPerformedWorkouts() {
        return "Performed Workouts";
    }

    /**
     * Get the string for the realm app id
     *
     * @return String realm app id
     */
    public String getRealmAppID() {
        return "fitapp-hviuu";
    }
}
