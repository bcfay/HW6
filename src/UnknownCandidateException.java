public class UnknownCandidateException  extends Exception {
  private String name;
  public UnknownCandidateException(String name) {
    super("Candidate is not on ballot");
    this.name=name;
  }

  public String getaName() {
    return name;
  }
}
