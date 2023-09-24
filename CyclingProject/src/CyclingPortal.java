//maybe use reflection
package cycling;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Duration;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Arrays;

public class CyclingPortal implements CyclingPortalInterface{

  // races attribute
  private ArrayList<Race> systemRaces = new ArrayList<>();
  private ArrayList<Team> systemTeams = new ArrayList<>();

  // check repurcusions with laoding portal etc
  private Integer nOfTeams = Integer.valueOf(0);
  private Integer nOfRiders = Integer.valueOf(0);
  private Integer nOfRaces = Integer.valueOf(0);
  private Integer nOfStages = Integer.valueOf(0);
  private Integer nOfSegments = Integer.valueOf(0);


  private Race checkRaceIdExists(int raceID) throws IDNotRecognisedException{
    /**
	 * The method checks if a specific raceID exists within the system, and if it does, returns the race object associated with that ID
	 * <p>
   *
	 * @param raceID the raceID to check for
   * @return raceSelected the race object that the ID belongs to
   * @throws IDNotRecognisedException If the ID does not match to any race in the
	 *                                  system.
	 */
    boolean recognisedID=false;
    Race raceSelected = null;
    for (Race eachRace : systemRaces){
      if (eachRace.getID() == raceID){
        recognisedID = true;
        raceSelected=eachRace;
        break;
      }
    }
    if (recognisedID == false){
      throw new IDNotRecognisedException(String.format("raceID %d not in system",raceID));
    }
    //may add else statement
    return raceSelected;
  }

  private Team checkTeamIdExists(int teamID) throws IDNotRecognisedException{
    /**
	 * The method checks if a specific teamID exists within the system, and if it does, returns the team object associated with that ID
	 * <p>
   *
	 * @param teamID the teamID to check for
   * @return teamSelected the team object that the ID belongs to
   * @throws IDNotRecognisedException If the ID does not match to any team in the
	 *                                  system.
	 */
    boolean recognisedID=false;
    Team teamSelected = null; // for some reason fixes that
    for (Team eachTeam : systemTeams){
      if (eachTeam.getID() == teamID){
        recognisedID = true;
        teamSelected=eachTeam;
        break;
      }
    }
    if (recognisedID == false){
      throw new IDNotRecognisedException(String.format("teamID %d not in system",teamID));
    }
    //may add else statement
    return teamSelected;
  }



  private Stage checkStageIdExists(int stageID) throws IDNotRecognisedException{
    /**
	 * The method checks if a specific stageID exists within the system, and if it does, returns the stage object associated with that ID
	 * <p>
   *
	 * @param stageID the stageID to check for
   * @return stageSelected the stage object that the ID belongs to
   * @throws IDNotRecognisedException If the ID does not match to any stage in the
	 *                                  system.
	 */
    Stage stageSelected = null;
    boolean recognisedID=false;
    ArrayList<Stage> systemStages= new ArrayList<>();

    for (Race eachRace : systemRaces){

      systemStages=eachRace.getRaceStages();

      for (Stage eachStage : systemStages){
        if (eachStage.getID()==stageID){
          recognisedID = true;
          stageSelected=eachStage;
          break;
          }
      }
      if (recognisedID){break;}
    }
    if (recognisedID == false){
      throw new IDNotRecognisedException(String.format("stageID %d not in system",stageID));
    }
    //may add else statement
    return stageSelected;
  }

  private Rider checkRiderIdExists(int riderID) throws IDNotRecognisedException{
    /**
	 * The method checks if a specific riderID exists within the system, and if it does, returns the rider object associated with that ID
	 * <p>
   *
	 * @param riderID the riderID to check for
   * @return riderSelected the rider object that the ID belongs to
   * @throws IDNotRecognisedException If the ID does not match to any rider in the
	 *                                  system.
	 */
    Rider riderSelected = null;
    boolean recognisedID=false;
    ArrayList<Rider> systemRiders= new ArrayList<>();

    for (Team eachTeam : systemTeams){

      systemRiders=eachTeam.getTeamRiders();

      for (Rider eachRider : systemRiders){
        if (eachRider.getID()== riderID){
          recognisedID = true;
          riderSelected=eachRider;
          break;
          }
      }
      if (recognisedID){break;}
    }
    if (recognisedID == false){
      throw new IDNotRecognisedException(String.format("riderID %d not in system",riderID));
    }
    //may add else statement
    return riderSelected;
  }


  @Override
	public int[] getRaceIds() {
		// Get the races currently created in the platform.
    int numRaces = systemRaces.size();
    int[] raceIDArray = new int[numRaces];

    for (int i=0 ; i< numRaces ; i++){
      int raceID = systemRaces.get(i).getID();
      raceIDArray[i] = raceID;
    }

		return raceIDArray;
  }

