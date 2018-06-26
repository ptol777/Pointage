package mg.misa.view.Pointage;

import mg.misa.controller.PointageController;

import javax.swing.*;
import java.awt.*;

public class PointageAutoView extends JPanel {

    private JPanel pointagePanel;
    private JButton pointageButton;
    private JButton cancelButton;
    private JLabel matriculeLabel;
    private JTextField matriculeTextField;
    private JLabel errorLabel;

    PointageController controller;

    public PointageAutoView() {
        controller = PointageController.getPointageController();
        controller.setView(this);
        initComponents();
    }
    
    private void initComponents() {

        matriculeTextField = new JTextField();
        matriculeLabel = new JLabel();
        pointageButton = new JButton();
        cancelButton = new JButton();
        errorLabel = new JLabel();
        pointagePanel = new JPanel();

        matriculeLabel.setText("ID:");
        pointageButton.setText("Pointer");
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
        layout.setAutoCreateContainerGaps(true);
        layout.setAutoCreateGaps(true);
        pointagePanel.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(matriculeLabel)
                                .addComponent(matriculeTextField, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE))
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

    public JTextField getMatriculeTextField() {
        return matriculeTextField;
    }

    public void setMatriculeTextField(JTextField matriculeTextField) {
        this.matriculeTextField = matriculeTextField;
    }

    public JLabel getErrorLabel() {
        return errorLabel;
    }

    public void setErrorLabel(JLabel errorLabel) {
        this.errorLabel = errorLabel;
    }
}
