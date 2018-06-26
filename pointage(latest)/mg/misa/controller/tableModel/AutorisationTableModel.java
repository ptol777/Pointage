package mg.misa.controller.tableModel;

import mg.misa.manager.AutorisationManager;
import mg.misa.model.Autorisation;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class AutorisationTableModel extends AbstractTableModel {

    private final String[] header = { "Matricule", "Date", "Heure début" , "Heure fin" , "motif"};
    private List<Autorisation> autorisations;
    private AutorisationManager autorisationManager;

    public AutorisationTableModel() {
        super();
        autorisationManager = new AutorisationManager();
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
        return autorisations.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return autorisations.get(rowIndex).getIdEmploye();
            case 1:
                return autorisations.get(rowIndex).getDateInString();
            case 2:
                return autorisations.get(rowIndex).getStart();
            case 3:
                return autorisations.get(rowIndex).getEnd();
            case 4:
                return autorisations.get(rowIndex).getMotif();
            case 5:
                return autorisations.get(rowIndex).getId();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void updateList(){
        autorisations = autorisationManager.getAll();
        //fireTableDataChanged();
    }
}
