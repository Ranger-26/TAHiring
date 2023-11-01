//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    HiringTesting.java
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


import java.awt.event.HierarchyBoundsAdapter;
import java.util.ArrayList;
import java.util.Random;

public class HiringTesting {

  /**
   * Tests the base case of the Hiring.greedyHiring() method.
   * @return True if the greedy hiring base case returns the right value.
   */
  public static boolean greedyHiringBaseTest(){
    //create a new testing list
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    //generate random candidate list that cover 6 hours with 5 candidates
    CandidateList expected = HiringTestingUtilities.generateRandomInput(6, 5);
    CandidateList actual = Hiring.greedyHiring(testingList, expected, 0);
    //check if the lists have the same contents
    return HiringTestingUtilities.compareCandidateLists(expected,actual);
  }

  /**
   * Tests the recursive case of the greedyHiring method.
   * @return True if the recursive case passes.
   */
  public static boolean greedyHiringRecursiveTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    //create and add the optimal candiates to a candidate lists
    CandidateList expectedList = new CandidateList();
    expectedList.add(testingList.get(0));
    expectedList.add(testingList.get(1));
    //get the actual results from my implementation
    CandidateList actual = Hiring.greedyHiring(testingList, new CandidateList(), 2);
    CandidateList actual2 = Hiring.greedyHiring(new CandidateList(), new CandidateList(), 2);

