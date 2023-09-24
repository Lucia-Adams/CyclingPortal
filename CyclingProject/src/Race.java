package cycling;
import java.util.ArrayList;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.Duration;

public class Race implements Serializable{

  private int id;
  private String raceName;
  private String raceDescription;

  private ArrayList<Stage> raceStages = new ArrayList<>();


  // toString, addstages and getters and setters
  public int getID(){
    /**
	 * The method returns the ID of the race
	 * <p>

	 * @return id The race's ID
	 */
    return id;
  }

  public String getRaceName(){
    /**
	 * The method returns the name of the race
	 * <p>

	 * @return raceName The race's name
	 */
    return raceName;
  }

  public String getRaceDescription(){
    /**
	 * The method returns the description of the race
	 * <p>

	 * @return raceDescription The race's description
	 */
    return raceDescription;
  }

  public ArrayList<Stage> getRaceStages(){
    /**
	 * The method returns an array of the stage objects stored in the race
	 * <p>

	 * @return raceStages an array of stages that belong to the race
	 */
    return raceStages;
  }

  public int getNumberOfStages(){
    /**
	 * The method returns the amount of stages in the race
	 * <p>

	 * @return raceStages.size() the size of (amount of stages stored in)  the raceStages array, which is an array that stores all of the stages in a race
	 */
    return raceStages.size();
  }


  public void setRaceName(String raceName){
    /**
	 * The method changes the name of a race to the value passed in as a parameter
	 * <p>

	 * @param raceName The race's new name
	 */
    this.raceName = raceName;
  }

  public void setRaceDescription(String raceDescription){
      /**
	 * The method changes the description of a race to the value passed in as a parameter
	 * <p>

	 * @param raceDescription The race's new description
	 */
    this.raceDescription = raceDescription;
  }

  public void setRaceStages(ArrayList<Stage> raceStages){
      /**
	 * The method changes the list of stages of a race to the value passed in as a parameter
	 * <p>

	 * @param raceStages the new list of stages that belong to a race
	 */
    this.raceStages = raceStages;
  }

  public void addRaceStage(Stage stageToAdd){
      /**
	 * The method adds a stage object to the list of stages belonging to a race
	 * <p>

	 * @param stageToAdd the new stage to add to the race
	 */
    raceStages.add(stageToAdd);
  }

  // could return true or false if removed or not
  public void removeRaceStage(Stage stageToRemove){
      /**
	 * The method removes a given stage from the race
	 * <p>

	 * @param stageToRemove The stage object to remove from the race
	 */
    boolean validRemove = false;
    for (Stage stageInList : raceStages){
      if (stageInList.equals(stageToRemove)){
        validRemove = true;
        break;
      }
    }
    if (validRemove){
    raceStages.remove(stageToRemove);
      }
  }

