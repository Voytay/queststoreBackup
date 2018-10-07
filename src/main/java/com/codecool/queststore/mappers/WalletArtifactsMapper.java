package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.WalletArtifacts;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WalletArtifactsMapper implements Mapper<WalletArtifacts> {

    @Override
    public WalletArtifacts map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int walletId = resultSet.getInt("wallet_id");
        int artifactId = resultSet.getInt("artifact_id");
        int quantity = resultSet.getInt("quantity");

        return new WalletArtifacts(walletId, artifactId, quantity);
    }
}
