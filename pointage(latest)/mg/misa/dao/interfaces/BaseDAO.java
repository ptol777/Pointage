package mg.misa.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BaseDAO<T> {

    public abstract boolean add(T object)throws SQLException;
    public abstract boolean update(T object)throws SQLException;
    public abstract boolean delete(T object);
    public abstract List<T> findByEmployeId(int id);
    public abstract List<T> findAll();
}
