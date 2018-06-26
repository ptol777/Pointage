package mg.misa.controller.tableModel;

import mg.misa.manager.EmployeManager;
import mg.misa.model.employe.Employe;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class EmployeTableModel extends AbstractTableModel {

    private final String[] header = { "Matricule", "Nom", "Prenom" , "Sexe" , "CIN"};
    private List<Employe> employes;
    private EmployeManager employeManager;

    public EmployeTableModel() {
        super();
        employeManager = new EmployeManager();
        updateList();
    }

    @Override
    public int getColumnCount() {
        return header.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return header[columnIndex];
    }

    @Override
    public int getRowCount() {
        return employes.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return employes.get(rowIndex).getId();
            case 1:
                return employes.get(rowIndex).getNom();
            case 2:
                return employes.get(rowIndex).getPrenom();
            case 3:
                return employes.get(rowIndex).getSexe();
            case 4:
                return employes.get(rowIndex).getCIN();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void updateList(){
        employes = employeManager.getAll();
        //fireTableDataChanged();
    }
}
