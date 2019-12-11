import org.junit.Test;

import java.util.Hashtable;
import java.util.LinkedList;
import java.io.*;
import static org.junit.Assert.*;

public class Examples {

    Hashtable<Integer, LinkedList<String>> hashVotes;
    ElectionData ED = new ElectionData();

    public Examples() {

    }
    // method to set up a ballot and cast votes

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }

    // now run a test on a specific election
    @Test
    public void testMostFirstWinner () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void test1() {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

            try {
                ED.addCandidate("Beef");
                ED.addCandidate("Steve");
                ED.addCandidate("Trump");
            } catch (CandidateExistsException e) {
            }
            try {
                ED.processVote("Beef", "Steve", "Trump");
            } catch (UnknownCandidateException e) {
            } catch (DuplicateVotesException e) {
            }

            String winner1 = ED.findWinnerMostFirstVotes();
            String winner2 = ED.findWinnerMostPoints();

            assertEquals(winner1, "Runoff Required");
        }
    @Test
    public void test2() {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }
        try {
            ED.processVote("Beef", "Steve", "Trump");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        assertEquals(winner1, "Runoff Required");
    }
    }