  @Override
	public int createRace(String name, String description) throws   IllegalNameException, InvalidNameException {
    //The method creates a staged race in the platform with the given name and * description

    boolean legalName=true;
    for (Race eachRace : systemRaces){
      if (eachRace.getRaceName().equalsIgnoreCase(name)){
        legalName = false;
        break;
      }
    }
    if (legalName == false){
      throw new IllegalNameException(String.format("raceName %s already in system",name));
    }

    if(name.equals(null) || name.contains(" ") || (name.length()>30) || name.equals("")){
      throw new InvalidNameException(String.format("raceName %s is an invalid name. Race names should not be empty, null, contain white space, or be more than 30 characters long",name));
    }

    Race newRace = new Race(name, description, nOfRaces.intValue());
    nOfRaces = new Integer(nOfRaces.intValue() + 1);
    int newRaceID = newRace.getID();
    systemRaces.add(newRace);

    return newRaceID;

	}


  @Override
	public String viewRaceDetails(int raceId) throws IDNotRecognisedException {
		//  Get the details from a race.
    Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.toString();
	}

  @Override
	public void removeRaceById(int raceId) throws IDNotRecognisedException {
    //need to deal with removing the results
	Race raceSelected=this.checkRaceIdExists(raceId);
  systemRaces.remove(raceSelected);
	}

  @Override
	public int getNumberOfStages(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.getRaceStages().size();
	}

  @Override
	public int addStageToRace(int raceId, String stageName, String description, double length, LocalDateTime startTime, StageType type)
			throws IDNotRecognisedException, IllegalNameException, InvalidNameException, InvalidLengthException {


		Race raceSelected=this.checkRaceIdExists(raceId);
    boolean legalName=true;
    for (Stage eachStage : raceSelected.getRaceStages()){
      if (eachStage.getStageName().equalsIgnoreCase(stageName)){
        legalName = false;
        break;
      }
    }
    if (legalName == false){
      throw new IllegalNameException(String.format("raceName %s already in system",stageName));
    }

    if(stageName.equals(null) || stageName.contains(" ") || (stageName.length()>30) || stageName.equals("")){
      throw new InvalidNameException(String.format("stageName %s is an invalid name. Stage names should not be empty, null, contain white space, or be more than 30 characters long",stageName));
    }
    if (length<5.0){
      throw new InvalidLengthException(String.format("%f is too short to be a valid stagelength. Stages should be 5km or longer",length));
    }
    Stage stageToAdd= new Stage(stageName,length,description,startTime, type, raceId, nOfStages.intValue());
    nOfStages = new Integer(nOfStages.intValue() + 1);

    raceSelected.addRaceStage(stageToAdd);
    return stageToAdd.getID();

  }


  @Override
	public int[] getRaceStages(int raceId) throws IDNotRecognisedException {
    // Retrieves the list of stage IDs of a race
    // ordered by localDateTime ie by sequence in race

    Race raceSelected=this.checkRaceIdExists(raceId);
    ArrayList<Stage> raceStagesArray = raceSelected.getRaceStages();
    int arrayIdLength = raceStagesArray.size();
    int[] raceStageId = new int[arrayIdLength];

    for (int i=0 ; i< arrayIdLength ; i++){
      int stageID = raceStagesArray.get(i).getID();
      raceStageId[i] = stageID;
    }

    return raceStageId;
	}

  @Override
	public double getStageLength(int stageId) throws IDNotRecognisedException {

    Stage stageSelected = this.checkStageIdExists(stageId);
    return stageSelected.getStageLength();

	}

  @Override
	public void removeStageById(int stageId) throws IDNotRecognisedException {

    Stage stageSelected = this.checkStageIdExists(stageId);

    // get the id of the race the stage belongs to
    int stageRaceID=stageSelected.getRaceID();

    // get the race object that corresponds to the stage's raceID variable
    Race raceSelected= this.checkRaceIdExists(stageRaceID);
    //removes the stage from the race's lists of stages
    raceSelected.removeRaceStage(stageSelected);

	}

