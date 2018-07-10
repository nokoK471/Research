public class Test {

    public static void main(String args[]){
        // 乱数の準備
        Constants.readRandomSeed("RandomSeed.csv");
        Constants.changeSfmtSeed(Integer.parseInt(Constants.randomSeed[0]));

        // エージェント・グラフの準備
        SpatialRandomGraph srg = new SpatialRandomGraph(Constants.r);

        Opinion[] firstOpinion = new Opinion[Constants.N];
        for(int i=0; i<Constants.N; i++){
            firstOpinion[i] = srg.agents[i].opi;
        }

        // ファイルパス
        String filepath = "result/20180710/";

        srg.outputAgentCSV(filepath + "Agent_step0");
        srg.outputNetworkCSV(filepath + "Network");

        // メイン
        for(int step=1; step<=Constants.steps; step++){
            // 1つ目のエージェント
            int a1 = Constants.sfmt.NextInt(Constants.N);
            srg.agents[a1].updateOpinion();
            // 2つ目のエージェント
            int a2 = Constants.sfmt.NextInt(srg.agents[a1].linkList.size());
            srg.agents[a1].updateQ(srg.agents[a1].linkList.get(a2));

            if(step%2500==0){
                srg.outputAgentCSV(filepath + "Agent_step" + step);
            }
        }

        for(int i=0; i<Constants.N; i++){
            System.out.println(i + ": " + firstOpinion[i] + "->" + srg.agents[i].opi);
        }
    }
}
