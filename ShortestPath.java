import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPath {
	private Queue<Integer> queue = new LinkedList<Integer>();
	private int[][] adjecencyMatrix;
	private List<Integer> prevNodes = new ArrayList<>(5);
	private List<Integer> visitedNodes = new ArrayList<>(5);
	private List<Boolean> visitedNodesStatus = new ArrayList<>(5);
	
	public ShortestPath(){
		this.adjecencyMatrix = getAdjecencyMatrix();
		for(int i=0;i<5;i++){
			prevNodes.add(i, -9999);
			visitedNodesStatus.add(i, false);
		}
	}
	private int[][] getAdjecencyMatrix(){
		int defaultMatrix[][] = {{0,1,1,1,0},{1,0,0,0,1},{1,0,0,0,1},{1,0,0,0,1},{0,1,1,1,0}};
		return defaultMatrix;
	}
	private Map<Integer,List<Integer>> getAdjecencyMap(){
		Map<Integer,List<Integer>>  adjecencyMap = new HashMap<Integer,List<Integer>>();
		List<Integer> vertexList = null;
		for(int i=0;i<5;i++){
			for(int j=0;j<5;j++){
				if(adjecencyMatrix[i][j]==1){
					if(adjecencyMap.get(i)!=null){
						vertexList = adjecencyMap.get(i);
					}else{
						vertexList = new ArrayList<Integer>();
					}
					vertexList.add(j);
					adjecencyMap.put(i,vertexList);
				}
			}
		}
		return adjecencyMap;
	}
	
	private void performBFS(int sourceNode,Map<Integer,List<Integer>> adjecencyMap){
		queue.add(sourceNode);
		visitedNodes.add(sourceNode);
		//visitedNodesStatus.set(sourceNode, true);
		while(!queue.isEmpty()){
			Integer currentVertex = queue.poll();
			List<Integer> neighbourNodesList = adjecencyMap.get(currentVertex);
			if(neighbourNodesList!=null && neighbourNodesList.size()!=0){
				visitedNodesStatus.set(currentVertex, true);
				for(Integer node: neighbourNodesList){
					if(!visitedNodesStatus.get(node)){
						queue.add(node);
						visitedNodesStatus.set(node, true);
						visitedNodes.add(node);
						prevNodes.set(node,currentVertex);
					}
				}
			}else{
				System.out.println("Source node is not part of graph");
			}
		}
		System.out.println(visitedNodes);
		System.out.println(prevNodes);
	}
	
	private void printRoute(int source,int dest){
		List<Integer> routeList = new ArrayList<>();
		routeList.add(dest);
		while(!prevNodes.get(dest).equals(source)){
			routeList.add(prevNodes.get(dest));
			dest = prevNodes.get(dest);
		}
		routeList.add(source);
		Collections.reverse(routeList);
		System.out.println(routeList);
	}
	public static void main(String[] args) {
		ShortestPath shortestPath = new ShortestPath();
		shortestPath.performBFS(1, shortestPath.getAdjecencyMap());
		shortestPath.printRoute(1,3);
	}

}
