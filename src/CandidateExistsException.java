public class CandidateExistsException   extends Exception {
    private String name;
    public CandidateExistsException(String name) {
        super("Candidate is already on ballot");
        this.name=name;
    }

    public String getaName() {
        return name;
    }
}
