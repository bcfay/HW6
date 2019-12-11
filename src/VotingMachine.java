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
        boolean done = false;
        while(!state.equals("Done")) {

            while(state.equals("Start Menu")) {
                this.printBallot();
                System.out.println("What would you like to do? (Enter 1-3)");
                System.out.println("Q. Quit");
                System.out.println("1. Cast Votes.");
                System.out.println("2. Add candidate.");
                System.out.println("3. Count votes.");
                System.out.print(":");

                String input = keyboard.next();

                if(input.equals("1")){
                    state = "Cast Votes";
                }else if(input.equals("2")){
                    state = "Add candidate";
                }else if(input.equals("3")){
                    state = "Count votes";
                }else if(input.equals("q") || input.equals("Q")){
                    state = "Done";
                }

            }

            while(state=="Add candidate"){
                boolean added = true;
                System.out.println("Q. go back");
                System.out.println("Who do you want to add to the ballot?");
                System.out.print(":");

                String candidate = keyboard.next();

                if (candidate.equals("q") || candidate.equals("Q")) {
                    state = "Start Menu";
                }

                try {
                    addWriteIn(candidate);
                } catch (CandidateExistsException f) {
                    added=false;
                    System.out.print("The candidate already exists.");
                }
                if(added){
                    state= "Start Menu";
                }

            }

            while (state.equals("Cast Votes")) {
                boolean doneHere = true;
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
                    doneHere = false;


                } catch (UnknownCandidateException e) {
                    System.out.println(e.getaName() + "is not on ballot.");
                    System.out.print("Would like to add the candidate's name to the ballot? y/n:");
                    String response = keyboard.next();
                    if (response.equals("y") || response.equals("Y")) {
                        try {
                            addWriteIn(e.getaName());
                        } catch (CandidateExistsException f) {
                            System.out.print("The candidate already exists.");
                            doneHere = false;
                        }
                    }
                }

                if(doneHere){
                    state = "Start Menu";
                }

            }


            while(state.equals("Count votes")) {
                System.out.println("Q. go back");
                System.out.println("How do you want to count the votes");
                System.out.println("Q. go back");
                System.out.println("1. Most first votes.");
                System.out.println("2. Most points.");
                System.out.print(":");

                String input = keyboard.next();
                String output = "only available to view to those who press 1-2";
                switch (input) {
                    case "1":
                        output = votingInformation.findWinnerMostFirstVotes();
                        break;
                    case "2":
                        output = votingInformation.findWinnerMostPoints();
                        break;
                    case "q":
                    case "Q":
                        state = "Start Menu";
                        break;
                }


                System.out.println("");
                System.out.println("The winner is: " + output);
                if(output.equals("Runoff Required")){
                    System.out.println("...our favorite candidate.");
                    System.out.println("");
                    System.out.println("You need to cast more votes.");
                    state = "Cast Votes";

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
