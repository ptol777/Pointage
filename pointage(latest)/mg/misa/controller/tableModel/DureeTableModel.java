package mg.misa.controller.tableModel;

import mg.misa.manager.DureeManager;
import mg.misa.model.duree.Duree;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class DureeTableModel extends AbstractTableModel {

    private final String[] header = { "Matricule", "Date", "Total"};
    private List<Duree> durees;
    private DureeManager dureeManager;

    public DureeTableModel(String type) {
        super();
        dureeManager = new DureeManager();
        updateList(type);
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
        return durees.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return durees.get(rowIndex).getIdEmploye();
            case 1:
                return durees.get(rowIndex).getDateInString();
            case 2:
                return durees.get(rowIndex).getTotalInMinutes();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void updateList(String type){
        fireTableDataChanged();
        durees = dureeManager.getAll(type);
    }

}
