package mg.misa.dao.interfaces;

import mg.misa.model.employe.Employe;

public interface EmployeDAO extends BaseDAO<Employe>{
    public boolean getHours(Employe employe);
    public boolean addHours(Employe employe);
    public boolean updateHours(Employe employe);
    public Employe findById(int id);
    public Employe findByCIN(String CIN);
    public Employe findByNom(String nom,String prenom);

}
