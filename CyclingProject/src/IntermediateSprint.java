package cycling;
import java.io.Serializable;

public class IntermediateSprint extends Segment{

  // maybe don't take segment type and set as static

  public IntermediateSprint(double kmLocation, int stageID, int nOfSegments){
    /**
	 * The method is used to create a intermediateSprint segment object with a  stageID and kmLocation. numberOfSegments is used to calculate the segmentID
	 * <p>


   * @param stageID the ID of the stage that the segment exists within
   * @param numberOfSegments the number of segments that have been created within the cycing portal
	 * @param kmLocation the location of the segment within a stage
	 */
    super(kmLocation, SegmentType.SPRINT, stageID, nOfSegments);
  }


}
