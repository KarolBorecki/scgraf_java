package src.main.com.scgraf;

import src.main.com.scgraf.algorithms.dijkstra.Dijkstra;
import src.main.com.scgraf.data_handling.FileWriterG;
import src.main.com.scgraf.data_structures.graph.Graph;
import src.main.com.scgraf.generator.GraphGenerator;

import java.io.File;
import java.util.Random;
import java.util.random.RandomGenerator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Graph g = GraphGenerator.GenerateExample();
        RandomGenerator rg = new Random();

        System.out.println(g);

        Dijkstra d = new Dijkstra(g, g.getNode(0, 0));

        d.Solve(g.getNode(1,1));
        System.out.println(d.getDijkstraResult(g.getNode(1,1)));
        System.out.println(d);

        System.out.println(d.getShortestPathLength(g.getNode(2, 2)) + "\n" + d.getShortestPathString(g.getNode(2, 2)));

        File file = new File("example");
        FileWriterG fg = new FileWriterG(file);
        fg.writeGraphToFile(g);

        /*try {
            FileReaderG readerG = new FileReaderG(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }*/

        String line= "1: 2.343743 2: 23.343223";
        Pattern p = Pattern.compile("^[-+]?\\d+:\\s+[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?$");
        Matcher m = p.matcher(line);
        while(m.find()){
            System.out.println(m.group());
        }





    }
}
