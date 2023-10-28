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

    //if we have reached the number of hires, return the current candidates
    if (0 == hiresLeft){
      return candidates;
    }
    //find the candidate which will increase the total amount of hours the most
    Candidate max = getCandidateWithMaxHours(candidates, hired);

    System.out.println("Max Candidate: "+max);
    CandidateList hired2 = new CandidateList(hired);
    CandidateList candidates2 = new CandidateList(candidates);
    hired2.add(max);
    candidates2.remove(max);
    hired.addAll(greedyHiring(candidates2, hired2, hiresLeft-1, iter+1));
    return hired;
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
