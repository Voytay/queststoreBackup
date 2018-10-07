package com.codecool.queststore.mappers;

import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Mapper<E> {

    E map(ResultSet resultSet) throws SQLException, PersistenceLayerException;
}
