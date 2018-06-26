package mg.misa.view;

import mg.misa.controller.tableModel.DureeTableModel;
import mg.misa.controller.tableModel.JourneeTableModel;
import mg.misa.controller.PointageController;

import javax.swing.*;
import java.awt.*;

public class StatistiqueView extends JPanel {

    private JPanel searchPanel;
    private JPanel tablePanel;

    private JPanel absencePanel;
    private JPanel retardPanel;
    private JPanel suppPanel;
    private JPanel departPanel;

    private JTable absenceTable;
    private JTable retardTable;
    private JTable suppTable;
    private JTable departTable;

    private JScrollPane absenceScrollPane;
    private JScrollPane retardScrollPane;
    private JScrollPane suppScrollPane;
    private JScrollPane departScrollPane;

    private JLabel absenceLabel;
    private JLabel retardLabel;
    private JLabel suppLabel;
    private JLabel departLabel;

    private Dimension scrollPaneDimension;

    private JLabel searchLabel;
    private JTextField searchTextField;

    private PointageController controller;

    public StatistiqueView(){
        controller = PointageController.getPointageController();
        controller.setView(this);
        initComponents();
    }

    private void initComponents(){
        searchPanel = new JPanel();
        tablePanel = new JPanel();

        initTablePanelComponents();
        //initSearchPanelComponents();

        this.add(searchPanel, BorderLayout.PAGE_START);
        this.add(new JSeparator(), BorderLayout.CENTER);
        this.add(tablePanel, BorderLayout.PAGE_END);
    }

    private void initTablePanelComponents(){
        absencePanel = new JPanel();
        retardPanel = new JPanel();
        suppPanel = new JPanel();
        departPanel = new JPanel();

        scrollPaneDimension = new Dimension(400,200);

        initAbsenceComponents();
        initRetardComponents();
        initSuppComponents();
        initDepartComponents();

        GridLayout tableLayout = new GridLayout(2,2);
        tablePanel.setLayout(tableLayout);
        tablePanel.setPreferredSize(new Dimension(800,600));

        tablePanel.add(absencePanel);
        tablePanel.add(retardPanel);
        tablePanel.add(suppPanel);
        tablePanel.add(departPanel);
    }

    private void initAbsenceComponents(){
        absenceLabel = new JLabel("ABSENCE");
        absenceTable = new JTable(new JourneeTableModel("absence"));
        absenceScrollPane = new JScrollPane(absenceTable);
        absenceScrollPane.setPreferredSize(scrollPaneDimension);

        absencePanel.setLayout(new BorderLayout());
        absencePanel.add(absenceLabel, BorderLayout.PAGE_START);
        absencePanel.add(absenceScrollPane, BorderLayout.CENTER);
    }

    private void initRetardComponents(){
        retardLabel = new JLabel("RETARD");
        retardTable = new JTable(new DureeTableModel("retard"));
        retardScrollPane = new JScrollPane(retardTable);
        retardScrollPane.setPreferredSize(scrollPaneDimension);

        retardPanel.setLayout(new BorderLayout());
        retardPanel.add(retardLabel, BorderLayout.PAGE_START);
        retardPanel.add(retardScrollPane, BorderLayout.CENTER);
    }

    private void initSuppComponents(){
        suppLabel = new JLabel("SUPPLEMENTAIRE");
        suppTable = new JTable(new DureeTableModel("supplementaire"));
        suppScrollPane = new JScrollPane(suppTable);
        suppScrollPane.setPreferredSize(scrollPaneDimension);

        suppPanel.setLayout(new BorderLayout());
        suppPanel.add(suppLabel, BorderLayout.PAGE_START);
        suppPanel.add(suppScrollPane, BorderLayout.CENTER);
    }

    private void initDepartComponents(){
        departLabel = new JLabel("DEPART");
        departTable = new JTable(new DureeTableModel("depart"));
        departScrollPane = new JScrollPane(departTable);
        departScrollPane.setPreferredSize(scrollPaneDimension);

        departPanel.setLayout(new BorderLayout());
        departPanel.add(departLabel, BorderLayout.PAGE_START);
        departPanel.add(departScrollPane, BorderLayout.CENTER);
    }

    private void initSearchPanelComponents(){
        searchLabel = new JLabel("Recherche:");
        searchTextField = new JTextField();
        searchTextField.setPreferredSize(new Dimension(200,30));

        searchPanel.setLayout(new GridLayout(1,4));
        searchPanel.add(searchLabel);
        searchPanel.add(searchTextField);
        searchPanel.add(new Label(" "));
        searchPanel.add(new Label(" "));
        searchPanel.setPreferredSize(new Dimension(600,30));
    }

    public JTable getAbsenceTable() {
        return absenceTable;
    }

    public void setAbsenceTable(JTable absenceTable) {
        this.absenceTable = absenceTable;
    }

    public JTable getRetardTable() {
        return retardTable;
    }

    public void setRetardTable(JTable retardTable) {
        this.retardTable = retardTable;
    }

    public JTable getSuppTable() {
        return suppTable;
    }

    public void setSuppTable(JTable suppTable) {
        this.suppTable = suppTable;
    }

    public JTable getDepartTable() {
        return departTable;
    }

    public void setDepartTable(JTable departTable) {
        this.departTable = departTable;
    }
}
