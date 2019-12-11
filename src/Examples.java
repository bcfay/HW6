import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
    public void testMostFirstWinner2() {
        assertFalse("husky".equals(Setup1().findWinnerMostFirstVotes()));
    }
    @Test
    public void testMostFirstWinner3() {
        assertFalse("ziggy".equals(Setup1().findWinnerMostFirstVotes()));
    }

    @Test
    public void testMostPoints1() {
        assertEquals("gompei", Setup1().findWinnerMostPoints());
    }
    @Test
    public void testMostPoints2() {
        assertFalse("husky".equals(Setup1().findWinnerMostPoints()));
    }
    @Test
    public void testMostPoints3() {
        assertFalse("ziggy".equals(Setup1().findWinnerMostPoints()));
    }

    /**
     * Extra Tests for findWinnerMostPoints
     */
    @Test
    public void Generaltest1() {
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
    public void Generaltest2() {
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
     * Testing Unknown Candidate Exceptions
     */
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test(expected = UnknownCandidateException.class)
    public void UnknownCandtest1() throws DuplicateVotesException, UnknownCandidateException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData A = new ElectionData();

        try {
            A.addCandidate("Beef");
            A.addCandidate("Steve");
            A.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }
        A.processVote("Husky", "Beef", "Steve");

        String winner1 = A.findWinnerMostFirstVotes();
        String winner2 = A.findWinnerMostPoints();

    }

    @Test(expected = UnknownCandidateException.class)
    public void UnknownCandtest2() throws DuplicateVotesException, UnknownCandidateException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }

        ED.processVote("Bernie", "Beef", "Steve");

        thrown.expect(UnknownCandidateException.class);

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();


    }
    /**
     * Testing the Duplicate Votes Exception
     * @throws UnknownCandidateException
     * @throws DuplicateVotesException
     */
    @Test(expected = DuplicateVotesException.class)
    public void DuplicateVoteTest1() throws DuplicateVotesException, UnknownCandidateException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }

        ED.processVote("Beef", "Beef", "Steve");
        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();


    }


    @Test(expected = DuplicateVotesException.class)
    public void DuplicateVoteTest2() throws UnknownCandidateException, DuplicateVotesException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }

        ED.processVote("Beef", "Steve", "Steve");

        thrown.expect(DuplicateVotesException.class);

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();


    }

    @Test(expected = DuplicateVotesException.class)
    public void DuplicateVoteTest3() throws UnknownCandidateException, DuplicateVotesException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();

        try {
            ED.addCandidate("Beef");
            ED.addCandidate("Steve");
            ED.addCandidate("Trump");
        } catch (CandidateExistsException e) {
        }

        ED.processVote("Steve", "Steve", "Steve");

        thrown.expect(DuplicateVotesException.class);

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();


    }

    /**
     * Testing the Candidate Exists Exception
     * @throws CandidateExistsException
     */
    @Test(expected = CandidateExistsException.class)
    public void CandExistsTest1() throws CandidateExistsException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();


            ED.addCandidate("Beef");
            ED.addCandidate("Beef");
            ED.addCandidate("Trump");


            try {
                ED.processVote("Beef", "Beef", "Steve");
            } catch (UnknownCandidateException e) {
            } catch (DuplicateVotesException e) {
            }

            thrown.expect(DuplicateVotesException.class);

            String winner1 = ED.findWinnerMostFirstVotes();
            String winner2 = ED.findWinnerMostPoints();



    }
    @Test(expected = CandidateExistsException.class)
    public void CandExistsTest2() throws CandidateExistsException {
        Hashtable<Integer, LinkedList<String>> hashVotes;
        ElectionData ED = new ElectionData();


        ED.addCandidate("Beef");
        ED.addCandidate("Beef");
        ED.addCandidate("Beef");


        try {
            ED.processVote("Beef", "Beef", "Steve");
        } catch (UnknownCandidateException e) {
        } catch (DuplicateVotesException e) {
        }

        thrown.expect(DuplicateVotesException.class);

        String winner1 = ED.findWinnerMostFirstVotes();
        String winner2 = ED.findWinnerMostPoints();



    }
}