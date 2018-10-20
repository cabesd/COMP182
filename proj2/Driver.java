/*
   TODO
      * File read
      * User input
      * Check cyclic process
      * Check if sorting is unique
*/

class Driver {

   public static void main(String args[]){
      Graph g = new Graph();
      g.addNode('a');
      g.addNode('b');
      g.addNode('c');
      g.addNode('d');
      g.addNode('e');
      
      g.addDep('a', 'b');
      g.addDep('a', 'c');
      g.addDep('b', 'd');
      g.addDep('d', 'e');
      g.addDep('e', 'c');
      g.addDep('c', 'a');
      
      System.out.println( g.outputSummary() );
      g.TopologicalSort();
   }

}