public class HiringTesting {

  public static boolean greedyHiringBaseTest(){
    return false;
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
    expectedList.add(testingList.get(1));
    expectedList.add(testingList.get(2));
    CandidateList actual = Hiring.greedyHiring(testingList, new CandidateList(), 2, 1);
    printCandidateList(actual);
    printCandidateList(expectedList);

    return HiringTestingUtilities.compareCandidateLists(expectedList,actual);
  }

  public static void printCandidateList(CandidateList list){
    for (Candidate c: list) {
      System.out.println(c.toString()+",");
    }
    System.out.println();
  }

  public static void main(String[] args){
    if (!greedyHiringRecursiveTest()){
      System.out.println("ERROR: EXPECTED AND ACTUAL ARRAY DONT MATCH FOR greedyHiringRecursiveTest!");
    }
  }
}
