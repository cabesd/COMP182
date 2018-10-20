import java.util.*;

class Graph {

   class Node {
      char letter;
      int indegree;
      int outdegree;
      public ArrayList<Node> fathers;
      public ArrayList<Node> childrens;
      
      public Node(char c){
         this.letter = c;
         this.indegree = 0;
         this.fathers = new ArrayList<Node>();
         this.childrens = new ArrayList<Node>();
      }
      
      public void addFather(Node n){
         if( !this.fathers.contains(n) ){
            this.fathers.add(n);
            this.indegree++;
         }
      }
      
      public void addChildren(Node n){
         if( !this.childrens.contains(n) ){
            this.childrens.add(n);
            this.outdegree++;
         }
      }
      
      public char getLetter(){
         return this.letter;
      }
   }
   
   public ArrayList<Node> TaskList;

   public Graph(){
      this.TaskList = new ArrayList<Node>();
   }
   
   private Node findNode(char input){
      for (Node item : this.TaskList) {
          if (item.getLetter() == (input)) {
              return item;
          }
      }
      return null;
   }
   
   // For debugging
   public String outputSummary(){
      String out = "";
      for (Node n1 : this.TaskList) {
         out += "'" + n1.getLetter() + "' depends on "+ n1.indegree +" : ";
         for(Node n2 : n1.fathers){
            out += n2.getLetter() + " ";
         }
         out += "\n";
      }
      return out;
   }
   
   public void addNode(char n){
      if( this.findNode(n) != null ) return; // avoids duplicates
      Node new_node = new Node(n);
      this.TaskList.add(new_node);
   }
   
   public void addDep(char father, char children){
      Node nFather = this.findNode(father);
      Node nChildren = this.findNode(children);
      nFather.addChildren(nChildren);
      nChildren.addFather(nFather);
   }
   
   public void TopologicalSort(){
      Queue<Node> q = new LinkedList<>();
      // First look for one that has 0 indegrees
      for( Node n : this.TaskList ){
         if( n.indegree == 0 ){
            q.add(n);
         }
      }
      
      int counter = 0;
      ArrayList<Node> SortedArray = new ArrayList<Node>();
      while( q.size() > 0 ){ // while its not empty
         Node graph_head = q.remove();
         SortedArray.add( graph_head );
         counter++;
         // Get edges for the q.removed
         for( Node ch : graph_head.childrens ){
            ch.indegree--;
            if( ch.indegree == 0 ) q.add(ch);
         }
      }
      
      String sortedOutput = "";
      for( Node n : SortedArray ){
         sortedOutput += n.getLetter() + " ";
      }
      System.out.println( sortedOutput );
   }

}