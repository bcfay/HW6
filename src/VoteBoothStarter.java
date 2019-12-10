import java.util.*;


class ElectionData {
  LinkedList<String> ballot = new LinkedList<String>();
  LinkedList<String> votes = new LinkedList<String>();
  Scanner keyboard = new Scanner(System.in);
  Hashtable<Integer, LinkedList<String>> hashVotes;




  public ElectionData() {
    this.ballot.add("Gompei");
    this.ballot.add("Husky");
  }

  public ElectionData(LinkedList<String> ballot, LinkedList<String> votes, Scanner keyboard) {
    this.ballot = ballot;
    this.votes = votes;
    this.keyboard = keyboard;
    this.hashVotes = new Hashtable<Integer, LinkedList<String>>();
  }

  public ElectionData(LinkedList<String> ballot, LinkedList<String> votes, Scanner keyboard, Hashtable<Integer, LinkedList<String>> hashVotes) {
    this.ballot = ballot;
//    this.votes = votes;
    this.keyboard = keyboard;
    this.hashVotes = hashVotes;
  }

  public void printBallot() {
    System.out.println("The candidates are ");
    for (String s : ballot) {
      System.out.println(s);
    }
  }

  public void screen() {
    this.printBallot();
    System.out.println("Who do you want to vote for?");
    String candidate = keyboard.next();
    votes.add(candidate);
    System.out.println("You voted for " + candidate);
  }

  public int countVotes(String forcand) {
    int numvotes = 0;
    for (String s : votes) {
      if (s.equals(forcand))
        numvotes = numvotes+1;
    }
    return numvotes;
  }

  /**
   * stores votes in hash map
   * @param candidate1
   * @param candidate2
   * @param candidate3
   */
  public void processVote(String candidate1,String candidate2,String candidate3) throws UnknownCandidateException, DuplicateVotesException {
    //UnknownCandidateException checking
    if(!this.ballot.contains(candidate1)){
      throw new UnknownCandidateException(candidate1);
    }else if(!this.ballot.contains(candidate1)){
      throw new UnknownCandidateException(candidate2);
    }else if(!this.ballot.contains(candidate1)){
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

      /*
      Throw CandidateExistsException if candidate already on ballot
          constructor takes the name as its only argument
      Setup hashmap to know about new candidate here

       */
  }

  public String findWinnerMostFirstVotes(){

    //creates votesPerCandidate to store number of votes per candidate
    int votesPerCandidate[];
    votesPerCandidate = new int[this.ballot.size()];

    //iterates though first votes
    for(String firstVote: hashVotes.get(1)){
      //compares to ballot to add vote to correct index
      for(int i = 0; i < this.ballot.size(); i++){
        if(firstVote.equals(this.ballot.get(i))){
          //increments position in votesPerCandidate corresponding to matching candidate by 3
          int oldVotesNum = votesPerCandidate[i];
          votesPerCandidate[i] = oldVotesNum++;
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
    return "Runoff required";
  }


  public String findWinnerMostPoints(){

    //creates votesPerCandidate to store number of votes per candidate
    int votesPerCandidate[];
    votesPerCandidate = new int[this.ballot.size()];

    //iterates though first votes
    for(String firstVote: hashVotes.get(1)){
      //compares to ballot to add vote to correct index
      for(int i = 0; i < this.ballot.size(); i++){
        if(firstVote.equals(this.ballot.get(i))){
          //increments position in votesPerCandidate corresponding to matching candidate by 3
          int oldVotesNum = votesPerCandidate[i];
          votesPerCandidate[i] = oldVotesNum += 3;
        }
      }
    }

    //iterates though second votes
    for(String secondVote: hashVotes.get(2)){
      //compares to ballot to add vote to correct index
      for(int i = 0; i < this.ballot.size(); i++){
        if(secondVote.equals(this.ballot.get(i))){
          //increments position in votesPerCandidate corresponding to matching candidate by 2
          int oldVotesNum = votesPerCandidate[i];
          votesPerCandidate[i] = oldVotesNum += 2;
        }
      }
    }

    //iterates though third votes
    for(String thirdVote: hashVotes.get(3)){
      //compares to ballot to add vote to correct index
      for(int i = 0; i < this.ballot.size(); i++){
        if(thirdVote.equals(this.ballot.get(i))){
          //increments position in votesPerCandidate corresponding to matching candidate by 1
          int oldVotesNum = votesPerCandidate[i];
          votesPerCandidate[i] = oldVotesNum += 1;
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

}



