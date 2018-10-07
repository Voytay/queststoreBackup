package com.codecool.queststore.criteria;


import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtifactByWalletId implements SqlCriteria {
    private final String QUERY = "SELECT * FROM artifacts WHERE id IN" +
                                 "(SELECT artifact_id FROM wallet_artifacts WHERE wallet_id=?)";
    private final int id;

    public ArtifactByWalletId(int id) {
        this.id = id;
    }

    @Override
    public PreparedStatement toPreparedStatement() throws PersistenceLayerException {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setInt(1, this.id);

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't perform this query due to " +
                    "exception occurance when creating PreparedStatement");
        }
    }
}
