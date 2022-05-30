package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.UI.UIConfig;
import com.scgraf.UI.elements.text.FormattedTextField;
import com.scgraf.UI.panels.functions_view.FunctionPanelManager;
import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class GeneratePanel extends FunctionAbstractPanel implements SolverCaller {
    FormattedTextField widthInput;
    FormattedTextField heightInput;
    FormattedTextField maxPathWeightInput;

    public GeneratePanel(FunctionPanelManager root) {
        super(root, "Generate", "Pass the parameters of generated graph:");
        HBox inputPane = new HBox();

        inputPane.setAlignment(Pos.CENTER);
        inputPane.setSpacing(UIConfig.panelRegularSpacing);

        widthInput = new FormattedTextField("Width");
        heightInput = new FormattedTextField("Height");
        maxPathWeightInput = new FormattedTextField("Maximum path weight");

        inputPane.getChildren().addAll(widthInput, heightInput, maxPathWeightInput);
        buildFunctionView(inputPane);
    }

    @Override @FXML //TODO REFACTOR OMFG
    public void solve() {
        //TODO Handle wrong data
        final int width = Integer.parseInt(widthInput.getText());
        final int height = Integer.parseInt(heightInput.getText());
        final double maxWeight = Double.parseDouble(maxPathWeightInput.getText());

        /*
        Thread generator = new Thread(() -> Platform.runLater(() -> Solver.getInstance().generate(width, height, maxWeight)));
        generator.setDaemon(true);
        generator.start();
        execBtn.setDisable(true);
        cancelBtn.setDisable(true);
        try {
            generator.join();
            execBtn.setDisable(false);
            cancelBtn.setDisable(false);
            Logger.getInstance().log(Logger.StatusLog.OK);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         */
        System.out.println("CLICKED"); //TODO DELETE
        execBtn.setDisable(true);
        cancelBtn.setDisable(true);
        Logger.getInstance().log(Logger.StatusLog.CALCULATING);
        Task<Void> databaseTask = new Task<Void>() {
            @Override
            public Void call(){
                Solver.getInstance().generate(width, height, maxWeight);
                return null;
            }
        };
        databaseTask.setOnSucceeded( event -> {
            execBtn.setDisable(false);
            cancelBtn.setDisable(false);
            Logger.getInstance().log(Logger.StatusLog.OK);
            Solver.getInstance().setGraph(Solver.getInstance().getGraph()); //TODO change
            System.out.println("DONE"); //TODO DELETE
        });
        Thread generatorThread = new Thread(databaseTask);
        generatorThread.setDaemon(true);
        generatorThread.start();

        try {
            generatorThread.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
