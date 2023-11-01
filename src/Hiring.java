//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Hiring.java
// Course:   CS 300 Fall 2023
//
// Author:   Siddharth Mohan
// Email:    mohan34@wisc.edu
// Lecturer: Mark Mansi
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    N/A
// Partner Email:   N/A
// Partner Lecturer's Name: N/A
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         (identify each by name and describe how they helped)
// Online Sources:
//
///////////////////////////////////////////////////////////////////////////////


/**
 * A simple class that contains useful for solving various hiring problems.
 */
public class Hiring {

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a maximum number of tas to hire, return the
   * set of hires made using a greedy strategy that always chooses the candidate that increases hours covered the most.
   * @param candidates  The set of available candidates to hire from (excluding those already hired).
   * @param hired The list of those currently hired.
   * @param hiresLeft The maximum number of candidates to hire.
   * @return A list of hired candidates.
   */
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

  /**
   * Gets the candidate that will maximize the total amount of hours given an array of people currently hire.
   * @param candidatesLeft The candidates to choose from. Size should not be 0.
   * @param currentHires The candidates that are already hired.
   * @return The candidate that will maximize the total amount of hours in a CandidateArray
   * @throws IllegalStateException If there are zero candidates in the candidatesArray.
   */
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


  /**
   * Find the minimum-budget set of hires to achieve a threshold number of hours.
   * @param candidates The set of available candidates to hire from.
   * @param hired The set of candidates already hired.
   * @param minHours The minimum number of hours we want to cover total.
   * @return A list of hired candidates.
   */
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

  /**
   * Given a set of `candidates` that we can hire, a list of candidates we've already hired, and a maximum number of tas to hire, return the set of hires that maximizes number of scheduled hours.
   * @param candidates The set of available candidates to hire from.
   * @param hired The list of those currently hired.
   * @param hiresLeft the maximum number of candidates to hire.
   * @return A list of hired candidates.
   */
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
