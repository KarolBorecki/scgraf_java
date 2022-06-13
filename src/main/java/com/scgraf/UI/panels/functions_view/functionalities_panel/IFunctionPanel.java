package com.scgraf.UI.panels.functions_view.functionalities_panel;

import com.scgraf.logger.Logger;
import com.scgraf.solver.Solver;

public interface IFunctionPanel {

    void solve();
    void cancel();
    void updateValues(Object[] val);
}
