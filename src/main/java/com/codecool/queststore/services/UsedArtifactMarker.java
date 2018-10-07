package com.codecool.queststore.services;

import com.codecool.queststore.criteria.WalletArtifactsById;
import com.codecool.queststore.entities.WalletArtifacts;
import com.codecool.queststore.repositories.*;

public class UsedArtifactMarker {

    private static final RepositoryPool REPOSITORY_POOL;

    static {
        REPOSITORY_POOL = RepositoryPool.getInstance();
    }

    public void mark(int wallet_id, int artifact_id) throws ServiceLayerException {
        try {
            Repository<WalletArtifacts> walletArtifactsRepository = (WalletArtifactsRepository)REPOSITORY_POOL
                    .getRepository(Repositories.WALLET_ARTIFACTS);

            WalletArtifacts walletArtifacts = walletArtifactsRepository
                    .query(new WalletArtifactsById(wallet_id, artifact_id)).get(0);

            walletArtifacts.decrementQuantity();

            if(walletArtifacts.getQuantity() > 0) {
                walletArtifactsRepository.update(walletArtifacts);
            } else {
                walletArtifactsRepository.delete(walletArtifacts);
            }

        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get artifact (id: %d) in wallet (id: %d): %s", artifact_id, wallet_id, e.getMessage()));
        }
    }
}