  @Override
	public int addCategorizedClimbToStage(int stageId, Double location, SegmentType type, Double averageGradient, Double length) throws IDNotRecognisedException, InvalidLocationException, InvalidStageStateException,
			InvalidStageTypeException {

    Stage stageSelected = this.checkStageIdExists(stageId);

    if (location>stageSelected.getStageLength()){
      throw new InvalidLocationException(String.format("location %f is larger than the length of the stage",location));
    }
    //check if stage is in correct state
    if (stageSelected.getStageState().equals("waiting for results")){
      throw new InvalidStageStateException("Stage is not in the correct state to add new segments");

    }

    if (stageSelected.getType().equals(StageType.TT)){
      throw new InvalidStageTypeException("Time trials can't contain segments");
    }

    CategorizedClimb newSegment= new CategorizedClimb(location, type, length, averageGradient,stageId, nOfSegments.intValue());

    nOfSegments = new Integer(nOfSegments.intValue() + 1);
    stageSelected.addStageSegment(newSegment);
    return newSegment.getID();

	}

  @Override
	public int addIntermediateSprintToStage(int stageId, double location) throws IDNotRecognisedException,
			InvalidLocationException, InvalidStageStateException, InvalidStageTypeException {

    Stage stageSelected = this.checkStageIdExists(stageId);
    if (location>stageSelected.getStageLength()){
      throw new InvalidLocationException(String.format("location %f is larger than the length of the stage",location));
    }

    if (stageSelected.getStageState().equals("waiting for results")){
      throw new InvalidStageStateException("Stage is not in the correct state to add new segments");

    }

    if (stageSelected.getType().equals(StageType.TT)){
      throw new InvalidStageTypeException("Time trials can't contain segments");
    }

    IntermediateSprint newSegment= new IntermediateSprint(location, stageId, nOfSegments.intValue());

    stageSelected.addStageSegment(newSegment);
    nOfSegments = new Integer(nOfSegments.intValue() + 1);
    return newSegment.getID();
	}


  @Override
	public void removeSegment(int segmentId) throws IDNotRecognisedException, InvalidStageStateException {

    Stage stageSelected = null;
    Segment segmentSelected = null;
    boolean recognisedID=false;
    ArrayList<Stage> systemStages= new ArrayList<>();
    ArrayList<Segment> systemSegments= new ArrayList<>();

    for (Race eachRace : systemRaces){

      systemStages=eachRace.getRaceStages();

      for (Stage eachStage : systemStages){

        systemSegments=eachStage.getStageSegments();

        for (Segment eachSegment : systemSegments){
          if (eachSegment.getID()==segmentId){
            recognisedID = true;
            segmentSelected=eachSegment;
            stageSelected=eachStage;
            break;
          }
        }
        if (recognisedID){break;}
      }
      if (recognisedID){break;}
    }
    if (recognisedID == false){
      throw new IDNotRecognisedException(String.format("segmentID %d not in system",segmentId));
    }

    if (stageSelected.getStageState().equals("waiting for results")){
      throw new InvalidStageStateException("Stage is not in the correct state to remove segments");
    }

    stageSelected.removeStageSegment(segmentSelected);

	}


  @Override
	public void concludeStagePreparation(int stageId) throws IDNotRecognisedException, InvalidStageStateException {
		Stage stageSelected = this.checkStageIdExists(stageId);
    if (stageSelected.getStageState().equals("waiting for results")){
      throw new InvalidStageStateException("Stage is already waiting for results");
    }
    stageSelected.concludeStagePreparations();

	}

  @Override
	public int[] getStageSegments(int stageId) throws IDNotRecognisedException {

    Stage stageSelected = this.checkStageIdExists(stageId);

    ArrayList<Segment> stageSegmentsArray = stageSelected.getStageSegments();
    int arrayIdLength = stageSegmentsArray.size();
    int[] stageSegmentIds = new int[arrayIdLength];

    for (int i=0 ; i< arrayIdLength ; i++){
      int segmentID = stageSegmentsArray.get(i).getID();
      stageSegmentIds[i] = segmentID;
    }

    return stageSegmentIds;

	}

  @Override
	public int createTeam(String name, String description) throws IllegalNameException, InvalidNameException {


		boolean legalName=true;
    for (Team eachTeam : systemTeams){
      if (eachTeam.getTeamName().equalsIgnoreCase(name)){
        legalName = false;
        break;
      }
    }
    if (legalName == false){
      throw new IllegalNameException(String.format("teamName %s already in system",name));
    }

    if(name.equals(null) || name.contains(" ") || (name.length()>30) || name.equals("")){
      throw new InvalidNameException(String.format("teamName %s is an invalid name. Team names should not be empty, null, contain white space, or be more than 30 characters long",name));
    }

    Team newTeam = new Team(name, description, nOfTeams);
    nOfTeams = new Integer(nOfTeams.intValue() + 1);
    int newTeamID = newTeam.getID();
    systemTeams.add(newTeam);

    return newTeamID;
    }

