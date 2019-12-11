import org.junit.Test;

import java.util.Hashtable;
import java.util.LinkedList;
import java.io.*;
import static org.junit.Assert.*;

public class Examples {


    public Examples() {

    }
    // method to set up a ballot and cast votes

    ElectionData Setup1() {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {
        }

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");
            ED.processVote("gompei", "husky", "ziggy");

        } catch (Exception e) {
        }

        return (ED);

    }

    // now run a test on a specific election
    @Test
    public void testMostFirstWinner1() {
        assertEquals("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostPoints() {
        assertEquals("gompei", Setup1().findWinnerMostPoints());
    }

    /**
     * Extra Tests for findWinnerMostPoints
     */
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
        try {
            ED.processVote("Steve", "Beef", "Trump");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        assertEquals("Runoff Required", winner1);
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

        assertEquals("Beef", winner1);
    }


/**
 * Testing Exceptions
 */
@Test
public void test3() {
    Hashtable<Integer, LinkedList<String>> hashVotes;
    ElectionData ED = new ElectionData();

    try {
        ED.addCandidate("Beef");
        ED.addCandidate("Steve");
        ED.addCandidate("Trump");
    } catch (CandidateExistsException e) {
    }
    try {
        ED.processVote("Gompei","Beef", "Steve");
    } catch (UnknownCandidateException e) {
    } catch (DuplicateVotesException e) {
    }


    String winner1 = ED.findWinnerMostFirstVotes();
    String winner2 = ED.findWinnerMostPoints();

    assertEquals("Candidate is not on ballot", winner1 );
}
    @Test
    public void test4() {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }
        try {
            ED.processVote("Husky","Beef", "Steve");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }


        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        assertEquals("Candidate is not on ballot", winner1 );
    }
    @Test
    public void test5() {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }
        try {
            ED.processVote("Beef","Beef", "Steve");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }


        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        assertEquals("Candidate can not be voted for twice", winner1 );
    }
    @Test
    public void test6() {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }
        try {
            ED.processVote("Beef","Steve", "Steve");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }


        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();

        assertEquals("Candidate can not be voted for twice", ED.processVote("Beef","Steve","Steve"));
    }
}
