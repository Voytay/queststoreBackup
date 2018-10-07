package com.codecool.queststore.repositories;

import com.codecool.queststore.ConnectionProvider;
import com.codecool.queststore.criteria.SqlCriteria;
import com.codecool.queststore.mappers.Mapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;
import java.util.ArrayList;

public abstract class AbstractRepository<E> implements Repository<E> {
    protected final Connection dbConnection;
    protected PreparedStatement preparedStatement;
    protected Mapper<E> mapper;

    protected static String ADD_QUERY;
    protected static String EDIT_QUERY;
    protected static String DELETE_QUERY;

    protected static int EDIT_QUERY_KEY_INDEX;
    private static final int DELETE_QUERY_KEY_INDEX = 1;

    public AbstractRepository() throws PersistenceLayerException {
        this.dbConnection = ConnectionProvider.getConnection();
    }

    abstract void fillStatementWithColumnsData(E entity) throws SQLException;

    abstract void addPrimaryKeyToStatement(int queryKeyIndex, E entity) throws SQLException;

    @Override
    public void add(E entity) throws PersistenceLayerException {
        try {
            this.preparedStatement = this.dbConnection.prepareStatement(ADD_QUERY);
            this.fillStatementWithColumnsData(entity);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't add object to the database");
        }
    }

    @Override
    public void update(E entity) throws PersistenceLayerException {
        try {
            this.preparedStatement = this.dbConnection.prepareStatement(EDIT_QUERY);
            this.fillStatementWithColumnsData(entity);
            this.addPrimaryKeyToStatement(EDIT_QUERY_KEY_INDEX, entity);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't edit object in the database");
        }
    }

    @Override
    public void delete(E entity) throws PersistenceLayerException {
        try {
            this.preparedStatement = this.dbConnection.prepareStatement(DELETE_QUERY);
            this.addPrimaryKeyToStatement(DELETE_QUERY_KEY_INDEX, entity);
            this.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Can't remove object from the database");
        }
    }

    @Override
    public List<E> query(SqlCriteria sqlCriteria) throws PersistenceLayerException {
        List<E> entities = new ArrayList<>();

        try {
            ResultSet resultSet = sqlCriteria.toPreparedStatement().executeQuery();
            while (resultSet.next()) {
                entities.add(mapper.map(resultSet));
            }
            return entities;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new PersistenceLayerException("Cannot perform this query");
        }
    }
}
