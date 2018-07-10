import java.io.*;

public class Constants {
    // =====乱数=====
    public static Sfmt sfmt;
    public static String[] randomSeed;

    // =====定数=====
    // エージェントの数
    public static int N = 100;
    // ステップ数
    public static int steps = 10000;

    // =====ネットワーク(グラフ)関係=====
    // Spatial Random Graphでのr
    public static double r = 0.175;

    // =====Q-learning=====
    // Q-learningのlearning rate
    public static double alpha = 0.05;
    // Q-learningのexploration rate
    public static double epsilon = 0.1;


    public static void readRandomSeed(String filename){
        try {
            File file = new File(filename);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            randomSeed = line.split(",", 0);
            br.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public static void changeSfmtSeed(int seed){
        sfmt = new Sfmt(seed);
    }
}