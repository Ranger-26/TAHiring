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
    expectedList.add(testingList.get(0));
    expectedList.add(testingList.get(1));
    CandidateList actual = Hiring.optimalHiring(testingList, new CandidateList(), 2);
    CandidateList actual2 = Hiring.optimalHiring(new CandidateList(), new CandidateList(), 2);

    System.out.println("------------END----------------");
    printCandidateList(actual);
    printCandidateList(expectedList);

    return HiringTestingUtilities.compareCandidateLists(expectedList,actual) || HiringTestingUtilities.compareCandidateLists(actual2, new CandidateList());
  }

  public static boolean optimalHiringFuzzTest(){
    return false;
  }

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
      System.out.println("FAIL optimalHiringRecursiveTest!");
      return;
    }else{
      System.out.println("PASS optimalHiringRecursiveTest!");
    }

    if (optimalHiringBaseTest()){
      System.out.println("PASS optimalHiringBaseTest!");
      return;
    }else{
      System.out.println("FAIL optimalHiringNaseTest!");
    }
  }


}
