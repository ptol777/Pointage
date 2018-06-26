package mg.misa.view;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public interface TableView {
    public JLabel getMatriculeLabel();
    public JTextField getMatriculeTextField();
    public JButton getAddButton();
    public JButton getUpdateButton();
    public TitledBorder getTitle();
    public JLabel getErrorLabel();
}
