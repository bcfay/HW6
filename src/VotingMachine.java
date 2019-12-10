import java.util.Scanner;

public class VotingMachine {
    ElectionData votingInformation;
    Scanner keyboard = new Scanner(System.in);


    public void screen(){
        this.printBallot();
        System.out.println("Who do you want to vote for first?");
        String candidate1 = keyboard.next();
        System.out.println("Who do you want to vote for second?");
        String candidate2 = keyboard.next();
        System.out.println("Who do you want to vote for third?");
        String candidate3 = keyboard.next();
        System.out.println("You voted for " + candidate1 + ", " + candidate2 + ", and " + candidate3 + ".");
        try{
            votingInformation.processVote(candidate1, candidate2, candidate3);
        }catch (DuplicateVotesException e){
            System.out.print("You have voted for " + e.getName() + " twice.");
            System.out.print("You cannot vote for the same canidate twice");



        }catch (UnknownCandidateException e){
            System.out.println( e.getaName() + "is not on ballot.");
            System.out.print("Would like to add the candidate's name to the ballot? y/n:");
            String response = keyboard.next();
            if(response.equals("y")||response.equals("Y")){
                try{
                    addWriteIn(e.getaName());
                }catch (CandidateExistsException f){
                    System.out.print("The candidate already exists.");
                    return;
                }
            }


        }

    }

    private void addWriteIn(String getaName) throws CandidateExistsException {
        votingInformation.addCandidate(getaName);
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }


}
