package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.MentorMapper;
import com.codecool.queststore.entities.Mentor;

import java.sql.SQLException;

public class MentorRepository extends AbstractRepository<Mentor> {
    static {
        ADD_QUERY = "INSERT INTO mentors(login) VALUES(?)";
        EDIT_QUERY = "UPDATE mentors SET login=? WHERE login=?";
        DELETE_QUERY = "DELETE FROM mentors * WHERE login=?";
        EDIT_QUERY_KEY_INDEX = 2;
    }

    public MentorRepository() throws PersistenceLayerException {
        super.mapper = new MentorMapper();
    }

    @Override
    void fillStatementWithColumnsData(Mentor entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getUserData().getLogin());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Mentor entity) throws SQLException {
        super.preparedStatement.setString(queryKeyIndex, entity.getUserData().getLogin());
    }
}