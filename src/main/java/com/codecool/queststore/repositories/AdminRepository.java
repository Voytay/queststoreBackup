package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.Admin;
import com.codecool.queststore.mappers.AdminMapper;

import java.sql.SQLException;

public class AdminRepository extends AbstractRepository<Admin> {
    static {
        ADD_QUERY = "INSERT INTO admins(login) VALUES(?)";
        EDIT_QUERY = "UPDATE admins SET login=? WHERE login=?";
        DELETE_QUERY = "DELETE FROM admins * WHERE login=?";
        EDIT_QUERY_KEY_INDEX = 2;
    }

    public AdminRepository() throws PersistenceLayerException {
        super.mapper = new AdminMapper();
    }

    @Override
    void fillStatementWithColumnsData(Admin entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getUserData().getLogin());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Admin entity) throws SQLException {
        super.preparedStatement.setString(queryKeyIndex, entity.getUserData().getLogin());
    }
}
