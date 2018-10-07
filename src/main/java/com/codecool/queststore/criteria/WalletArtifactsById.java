package com.codecool.queststore.criteria;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class WalletArtifactsById implements SqlCriteria {
    private final String QUERY = "SELECT * FROM wallet_artifacts WHERE wallet_id=? AND artifact_id=?";
    private final int walletId;
    private final int artifactId;

    public WalletArtifactsById(int walletId, int artifactId) {
        this.walletId = walletId;
        this.artifactId = artifactId;
    }

    @Override
    public PreparedStatement toPreparedStatement() throws PersistenceLayerException {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setInt(1, this.walletId);
            preparedStatement.setInt(2, this.artifactId);

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't perform this query due to " +
                    "exception occurance when creating PreparedStatement");
        }
    }
}
