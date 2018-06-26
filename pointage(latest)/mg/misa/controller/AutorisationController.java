package mg.misa.controller;

import mg.misa.controller.tableModel.AutorisationTableModel;
import mg.misa.manager.AutorisationManager;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.view.AutorisationView;

import javax.swing.event.ListSelectionEvent;
import java.sql.SQLException;
import java.time.format.DateTimeParseException;

public class AutorisationController extends TableController {

    int id;

    private static AutorisationController autorisationController;
    private AutorisationController(){
        autorisationManager = new AutorisationManager();

    }
    public static AutorisationController getAutorisationController(){
        if(autorisationController == null){
            autorisationController = new AutorisationController();
        }
        return  autorisationController;
    }

    private AutorisationManager autorisationManager;

    protected void setErrorLabel(String message){
        view.getErrorLabel().setText(message);
    }

    protected void moveToTextFields(ListSelectionEvent e){
        int rowNumber = getView().getTable().getSelectedRow();
        AutorisationTableModel tableModel = (AutorisationTableModel) getView().getTable().getModel();
        getView().getMatriculeTextField().setText(tableModel.getValueAt(rowNumber,0).toString());
        getView().getDateTextField().setText(tableModel.getValueAt(rowNumber,1).toString());
        getView().getStartTextField().setText(tableModel.getValueAt(rowNumber,2).toString());
        getView().getEndTextField().setText(tableModel.getValueAt(rowNumber,3).toString());
        getView().getMotifTextField().setText(tableModel.getValueAt(rowNumber,4).toString());

        id = Integer.parseInt(tableModel.getValueAt(rowNumber,5).toString());
    }

    protected boolean checkTextFieldFilled(){
        boolean result = true;
        if(getView().getDateTextField().getText().equals("")||
                getView().getStartTextField().getText().equals("")||
                getView().getMotifTextField().getText().equals("")||
                getView().getMatriculeTextField().getText().equals("")){
            result = false;
        }
        return result;
    }

    protected void clearTextField(){
        getView().getMatriculeTextField().setText(" ");
        getView().getDateTextField().setText(" ");
        getView().getStartTextField().setText(" ");
        getView().getMotifTextField().setText(" ");
        getView().getEndTextField().setText(" ");
    }

    protected void updateData(){
        String idEmploye = getView().getMatriculeTextField().getText();
        String date = getView().getDateTextField().getText();
        String start = getView().getStartTextField().getText();
        String end = getView().getEndTextField().getText();
        String motif = getView().getMotifTextField().getText();

        try{
            autorisationManager.updateAutorisation(id,date,start,end,motif);
            refreshTable();
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un conge");
        }catch (MyDateTimeException exc){
            setErrorLabel(exc.getMessage());
        }catch (DateTimeParseException exce){
            setErrorLabel("Format de la date invalide");
            exce.printStackTrace();
        }
    }

    protected void insert(){
        String idEmploye = getView().getMatriculeTextField().getText();
        String nom = getView().getDateTextField().getText();
        String prenom = getView().getStartTextField().getText();
        String sexe = getView().getEndTextField().getText();
        String Motif = getView().getMotifTextField().getText();
        try{
            autorisationManager.createAutorisation(idEmploye,nom,prenom,sexe,Motif);
            refreshTable();
        }catch (SQLException e){
            setErrorLabel("Duplication d'information sur un autorisation");
        }catch (NumberFormatException ex){
            setErrorLabel("Format matricule invalide");
        }catch (DateTimeParseException exception){
            setErrorLabel("Format de la date invalide");
        }catch (MyDateTimeException e){
            setErrorLabel(e.getMessage());
        }catch (NullPointerException exce){
            setErrorLabel("Cet Employe n'existe pas");
        }
    }

    public void refreshTable(){
        AutorisationTableModel tableModel = (AutorisationTableModel) getView().getTable().getModel();
        tableModel.updateList();
        getView().getTable().repaint();
        getView().getTable().revalidate();
    }

    public void setView(AutorisationView autorisationView) {
        this.view = autorisationView;
    }

    public AutorisationView getView(){
        return (AutorisationView)view;
    }
}
