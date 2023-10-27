public class Hiring {

  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired, int hiresLeft, int iter){
    System.out.println("---------------------Iteration: "+iter+"-----------------------------");
    System.out.println("Candidates: ");
    HiringTesting.printCandidateList(candidates);
    System.out.print("Hiring: ");
    HiringTesting.printCandidateList(hired);
    if (hired.size() == hiresLeft){
      return candidates;
    }
    Candidate max = getCandidateWithMaxHours(candidates, hired);
    hired.add(max);
    candidates.remove(max);
    return greedyHiring(candidates, hired, hiresLeft, iter+1);
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
