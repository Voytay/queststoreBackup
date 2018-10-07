package com.codecool.queststore.services;

import com.codecool.queststore.criteria.SqlCriteria;
import com.codecool.queststore.criteria.WalletById;
import com.codecool.queststore.entities.Artifact;
import com.codecool.queststore.entities.Wallet;
import com.codecool.queststore.repositories.*;

public class GetWallet {

    private static final RepositoryPool repositoryPool;

    static {
        repositoryPool = RepositoryPool.getInstance();
    }

    public Wallet getWallet(int id) throws ServiceLayerException {
        try {
            Repository<Wallet> walletRepository = (WalletRepository) repositoryPool.getRepository(Repositories.WALLET);
            SqlCriteria sqlCriteria = new WalletById(id);
            return walletRepository.query(sqlCriteria).get(0);

        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get wallet (id: %d): %s", id, e.getMessage()));
        }

    }




}

