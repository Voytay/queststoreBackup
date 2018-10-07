package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.WalletArtifactsMapper;
import com.codecool.queststore.entities.WalletArtifacts;

import java.sql.SQLException;

public class WalletArtifactsRepository extends AbstractRepository<WalletArtifacts> {
    static {
        ADD_QUERY = "INSERT INTO wallet_artifacts(wallet_id, artifact_id, quantity) VALUES(?,?,?)";
        EDIT_QUERY = "UPDATE wallet_artifacts SET wallet_id=?, artifact_id=?, quantity=? WHERE wallet_id=? AND artifact_id=?";
        DELETE_QUERY = "DELETE FROM wallet_artifacts * WHERE wallet_id=? AND artifact_id=?";
        EDIT_QUERY_KEY_INDEX = 4;
    }

    public WalletArtifactsRepository() throws PersistenceLayerException {
        super.mapper = new WalletArtifactsMapper();
    }

    @Override
    void fillStatementWithColumnsData(WalletArtifacts entity) throws SQLException {
        super.preparedStatement.setInt(1, entity.getWalletId());
        super.preparedStatement.setInt(2, entity.getArtifactId());
        super.preparedStatement.setInt(3, entity.getQuantity());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, WalletArtifacts entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getWalletId());
        super.preparedStatement.setInt(queryKeyIndex+1, entity.getArtifactId());
    }
}
