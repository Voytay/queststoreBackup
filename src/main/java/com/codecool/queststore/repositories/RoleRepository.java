package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.Role;
import com.codecool.queststore.mappers.RoleMapper;

import java.sql.SQLException;

public class RoleRepository extends AbstractRepository<Role> {
    static {
        ADD_QUERY = "INSERT INTO roles(name) VALUES(?)";
        EDIT_QUERY = "UPDATE roles SET name=? WHERE id=?";
        DELETE_QUERY = "DELETE * FROM roles WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 2;
    }

    public RoleRepository() throws PersistenceLayerException {
        super.mapper = new RoleMapper();
    }

    @Override
    void fillStatementWithColumnsData(Role entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Role entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
