package mg.misa.view;

import mg.misa.controller.CongeController;
import mg.misa.controller.tableModel.JourneeTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class CongeView extends JPanel implements TableView {

    private JPanel tablePanel;
    private JPanel textPanel;
    private JPanel addUpdatePanel;

    private JTable table;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelectionModel;

    private JLabel matriculeLabel;
    private JLabel dateLabel;
    private JLabel dateFinLabel;
    private JLabel typeLabel;
    private JLabel errorLabel;
    private JLabel statusLabel;

    private JTextField matriculeTextField;
    private JTextField dateTextField;
    private JTextField dateFinTextField;
    private JComboBox typeComboBox;

    private JButton addButton;
    private JButton updateButton;
    private JButton cancelButton;

    private TitledBorder title;

    private CongeController controller;

    public CongeView(){
        controller = CongeController.getCongeController();
        controller.setView(this);
        initComponents();
    }

    private void initComponents(){
        tablePanel = new JPanel();
        textPanel = new JPanel();

        initTableComponents();
        initTextComponents();

        setLayout(new GridLayout(2,1));
        add(tablePanel);
        add(textPanel);

    }

    private void initTableComponents(){
        table = new JTable(new JourneeTableModel("conge"));
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listSelectionModel = table.getSelectionModel();
        listSelectionModel.addListSelectionListener(controller);
        table.setSelectionModel(listSelectionModel);
        scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(700,250));
        tablePanel.add(scrollPane);
    }

    private void initTextComponents(){
        addUpdatePanel = new JPanel();

        String[] types = {"FULL","HALF_MORNING", "HALF_AFTERNOON"};
        title = new TitledBorder("AJOUT");
        matriculeLabel = new JLabel("Matricule:");
        dateLabel = new JLabel("Date:");
        dateFinLabel = new JLabel("Date fin:");
        typeLabel = new JLabel("Type:");
        errorLabel = new JLabel(" ");

        matriculeTextField = new JTextField();
        dateTextField = new JTextField();
        dateFinTextField = new JTextField();
        typeComboBox = new JComboBox(types);

        addButton = new JButton("Ajouter");
        updateButton = new JButton("Modifier");
        cancelButton = new JButton("Annuler");

        addButton.addActionListener(controller);
        updateButton.addActionListener(controller);
        cancelButton.addActionListener(controller);

        updateButton.setVisible(false);

        createTextLayout();
        addUpdatePanel.setBorder(title);
        addUpdatePanel.setPreferredSize(new Dimension(700,300));
        textPanel.add(addUpdatePanel);
    }

    private void createTextLayout(){
        GroupLayout layout = new GroupLayout(addUpdatePanel);
        addUpdatePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(matriculeLabel)
                                                .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dateLabel)
                                                .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dateFinLabel)
                                                .addComponent(dateFinTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(typeLabel)
                                                .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton)
                                                .addComponent(updateButton))
                                        .addComponent(cancelButton)))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(errorLabel))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matriculeLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateFinTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateFinLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(typeComboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeLabel))
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(addButton)
                                        .addComponent(updateButton))
                                .addComponent(cancelButton)
                                .addComponent(errorLabel))
        );
    }

    public JTable getTable() {
        return table;
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    @Override
    public JLabel getMatriculeLabel() {
        return matriculeLabel;
    }

    public void setMatriculeLabel(JLabel matriculeLabel) {
        this.matriculeLabel = matriculeLabel;
    }

    public JLabel getDateLabel() {
        return dateLabel;
    }

    public void setDateLabel(JLabel dateLabel) {
        this.dateLabel = dateLabel;
    }

    public JLabel getDateFinLabel() {
        return dateFinLabel;
    }

    public void setDateFinLabel(JLabel dateFinLabel) {
        this.dateFinLabel = dateFinLabel;
    }

    public JLabel getTypeLabel() {
        return typeLabel;
    }

    public void setTypeLabel(JLabel typeLabel) {
        this.typeLabel = typeLabel;
    }

    @Override
    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    @Override
    public JTextField getMatriculeTextField() {
        return matriculeTextField;
    }

    public void setMatriculeTextField(JTextField matriculeTextField) {
        this.matriculeTextField = matriculeTextField;
    }

    public JTextField getDateTextField() {
        return dateTextField;
    }

    public void setDateTextField(JTextField dateTextField) {
        this.dateTextField = dateTextField;
    }

    public JTextField getDateFinTextField() {
        return dateFinTextField;
    }

    public void setDateFinTextField(JTextField dateFinTextField) {
        this.dateFinTextField = dateFinTextField;
    }

    public JComboBox getTypeComboBox() {
        return typeComboBox;
    }

    public void setTypeComboBox(JComboBox typeComboBox) {
        this.typeComboBox = typeComboBox;
    }

    @Override
    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

    @Override
    public JButton getUpdateButton() {
        return updateButton;
    }

    public void setUpdateButton(JButton updateButton) {
        this.updateButton = updateButton;
    }

    public JButton getCancelButton() {
        return cancelButton;
    }

    public void setCancelButton(JButton cancelButton) {
        this.cancelButton = cancelButton;
    }

    @Override
    public TitledBorder getTitle() {
        return title;
    }

    public void setTitle(TitledBorder title) {
        this.title = title;
    }
}
