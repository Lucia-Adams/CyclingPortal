package cycling;
import java.io.Serializable;
import java.util.Arrays;
//import cycling.SegmentType;

public class Segment implements Serializable{

  private int id;
  private double kmLocation;
  private SegmentType type;
  private int stageID;

  public int getID(){
    /**
	 * The method returns the segment's ID
	 * <p>


	 * @return id the segment ID
	 */
    return id;
  }

  public double getKmLocation(){
    /**
	 * The method returns the segment's KmLocation
	 * <p>


	 * @return kmLocation how far into the stage (in kms) the segment lays
	 */
    return kmLocation;
  }

  public SegmentType getType(){
    /**
	 * The method returns the segment's type
	 * <p>


	 * @return type the segment type as one of the SegmentTyoe enums
	 */
    return type;
  }

  public int getStageID(){
    /**
	 * The method returns the ID of the stage the segment belongs to
	 * <p>


	 * @return stageID the stageID for the segment
	 */
    return stageID;
  }

  public void setKmLocation(double kmLocation){
    /**
	 * The method changes the segment's KmLocation
	 * <p>


	 * @param kmLocation how far into the stage (in kms) the segment lays
	 */
    this.kmLocation = kmLocation;
  }

  public void setSegmentType(SegmentType type){
    /**
	 * The method changes the segment's type
	 * <p>


	 * @param type the segment type as one of the SegmentTyoe enums
	 */
    this.type = type;
  }

  public void setStageID(int stageID){
    /**
	 * The method returns the ID of the stage the segment belongs to
	 * <p>


	 * @param stageID the stageID for the segment
	 */
    this.stageID = stageID;
  }

  public int[] getPoints(int amountOfRiders){
    /**
	 * The method creates an integer array that determines how many points each rider gets for completion of the segment. The length of the array is determined by amountOfRiders, a parameter that says how many riders were competing in the stage. An integer array is created that awards varying points to a varying number of riders depending on the segment type. The rank of riders in the segment will be used to match the index of the points and the index of the riderIDs to award the points to.
	 * <p>


   * @param amountOfRiders the amount of riders in the segment
	 * @return riderPoints an array of points to award to the riders
	 */
    int[] riderPoints = new int[amountOfRiders];
    switch(type){
    case SPRINT:
      //checks if there are less riders than different point values to give to them. Needed to avoid index errors
      if (amountOfRiders<16){

            int[] pointsArray={20,17,15,13,11,10,9,8,7,6,5,4,3,2,1};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders - 1);
            // might go weird

      }else{
        //goes through and assign points to riders in order of rank
      riderPoints[0]=20;
      riderPoints[1]=17;
      riderPoints[2]=15;
      riderPoints[3]=13;
      riderPoints[4]=11;
      riderPoints[5]=10;
      riderPoints[6]=9;
      riderPoints[7]=8;
      riderPoints[8]=7;
      riderPoints[9]=6;
      riderPoints[10]=5;
      riderPoints[11]=4;
      riderPoints[12]=3;
      riderPoints[13]=2;
      riderPoints[14]=1;
      //goes through and gives every other rider in the list 0 points
      for (int loop = 15; loop< amountOfRiders; ++loop) {
        riderPoints[loop]=0;
        }
        }
      break;


    case C4:

      if (amountOfRiders<2){
            int[] pointsArray={1};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders - 1);
        }else{
      riderPoints[0]=1;
    for (int loop = 1; loop< amountOfRiders; ++loop) {
        riderPoints[loop]=0;
        }
        }
    break;

    case C3:
      if (amountOfRiders<3){

            int[] pointsArray={2,1};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders - 1);  }else{
      riderPoints[0]=2;
      riderPoints[1]=1;
    for (int loop = 2; loop<amountOfRiders; ++loop) {
        riderPoints[loop]=0;
        }
        }
      break;

    case C2:
      if (amountOfRiders<5){

            int[] pointsArray={5,3,2,1};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders );  }else{
      riderPoints[0]=5;
      riderPoints[1]=3;
      riderPoints[2]=2;
      riderPoints[3]=1;
    for (int loop = 4; loop< amountOfRiders; ++loop) {
        riderPoints[loop]=0;
        }
    }
    break;

    case C1:
      if (amountOfRiders<7){

            int[] pointsArray={10,8,6,4,2,1};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders);   }else{
      riderPoints[0]=10;
      riderPoints[1]=8;
      riderPoints[2]=6;
      riderPoints[3]=4;
      riderPoints[4]=2;
      riderPoints[5]=1;
    for (int loop = 6; loop< amountOfRiders; ++loop) { //check the rules for ++x vs x++
        riderPoints[loop]=0;
        }
    }
    break;

    case HC:
      if (amountOfRiders<9){

            int[] pointsArray={20,15,12,10,8,6,4,2};
            riderPoints=Arrays.copyOfRange(pointsArray, 0, amountOfRiders);
        }else{
      riderPoints[0]=20;
      riderPoints[1]=15;
      riderPoints[2]=12;
      riderPoints[3]=10;
      riderPoints[4]=8;
      riderPoints[5]=6;
      riderPoints[6]=4;
      riderPoints[7]=2;

    for (int loop = 8; loop< amountOfRiders; ++loop) {
        riderPoints[loop]=0;
        }
    }
  }
    return riderPoints;
  }

  public String toString(){
    /**
	 * The method returns a string containing information about the segment object
	 * <p>



	 * @return segmentString a string containing the segmentID, kmLocation, segmentType and stageID
	 */
    String segmentString = String.format("Segment[SegmentID:%d,kmLocation:%f,segmentType:%s,stageID:%d]", id, kmLocation, type.name() ,stageID);
    return segmentString;
    }

  public Segment(double kmLocation, SegmentType type, int stageID, int numberOfSegments){
     /**
	 * The method is used to create a segment object with a type, stageID and kmLocation. numberOfSegments is used to calculate the segmentID
	 * <p>

   * @param stageID the ID of the stage that the segment exists within
   * @param numberOfSegments the number of segments that have been created within the cycing portal
   * @param type the type of the segment (all possible types are in SegmentType)
	 * @param kmLocation the location of the segment within a stage
	 */
    this.kmLocation = kmLocation;
    this.type = type;
    this.stageID = stageID;
    id = ++numberOfSegments;
  }

}
