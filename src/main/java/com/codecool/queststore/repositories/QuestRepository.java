package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.QuestMapper;
import com.codecool.queststore.entities.Quest;

import java.sql.SQLException;

public class QuestRepository extends AbstractRepository<Quest> {
    static {
        ADD_QUERY = "INSERT INTO quests(name, description, reward) VALUES(?,?,?)";
        EDIT_QUERY = "UPDATE quests SET name=?, description=?, reward=? WHERE id=?";
        DELETE_QUERY = "DELETE FROM artifacts * WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 4;
    }

    public QuestRepository() throws PersistenceLayerException {
        super.mapper = new QuestMapper();
    }

    @Override
    void fillStatementWithColumnsData(Quest entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setString(2, entity.getDescription());
        super.preparedStatement.setInt(3, entity.getReward());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Quest entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