  @Override
	public void removeTeam(int teamId) throws IDNotRecognisedException {
    Team teamSelected=checkTeamIdExists(teamId);
    systemTeams.remove(teamSelected);
	}

  @Override
	public int[] getTeams() {
		// Get the teams currently created in the platform.
    int numTeams = systemTeams.size();
    int[] teamIDArray = new int[numTeams];

    for (int i=0 ; i< numTeams ; i++){
      int teamID = systemTeams.get(i).getID();
      teamIDArray[i] = teamID;
    }

		return teamIDArray;
	}


  @Override
	public int[] getTeamRiders(int teamId) throws IDNotRecognisedException {
		Team teamSelected=checkTeamIdExists(teamId);
    ArrayList<Rider> teamRidersArray = teamSelected.getTeamRiders();
    int arrayIdLength = teamRidersArray.size();
    int[] teamRidersId = new int[arrayIdLength];

    for (int i=0 ; i< arrayIdLength ; i++){
      int riderID = teamRidersArray.get(i).getID();
      teamRidersId[i] = riderID;
    }
    return teamRidersId;
	}

  @Override
	public int createRider(int teamID, String name, int yearOfBirth)
			throws IDNotRecognisedException, IllegalArgumentException {

    Team teamSelected=checkTeamIdExists(teamID);
    if(name.equals(null)){
      throw new IllegalArgumentException(String.format("name %s is an invalid name. Rider names should not be null",name));
    }
    if (yearOfBirth<1900){
      throw new IllegalArgumentException(String.format("year of birth %d  must be greater than 1900",yearOfBirth));
    }
    Rider createdRider= new Rider(teamID,name,yearOfBirth, nOfRiders);
    nOfRiders = new Integer(nOfRiders.intValue() + 1);
    teamSelected.addTeamRider(createdRider);
    return (createdRider.getID());
	}


  @Override
	public void removeRider(int riderId) throws IDNotRecognisedException {
		Rider riderSelected = this.checkRiderIdExists(riderId);

    // get the id of the team the rider belongs to
    int riderTeamID=riderSelected.getTeamID();

    for (Race eachRace: systemRaces){
      if (Arrays.asList(eachRace.getRidersInRace()).contains(riderId)){
        eachRace.removeRider(riderId);
      }
    }

    // get the team object that corresponds to the rider's teamID variable
    Team teamSelected= this.checkTeamIdExists(riderTeamID);
    //removes the rider from the team's lists of stages
    teamSelected.removeTeamRider(riderSelected);

	}

  // assuming checkpoints would be passed in as 0 1.1 2.2 etc
  // so array type localtime
  @Override
	public void registerRiderResultsInStage(int stageId, int riderId, LocalTime... checkpoints)
			throws IDNotRecognisedException, DuplicatedResultException, InvalidCheckpointsException,
			InvalidStageStateException {

    Rider riderSelected= checkRiderIdExists(riderId);
    Stage stageSelected= checkStageIdExists(stageId);
    int numExpectedSegments = stageSelected.getNumberOfSegmentsInStage()+2;

    if (numExpectedSegments != checkpoints.length){
      throw new InvalidCheckpointsException(String.format("Number of checkpoints does not equal the number of segments + 2 (%d)", numExpectedSegments));
    }

    LocalTime[] riderStageTimes = stageSelected.getRiderTime(riderId);

    if (!(riderStageTimes.length == 0)){
      throw new DuplicatedResultException(String.format("Rider %d already has results stored. Each rider can have only one result per stage",riderId));
    }

    if (!stageSelected.getStageState().equals("waiting for results")){
      throw new InvalidStageStateException("Stage is not waiting for results. Results can only be added if waiting for results.");
	  }

    stageSelected.addRiderTimes(checkpoints, riderId);
}


  @Override
	public LocalTime[] getRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {

    Rider riderSelected= checkRiderIdExists(riderId);
    Stage stageSelected= checkStageIdExists(stageId);

    LocalTime[] riderStageTimes = stageSelected.getRiderTime(riderId);
    return riderStageTimes;

	}

  @Override
	public LocalTime getRiderAdjustedElapsedTimeInStage(int stageId, int riderId) throws IDNotRecognisedException {
		Rider riderSelected= checkRiderIdExists(riderId);
    Stage stageSelected= checkStageIdExists(stageId);
    LocalTime finishTime = null;
    Duration adjustedElapsedDuration = stageSelected.adjustRiderTime(riderId);

    if (adjustedElapsedDuration != null){
      finishTime = LocalTime.MIDNIGHT.plus(adjustedElapsedDuration);
    }
		return finishTime;
	}

