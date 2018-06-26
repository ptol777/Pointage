package mg.misa.view;

import mg.misa.controller.EmployeController;
import mg.misa.controller.tableModel.EmployeTableModel;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class EmployeView extends JPanel implements TableView{

    private JPanel tablePanel;
    private JPanel textPanel;
    private JPanel addUpdatePanel;

    private JTable table;
    private JScrollPane scrollPane;
    private ListSelectionModel listSelectionModel;

    private JLabel matriculeLabel;
    private JLabel nomLabel;
    private JLabel prenomLabel;
    private JLabel sexeLabel;
    private JLabel CINLabel;
    private JLabel errorLabel;
    private JLabel statusLabel;

    private JTextField matriculeTextField;
    private JTextField nomTextField;
    private JTextField prenomTextField;
    private JTextField CINTextField;
    private JComboBox sexeComboBox;

    private JButton addButton;
    private JButton updateButton;
    private JButton cancelButton;

    private TitledBorder title;

    private EmployeController controller;

    public EmployeView(){
        controller = EmployeController.getEmployeController();
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
        table = new JTable(new EmployeTableModel());
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

        String[] sexes = {"M","F"}; 
        title = new TitledBorder("AJOUT");
        matriculeLabel = new JLabel("Matricule:");
        nomLabel = new JLabel("Nom:");
        prenomLabel = new JLabel("Prenom:");
        sexeLabel = new JLabel("sexe:");
        CINLabel = new JLabel("CIN:");
        errorLabel = new JLabel(" ");
        
        matriculeTextField = new JTextField();
        nomTextField = new JTextField();
        prenomTextField = new JTextField();
        sexeComboBox = new JComboBox(sexes);
        CINTextField = new JTextField();
        
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
                                                .addComponent(nomLabel)
                                                .addComponent(nomTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(prenomLabel)
                                                .addComponent(prenomTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(sexeLabel)
                                                .addComponent(sexeComboBox, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(CINLabel)
                                                .addComponent(CINTextField, GroupLayout.PREFERRED_SIZE, 400, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(addButton)
                                                .addComponent(updateButton))
                                        .addComponent(cancelButton)))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(errorLabel))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matriculeLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(nomTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(nomLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(prenomTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(prenomLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(sexeComboBox, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(sexeLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(CINTextField, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(CINLabel))
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

    public JLabel getMatriculeLabel() {
        return matriculeLabel;
    }

    public void setMatriculeLabel(JLabel matriculeLabel) {
        this.matriculeLabel = matriculeLabel;
    }

    public JLabel getNomLabel() {
        return nomLabel;
    }

    public void setNomLabel(JLabel nomLabel) {
        this.nomLabel = nomLabel;
    }

    public JLabel getPrenomLabel() {
        return prenomLabel;
    }

    public void setPrenomLabel(JLabel prenomLabel) {
        this.prenomLabel = prenomLabel;
    }

    public JLabel getSexeLabel() {
        return sexeLabel;
    }

    public void setSexeLabel(JLabel sexeLabel) {
        this.sexeLabel = sexeLabel;
    }

    public JLabel getCINLabel() {
        return CINLabel;
    }

    public void setCINLabel(JLabel CINLabel) {
        this.CINLabel = CINLabel;
    }

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

    public JTextField getMatriculeTextField() {
        return matriculeTextField;
    }

    public void setMatriculeTextField(JTextField matriculeTextField) {
        this.matriculeTextField = matriculeTextField;
    }

    public JTextField getNomTextField() {
        return nomTextField;
    }

    public void setNomTextField(JTextField nomTextField) {
        this.nomTextField = nomTextField;
    }

    public JTextField getPrenomTextField() {
        return prenomTextField;
    }

    public void setPrenomTextField(JTextField prenomTextField) {
        this.prenomTextField = prenomTextField;
    }

    public JTextField getCINTextField() {
        return CINTextField;
    }

    public void setCINTextField(JTextField CINTextField) {
        this.CINTextField = CINTextField;
    }

    public JComboBox getSexeComboBox() {
        return sexeComboBox;
    }

    public void setSexeComboBox(JComboBox sexeComboBox) {
        this.sexeComboBox = sexeComboBox;
    }

    public JButton getAddButton() {
        return addButton;
    }

    public void setAddButton(JButton addButton) {
        this.addButton = addButton;
    }

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

    public TitledBorder getTitle() {
        return title;
    }

    public void setTitle(TitledBorder title) {
        this.title = title;
    }
}
