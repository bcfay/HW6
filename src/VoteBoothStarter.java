import java.util.*;


class ElectionData {

  //Fields\\\\\\\\\\\\\\
  private LinkedList<String> ballot = new LinkedList<String>();
  private Hashtable<Integer, LinkedList<String>> hashVotes;



  /**
   *Constructor that takes a linked list for a balot and a hashtable for hashVotes
   * @param ballot this.ballot
   * @param hashVotes this.hashVotes
   */
  public ElectionData(LinkedList<String> ballot, Hashtable<Integer, LinkedList<String>> hashVotes) {
    this.ballot = ballot;
    this.hashVotes = hashVotes;
  }



  /**
   *Constructor that takes a linked list for a balot
   * @param ballot
   */
  public ElectionData(LinkedList<String> ballot) {
    this.ballot = ballot;
    this.hashVotes = new Hashtable<Integer, LinkedList<String>>();
  }


  /**
   * Constructor that takes no parameters
   */
  public ElectionData() {
    LinkedList<String> ballot = new LinkedList<String>();
    this.hashVotes = new Hashtable<Integer, LinkedList<String>>();
    hashVotes.put(1,new LinkedList<String>());
    hashVotes.put(2,new LinkedList<String>());
    hashVotes.put(3,new LinkedList<String>());
  }

  /**
   *
   */
  public void printBallot() {
    System.out.println("The candidates are ");
    for (String s : ballot) {
      System.out.println(s);
    }
  }


  /**
   *Takes the strings for the names of the candidates being voted for and adds the votes to voteHash
   * @param candidate1 the candidate that receives the first vote
   * @param candidate2 the candidate that receives the second vote
   * @param candidate3 the candidate that receives the third vote
   * @throws UnknownCandidateException when a canidate provided is not present on the ballot
   * @throws DuplicateVotesException when two or more of the votes are identical Strings
   */
  public void processVote(String candidate1,String candidate2,String candidate3) throws UnknownCandidateException, DuplicateVotesException {

    //UnknownCandidateException checking
    if(!this.ballot.contains(candidate1)){
      throw new UnknownCandidateException(candidate1);
    }else if(!this.ballot.contains(candidate2)){
      throw new UnknownCandidateException(candidate2);
    }else if(!this.ballot.contains(candidate3)){
      throw new UnknownCandidateException(candidate3);
    }
    //DuplicateVotesException checking
    if(candidate1.equals(candidate2)){
      throw new DuplicateVotesException(candidate1);
    }else if(candidate1.equals(candidate3)){
      throw new DuplicateVotesException(candidate1);
    }else if(candidate3.equals(candidate2)){
      throw new DuplicateVotesException(candidate3);
    }


    LinkedList<String> firstVotes = hashVotes.get(1);
    firstVotes.add(candidate1);
    hashVotes.put(1, firstVotes);

    LinkedList<String> secondVotes = hashVotes.get(2);
    secondVotes.add(candidate2);
    hashVotes.put(2, secondVotes);
    LinkedList<String> thirdVotes = hashVotes.get(3);
    thirdVotes.add(candidate3);
    hashVotes.put(3, thirdVotes);
  }



  /**
   *Setup hashmap to know about new candidate here
   * @param candidateName canidate hashmap is setup to know about
   * @throws CandidateExistsException indicates canidateName is already on ballot
   */
  public void addCandidate(String candidateName) throws CandidateExistsException {
    if(ballot.contains(candidateName)){
      throw new CandidateExistsException(candidateName);
    }
    this.ballot.add(candidateName);
  }



  /**
   * Returns the candidate with 50% of the votes, or "Runoff requiered." if no such candidate exists.
   * @return the candidate with 50% of the votes, or "Runoff requiered."
   */
  public String findWinnerMostFirstVotes(){

    //creates votesPerCandidate to store number of votes per candidate
    int[] votesPerCandidate;
    votesPerCandidate = new int[this.ballot.size()];

    //iterates though first votes
    for(String firstVote: hashVotes.get(1)){
      //compares to ballot to add vote to correct index
      for(int i = 0; i < this.ballot.size(); i++){
        if(firstVote.equals(this.ballot.get(i))){
          //increments position in votesPerCandidate corresponding to matching candidate by 3
          int oldVotesNum = votesPerCandidate[i];
          votesPerCandidate[i] = (oldVotesNum +1);
        }
      }
    }

    //Total first votes
    int totalVotes = hashVotes.get(1).size();

    for(int i = 0; i<this.ballot.size();i++){
      if(((double)votesPerCandidate[i])/((double)totalVotes)>.5){
        return this.ballot.get(i);
      }
    }
    return "Runoff Required";
  }



  /**
   * finds and returns the canidate with the most points.
   * 3 points awarded for 1st votes
   * 2 points awarded for 2nd votes
   * 1 points awarded for 3rd votes
   * @return the canidate with the most points.
   */
  public String findWinnerMostPoints(){

    //creates votesPerCandidate to store number of votes per candidate
    int[] votesPerCandidate;
    votesPerCandidate = new int[this.ballot.size()];

    //iterates though first votes
    for(int j = 1; j<4;j++) {
      for (String firstVote : hashVotes.get(j)) {
        //compares to ballot to add vote to correct index
        for (int i = 0; i < this.ballot.size(); i++) {
          if (firstVote.equals(this.ballot.get(i))) {
            //increments position in votesPerCandidate corresponding to matching candidate by 3
            int oldVotesNum = votesPerCandidate[i];
            votesPerCandidate[i] = (oldVotesNum + 3);
          }
        }
      }
    }




    //find index of most votes
    int indexOfMax=0;
    int maxVotes=0;
    for(int i = 0; i < this.ballot.size(); i++){
      if(votesPerCandidate[i]>maxVotes){
        maxVotes=votesPerCandidate[i];
        indexOfMax = i;
      }
    }
    return this.ballot.get(indexOfMax);
  }

  /**
   *returns the ballot of current runners
   * @return ballot
   */
  public LinkedList<String> getBallot() {
    return ballot;
  }
}

