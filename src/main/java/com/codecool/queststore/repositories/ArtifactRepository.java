package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.ArtifactMapper;
import com.codecool.queststore.entities.Artifact;

import java.sql.SQLException;

public class ArtifactRepository extends AbstractRepository<Artifact> {
    static {
        ADD_QUERY = "INSERT INTO artifacts(name, description, price) VALUES(?,?,?)";
        EDIT_QUERY = "UPDATE artifacts SET name=?, description=?, price=? WHERE id=?";
        DELETE_QUERY = "DELETE * FROM artifacts WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 4;
    }

    public ArtifactRepository() throws PersistenceLayerException {
        super.mapper = new ArtifactMapper();
    }

    @Override
    void fillStatementWithColumnsData(Artifact entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getName());
        super.preparedStatement.setString(2, entity.getDescription());
        super.preparedStatement.setInt(3, entity.getPrice());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Artifact entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
