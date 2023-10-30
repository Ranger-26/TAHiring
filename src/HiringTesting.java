import java.util.ArrayList;
import java.util.Random;

public class HiringTesting {

  public static boolean greedyHiringBaseTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    CandidateList expected = HiringTestingUtilities.generateRandomInput(6, 5);
    CandidateList actual = Hiring.greedyHiring(testingList, expected, 0);
    System.out.println("------------END----------------");
    printCandidateList(actual);
    printCandidateList(expected);
    return HiringTestingUtilities.compareCandidateLists(expected,actual);
  }

  public static boolean greedyHiringRecursiveTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    printCandidateList(testingList);
    CandidateList expectedList = new CandidateList();
    expectedList.add(testingList.get(0));
    expectedList.add(testingList.get(1));
    CandidateList actual = Hiring.greedyHiring(testingList, new CandidateList(), 2);
    CandidateList actual2 = Hiring.greedyHiring(new CandidateList(), new CandidateList(), 2);

    System.out.println("------------END----------------");
    printCandidateList(actual);
    printCandidateList(expectedList);

    return HiringTestingUtilities.compareCandidateLists(expectedList,actual) || HiringTestingUtilities.compareCandidateLists(actual2, new CandidateList());
  }



  public static void printCandidateList(CandidateList list){
    System.out.print("List: ");
    for (Candidate c: list) {
      System.out.print(c.toString()+",");
    }
    System.out.println();
  }

  public static boolean optimalHiringBaseTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    CandidateList expected = HiringTestingUtilities.generateRandomInput(6, 5);
    CandidateList actual = Hiring.optimalHiring(testingList, expected, 0);
    System.out.println("------------END----------------");
    printCandidateList(actual);
    printCandidateList(expected);
    return HiringTestingUtilities.compareCandidateLists(expected,actual);
  }

  public static boolean optimalHiringRecursiveTest(){
    CandidateList testingList = HiringTestingUtilities.makeCandidateList(new boolean[][]
        {
            {true, false, false, true, true, true},
            {true, true, false, false, true, false},
            {false, false, true, true, false, true}
        });
    printCandidateList(testingList);
    CandidateList expectedList = new CandidateList();
    expectedList.add(testingList.get(1));
    expectedList.add(testingList.get(2));
    CandidateList actual = Hiring.optimalHiring(testingList, new CandidateList(), 2);
    CandidateList actual2 = Hiring.optimalHiring(new CandidateList(), new CandidateList(), 2);

    System.out.println("------------END(optimal)----------------");
    printCandidateList(actual);
    printCandidateList(expectedList);
    return HiringTestingUtilities.compareCandidateLists(expectedList,actual) && HiringTestingUtilities.compareCandidateLists(actual2, new CandidateList());
  }

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

      if (!allCombinations.contains(actualResult)) {
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

  public static boolean minCoverageHiringBaseTest(){return false;}
  public static boolean minCoverageHiringRecursiveTest(){return false;}
  public static boolean minCoverageHiringFuzzTest(){return false;}

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
  }

}
