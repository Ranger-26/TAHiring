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
    System.out.println("------------END----------------");
    printCandidateList(actual);
    printCandidateList(expectedList);

    return HiringTestingUtilities.compareCandidateLists(expectedList,actual);
  }



  public static void printCandidateList(CandidateList list){
    System.out.print("List: ");
    for (Candidate c: list) {
      System.out.print(c.toString()+",");
    }
    System.out.println();
  }

  public static void main(String[] args){
    if (!greedyHiringRecursiveTest()){
      System.out.println("ERROR: EXPECTED AND ACTUAL ARRAY DONT MATCH FOR greedyHiringRecursiveTest!");
    }
    else{
      System.out.println("PASS greedyHiringRecursiveTest!");
    }
    if (!greedyHiringBaseTest()){
      System.out.println("ERROR: EXPECTED AND ACTUAL ARRAY DONT MATCH FOR greedyHiringBaseTest!");
    }
    else {
      System.out.println("PASS greedyHiringBaseTest!");
    }
  }
}
