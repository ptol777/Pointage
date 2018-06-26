package mg.misa.dao.interfaces;

import mg.misa.model.Autorisation;

import java.time.LocalDate;
import java.util.List;

public interface AutorisationDAO extends BaseDAO<Autorisation>{
    public List<Autorisation> findByEmployeIdDate(int id, LocalDate date);

}