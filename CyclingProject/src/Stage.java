package cycling;
//get stageID's should be sorted by start time
import java.util.ArrayList;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.time.Period;
import java.util.Arrays;
import java.time.Duration;
import java.io.Serializable;

public class Stage implements Serializable{

  private int id;
  private double stageLength;
  private String stageName;
  private String stageDescription;
  private LocalDateTime stageStartTime;
  private StageType type;
  private ArrayList<Segment> stageSegments = new ArrayList<>();
  // rider results?
  private int raceID;
  private String stageState;
  private HashMap<Integer, LocalTime[]> riderTimes = new HashMap<Integer, LocalTime[]>();
  private int[] sortedRidersByTime;
  private int[] riderStagePointsMountain;
  private int[] riderStagePointsSprinter;
  private boolean isSortedRidersUpdated = false;
  private boolean riderMountainPointsUpdated= false;
  private boolean riderSprinterPointsUpdated= false;

  public String getStageName(){
    /**
	 * The method returns a string that represents the stage's name
	 * <p>


	 * @return stageName the name of the stage
	 */
    return stageName;
  }




  public String getStageDescription(){
    /**
	 * The method returns a string that represents the stage's description
	 * <p>


	 * @return stageDescription the description of the stage
	 */
    return stageDescription;
  }

  public int getID(){
    /**
	 * The method returns an int that represents the stage's ID
	 * <p>


	 * @return id the ID of the stage
	 */
    return id;
  }

  public int getRaceID(){
    /**
	 * The method returns an int that represents the ID of the race the stage belongs in
	 * <p>


	 * @return raceID the ID of the race that contains this stage
	 */
    return raceID;
  }

  public ArrayList<Segment> getStageSegments(){
    /**
	 * The method returns an array that contains all the segment objects that belong in the stage
	 * <p>


	 * @return stageSegments an array of the segments in a stage
	 */

    return stageSegments;
  }

  public LocalDateTime getStageStartTime(){
    /**
	 * The method returns a LocalDateTime that represents the date and time when the race is started
	 * <p>


	 * @return stageStartTime a LocalDateTime of when the stage starts
	 */
    return stageStartTime;
  }

  public StageType getType(){
     /**
	 * The method returns the type of the satge. It a StageType object
	 * <p>


	 * @return type the StageType of the stage
	 */
    return type;
  }

  public double getStageLength(){
      /**
	 * The method returns the length of the stage in km
	 * <p>


	 * @return stageLength the length of the stage
	 */
    return stageLength;
  }

  public int getNumberOfSegmentsInStage(){
      /**
	 * The method returns the amount of segments in the stage
	 * <p>


	 * @return stageSegments.size() the length of the array that stored all the Segment objects in a stage
	 */
    return stageSegments.size();
  }

  public String getStageState(){
    /**
	 * The method returns the state of the stage. i.e. f it is waiting for results or is still being set up
	 * <p>


	 * @return stageState a string that is either "Preparations ongoing" or "waiting for results"
	 */
    return stageState;
  }

  // take Rider ID and return times for stage (or empty array)
  public LocalTime[] getRiderTime(int riderID){
    /**
	 * The method returns a LocalTime array that represents the times a given rider crossed the different checkpoints in the stage (including start and finish)
	 * <p>

   * @param riderID the specific rider to find times for
	 * @return riderTimesToReturn an array containing the times a rider crossed checkpoints in a stage
	 */

    Integer riderIDInteger= riderID;
    LocalTime[] riderTimesToReturn = new LocalTime[0];

    for (Integer i : riderTimes.keySet()) {
      if (i.equals(riderIDInteger)){
        riderTimesToReturn = riderTimes.get(riderIDInteger);
      }
    }

    return riderTimesToReturn;
    }




  public void setStageName(String stageName){
    /**
	 * The method changes the stage's name
	 * <p>


	 * @param stageName the new name for the stage
	 */
    this.stageName=stageName;
  }

