package mg.misa.dao.interfaces;

import mg.misa.model.duree.Duree;

import java.util.List;

public interface DureeDAO extends BaseDAO<Duree>{
    public abstract List<Duree> findByEmployeId(String type, int id);
    public abstract List<Duree> findAll(String type);

}
