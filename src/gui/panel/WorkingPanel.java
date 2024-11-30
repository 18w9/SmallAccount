package gui.panel;

import util.CenterPanel;

import javax.swing.*;
import java.awt.*;

public abstract class WorkingPanel extends JPanel {
    public abstract void updateData();
    public abstract void addListener();
}
