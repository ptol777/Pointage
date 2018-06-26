package mg.misa.view.Pointage;

import mg.misa.controller.PointageController;

import javax.swing.*;
import java.awt.*;

public class PointageManuelView extends JPanel {

    private JLabel matriculeLabel;
    private JLabel dateLabel;
    private JLabel heureLabel;
    private JLabel remarqueLabel;
    private JLabel antenneLabel;
    private JTextField matriculeTextField;
    private JTextField dateTextField;
    private JTextField heureTextField;
    private JTextField remarqueTextField;
    private JTextField antenneTextField;
    private JButton pointageButton;
    private JButton cancelButton;
    private JLabel errorLabel;

    private JPanel pointagePanel;

    private PointageController controller;

    public PointageManuelView() {
        controller = PointageController.getPointageController();
        controller.setView(this);
        initComponents();
    }
    
    private void initComponents() {

        matriculeTextField = new JTextField();
        matriculeLabel = new JLabel();
        dateTextField = new JTextField();
        dateLabel = new JLabel();
        heureTextField = new JTextField();
        heureLabel = new JLabel();
        antenneLabel = new JLabel();
        remarqueTextField = new JTextField();
        remarqueLabel = new JLabel();
        antenneTextField = new JTextField();
        errorLabel = new JLabel();
        pointagePanel = new JPanel();
        pointageButton = new JButton();
        cancelButton = new JButton();

        matriculeLabel.setText("ID:");
        dateLabel.setText("Date:");
        heureLabel.setText("Heure:");
        antenneLabel.setText("Antenne:");
        remarqueLabel.setText("Remarque:");
        pointageButton.setText("Pointer manuellement");
        cancelButton.setText("Annuler");
        errorLabel.setText(" ");
        errorLabel.setBackground(Color.red);

        pointageButton.addActionListener(controller);
        cancelButton.addActionListener(controller);

        createLayout();
        this.setLayout(new GridBagLayout());
        this.add(pointagePanel, new GridBagConstraints());
    }

    private void createLayout(){
        GroupLayout layout = new GroupLayout(pointagePanel);
        pointagePanel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(dateLabel)
                                                .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(matriculeLabel)
                                                .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(heureLabel)
                                                .addComponent(heureTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(antenneLabel)
                                                .addComponent(antenneTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(remarqueLabel)
                                                .addComponent(remarqueTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup()
                                        .addComponent(pointageButton)
                                        .addComponent(cancelButton)))
                        .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(errorLabel))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(matriculeLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(dateLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(heureTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(heureLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(antenneTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(antenneLabel))
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                        .addComponent(remarqueTextField, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(remarqueLabel))
                                .addComponent(pointageButton)
                                .addComponent(cancelButton)
                                .addComponent(errorLabel))
        );


    }


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

    public JLabel getHeureLabel() {
        return heureLabel;
    }

    public void setHeureLabel(JLabel heureLabel) {
        this.heureLabel = heureLabel;
    }

    public JLabel getRemarqueLabel() {
        return remarqueLabel;
    }

    public void setRemarqueLabel(JLabel remarqueLabel) {
        this.remarqueLabel = remarqueLabel;
    }

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

    public JTextField getHeureTextField() {
        return heureTextField;
    }

    public void setHeureTextField(JTextField heureTextField) {
        this.heureTextField = heureTextField;
    }

    public JLabel getAntenneLabel() {
        return antenneLabel;
    }

    public void setAntenneLabel(JLabel antenneLabel) {
        this.antenneLabel = antenneLabel;
    }

    public JTextField getAntenneTextField() {
        return antenneTextField;
    }

    public void setAntenneTextField(JTextField antenneTextField) {
        this.antenneTextField = antenneTextField;
    }

    public JTextField getRemarqueTextField() {
        return remarqueTextField;
    }

    public void setRemarqueTextField(JTextField remarqueTextField) {
        this.remarqueTextField = remarqueTextField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }
}
