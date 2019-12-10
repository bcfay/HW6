import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

class ElectionData {
  LinkedList<String> ballot = new LinkedList<String>();
  LinkedList<String> votes = new LinkedList<String>();
  Scanner keyboard = new Scanner(System.in);
  HashMap<String, Integer> hashVotes;




  ElectionData() {
    this.ballot.add("Gompei");
    this.ballot.add("Husky");
  }

  public ElectionData(LinkedList<String> ballot, LinkedList<String> votes, Scanner keyboard) {
    this.ballot = ballot;
    this.votes = votes;
    this.keyboard = keyboard;
    this.hashVotes = new HashMap<String, Integer>();
  }

  public ElectionData(LinkedList<String> ballot, LinkedList<String> votes, Scanner keyboard, HashMap<String, Integer> hashVotes) {
    this.ballot = ballot;
    this.votes = votes;
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
    public void processVote(String candidate1,String candidate2,String candidate3){
     //store votes in hashmap here

      hashVotes.put(candidate1,(hashVotes.get(candidate1)+1));
      hashVotes.put(candidate2,(hashVotes.get(candidate2)+1));
      hashVotes.put(candidate3,(hashVotes.get(candidate3)+1));

    }

  /**
   *Setup hashmap to know about new candidate here
   * @param candidateName canidate hashmap is setup to know about
   */
  public void addCandidate(String candidateName){

      hashVotes.put(candidateName, 1);

      /*
      Throw CandidateExistsException if candidate already on ballot
          constructor takes the name as its only argument
      Setup hashmap to know about new candidate here

       */
    }

  }
