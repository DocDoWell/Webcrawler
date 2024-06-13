package main;

import webcrawler.BreadthFirstSearch;

public class App {
    public static void main(String[] args){
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.discoverWeb("https://www.bbc.co.uk/sport");
    }
}