  @Override
	public void deleteRiderResultsInStage(int stageId, int riderId) throws IDNotRecognisedException {
		Rider riderSelected= checkRiderIdExists(riderId);
    Stage stageSelected= checkStageIdExists(stageId);
    stageSelected.removeRiderTimes(riderId);
	}


  @Override
	public int[] getRidersRankInStage(int stageId) throws IDNotRecognisedException {

    Stage stageSelected= checkStageIdExists(stageId);
    return stageSelected.getRiderRanks();
	}

  @Override
	public LocalTime[] getRankedAdjustedElapsedTimesInStage(int stageId) throws IDNotRecognisedException {
		Stage stageSelected= checkStageIdExists(stageId);
    int[] riderIDList= stageSelected.getRiderRanks();
    LocalTime[] rankedElapsedTimes = new LocalTime[riderIDList.length];

    // adjustRiderTime returns duration
    for (int i=0; i< riderIDList.length; i++){
      Duration adjustedElapsedDuration =stageSelected.adjustRiderTime(riderIDList[i]);
      LocalTime finishTime = LocalTime.MIDNIGHT.plus(adjustedElapsedDuration);
      rankedElapsedTimes[i] = finishTime;
    }
    return rankedElapsedTimes;

	}

  @Override
	public int[] getRidersPointsInStage(int stageId) throws IDNotRecognisedException {
		Stage stageSelected= checkStageIdExists(stageId);
    int[] ridersPoints=stageSelected.calculateRiderStagePoints("sprinter");
    return ridersPoints;
	}

  @Override
  public int[] getRidersMountainPointsInStage(int stageId) throws IDNotRecognisedException{
    Stage stageSelected= checkStageIdExists(stageId);
    int[] ridersPoints=stageSelected.calculateRiderStagePoints("mountain");
    return ridersPoints;
  }

  @Override
	public void eraseCyclingPortal() {
		systemRaces.clear();
    systemTeams.clear();
    nOfTeams=0;
    nOfRiders=0;
    nOfRaces= 0;
    nOfStages=0;
    nOfSegments=0;

	}

  @Override
	public void saveCyclingPortal(String filename) throws IOException {
		Object[] portalContents = {systemRaces,systemTeams, nOfTeams, nOfRiders, nOfRaces, nOfStages, nOfSegments};


   FileOutputStream fileOut = new FileOutputStream(filename);

   ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);

   objectOut.writeObject(portalContents);


   objectOut.close();
   fileOut.close();

   System.out.println("Serialized data is saved in " +
      filename);

	}

  @Override
	public void loadCyclingPortal(String filename) throws IOException, ClassNotFoundException {
		Object[] portalContents=new Object[7];

		FileInputStream fileIn = new FileInputStream(filename);

	  ObjectInputStream objectIn = new ObjectInputStream(fileIn);


	  portalContents = (Object[]) objectIn.readObject();


	  objectIn.close();
	  fileIn.close();
    systemRaces= (ArrayList<Race>) portalContents[0];
    systemTeams=(ArrayList<Team>) portalContents[1];
    nOfTeams=(Integer) portalContents[2];
    nOfRiders=(Integer) portalContents[3];
    nOfRaces= (Integer) portalContents[4];
    nOfStages= (Integer) portalContents[5];
    nOfSegments= (Integer) portalContents[6];


	}
  @Override
	public void removeRaceByName(String name) throws NameNotRecognisedException {
    boolean nameFound=false;
    Race raceFound=null;
		for (Race eachRace:systemRaces){
      if (eachRace.getRaceName()==name){
        nameFound=true;
        raceFound = eachRace;
        break;
      }
    }
    if (nameFound){
      systemRaces.remove(raceFound);
    }else{
      throw new NameNotRecognisedException(String.format("Stage %s is not recognised",name));
    }

	}

  @Override
	public LocalTime[] getGeneralClassificationTimesInRace(int raceId) throws IDNotRecognisedException {
    Race raceSelected=this.checkRaceIdExists(raceId);
		return raceSelected.getGCTimes();
	}

  @Override
	public int[] getRidersPointsInRace(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.getPoints("sprint");
	}

	@Override
	public int[] getRidersMountainPointsInRace(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.getPoints("mountain");
	}

	@Override
	public int[] getRidersGeneralClassificationRank(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
		return raceSelected.getGCRanks();
	}

	@Override
	public int[] getRidersPointClassificationRank(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.getPointRanks("sprint");
	}

	@Override
	public int[] getRidersMountainPointClassificationRank(int raceId) throws IDNotRecognisedException {
		Race raceSelected=this.checkRaceIdExists(raceId);
    return raceSelected.getPointRanks("mountain");
	}





}//rider name exception change?!
