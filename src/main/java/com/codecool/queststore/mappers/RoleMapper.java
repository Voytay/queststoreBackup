package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Role;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleMapper implements Mapper<Role> {
    @Override
    public Role map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new Role(id, name);
    }
}
