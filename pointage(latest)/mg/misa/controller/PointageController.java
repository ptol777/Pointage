package mg.misa.controller;

import mg.misa.controller.tableModel.DureeTableModel;
import mg.misa.controller.tableModel.JourneeTableModel;
import mg.misa.manager.PointageManager;
import mg.misa.model.exceptions.MyDateTimeException;
import mg.misa.view.Pointage.PointageAutoView;
import mg.misa.view.Pointage.PointageManuelView;
import mg.misa.view.StatistiqueView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;

public class PointageController implements ActionListener {

    private static PointageController pointageController;
    private PointageController(){
        pointageManager = new PointageManager();
    }
    public static PointageController getPointageController(){
        if(pointageController == null){
            pointageController = new PointageController();
        }
        return  pointageController;
    }

    private PointageAutoView pointageAutoView;
    private PointageManuelView pointageManuelView;
    private StatistiqueView statistiqueView;

    private PointageManager pointageManager;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Pointer":
                pointerAuto();
                break;
            case "Pointer manuellement":
                pointerManuel();
                break;
            case "Annuler":
                annuler();
                break;
        }
    }

    private void pointerAuto(){
        setErrorAutoMessage(" ");
        if(!checkAutoTextFieldFilled()){
            setErrorAutoMessage("Veuillez remplir le champs");
        }else{
            String identity = pointageAutoView.getMatriculeTextField().getText();
            try{
                pointageManager.pointer(identity);
                clearAutoTextField();
                refreshTable();
            }catch (NullPointerException exc){
                exc.printStackTrace();
                setErrorAutoMessage("Cet employe n'existe pas");
            }
        }
    }

    private  void pointerManuel(){
        if(!checkManuelTextFieldFilled()){
            setErrorManuelMessage("Veuillez remplir tous les champs");
        }else{
            String identity = pointageManuelView.getMatriculeTextField().getText();
            String date = pointageManuelView.getDateTextField().getText();
            String heure = pointageManuelView.getHeureTextField().getText();
            String antenne = pointageManuelView.getAntenneTextField().getText();
            String remarque = pointageManuelView.getRemarqueTextField().getText();

            try{
                pointageManager.pointer(identity,date,heure,antenne,remarque);
                clearManuelTextField();
                refreshTable();
            }catch (NullPointerException exc){
                setErrorManuelMessage("Cet employe n'existe pas");
                exc.printStackTrace();
            }catch (DateTimeParseException exce){
                setErrorManuelMessage("Format de la date invalide");
            }catch (MyDateTimeException excep){
                setErrorManuelMessage(excep.getMessage());
            }
        }
    }

    private void annuler(){
        clearAutoTextField();
        clearManuelTextField();
    }

    private boolean checkManuelTextFieldFilled(){
        boolean result = true;
        if(pointageManuelView.getMatriculeTextField().getText().equals("") ||
                pointageManuelView.getDateTextField().getText().equals("") ||
                pointageManuelView.getHeureTextField().getText().equals("") ||
                pointageManuelView.getAntenneTextField().getText().equals("")){
            result = false;
        }
        return result;
    }

    private boolean checkAutoTextFieldFilled(){
        boolean result = true;
        if(pointageAutoView.getMatriculeTextField().getText().equals("")){
            result = false;
        }
        return result;
    }

    private void setErrorManuelMessage(String message){
        pointageManuelView.getErrorLabel().setText(message);
    }

    private void setErrorAutoMessage(String message){
        pointageAutoView.getErrorLabel().setText(message);
    }

    private void clearAutoTextField(){
        pointageAutoView.getMatriculeTextField().setText("");
    }

    private void clearManuelTextField(){
        pointageManuelView.getMatriculeTextField().setText("");
        pointageManuelView.getDateTextField().setText("");
        pointageManuelView.getHeureTextField().setText("");
        pointageManuelView.getAntenneTextField().setText("");
        pointageManuelView.getRemarqueTextField().setText("");
    }

    public void setView(PointageAutoView pointageAutoView) {
        this.pointageAutoView = pointageAutoView;
    }

    public void setView(PointageManuelView pointageManuelView) {
        this.pointageManuelView = pointageManuelView;
    }

    public void setView(StatistiqueView statistiqueView){
        this.statistiqueView = statistiqueView;
    }

    public void refreshTable(){
        JourneeTableModel absenceTableModel = (JourneeTableModel) statistiqueView.getAbsenceTable().getModel();
        DureeTableModel retardTableModel = (DureeTableModel) statistiqueView.getRetardTable().getModel();
        DureeTableModel suppTableModel = (DureeTableModel) statistiqueView.getSuppTable().getModel();
        DureeTableModel departTableModel = (DureeTableModel) statistiqueView.getDepartTable().getModel();

        absenceTableModel.updateList("absence");
        retardTableModel.updateList("retard");
        suppTableModel.updateList("supplementaire");
        departTableModel.updateList("deprat");
    }
}
