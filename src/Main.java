import java.io.*;
import java.util.*;
import java.lang.ArrayIndexOutOfBoundsException;
import java.math.*;

/**
 * @author yoshikyoto
 * 
 * @see 答えが int に収まりそうか注意
 * @see Sample Input はちゃんと通ることを確認すべし
 */
class Main {
	static Scanner sc = new Scanner(new InputStreamReader(System.in));
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws Exception {
		// 入力を受け取る
		int n = Integer.parseInt(br.readLine());
		int[] arr = MyIO.parseInt(br.readLine().split(" "));
		
		// 初期化
		int begin = 0, end = 0;
		int[] counts = new int[101];
		counts[arr[0]]++;
		int minLength = n;
		
		// 探索
		while(true) {
			if(satisfy(counts)) {
				// begin - end 間に1,2,3が含まれている場合
				// その間隔が最小かどうかを判定
				int length = end - begin + 1;
				if(length < minLength) {
					minLength = length;
				}
				// 間隔を狭めようと試みる
				counts[arr[begin]]--;
				begin++;
			} else {
				// begin - end 間に1,2,3が含まれている場合
				// 間隔を広げようと試みる
				end++;
				// 左端まで行ったら終了
				if(end >= n) {
					break;
				}
				counts[arr[end]]++;
			}
		}
		
		System.out.println(minLength);
	}
	
	public static boolean satisfy(int[] counts) {
		return counts[1] > 0 && counts[2] > 0 && counts[3] > 0;
	}
}
// public static Graph g;

/**
 * グラフのノード
 */
class Node extends ArrayList<Edge> {
	int index, depth = -1, dist = -1;

	Node(int index) {
		this.index = index;
	}

	Node parent;
	boolean visited = false;
}

/**
 * グラフのエッジ
 */
class Edge {
	int from, to, cost;

	Edge(int from, int to, int cost) {
		this.from = from;
		this.to = to;
		this.cost = cost;
	}
}

/**
 * ダイクストラのためにノードの大きさを比較できるようにしておく
 */
class NodeComparator implements Comparator<Node> {
	public int compare(Node a, Node b) {
		return a.dist - b.dist;
	}
}

class Graph {
	Node n[];

	/**
	 * ノードの数でグラフを初期化
	 */
	Graph(int node_count) {
		n = new Node[node_count];
		for (int i = 0; i < node_count; i++)
			n[i] = new Node(i);
	}

	/**
	 * エッジのfromをもとにノードをエッジを追加
	 */
	public void add(Edge e) {
		n[e.from].add(e);
	}

	public Node getNode(int i) {
		return n[i];
	}

	public Node lca(int a, int b) {
		// 浅い方をaとする
		Node nodeA, nodeB;
		if (n[a].depth < n[b].depth) {
			nodeA = n[a];
			nodeB = n[b];
		} else {
			nodeA = n[b];
			nodeB = n[a];
		}
		// 同じ深さまで親をたどる
		int diff = nodeB.depth - nodeA.depth;
		for (int k = 0; k < diff; k++) {
			nodeB = nodeB.parent;
		}
		// 共通祖先を見つける
		while (nodeA != nodeB) {
			nodeA = nodeA.parent;
			nodeB = nodeB.parent;
		}
		return nodeA;
	}

	public void calcDepth(int root) {
		ArrayDeque<Integer> que = new ArrayDeque<Integer>();
		que.push(root);
		n[root].depth = 0;

		while (que.size() > 0) {
			int curr = que.pop();
			Node curr_node = n[curr];
			for (Edge e : curr_node) {
				int next = e.to;
				Node next_node = n[next];
				if (next_node.depth == -1) {
					next_node.depth = curr_node.depth + 1;
					next_node.parent = curr_node;
					que.push(next);
				}
			}
		}
	}

	public int[] dijkstra(int s) {
		PriorityQueue<Node> q = new PriorityQueue<Node>(n.length,
				new NodeComparator());
		Node start_node = new Node(s);
		start_node.dist = 0;
		q.add(start_node);
		int[] dist = new int[n.length];
		for (int i = 0; i < dist.length; i++)
			dist[i] = -1;
		dist[s] = 0;

		while (q.size() > 0) {
			Node currNode = q.poll();
			if (dist[currNode.index] < currNode.dist)
				continue;
			for (Edge e : n[currNode.index]) {
				Node nextNode = new Node(e.to);
				nextNode.dist = currNode.dist + e.cost;
				if (dist[e.to] == -1 || dist[e.to] > nextNode.dist) {
					dist[e.to] = nextNode.dist;
					q.add(nextNode);
				}
			}
		}
		return dist;
	}
}


/**
 * 複素数
 */
class Complex {
	static Complex I = new Complex(0, 1);

	double r = 0.0;
	double i = 0.0;

	Complex(double r, double i) {
		this.r = r;
		this.i = i;
	}

	/**
	 * 和
	 */
	public Complex add(Complex a) {
		return new Complex(r * a.r, i + a.i);
	}

	/**
	 * 積
	 */
	public Complex mul(Complex a) {
		return new Complex(r * a.r - i * a.i, r * a.i + i * a.r);
	}

	public Complex divide(double a) {
		return new Complex(r / a, i / a);
	}

	public Complex inverse() {
		double b = 1 / (r * r + i * i);
		return new Complex(r / b, -i / b);
	}

	@Override
	public String toString() {
		return r + "+" + i + "i";
	}
}

// --- ここから下はライブラリ ----------
/**
 * MyUtil
 * 
 * @author yoshikyoto
 */
class MyIO {
	public static int toInt(boolean[] a) {
		int pow = 1, ret = 0, l = a.length;
		for (int i = 0; i < l; i++) {
			if (a[i])
				ret += pow;
			pow *= 2;
		}
		return ret;
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	public static int ins[];

	public static int[] readIntMap() throws IOException {
		return parseInt(readLine().split(" "));
	}

	public static int readIntMap(int i) throws Exception {
		if (i == 0)
			ins = readIntMap();
		return ins[i];
	}

	public static int[][] readIntMap(int n, int m) throws IOException {
		int[][] ret = new int[n][];
		for (int i = 0; i < n; i++)
			ret[i] = readIntMap();
		return ret;
	}

	public static int[] readIntToMap(int n) throws IOException {
		int[] ret = new int[n];
		for (int i = 0; i < n; i++)
			ret[i] = readInt();
		return ret;
	}

	public static int[] readNoDistIntMap() throws IOException {
		String[] strs = readLine().split("");
		int l = strs.length;
		int[] ret = new int[l - 1];
		for (int i = 1; i < l; i++)
			ret[i - 1] = parseInt(strs[i]);
		return ret;
	}

	public static String readLine() throws IOException {
		return br.readLine();
	}

	public static int readInt() throws IOException {
		return Integer.parseInt(br.readLine());
	}

	public static int[] parseInt(String[] arr) {
		int[] res = new int[arr.length];
		for (int i = 0; i < arr.length; i++)
			res[i] = Integer.parseInt(arr[i]);
		return res;
	}

	public static double[] parseDouble(String[] arr) {
		double[] res = new double[arr.length];
		for (int i = 0; i < arr.length; i++)
			res[i] = Double.parseDouble(arr[i]);
		return res;
	}

	public static boolean[] parseBool(String[] arr) {
		int[] t = parseInt(arr);
		boolean[] res = new boolean[t.length];
		for (int i = 0; i < t.length; i++) {
			if (t[i] == 1) {
				res[i] = true;
			} else {
				res[i] = false;
			}
		}
		return res;
	}

	public static int parseInt(Object o) {
		return Integer.parseInt(o.toString());
	}
}