    System.out.println("------------END----------------");
    return HiringTestingUtilities.compareCandidateLists(expectedList,actual) || HiringTestingUtilities.compareCandidateLists(actual2, new CandidateList());
  }

  /**
   * Tests the base case of the optimalHiring method.
   * @return True if the base cases passes.
   */
  public static boolean optimalHiringBaseTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    CandidateList expected = HiringTestingUtilities.generateRandomInput(6, 5);
    CandidateList actual = Hiring.optimalHiring(testingList, expected, 0);
    return HiringTestingUtilities.compareCandidateLists(expected,actual);
  }

  /**
   * Tests the optimalHiring method's recursive case.
   * @return True if the recursive case pases.
   */
  public static boolean optimalHiringRecursiveTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });

    CandidateList expectedList = new CandidateList();
    expectedList.add(testingList.get(1));
    expectedList.add(testingList.get(2));
    CandidateList actual = Hiring.optimalHiring(testingList, new CandidateList(), 2);
    CandidateList actual2 = Hiring.optimalHiring(new CandidateList(), new CandidateList(), 2);

    return HiringTestingUtilities.compareCandidateLists(expectedList,actual) && HiringTestingUtilities.compareCandidateLists(actual2, new CandidateList());
  }

  /**
   * Fuzz tests the optimalHiringFuzz method by comparing the result of it with an iterative solution with random inputs.
   * @return True if the fuzz test passes.
   */
  public static boolean optimalHiringFuzzTest(){
    long seed = new Random().nextLong();
    Random random = new Random(seed);

    for (int i = 0; i< 200; i++) {
      CandidateList randomCandidatesList =
          HiringTestingUtilities.generateRandomInput(random.nextInt(1, 6), random.nextInt(1, 10));
      int totalHires = random.nextInt(randomCandidatesList.size() > 1 ? 1:0, randomCandidatesList.size());
      ArrayList<CandidateList> allCombinations =
          HiringTestingUtilities.allOptimalSolutions(randomCandidatesList, totalHires);
      CandidateList actualResult =
          Hiring.optimalHiring(randomCandidatesList, new CandidateList(), totalHires);

      if (!HiringTestingUtilities.compareCandidateLists(allCombinations, actualResult)) {
        System.out.println("ERROR: FUZZ TESTING CAUGHT A TESTCASE THAT FAILS optimalHiringTest");
        System.out.println("Seed: "+seed+", Index: "+i);
        System.out.println("Candidate List:" + randomCandidatesList);
        System.out.println("Total Hires: "+totalHires);
        System.out.println("Actual Result: "+actualResult);
        System.out.println("Allowed Combinations: "+allCombinations);
        System.out.println();
        return false;
      }
    }
    return true;
  }

  /**
   * Tests the base case of the minCoverageHiring method.
   * @return True if the base test passes.
   */
  public static boolean minCoverageHiringBaseTest(){
    boolean[][] hours = new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        };
    int[] wages = {4,2,3};
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(hours, wages);
    CandidateList hired = new CandidateList();
    hired.add(candidates.get(0));
    CandidateList actual = Hiring.minCoverageHiring(candidates, hired, 4);
    return HiringTestingUtilities.compareCandidateLists(actual, hired);
  }

  /**
   * Tests the recursive case of the minCoverageHiring method.
   * @return True if the recursive test passes.
   */
  public static boolean minCoverageHiringRecursiveTest(){
    boolean[][] hours = new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        };

    int[] wages = {4,2,3};
    CandidateList candidates = HiringTestingUtilities.makeCandidateList(hours, wages);
    CandidateList expected = new CandidateList();
    CandidateList actual = Hiring.minCoverageHiring(candidates, new CandidateList(), 4);
    expected.add(candidates.get(0));
    return HiringTestingUtilities.compareCandidateLists(expected, actual);
  }

  /**
   * Fuzz tests the minCoverageHiring method by comparing the result of it with an iterative solution with random inputs.
   * @return True if the fuzz test passes.
   */
  public static boolean minCoverageHiringFuzzTest(){
    long seed = new Random().nextLong();
    Random random = new Random(seed);

    for (int i = 0; i< 500; i++) {
      //generate random parameters
      int numHours = random.nextInt(1, 6);
      int numCandidates = random.nextInt(1, 10);
      int minHours = random.nextInt(1, numHours > 1 ? numHours : 2);
      int maxPayRate = numCandidates/2;
      if (numCandidates/2 <= 1){
        maxPayRate =2;
      }
      //generate random candidate list with the generated parameters
      CandidateList randomCandidatesList = HiringTestingUtilities.generateRandomInput(numHours, numCandidates, maxPayRate);

      //get all possible minimum combinations and compare against my implementation
      ArrayList<CandidateList> allCombinations = HiringTestingUtilities.allMinCoverageSolutions(randomCandidatesList, minHours);
      CandidateList actualResult = Hiring.minCoverageHiring(randomCandidatesList, new CandidateList(), minHours);
      //print out error message with useful info if any fuzz test fails
      if (!HiringTestingUtilities.compareCandidateLists(allCombinations, actualResult) && !(actualResult == null && allCombinations.size() == 0)) {
        System.out.println("ERROR: FUZZ TESTING CAUGHT A TESTCASE THAT FAILS optimalHiringTest");
        System.out.println("Seed: " + seed + ", Index: " + i);
        System.out.println("Candidate List:" + randomCandidatesList);
        System.out.println("Min Hours: " + minHours);
        System.out.println("Actual Result: " + actualResult);
        System.out.println("Allowed Combinations: " + allCombinations);
        System.out.println();
        return false;
      }
    }
    return true;
  }

  /**
   * Main method of the program. Runs and checks every test.
   * @param args Command line args passed to the program.
   */
  public static void main(String[] args){
    if (!greedyHiringRecursiveTest()){
      System.out.println("FAIL greedyHiringRecursiveTest!");
      return;
    }
    else{
      System.out.println("PASS greedyHiringRecursiveTest!");
    }

    if (!greedyHiringBaseTest()){
      System.out.println("FAIL greedyHiringBaseTest!");
      return;
    }
    else {
      System.out.println("PASS greedyHiringBaseTest!");
    }

    if (optimalHiringRecursiveTest()){
      System.out.println("PASS optimalHiringRecursiveTest!");
    }else{
      System.out.println("FAIL optimalHiringRecursiveTest!");
    }

    if (optimalHiringBaseTest()){
      System.out.println("PASS optimalHiringBaseTest!");
    }else{
      System.out.println("FAIL optimalHiringBaseTest!");
    }

    if (optimalHiringFuzzTest()){
      System.out.println("PASS optimalHiringFuzzTest");
    }
    else{
      System.out.println("FAIL optimalHiringFUzzTest");
    }

    if (minCoverageHiringRecursiveTest()){
      System.out.println("PASS minCoverageHiringRecursiveTest");
    }
    else{
      System.out.println("FAIL minCoverageHiringRecursiveTest");
    }

    if (minCoverageHiringBaseTest()){
      System.out.println("PASS minCoverageHiringBaseTest");
    }else {
      System.out.println("FAIL minCoverageHiringBaseTest");
    }

    if (minCoverageHiringFuzzTest()){
      System.out.println("PASS minCoverageHiringFuzzTest");
    }else {
      System.out.println("FAIL minCoverageHiringFuzzTest");
    }
  }

}
