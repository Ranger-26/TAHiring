public class Hiring {

  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired, int hiresLeft) {
    return greedyHiring(candidates, hired, hiresLeft, 1);
  }


  private static CandidateList greedyHiring(CandidateList candidates, CandidateList hired, int hiresLeft, int iter){
    //debug
    System.out.println("---------------------Iteration: "+iter+"-----------------------------");
    System.out.println("Candidates: ");
    HiringTesting.printCandidateList(candidates);
    System.out.print("Hiring: ");
    HiringTesting.printCandidateList(hired);

    //if we have reached the number of hires or we have no more candidates left to hire, return all the hired candidates
    if (hiresLeft <= 0 || candidates.isEmpty()){
      return hired;
    }
    //find the candidate which will increase the total amount of hours the most
    Candidate max = getCandidateWithMaxHours(candidates, hired);

    System.out.println("Max Candidate: "+max);
    //create copies of the hired and candidate list
    CandidateList hired2 = new CandidateList(hired);
    CandidateList candidates2 = new CandidateList(candidates);
    //add the candidate that maximizes the hours to the new hire list and remove it from the new candidate list
    hired2.add(max);
    candidates2.remove(max);
    //return a recursive call that gets the candidate list with the updated lists
    return greedyHiring(candidates2, hired2, hiresLeft-1, iter+1);
  }

  private static Candidate getCandidateWithMaxHours(CandidateList candidatesLeft, CandidateList currentHires) throws IllegalStateException{
    if (candidatesLeft.size() == 0){
      throw new IllegalStateException("candidatesLeft array is empty!");
    }
    int index = 0;
    //how to check which
    CandidateList dummyList = new CandidateList(currentHires);
    Candidate maxCandidate = candidatesLeft.get(0);
    int maxHoursCovered = dummyList.withCandidate(candidatesLeft.get(0)).numCoveredHours();
    for (int i = 1; i < candidatesLeft.size(); i++){
      Candidate curCandidate = candidatesLeft.get(i);
      int hoursCovered = dummyList.withCandidate(curCandidate).numCoveredHours();
      if (hoursCovered > maxHoursCovered){
        maxHoursCovered = hoursCovered;
        maxCandidate = curCandidate;
      }
    }
    return maxCandidate;
  }


  public static CandidateList minCoverageHiring(CandidateList candidates,
      CandidateList hired,
      int minHours){
      return null;
  }

  public static CandidateList optimalHiring(CandidateList candidates,
      CandidateList hired,
      int hiresLeft){
      return null;
  }

}
