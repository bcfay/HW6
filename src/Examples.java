import java.util.Hashtable;
import java.util.LinkedList;

public class Examples {

    Hashtable<Integer, LinkedList<String>> hashVotes;
    ElectionData ED = new ElectionData();
    public String Examples(){
        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {}
        try {
            ED.processVote("Beef","Steve","Trump");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {}

        String winner1 = ED.findWinnerMostFirstVotes();
        return winner1;
    }
}
