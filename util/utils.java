package util;

public class utils {

    public static void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }


    class DSU {     // DisjointSetUnion
        int[] father;

        public DSU(int size) {
            father = new int[size];
            for(int i = 0; i <= size - 1; i++) {
                father[i] = i;
            }
        }

        public int find(int u) {
            if(father[u] == u) {
                return u;
            } else {
                father[u] = find(father[u]);
                return father[u];
            }
        }

        public void join(int u, int v) {
            u = find(u);
            v = find(v);
            if(u == v) return;  // 说明两者已经在同一个串内了，不用合并
            else father[v] = u;
        }

        public boolean isSame(int u, int v) {
            // 这里有个误区，可以直接用isSame代替join的3行吗？其实不行，因为这样的话，this.father[v]=u就把v连到u上了，而不是把v的根连到u的根
            u = find(u);
            v = find(v);
            return u == v;
        }
    }

}