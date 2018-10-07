package com.codecool.queststore.repositories;

import com.codecool.queststore.entities.Level;
import com.codecool.queststore.mappers.LevelMapper;

import java.sql.SQLException;

public class LevelRepository extends AbstractRepository<Level> {
    static {
        ADD_QUERY = "INSERT INTO levels(name, threshold) VALUES(?,?)";
        EDIT_QUERY = "UPDATE levels SET name=?, threshold=? WHERE id=?";
        DELETE_QUERY = "DELETE FROM levels * WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 3;
    }

    public LevelRepository() throws PersistenceLayerException {
        super.mapper = new LevelMapper();
    }

    @Override
    void fillStatementWithColumnsData(Level entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setInt(2, entity.getThreshold());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Level entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
