package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.UserData;
import com.codecool.queststore.mappers.UserDataMapper;

import java.sql.SQLException;

public class UserDataRepository extends AbstractRepository<UserData> {
    static {
        ADD_QUERY = "INSERT INTO users(login, first_name, last_name, email, role_id, password) VALUES(?,?,?,?,?,?)";
        EDIT_QUERY = "UPDATE users SET login=?, first_name=?, last_name=?, email=?, role_id=?, password=? WHERE login=?";
        DELETE_QUERY = "DELETE * FROM users WHERE login=?";
        EDIT_QUERY_KEY_INDEX = 7;
    }

    public UserDataRepository() throws PersistenceLayerException {
        super.mapper = new UserDataMapper();
    }

    @Override
    void fillStatementWithColumnsData(UserData entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getLogin());
        super.preparedStatement.setString(2, entity.getFirstName());
        super.preparedStatement.setString(3, entity.getLastName());
        super.preparedStatement.setString(4, entity.getEmail());
        super.preparedStatement.setInt(5, entity.getRole().getId());
        super.preparedStatement.setInt(6, entity.getPassword());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, UserData entity) throws SQLException {
        super.preparedStatement.setString(queryKeyIndex, entity.getLogin());
    }
}
