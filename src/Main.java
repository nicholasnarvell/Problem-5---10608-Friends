import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cases = Integer.parseInt(br.readLine());
        for (int i = 0; i < cases; i++) {
            String[] s = br.readLine().split(" ");
            int v = Integer.parseInt(s[0]);
            int e = Integer.parseInt(s[1]);
            Graph g = new Graph(v);
            for (int j = 0; j < e; j++) {
                s = br.readLine().split(" ");
                int v1 = Integer.parseInt(s[0])-1;
                int v2 = Integer.parseInt(s[1])-1;
                g.addEdge(v1, v2);
            }
            int largestGroup = 0;
            Dfs dfs = new Dfs(g);
            while(true) {
                if (largestGroup < dfs.getCount()) largestGroup = dfs.getCount();
                int u = dfs.firstUnmarkedVertex();
                if (u == -1) {
                    break;
                }
                dfs.dfs(g, u);
            }
            System.out.println(largestGroup);
        }

    }
    public static class Dfs {
        private boolean[] marked;
        private int count;

        public Dfs(Graph g){
            marked = new boolean[g.V()];
        }

        public void dfs(Graph g, int v) {
            marked[v] = true;
            count++;
            for (int w : g.adj(v)) {
                if (!marked[w])
                    dfs(g, w);
            }
        }
        public int firstUnmarkedVertex() {
            count = 0;
            for (int i = 0; i < marked.length; i++) {
                if (!marked[i]) return i;
            }
            return -1;
        }

        public int getCount() {
            return count;
        }
    }

    public static class Graph {

        private int v;
        private int e;
        private ArrayList<ArrayList<Integer>> adj;

        public Graph(int v) {
            this.v = v;
            this.e = 0;
            adj = new ArrayList<>();
            for (int i = 0; i < v; i++) {
                adj.add(new ArrayList<Integer>());
            }
        }

        public int V() {
            return v;
        }

        public void addEdge(int v1, int v2) {
            adj.get(v1).add(v2);
            adj.get(v2).add(v1);
            e++;
        }

        public void printGraph(){
            for (int a = 0; a < adj.size(); a++) {
                for (int v : adj.get(a)) {
                    System.out.println("Indices:" + a + ", " + v);
                }
            }
        }

        public ArrayList<Integer> adj(int v) {
            return adj.get(v);
        }
    }
}
