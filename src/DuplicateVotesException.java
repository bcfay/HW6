public class DuplicateVotesException  extends Exception {
  private String name;
  public DuplicateVotesException(String name) {
    super("Candidate can not be voted for twice");
    this.name=name;
  }

  public String getName() {
    return name;
  }
}
