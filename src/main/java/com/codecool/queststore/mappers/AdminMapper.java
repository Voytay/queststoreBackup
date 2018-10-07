package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Admin;
import com.codecool.queststore.entities.UserData;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminMapper implements Mapper<Admin> {
    @Override
    public Admin map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        String login = resultSet.getString("login");

        return new Admin(new UserData(login));
    }
}