  public void setStageDescription(String stageDescription){
    /**
	 * The method changes the stage's description
	 * <p>


	 * @param stageDescription the new description for the stage
	 */
    this.stageDescription=stageDescription;
  }

  public void setStageSegments(ArrayList<Segment> stageSegments){
    /**
	 * The method changes the stage's list of segments
	 * <p>


	 * @param stageSegments the new list of segment objects for the stage
	 */
    this.stageSegments=stageSegments;
  }

  public void setStageStartTime(LocalDateTime stageStartTime){
    /**
	 * The method changes the stage's start time
	 * <p>


	 * @param stageStartTime the new localDateTime that represents when the stage starts
	 */
    this.stageStartTime=stageStartTime;
  }

  public void sortStageSegments(){
    /**
	 * The method sorts the array of segments in the race in ascending order by their kmLocation
	 * <p>

	 */


    int n = stageSegments.size();
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 1; i < n; ++i) {
      //get the value pertaining to an index
        Segment key = stageSegments.get(i);

        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && stageSegments.get(j).getKmLocation()>key.getKmLocation()) {

          // shifts that current value up in the list
          Segment elemToAdd= stageSegments.get(j);
          stageSegments.set((j+1),elemToAdd);

          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        stageSegments.set((j+1),key);

      }
  }

  public void addRiderTimes(LocalTime[] checkpoints, int riderID){
    /**
	 * The method adds a list of completion times for checkpoints and the corresponding rider ID to the hashmap that stores all the riders and their corresponding times
	 * <p>


	 * @param checkpoints an array of the LocalTimes when the rider crossed checkpoints in the stage
   * @param riderID the ID of the rider who we are adding times for
	 */

    Integer riderIDInteger= riderID;
    isSortedRidersUpdated=false; // new rider so existing sort not accurate
    riderTimes.put(riderIDInteger, checkpoints);
  }

  public void setType(StageType type){
    /**
	 * The method changes the type of the stage
	 * <p>

   * @param type a StageType object that represents the new type for the stage
	 */
    this.type=type;
  }

  public void setStageLength(double stageLength){
    /**
	 * The method changes the length of the stage
	 * <p>

   * @param stageLength the new length of the stage in km
	 */
    this.stageLength=stageLength;
  }

  public void addStageSegment(Segment segmentToAdd){
    /**
	 * The method adds a new segment object to the stage's list of segments
	 * <p>

   * @param segmentToAdd a segment object to ass to the stageSegments arrayList
	 */
    stageSegments.add(segmentToAdd);
    sortStageSegments();
  }

  public void removeStageSegment(Segment segmentToRemove){
    /**
	 * The method checks if a segment is in the stage, and if it is, removes it from teh list of segments
	 * <p>

   * @param segmentToRemove a segment object that is to be removed from stageSegments
	 */

    boolean validRemove = false;
    for (Segment segmentInList : stageSegments){
      if (segmentInList.equals(segmentToRemove)){
        validRemove = true;
        break;
      }
    }
    if (validRemove){
      stageSegments.remove(segmentToRemove);
    }
  }

  public Duration adjustRiderTime(int riderID){
    /**
	 * The method finds the amount of time a rider took to complete a stage (finish time- start time) then adjusts it to account for pelatons before returning the adjusted amount of time the rider took as a duration.
	 * <p>

   * @param riderID the rider to adjust the time of
   * @return adjustedDurationOfRace an adjusted duration representing the adjusted elapsed time for the rider in this race
	 */

    //return elapsed time not finish time as duration

  boolean comparisonMade = true;
  LocalTime adjustedFinishTime = null;
  Duration adjustedDurationOfRace = null;
  // check if empty array return null

    LocalTime[] finishTimeArray=riderTimes.get(riderID);

    if (!(finishTimeArray == null)){

    LocalTime finishTime= finishTimeArray[finishTimeArray.length-1];
    LocalTime startTime= finishTimeArray[0];

    if (!(this.type == StageType.TT)){
      LocalTime[] comparisonRiderTimeArray;
      LocalTime comparisonRiderTime;

    while (comparisonMade){
      comparisonMade = false;
      for (Integer i : riderTimes.keySet()) {

        comparisonRiderTimeArray=riderTimes.get(i);
        comparisonRiderTime= comparisonRiderTimeArray[comparisonRiderTimeArray.length-1];

        // if same goes to next iteration
        if (finishTime.equals(comparisonRiderTime)){
          continue;
        }

        Duration durationInbetween = Duration.between(finishTime, comparisonRiderTime);
        long timeInBetween = durationInbetween.getSeconds();


        // if difference of less than a second
        if (timeInBetween < 1 && comparisonRiderTime.isBefore(finishTime)){
          finishTimeArray[finishTimeArray.length-1]=comparisonRiderTime;
          finishTime = comparisonRiderTime;
          riderTimes.put(riderID,finishTimeArray);
          comparisonMade = true;
          break;
        }
      }
    } // end while loop

    } // end if for time trial

      adjustedFinishTime = finishTimeArray[finishTimeArray.length-1];
      adjustedDurationOfRace = Duration.between(startTime, adjustedFinishTime);

    } // end if for empty
    return adjustedDurationOfRace;
  }

  public void removeRiderTimes(int riderID){
    /**
	 * The method checks if the rider has times stored in this race, and if they do, removes those times
	 * <p>

   * @param riderID the rider to remove the times of
	 */


    boolean riderIDCheck=false;
    Integer riderIDInteger= riderID;
    for (Integer i : riderTimes.keySet()) {
      if (i.equals(riderIDInteger)){
        riderIDCheck=true;
        break;
      }
      }
      if (riderIDCheck){
        riderTimes.remove(riderIDInteger);
        isSortedRidersUpdated = false;
        riderMountainPointsUpdated= false;
        riderSprinterPointsUpdated= false;

      }

  }

  // remove rider key pair completely for a stage

  public int[] getRiderRanks(){

    //check how this works for time trials!!!!

    /**
	 * The method will first check if the rider ranks have already been calculated before, and will return those ranks if they have. If they haven't been calculated it creates an array of riderIDs and an array of their finish times with matching indexes. The method then sorts both arrays based on the finish times in ascending order to put the riderIDs in their correct rankings
	 * <p>

   * @return sortedRidersByTime a list of riderIDs sorted by their finish times
	 */
//if this hasen't already been calculated, creates 2 arrays, one of rider IDs and one of their
    // gets arrayLists of keys and values
    if (!isSortedRidersUpdated){
    ArrayList<Integer> riderIDs = new ArrayList<>();
    ArrayList<LocalTime> finishTimes = new ArrayList<>();
    for (Integer i : riderTimes.keySet()) {
      riderIDs.add(i);
      LocalTime[] riderTimesArray=riderTimes.get(i);

      // if time trial gets time between end and start offset
      if (type.equals(StageType.TT)){
        Duration ttDuration = Duration.between(riderTimesArray[0], riderTimesArray[riderTimesArray.length-1]);
        LocalTime ttTimeToAdd = LocalTime.MIDNIGHT.plus(ttDuration);
        finishTimes.add(ttTimeToAdd);
      }else{
        finishTimes.add(riderTimesArray[riderTimesArray.length-1]);
      }
      }

    int n = riderIDs.size();
      //insertion sort - this part is a basic insertion sort
      // i holds index of key currently up to in array - first unsorted element
    for (int i = 0; i < n; ++i) {
      //get the value pertaining to an index
        LocalTime key = finishTimes.get(i);
        int key2 =riderIDs.get(i);
        // j holds last element of sorted list
        int j = i - 1;

        //Goes back through sorted list - checks if current value smaller than key wanting to sort
        while (j >= 0 && finishTimes.get(j).isAfter(key)) {

          // shifts that current value up in the list
          LocalTime elemToAdd= finishTimes.get(j);
          finishTimes.set((j+1),elemToAdd);
          // Does same operation to riders so sorts both lists
          int riderToAdd=riderIDs.get(j);
          riderIDs.set((j+1),riderToAdd);
          j = j - 1;
          }

        // j holds element smaller than key and ones greater than have been shifted so sets j+1 to key and thus sorted
        finishTimes.set((j+1),key);
        riderIDs.set((j+1),key2);

      }

      int[] riderIDArray = new int[riderIDs.size()];
      for (int loop = 0; loop< riderIDs.size(); ++loop) {
        riderIDArray[loop]=riderIDs.get(loop).intValue();
        }
      this.sortedRidersByTime=riderIDArray;
      isSortedRidersUpdated=true; //caching
      }
    return sortedRidersByTime;

  }

  public int getRiderSprintPoints(int riderID){
    /**
	 * The method will first check if the riderRanks and riderSprintPoints have already been calculated before, and will use those ranks and sprint pointsif they have. If they haven't been calculated it calls the methods to update/calculate these ranks and points. It then searches through the ranks until it finds the position of the rider specified then used that position in the array that stored sprint points to find the points of that specific rider.
	 * <p>

   * @param riderID the rider to find the points of
   * @return riderPointsToReturn how many sprint points the rider got in that stage
	 */
    int riderPointsToReturn=0;
    if(!isSortedRidersUpdated){
      getRiderRanks();

    }
    if(!riderSprinterPointsUpdated){
      calculateRiderStagePoints("sprinter");
    }
    for (int i=0; i< sortedRidersByTime.length; i++){
      if (riderID == sortedRidersByTime[i]){
        riderPointsToReturn=riderStagePointsSprinter[i];
        break;
      }

    }

    return riderPointsToReturn;
  }

  public int getRiderMountainPoints(int riderID){
    /**
	 * The method will first check if the riderRanks and riderMountainPoints have already been calculated before, and will use those ranks and mountain point sif they have. If they haven't been calculated it calls the methods to update/calculate these ranks and points. It then searches through the ranks until it finds the position of the rider specified then used that position in the array that stored mountain points to find the points of that specific rider.
	 * <p>

   * @param riderID the rider to find the points of
   * @return riderPointsToReturn how many mountain points the rider got in that stage
	 */
    int riderPointsToReturn=0;
    if(!isSortedRidersUpdated){
      getRiderRanks();

    }
    if(!riderMountainPointsUpdated){
      calculateRiderStagePoints("mountain");
    }
    for (int i=0; i< sortedRidersByTime.length; i++){
      if (riderID == sortedRidersByTime[i]){
        riderPointsToReturn=riderStagePointsMountain[i];
        break;
      }

    }

    return riderPointsToReturn;
  }



  public void concludeStagePreparations(){
    /**
	 * The method change the state of teh stage to "waiting for results"
	 * <p>

	 */
    stageState="waiting for results";
  }


  public void removeStageSegment(Stage segmentToRemove){
    /**
	 * The method will first check if the segment specified exist in the race, and then will remove it is it does
	 * <p>

   * @param segmentToRemove the segment object to remove from the race
	 */

    for (Segment segmentInList : stageSegments){
      //checks through whole list to see if the segment we want to remove is present, and removes it if possible
      if (segmentInList.equals(segmentToRemove)){
        stageSegments.remove(segmentInList);
      }
    }
  }


  public int[] calculateRiderStagePoints(String mountainOrSprinter){
    /**
	 * The method will first check if the riderRanks  have already been calculated before, and will use those ranks . If they haven't been calculated it calls the methods to update/calculate these ranks. It then creates a list of mlountain or sprinter points to award to riders depending on if mountain or sprinter points are being calculated and what the stage type is. The method then loops through each segment in the race and updates the rider points with any points they scored in the segments
	 * <p>

   * @param mountainOrSprinter a string that specifies if points for the sprinter or mountain classification are being calculated
   * @return riderStagePoints how many points the riders got in that stage
	 */
    if (!isSortedRidersUpdated){
      this.getRiderRanks();
    }
    int[] riderStagePoints=new int[sortedRidersByTime.length];
    if (mountainOrSprinter.equals("sprinter")){

        if (!riderSprinterPointsUpdated){

      switch(type){
        case FLAT:
          //need to make sure number of riders is sufficiently high to prevent index errors- don't want // copy this logic for segment points
          if (sortedRidersByTime.length<16){

            int[] pointsArray={50,30,20,18,16,14,12,10,8,7,6,5,4,3,2};
            riderStagePoints=Arrays.copyOfRange(pointsArray, 0, sortedRidersByTime.length );
            assert sortedRidersByTime.length == riderStagePoints.length : " lengths don't match";

          }else{
          riderStagePoints[0]=50;
          riderStagePoints[1]=30;
          riderStagePoints[2]=20;
          riderStagePoints[3]=18;
          riderStagePoints[4]=16;
          riderStagePoints[5]=14;
          riderStagePoints[6]=12;
          riderStagePoints[7]=10;
          riderStagePoints[8]=8;
          riderStagePoints[9]=7;
            }
          break;
        case MEDIUM_MOUNTAIN:
          if (sortedRidersByTime.length<16){

            int[] pointsArray={30,25,22,19,17,15,13,11,9,7,6,5,4,3,2};
            riderStagePoints=Arrays.copyOfRange(pointsArray, 0, sortedRidersByTime.length);

          }else{

          riderStagePoints[0]=30;
          riderStagePoints[1]=25;
          riderStagePoints[2]=22;
          riderStagePoints[3]=19;
          riderStagePoints[4]=17;
          riderStagePoints[5]=15;
          riderStagePoints[6]=13;
          riderStagePoints[7]=11;
          riderStagePoints[8]=9;
          riderStagePoints[9]=7;
            }
          break;
        case HIGH_MOUNTAIN:
        case TT:
          if (sortedRidersByTime.length<16){

            int[] pointsArray={20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
            riderStagePoints=Arrays.copyOfRange(pointsArray, 0, sortedRidersByTime.length );

          }else{

          riderStagePoints[0]=20;
          riderStagePoints[1]=17;
          riderStagePoints[2]=15;
          riderStagePoints[3]=13;
          riderStagePoints[4]=11;
          riderStagePoints[5]=10;
          riderStagePoints[6]=9;
          riderStagePoints[7]=8;
          riderStagePoints[8]=7;
          riderStagePoints[9]=6;
            }
          break;

          }
      //this shouldn't be able to run if list is too small
      for (int loop = 15; loop< sortedRidersByTime.length; ++loop) {
          riderStagePoints[loop]=0;
        }
      for (int i = 0; i < stageSegments.size(); ++i){
        Segment currentSegment= stageSegments.get(i);
        if (currentSegment.getType()==SegmentType.SPRINT){
          int[] addedRiderStagePoints=calculateRiderSegmentPoints(i);
          for (int j =0; j < riderStagePoints.length; ++j){
            riderStagePoints[j]=riderStagePoints[j]+addedRiderStagePoints[j];
          }

        }
      }
      this.riderStagePointsSprinter=riderStagePoints;
      this.riderSprinterPointsUpdated=true;
      }else{riderStagePoints=this.riderStagePointsSprinter;}

  }
    else if(mountainOrSprinter.equals("mountain")) {
      if (!riderMountainPointsUpdated){
      for (int i = 0; i < stageSegments.size(); ++i){
        Segment currentSegment= stageSegments.get(i);
        if (currentSegment.getType()!=SegmentType.SPRINT){
          int[] addedRiderStagePoints=calculateRiderSegmentPoints(i);
          for (int j =1; j < riderStagePoints.length; ++j){
            riderStagePoints[j]=riderStagePoints[j]+addedRiderStagePoints[j];
    }
          }
        }
      this.riderStagePointsMountain=riderStagePoints;
      this.riderMountainPointsUpdated=true;
      }else{riderStagePoints=this.riderStagePointsMountain;}
      }
    return riderStagePoints;
    }



  private int[] calculateRiderSegmentPoints(int segmentNumber){
    /**
	 * The method, given the position of a segment in the list of all segments, organises all the riders by the time where they completed the segment, then awards the top riders points based on their position and the segment type. Sorts these points so that they match the riders ranks in the stage, and then returns the array of points
	 * <p>

   * @param segmentNumber an integer that specifies the position of a segment within stageSegments
   * @return sortedRiderPoints how many points the riders got in that segment
	 */

    //will need to sort stage segments
    Segment segmentSelected=stageSegments.get(segmentNumber);
    int checkpointNumber=segmentNumber+1;



    //this chunk  sorts the riderIds by their segment finish tiem
    ArrayList<Integer> riderIDs = new ArrayList<>();
    ArrayList<LocalTime> finishTimes = new ArrayList<>();
    for (Integer i : riderTimes.keySet()) {
      riderIDs.add(i);
      LocalTime[] riderTimesArray=riderTimes.get(i);
      finishTimes.add(riderTimesArray[checkpointNumber]);
      }

    int n = riderIDs.size();
    for (int i = 1; i < n; ++i) {
        LocalTime key = finishTimes.get(i);
        int key2 =riderIDs.get(i);
        int j = i - 1;


        while (j >= 0 && finishTimes.get(j).isAfter(key)) {
          LocalTime elemToAdd= finishTimes.get(j);
          finishTimes.set((j+1),elemToAdd);
          int riderToAdd=riderIDs.get(j);
          riderIDs.set((j+1),riderToAdd);
          j = j - 1;
          }


        finishTimes.set((j+1),key);
        riderIDs.set((j+1),key2);

      }


  int[] riderPoints = new int[riderIDs.size()];
  riderPoints=segmentSelected.getPoints(riderIDs.size());


    //sorts point list so that the points for a given rider have the same index as the rank of the rider
    int[] sortedRiderPoints= new int[riderPoints.length];
      for (int loop = 0; loop< riderPoints.length; ++loop) {
        for (int innerLoop = 0; innerLoop< riderPoints.length; ++innerLoop) {
        if (riderIDs.get(loop)==sortedRidersByTime[innerLoop]){
          sortedRiderPoints[innerLoop]=riderPoints[loop];
        }
      }
    }


    return sortedRiderPoints;

    }



  public String toString(){
    /**
	 * The method returns a string containing information about the stage object
	 * <p>



	 * @return stageString a string containing the stageID, stageName, stageType, stageDescription, stageLength, and stageStartTime
	 */
    String stageString = String.format("Stage[stageID:%d,stageName:%s,stageDescription:%s,stageLength:%f,stageStartTime:%s, stageType:%s]",id,stageName, stageDescription,stageLength,stageStartTime.toString(),type.name());
    return stageString;
  }

  public Stage(String name, double length, String description, LocalDateTime startTime, StageType type, int raceID, int numberOfStages){
     /**
	 * The method is used to create a stage object with a type, raceID,startTime, length, name and description. numberOfSegments is used to calculate the stageID
	 * <p>

   * @param raceID the ID of the race that the stage exists within
   * @param numberOfStages the number of stages that have been created within the cycing portal
   * @param type the type of the stage (all possible types are in StageType)
	 * @param length the length of the stage
   * @param name the name of the stage
   * @param startTime the date and time when the stage will start
   * @param description a description of the stage
	 */
    this.stageName=name;
    this.stageLength=length;
    this.stageDescription= description;
    this.stageStartTime= startTime;
    this.type=type;
    this.raceID=raceID;
    id=++numberOfStages;
    stageState="Preparations ongoing";
  }

}