  public void sortRaceStages(){
      /**
	 * The method returns arraylist of stages in order. The stages are sorted by their start time using an insertion sort
	 * <p>


	 */


    int n = raceStages.size();
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        Stage key = raceStages.get(i);

        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && raceStages.get(j).getStageStartTime().isAfter(key.getStageStartTime())) {

          // shifts that current value up in the list
          Stage elemToAdd= raceStages.get(j);
          raceStages.set((j+1),elemToAdd);

          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        raceStages.set((j+1),key);

      }
  }

  private Duration[] tallyUpTimes(){
    /**
	 * The method adds up all of the riders adjusted elapsed times across every stage in the race
	 * <p>

	 * @return totalElapsedTime an array of durations that represent the total elapsed time for each rider. The times match the order of riders returned by getRidersInRace()
	 */

    int[] ridersInRace=getRidersInRace();
    Duration[] totalElapsedTime = new Duration[ridersInRace.length];

    //fill up this array with 0s duration wise
    for (int i = 0; i < ridersInRace.length; ++i){
      totalElapsedTime[i] = Duration.ZERO;
    }

    for (Stage eachStage:raceStages){
      for (int i = 0; i < ridersInRace.length; ++i){
        int eachRider= ridersInRace[i];
        Duration timeForStage=eachStage.adjustRiderTime(eachRider);
        totalElapsedTime[i]=totalElapsedTime[i].plus(timeForStage);

      }
    }
    return totalElapsedTime;
  }

  public LocalTime[] getGCTimes(){
    /**
	 * The method sorts a list of all the riders' total elapsed times in ascending order and returns the sorted list
	 * <p>

	 * @return totalElapsedTimeLT a sorted array of localTimes that represent the total elapsed time for each rider.
	 */
    Duration[] totalElapsedTime = tallyUpTimes();
    LocalTime baseTime;

    int n = totalElapsedTime.length;
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        Duration key = totalElapsedTime[i];
        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && (totalElapsedTime[j].compareTo(key))>0) {

          // shifts that current value up in the list
          Duration elemToAdd= totalElapsedTime[j];
          totalElapsedTime[j+1]=elemToAdd;


          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        totalElapsedTime[j+1]=key;

      }

      LocalTime[] totalElapsedTimeLT = new LocalTime[totalElapsedTime.length];
      for (int i=0; i<totalElapsedTime.length; i++){
      // converts duration list to localtime list
        baseTime = LocalTime.of(0,0,0,0);
        baseTime.plus(totalElapsedTime[i]);
        totalElapsedTimeLT[i] = baseTime;
      }

    // loop thorugh all durations and make into local time
    //convert the durations to local time
    return totalElapsedTimeLT;



  }

  public int[] getRidersInRace(){
     /**
	 * The method returns a list of all of the riders in the first stage of a race. This should also be equal to the riders in every stage, as we assume that riders remain the same for every stage in the race
	 * <p>

	 * @return returnRiders the riders in the first stage of a race
	 */
    int[] returnRiders= new int[raceStages.size()];
    if(raceStages.size()>0){
       returnRiders=raceStages.get(0).getRiderRanks();
      }
    assert returnRiders.length==raceStages.size(): "sizes don't match";
    return returnRiders;
  }

  public void removeRider(int riderID){
    /**
	 * The method loops through each stage in the race and removes the specified rider and their times from the stage
	 * <p>

	 * @param riderID the ID of the rider to remove from the race
	 */
    for (Stage eachStage: raceStages){
      eachStage.removeRiderTimes(riderID);
    }
  }


  public int[] getGCRanks(){
    /**
	 * The method sorts a list of all the riders' IDs by their total elapsed time in ascending order and returns the sorted list
	 * <p>

	 * @return ridersInRace a sorted array of riderIDs that represent the ranks that the riders achieved in the stage
	 */
    int[] ridersInRace=getRidersInRace();
    Duration[] totalElapsedTime = tallyUpTimes();


    int n = ridersInRace.length;
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        Duration key = totalElapsedTime[i];
        int key2 =ridersInRace[i];
        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && totalElapsedTime[j].compareTo(key)>0) {

          // shifts that current value up in the list
          Duration elemToAdd= totalElapsedTime[j];
          totalElapsedTime[j+1]=elemToAdd;

          // Does same operation to riders so sorts both lists
          int riderToAdd= ridersInRace[j];
          ridersInRace[j+1]=riderToAdd;

          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        totalElapsedTime[j+1]=key;
        ridersInRace[j+1]=key2;


      }
    return ridersInRace;

  }

  private int[] tallyUpPoints(String mountainOrSprint){
    /**
	 * The method adds up all the points a rider achieved across the race (adding up the point totals for each stage) for either the mountain or sprinters classification depending on the input.
	 * <p>

   * @param mountainOrSprint specifies what type of points will be tallied up
	 * @return totalPoints an array of the added up points for riders, sorted so that the points at a given position will match the rider at that same position (based on the list of riders returned by getRidersInRace())
	 */
    // to get list of riders
    int[] ridersInRace=getRidersInRace(); // as above
    int[] totalPoints = new int[ridersInRace.length];
    int pointsForStage=0;
    //fill up this array with 0s duration wise
    for (int k = 0; k< totalPoints.length; ++k){
      totalPoints[k]=0;
      }
    for (Stage eachStage:raceStages){
      for (int i = 0; i < ridersInRace.length; ++i){
        int eachRider= ridersInRace[i];
        if (mountainOrSprint.equals("sprint")){
          pointsForStage=eachStage.getRiderSprintPoints(eachRider);
        }else if (mountainOrSprint.equals("mountain")){
          pointsForStage=eachStage.getRiderMountainPoints(eachRider);
        }
        totalPoints[i]=totalPoints[i] + pointsForStage;

      }
    }
    return totalPoints;
  }




  public int[] getPoints(String mountainOrSprint){
    /**
	 * The method adds up all the points a rider achieved across the race (adding up the point totals for each stage) for either the mountain or sprinters classification depending on the input. It then sorts these points in descending order.
	 * <p>

   * @param mountainOrSprint specifies what type of points will be tallied up
	 * @return totalPoints an array of the added up points for riders, sorted in descending order
	 */
    int[] totalPoints = tallyUpPoints(mountainOrSprint);


    int n = totalPoints.length;
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        int key = totalPoints[i];

        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && (totalPoints[j]<key)) {

          // shifts that current value up in the list
          int elemToAdd= totalPoints[j];
          totalPoints[j+1]=elemToAdd;


          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        totalPoints[j+1]=key;



      }
    return totalPoints;

  }

  public int[] getPointRanks(String mountainOrSprint){
    /**
	 * The method adds up all the points a rider achieved across the race (adding up the point totals for each stage) for either the mountain or sprinters classification depending on the input. It then sorts these points in descending order and sorts a list of riderIDs so that a specific rider's position matches their points ranking
	 * <p>

   * @param mountainOrSprint specifies what type of points will be tallied up
	 * @return ridersInRace an array of the riderIDs sorted to match the order of their points
	 */
    int[] ridersInRace=getRidersInRace();
    int[] totalPoints = tallyUpPoints(mountainOrSprint);
    assert totalPoints.length==ridersInRace.length: "lengths don't amtch";

    int n = ridersInRace.length;
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        int key = totalPoints[i];
        int key2 =ridersInRace[i];
        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && (totalPoints[j]<key)) {

          // shifts that current value up in the list
          int elemToAdd= totalPoints[j];
          totalPoints[j+1]=elemToAdd;

          // Does same operation to riders so sorts both lists
          int riderToAdd= ridersInRace[j];
          ridersInRace[j+1]=riderToAdd;

          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        totalPoints[j+1]=key;
        ridersInRace[j+1]=key2;


      }
    return ridersInRace;

  }

  // add stage to strings
  public String toString(){
    /**
	 * The method returns a string that gives information about this specific race object
	 * <p>


	 * @return raceString a string containing the race's name, id, description,and total length, as well as the number of stages in the race
	 */

    double totalLength=0;
		for (Stage eachStage : raceStages) {
          totalLength+= eachStage.getStageLength();
       		}
    String raceString = String.format("Race[raceID:%d,raceName:%s,raceDescription:%s,numberOfStages:%d,totalLength:%f]", id, raceName, raceDescription,raceStages.size() , totalLength);
    return raceString;
  }

  public Race(String raceName, int numberOfRaces){
     /**
	 * The method creates a race object when given a name
	 * <p>


	 * @param raceName the name of the race
   * @param numberOfRaces the number of races already in the system. This is used to create the raceID
	 */
    this.raceName = raceName;
    id = ++numberOfRaces;
  }

  public Race(String raceName, String raceDescription, int numberOfRaces){
    /**
	 * The method creates a race object when given a name and description
	 * <p>


	 * @param raceName the name of the race
   * @param raceDescription the description of the race
   * @param numberOfRaces the number of races already in the system. This is used to create the raceID
	 */
    this.raceName = raceName;
    this.raceDescription = raceDescription;
    id = ++numberOfRaces;
  }



}

// Potential methods:
// check if stage in race
// check if race has no stages
// check how many stages in a race
