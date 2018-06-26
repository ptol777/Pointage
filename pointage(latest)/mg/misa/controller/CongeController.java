package mg.misa.controller;

import mg.misa.controller.tableModel.JourneeTableModel;
import mg.misa.manager.JourneeManager;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.view.CongeView;

import javax.swing.event.ListSelectionEvent;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class CongeController extends TableController {

    private int id;
    private static CongeController congeController;
    private CongeController(){
        journeeManager = new JourneeManager();

    }
    public static CongeController getCongeController(){
        if(congeController == null){
            congeController = new CongeController();
        }
        return  congeController;
    }

    private JourneeManager journeeManager;
    
    public void setView(CongeView view){
        this.view = view;
    }
    
    public CongeView getView(){
        return (CongeView)view;
    }

    protected void setErrorLabel(String message){
        view.getErrorLabel().setText(message);
    }

    protected void moveToTextFields(ListSelectionEvent e){
        int rowNumber = getView().getTable().getSelectedRow();
        JourneeTableModel tableModel = (JourneeTableModel) getView().getTable().getModel();
        getView().getMatriculeTextField().setText(tableModel.getValueAt(rowNumber,0).toString());
        getView().getDateTextField().setText(tableModel.getValueAt(rowNumber,1).toString());
        getView().getTypeComboBox().setSelectedItem(tableModel.getValueAt(rowNumber,2).toString());
        id = Integer.parseInt(tableModel.getValueAt(rowNumber,3).toString());
    }

    protected boolean checkTextFieldFilled(){
        boolean result = true;
        if(getView().getDateTextField().getText().equals("")||
                getView().getMatriculeTextField().getText().equals("")){
            result = false;
        }
        return result;
    }

    protected void clearTextField(){
        getView().getMatriculeTextField().setText(" ");
        getView().getDateTextField().setText(" ");
        getView().getDateFinTextField().setText(" ");
        getView().getTypeComboBox().setSelectedItem("FULL");
    }

    protected void updateData(){
        String date = getView().getDateTextField().getText();
        String type = getView().getTypeComboBox().getSelectedItem().toString();

        try{
            journeeManager.updateJournee(id,date,type);
            refreshTable();
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un conge");
        }catch (MyDateTimeException exc){
            setErrorLabel(exc.getMessage());
        }catch (DateTimeParseException exce){
            setErrorLabel("Format de la date invalide");
        }
    }

    protected void insert(){
        String idEmploye = getView().getMatriculeTextField().getText();
        String date = getView().getDateTextField().getText();
        String dateFin = getView().getDateFinTextField().getText();
        String type = getView().getTypeComboBox().getSelectedItem().toString();
        try{
            if(dateFin.equals("")) {
                journeeManager.createConge(idEmploye, date, date, type);
            }else{
                journeeManager.createConge(idEmploye, date, dateFin, type);
            }
            refreshTable();
        }catch (MyDateTimeException e){
            setErrorLabel(e.getMessage());
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un conge");
        }catch (NumberFormatException ex){
            setErrorLabel("Format matricule invalide");
        }catch(NullPointerException excep){
            setErrorLabel("Cet employe n'existe pas");
        }catch (DateTimeParseException excpet){
            setErrorLabel("Format date invalide");
        }
    }

    @Override
    protected void toModify(){
        view.getTitle().setTitle("MODIFIER");
        view.getMatriculeLabel().setVisible(false);
        view.getMatriculeTextField().setVisible(false);
        getView().getDateFinTextField().setVisible(false);
        getView().getDateFinLabel().setVisible(false);
        view.getAddButton().setVisible(false);
        view.getUpdateButton().setVisible(true);
        view.getErrorLabel().setText(" ");
        checkTextFieldFilled();
    }

    @Override
    protected void toAdd(){
        view.getTitle().setTitle("AJOUTER");
        view.getErrorLabel().setText(" ");
        view.getMatriculeLabel().setVisible(true);
        view.getMatriculeTextField().setVisible(true);
        getView().getDateFinTextField().setVisible(true);
        getView().getDateFinLabel().setVisible(true);
        view.getAddButton().setVisible(true);
        view.getUpdateButton().setVisible(false);
        view.getErrorLabel().setText(" ");
        clearTextField();
    }

    public void refreshTable(){
        JourneeTableModel tableModel = (JourneeTableModel) getView().getTable().getModel();
        tableModel.updateList("conge");
        getView().getTable().repaint();
        getView().getTable().revalidate();
    }
}
