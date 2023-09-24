package cycling;
import java.util.ArrayList;
import java.io.Serializable;

public class Team implements Serializable{

  private int id;
  private String teamName;
  private String teamDescription;
  private ArrayList<Rider> teamRiders = new ArrayList<>();

  public int getID(){
    /**
	 * The method returns an int that details the ID of the team
	 * <p>



	 * @return id the unique ID of the team
	 */
    return id;
  }

  public String getTeamName(){
    /**
	 * The method returns a string that details the name of the team
	 * <p>



	 * @return name the name of the team
	 */
    return teamName;
  }

  public String getTeamDescription(){
    /**
	 * The method returns a string that details the description of the team
	 * <p>



	 * @return teamDescription the description of the team
	 */
    return teamDescription;
  }

  public ArrayList<Rider> getTeamRiders(){
    /**
	 * The method returns an array that contains all the rider objects in a team
	 * <p>



	 * @return teamRiders an array of rider objects
	 */
    return teamRiders;
  }

  public void setTeamRiders(ArrayList<Rider> teamRiders){
    /**
	 * The method changes an array that contains all the rider objects in a team
	 * <p>



	 * @param teamRiders an array of rider objects
	 */
    this.teamRiders=teamRiders;
  }

  public void setTeamName(String teamName){
    /**
	 * The method changes the team's name
	 * <p>



	 * @param teamName the new team name
	 */
    this.teamName=teamName;
  }


  public void setTeamDescription(String teamDescription){
    /**
	 * The method changes the team's description
	 * <p>



	 * @param teamDescription the new team description
	 */
    this.teamDescription=teamDescription;
  }

  public void addTeamRider(Rider teamRider){
    /**
	 * The method adds a rider to the team's array of riders
	 * <p>



	 * @param teamRider a rider object to add to the team's list of riders
	 */
    teamRiders.add(teamRider);
  }

  public void removeTeamRider(Rider riderToRemove){
    /**
	 * The method removes a rider from the team's array of riders
	 * <p>



	 * @param teamRider a rider object to remove from the team's list of riders
	 */
    boolean validRemove = false;
    for (Rider riderInList : teamRiders){
      if (riderInList.equals(riderToRemove)){
        validRemove = true;
        break;
      }
    }
    if (validRemove){
    teamRiders.remove(riderToRemove);
  }
  }

  public String toString(){
    /**
	 * The method returns a string containing information about the team object
	 * <p>



	 * @return teamString a string containing the teamID, teamName, teamDescription and teamRiders
	 */

    String teamRidersInfo="";
		for (Rider eachRider : teamRiders) {
          String eachRiderString = eachRider.toString();
          teamRidersInfo += "{" + eachRiderString + "}";
       		}
    String teamString = String.format("Team[teamID:%d,teamName:%s,teamDescription:%s,riders:%s]", id, teamName, teamDescription,teamRidersInfo);
    return teamString;
  }

  public Team(String name,String description, int numberOfTeams){
    /**
	 * The method is used to create a team object with a name and description. numberOfSegments is used to calculate the teamID
	 * <p>


   * @param numberOfTeamss the number of teams that have been created within the cycing portal
   * @param name the name for the team
	 * @param description the description of the team
	 */
    this.teamName=name;
    this.teamDescription=description;
    id=++numberOfTeams;
  }

}
