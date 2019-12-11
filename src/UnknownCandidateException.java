public class UnknownCandidateException  extends Exception {
  private String name;

    /**
     * \
     * This is the exception for when there is a candidate present that is not on the ballot
     * @param name
     */
  public UnknownCandidateException(String name) {
    super("Candidate is not on ballot");
    this.name=name;
  }

  public String getaName() {
    return name;
  }
}
