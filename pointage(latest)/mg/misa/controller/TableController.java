package mg.misa.controller;

import mg.misa.manager.EmployeManager;
import mg.misa.model.exceptions.MyInvalidFormatException;
import mg.misa.view.EmployeView;
import mg.misa.view.TableView;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class TableController implements ActionListener, ListSelectionListener {

    protected TableView view;

    @Override
    public void actionPerformed(ActionEvent e) {
        switch(e.getActionCommand()){
            case "Ajouter":
                ajouter();
                break;
            case "Modifier":
                modifier();
                break;
            case "Annuler":
                annuler();
                break;
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        toModify();
        moveToTextFields(e);
    }

    protected void toModify(){
        view.getTitle().setTitle("MODIFIER");
        view.getMatriculeLabel().setVisible(false);
        view.getMatriculeTextField().setVisible(false);
        view.getAddButton().setVisible(false);
        view.getUpdateButton().setVisible(true);
        view.getErrorLabel().setText(" ");
        checkTextFieldFilled();
    }

    protected void toAdd(){
        view.getTitle().setTitle("AJOUTER");
        view.getErrorLabel().setText(" ");
        view.getMatriculeLabel().setVisible(true);
        view.getMatriculeTextField().setVisible(true);
        view.getAddButton().setVisible(true);
        view.getUpdateButton().setVisible(false);
        view.getErrorLabel().setText(" ");
        clearTextField();
    }

    protected void setErrorLabel(String message){
        view.getErrorLabel().setText(message);
    }

    protected abstract void moveToTextFields(ListSelectionEvent e);

    protected abstract boolean checkTextFieldFilled();

    protected abstract void clearTextField();

    private void modifier(){
        setErrorLabel(" ");
        if(!checkTextFieldFilled()){
            setErrorLabel("Veuillez remplir tous les champs");
        }else{
            updateData();
            if(view.getErrorLabel().getText().equals(" ")){
                toAdd();
            }
        }
    }

    protected abstract void updateData();

    private void ajouter(){
        setErrorLabel(" ");
        if(!checkTextFieldFilled()){
            setErrorLabel("Veuillez remplir tous les champs");
        }else{
            insert();
            if(view.getErrorLabel().getText().equals(" ")){
                toAdd();
            }
        }

    }

    protected abstract void insert();

    protected abstract void refreshTable();

    private void annuler(){
        toAdd();
    }
}
