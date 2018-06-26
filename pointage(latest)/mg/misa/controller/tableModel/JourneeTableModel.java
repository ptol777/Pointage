package mg.misa.controller.tableModel;

import mg.misa.manager.JourneeManager;
import mg.misa.model.journee.Journee;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class JourneeTableModel extends AbstractTableModel {
    private final String[] header = { "Matricule", "Date", "Type"};
    private List<Journee> journees;
    private JourneeManager journeeManager;

    public JourneeTableModel(String type) {
        super();
        journeeManager = new JourneeManager();
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
        return journees.size();
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return journees.get(rowIndex).getIdEmploye();
            case 1:
                return journees.get(rowIndex).getDateInString();
            case 2:
                return journees.get(rowIndex).getType();
            case 3:
                return journees.get(rowIndex).getId();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void updateList(String type){
        journees = journeeManager.getAll(type);
    }

}
