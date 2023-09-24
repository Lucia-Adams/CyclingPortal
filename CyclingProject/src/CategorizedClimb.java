package cycling;
import java.io.Serializable;

public class CategorizedClimb extends Segment{

  private double climbLength;
  private double gradient;

  @Override
  public String toString(){
    /**
	 * The method returns a string containing information about the segment object
	 * <p>
   *
	 * @return segmentString a string containing the segmentID, kmLocation,     segmentType, climbLength, gradient and stageID
	 */
    String segmentString = String.format("Segment[SegmentID:%d,kmLocation:%f,segmentType:%s,stageID:%d,climbLength:%f,gradient:%f]", this.getID(), this.getKmLocation(), this.getType().name() ,this.getStageID(), climbLength, gradient);
    return segmentString;
  }

  public CategorizedClimb(double kmLocation, SegmentType type, double climbLength, double gradient, int stageID, int nOfSegments){
    /**
	 * The method is used to create a categorizedClimb segment object with a type, climblength, gradient, stageID and kmLocation. numberOfSegments is used to calculate the segmentID
	 * <p>
   *
   * @param gradient the average gradient of the climb
   * @param climbLength the length in km of the climb
   * @param stageID the ID of the stage that the segment exists within
   * @param numberOfSegments the number of segments that have been created within the cycing portal
   * @param type the type of the segment (all possible types are in SegmentType)
	 * @param kmLocation the location of the segment within a stage
	 */
    super(kmLocation, type, stageID, nOfSegments);
    this.climbLength = climbLength;
    this.gradient = gradient;
  }


}
