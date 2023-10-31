public class Hiring {

  public static CandidateList greedyHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {

    //if we have reached the number of hires or we have no more candidates left to hire, return all the hired candidates
    if (hiresLeft <= 0 || candidates.isEmpty()) {
      return hired;
    }
    //find the candidate which will increase the total amount of hours the most
    Candidate max = getCandidateWithMaxHours(candidates, hired);

    //return a recursive call that gets the candidate list with the updated lists
    return greedyHiring(candidates.withoutCandidate(max), hired.withCandidate(max), hiresLeft - 1);
  }

  private static Candidate getCandidateWithMaxHours(CandidateList candidatesLeft,
      CandidateList currentHires) throws IllegalStateException {
    if (candidatesLeft.size() == 0) {
      throw new IllegalStateException("candidatesLeft array is empty!");
    }
    //set up the maxCandidate variable by getting the first candidate and
    Candidate maxCandidate = candidatesLeft.get(0);
    int maxHoursCovered = currentHires.withCandidate(candidatesLeft.get(0)).numCoveredHours();
    for (int i = 1; i < candidatesLeft.size(); i++) {
      //get the current candidate in the candidate array
      Candidate curCandidate = candidatesLeft.get(i);
      //calculate the TOTAL number of hours that will be covered with this candidate and the previously hired candidates
      int hoursCovered = currentHires.withCandidate(curCandidate).numCoveredHours();
      //set maxCandidate variable if number of hours is more than the max
      if (hoursCovered > maxHoursCovered) {
        maxHoursCovered = hoursCovered;
        maxCandidate = curCandidate;
      }
    }
    return maxCandidate;
  }


  public static CandidateList minCoverageHiring(CandidateList candidates, CandidateList hired,
      int minHours) {
    //return if the hired people can meet the minimum hours
    if (minHours <= hired.numCoveredHours()) {
      return hired;
    }

    //if no solution was found, return null
    if (candidates.isEmpty()) {
      return null;
    }

    //set the candidate with the minimum wage to null
    CandidateList minWageCandidateList = null;

    for (int i = 0; i < candidates.size(); i++) {
      //get current candidate
      Candidate currentCandidate = candidates.get(i);

      //get the candidate list with the minimum wage recursivley
      CandidateList newList = minCoverageHiring(candidates.withoutCandidate(currentCandidate),
          hired.withCandidate(currentCandidate), minHours);

      if (newList == null) {
        continue;
      }
      //update the minimum list
      if (minWageCandidateList == null || minWageCandidateList.totalCost() > newList.totalCost()) {
        minWageCandidateList = newList.deepCopy();
      }
    }

    return minWageCandidateList;
  }

  public static CandidateList optimalHiring(CandidateList candidates, CandidateList hired,
      int hiresLeft) {
    //base case
    if (hiresLeft == 0) {
      return hired;
    }

    //set the maxCandidate list to a blank candidateList
    CandidateList maxHoursCandidateList = null;
    for (int i = 0; i < candidates.size(); i++) {
      //get current candidate
      Candidate currentCandidate = candidates.get(i);

      //get the new candidate list recursivley
      CandidateList newList = optimalHiring(candidates.withoutCandidate(currentCandidate),
          hired.withCandidate(currentCandidate), hiresLeft - 1);
      //update the maximum list with the list that covers the most amount of hours
      if (maxHoursCandidateList == null
          || maxHoursCandidateList.numCoveredHours() < newList.numCoveredHours()) {
        maxHoursCandidateList = newList.deepCopy();
      }
    }

    //return the max candidate list
    if (maxHoursCandidateList != null)
      return maxHoursCandidateList;
    return new CandidateList();
  }
}
