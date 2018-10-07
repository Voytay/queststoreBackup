package com.codecool.queststore.criteria;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CodecoolClassByMentorLogin implements SqlCriteria {
    private final String QUERY = "SELECT * FROM classes WHERE id IN (SELECT class_id FROM class_mentors WHERE mentor_login=?)";
    private final String login;

    public CodecoolClassByMentorLogin(String login) {
        this.login = login;
    }

    @Override
    public PreparedStatement toPreparedStatement() throws PersistenceLayerException {
        try {
            Connection connection = ConnectionProvider.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY);

            preparedStatement.setString(1, this.login);

            return preparedStatement;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't perform this query due to " +
                    "exception occurance when creating PreparedStatement");
        }
    }
}
