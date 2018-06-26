package mg.misa.dao.interfaces;

import mg.misa.model.pointage.Pointage;

public interface PointageDAO extends BaseDAO<Pointage>{
    public Pointage findLastByEmployeId(int id);
}
