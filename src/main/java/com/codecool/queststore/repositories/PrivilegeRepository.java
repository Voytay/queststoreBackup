package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.Privilege;
import com.codecool.queststore.mappers.PrivilegeMapper;
import java.sql.SQLException;

public class PrivilegeRepository extends AbstractRepository<Privilege> {
    static {
        ADD_QUERY = "INSERT INTO privileges(name) VALUES(?)";
        EDIT_QUERY = "UPDATE privileges SET name=? WHERE id=?";
        DELETE_QUERY = "DELETE * FROM privileges WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 2;
    }

    public PrivilegeRepository() throws PersistenceLayerException {
        super.mapper = new PrivilegeMapper();
    }

    @Override
    void fillStatementWithColumnsData(Privilege entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Privilege entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
