package mg.misa.controller;

import mg.misa.controller.tableModel.EmployeTableModel;
import mg.misa.manager.EmployeManager;
import mg.misa.model.exceptions.MyInvalidFormatException;
import mg.misa.view.EmployeView;

import javax.swing.event.ListSelectionEvent;
import java.sql.SQLException;

public class EmployeController  extends TableController{

    private static EmployeController employeController;
    private EmployeController(){
        employeManager = new EmployeManager();

    }
    public static EmployeController getEmployeController(){
        if(employeController == null){
            employeController = new EmployeController();
        }
        return  employeController;
    }

    private EmployeManager employeManager;

    protected void setErrorLabel(String message){
        view.getErrorLabel().setText(message);
    }

    protected void moveToTextFields(ListSelectionEvent e){
        int rowNumber = getView().getTable().getSelectedRow();
        EmployeTableModel tableModel = (EmployeTableModel) getView().getTable().getModel();
        getView().getMatriculeTextField().setText(tableModel.getValueAt(rowNumber,0).toString());
        getView().getNomTextField().setText(tableModel.getValueAt(rowNumber,1).toString());
        getView().getPrenomTextField().setText(tableModel.getValueAt(rowNumber,2).toString());
        getView().getSexeComboBox().setSelectedItem(tableModel.getValueAt(rowNumber,3).toString());
        getView().getCINTextField().setText(tableModel.getValueAt(rowNumber,4).toString());
    }

    protected boolean checkTextFieldFilled(){
        boolean result = true;
        if(getView().getNomTextField().getText().equals("")||
                getView().getPrenomTextField().getText().equals("")||
                getView().getCINTextField().getText().equals("")||
                getView().getMatriculeTextField().getText().equals("")){
            result = false;
        }
        return result;
    }

    protected void clearTextField(){
        getView().getMatriculeTextField().setText(" ");
        getView().getNomTextField().setText(" ");
        getView().getPrenomTextField().setText(" ");
        getView().getCINTextField().setText(" ");
        getView().getSexeComboBox().setSelectedItem("M");
    }

    protected void updateData(){
        String idEmploye = getView().getMatriculeTextField().getText();
        String nom = getView().getNomTextField().getText();
        String prenom = getView().getPrenomTextField().getText();
        String sexe = getView().getSexeComboBox().getSelectedItem().toString();
        System.out.println(sexe);
        String CIN = getView().getCINTextField().getText();

        try{
            employeManager.updateEmploye(idEmploye,nom,prenom,sexe,CIN);
            refreshTable();
        }catch (MyInvalidFormatException e){
            setErrorLabel(e.getMessage());
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un employe");
        }
    }

    protected void insert(){
        String idEmploye = getView().getMatriculeTextField().getText();
        String nom = getView().getNomTextField().getText();
        String prenom = getView().getPrenomTextField().getText();
        String sexe = getView().getSexeComboBox().getSelectedItem().toString();
        String CIN = getView().getCINTextField().getText();
        try{
            employeManager.createEmploye(idEmploye,nom,prenom,sexe,CIN);
            refreshTable();
        }catch (MyInvalidFormatException e){
            setErrorLabel(e.getMessage());
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un employe");
        }catch (NumberFormatException ex){
            setErrorLabel("Format matricule invalide");
        }
    }

    public void refreshTable(){
        EmployeTableModel tableModel = (EmployeTableModel) getView().getTable().getModel();
        tableModel.updateList();
        getView().getTable().repaint();
        getView().getTable().revalidate();
    }

    public void setView(EmployeView employeView) {
        this.view = employeView;
    }

    public EmployeView getView(){
        return (EmployeView)view;
    }
}
