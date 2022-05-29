package com.scgraf.logger;

import com.scgraf.data_structures.graph.Graph;
import com.scgraf.generator.GraphGenerator;
import com.scgraf.solver.Solver;
import com.scgraf.utils.Observer;

import java.util.ArrayList;
import java.util.List;

public class Logger {
    public static Logger instance;

    public static Logger getInstance(Graph graph) {
        if (instance == null)
            instance = new Logger();

        return instance;
    }
}
