package cycling;
import java.io.Serializable;

public class Rider implements Serializable{
  private int id;
  private int teamID;
  private int yearOfBirth;
  private String riderName;

  public int getID(){
    /**
	 * The method returns an int that represents the rider's ID
	 * <p>


	 * @return id the rider's ID
	 */
    return id;
  }

  public int getTeamID(){
     /**
	 * The method returns an int that represents the rider's team's ID
	 * <p>


	 * @return id the rider's team's ID
	 */
    return teamID;
  }

  public int getYearOfBirth(){
     /**
	 * The method returns an int that represents the rider's year of birth
	 * <p>


	 * @return yearOfBirth the rider's year of birth
	 */
    return yearOfBirth;
  }

  public String getRiderName(){
     /**
	 * The method returns a string that represents the rider's name
	 * <p>


	 * @return riderName the rider's name
	 */
    return riderName;
  }

  public void setTeamID(int teamID){
    /**
	 * The method changes the rider's team
	 * <p>


	 * @param teamID the ID of the new team for the rider
	 */
    this.teamID=teamID;
  }

  public void setRiderName(String name){
    /**
	 * The method changes the rider's name
	 * <p>


	 * @param name the rider's name
	 */
    this.riderName=name;
  }

  public void setYearOfBirth(int yearOfBirth){
    /**
	 * The method changes the rider's year of birth
	 * <p>


	 * @param yearOfBirth the rider's year of birth
	 */
    this.yearOfBirth=yearOfBirth;
  }

  public String toString(){
    /**
	 * The method returns a string that contains information about the rider's ID, name, teamID and year of birth
	 * <p>


	 * @return riderString a string containing the riderID, riderName, yearOfBirth and teamID
	 */
    String riderString = String.format("Rider[riderID:%d,riderName:%s,yearOfBirth:%s,teamID:%d]", id, riderName, yearOfBirth, teamID);
    return riderString;
  }

  public Rider(int teamID, String name, int yearOfBirth, int numberOfRiders){
    /**
	 * The method creates a new Rider object when given a teamID, yearOfBirth, name and the number of riders currently created in the portal (which is used to set up their riderID)
	 * <p>

   * @param teamID the rider's team's ID
   * @param name the rider's name
   * @param numberOfRiders the amount of riders in the portal
	 * @param yearOfBirth the rider's year of birth
	 */
    this.teamID=teamID;
    this.riderName=name;
    this.yearOfBirth=yearOfBirth;
    id=++numberOfRiders;
  }
}
