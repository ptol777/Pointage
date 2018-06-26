package mg.misa.dao.interfaces;

import mg.misa.model.journee.Journee;

import java.sql.SQLException;
import java.util.List;

public interface JourneeDAO extends BaseDAO<Journee>{
    public abstract List<Journee> findByEmployeId(String nature, int id);
    public abstract List<Journee> findAll(String nature);
    public void addList(List<Journee> journees)throws SQLException;
}
