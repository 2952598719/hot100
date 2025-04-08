package algo.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Basic {

    // 207.课程表
    // 其实就是求图中是否有环：拓扑排序法
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 拓扑排序法：构造入度数组+邻接表，获取入度为0的点并bfs，过程中将零度节点加入zeroDegree列表中，如果所有节点都加入到zeroDegree中，就说明无环
        // 如果是无向图，则应该将度为1的节点加入zeroDegree
        int[] inDegree = new int[numCourses];
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : prerequisites) {
            inDegree[edge[1]]++;
            List<Integer> outNodes = graph.getOrDefault(edge[0], new ArrayList<>());
            outNodes.add(edge[1]);
            graph.put(edge[0], outNodes);
        }
        List<Integer> zeroDegree = new ArrayList<>();
        Queue<Integer> bfs = new ArrayDeque<>();
        for(int i = 0; i <= numCourses - 1; i++) {
            if(inDegree[i] == 0) {
                zeroDegree.add(i);
                bfs.add(i);
            }
        }
        while(!bfs.isEmpty()) {
            int pos = bfs.poll();
            if(graph.containsKey(pos)) {
                for(int i : graph.get(pos)) {
                    inDegree[i]--;
                    if(inDegree[i] == 0) {
                        zeroDegree.add(i);
                        bfs.add(i);
                    }
                }
            }
        }
        return zeroDegree.size() == numCourses;
    }
    
}
