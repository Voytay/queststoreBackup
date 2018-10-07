package com.codecool.queststore.repositories;

import com.codecool.queststore.mappers.WalletMapper;
import com.codecool.queststore.entities.Wallet;

import java.sql.SQLException;

public class WalletRepository extends AbstractRepository<Wallet> {
    static {
        ADD_QUERY = "INSERT INTO wallets(owner_login, balance) VALUES(?,?)";
        EDIT_QUERY = "UPDATE wallets SET owner_login=?, balance=? WHERE id=?";
        DELETE_QUERY = "DELETE FROM wallets * WHERE id=?";
        EDIT_QUERY_KEY_INDEX = 3;
    }

    public WalletRepository() throws PersistenceLayerException {
        super.mapper = new WalletMapper();
    }

    @Override
    void fillStatementWithColumnsData(Wallet entity) throws SQLException {
        super.preparedStatement.setString(1, entity.getOwnerLogin());
        super.preparedStatement.setInt(2, entity.getBalance());
    }

    @Override
    void addPrimaryKeyToStatement(int queryKeyIndex, Wallet entity) throws SQLException {
        super.preparedStatement.setInt(queryKeyIndex, entity.getId());
    }
}
