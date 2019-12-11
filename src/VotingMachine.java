import java.util.Scanner;

public class VotingMachine {
    ElectionData votingInformation;
    Scanner keyboard ;

    public VotingMachine() {
        this.votingInformation = new ElectionData();
        this.keyboard = new Scanner(System.in);
    }


    public void screen(){
        String state = "Start Menu";
        while(true) {

            while(state=="Start Menu") {
                this.printBallot();
                System.out.println("What would you like to do? (Enter 1-3)");
                System.out.println("1. Cast Votes.");
                System.out.println("2. Add candidate.");
                System.out.println("3. Count votes.");
                System.out.print(":");

                String input = keyboard.next();

                if(input=="1"){
                    state = "Cast Votes";
                }else if(input=="2"){
                    state = "Add candidate";
                }else if(input=="3"){
                    state = "Count votes";
                }else if(input=="1"){

                }
            }
            while(state=="Add candidate"){
                System.out.println("Who do you want to add to the ballot?");
                System.out.print(":");

                String candidate = keyboard.next();
            }

            while (state=="Cast Votes") {
                System.out.println("Who do you want to vote for first?");
                String candidate1 = keyboard.next();
                System.out.println("Who do you want to vote for second?");
                String candidate2 = keyboard.next();
                System.out.println("Who do you want to vote for third?");
                String candidate3 = keyboard.next();
                System.out.println("You voted for " + candidate1 + ", " + candidate2 + ", and " + candidate3 + ".");
                try {
                    votingInformation.processVote(candidate1, candidate2, candidate3);
                } catch (DuplicateVotesException e) {
                    System.out.print("You have voted for " + e.getName() + " twice.");
                    System.out.print("You cannot vote for the same canidate twice");


                } catch (UnknownCandidateException e) {
                    System.out.println(e.getaName() + "is not on ballot.");
                    System.out.print("Would like to add the candidate's name to the ballot? y/n:");
                    String response = keyboard.next();
                    if (response.equals("y") || response.equals("Y")) {
                        try {
                            addWriteIn(e.getaName());
                        } catch (CandidateExistsException f) {
                            System.out.print("The candidate already exists.");
                        }
                    }
                }
            }

        }


    }

    private void addWriteIn(String getaName) throws CandidateExistsException {
        votingInformation.addCandidate(getaName);
    }

    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : votingInformation.getBallot()) {
            System.out.println(s);
        }
    }


}
