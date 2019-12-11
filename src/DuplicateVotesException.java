public class DuplicateVotesException  extends Exception {
  private String name;

    /**
     * This is the exception for when a certain candidate is entered twice into a vote
     * @param name
     */
  public DuplicateVotesException(String name) {
    super("Candidate can not be voted for twice");
    this.name=name;
  }

  public String getName() {
    return name;
  }
}
