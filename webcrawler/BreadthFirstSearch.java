package webcrawler;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BreadthFirstSearch {
    private Queue<String> queue;
    private List<String> discoveredWebsite;

    public BreadthFirstSearch() {
        this.queue = new LinkedList<>();
        this.discoveredWebsite = new ArrayList<>();
    }

    public void discoverWeb(String root){
        this.queue.add(root);
        this.discoveredWebsite.add(root);

        while(!queue.isEmpty()){
            String url = this.queue.remove();
            String rawHtml = readUrl(url);

            String regexp = "https://(\\w+\\.)*(\\w+)";
            Pattern pattern = Pattern.compile(regexp);
            Matcher matcher = pattern.matcher(rawHtml);

            while(matcher.find()){
                String w = matcher.group();
                if(!discoveredWebsite.contains(w)){
                    discoveredWebsite.add(w);
                    System.out.println("Website found: " + w);
                    queue.add(w);
                }
            }
        }
    }

    private String readUrl(String v){
        StringBuilder rawHtml = new StringBuilder("");
        try{
            URL url = new URL(v);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));

            String line = "";

            while((line=reader.readLine()) != null){
                rawHtml.append(line);
            }

            reader.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return rawHtml.toString();
    }
}
