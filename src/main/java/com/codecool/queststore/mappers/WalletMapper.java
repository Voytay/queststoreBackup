package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Artifact;
import com.codecool.queststore.entities.Wallet;
import com.codecool.queststore.repositories.PersistenceLayerException;
import com.codecool.queststore.criteria.ArtifactByWalletId;
import com.codecool.queststore.criteria.SqlCriteria;
import com.codecool.queststore.repositories.PersistenceLayerException;
import com.codecool.queststore.repositories.Repositories;
import com.codecool.queststore.repositories.Repository;
import com.codecool.queststore.repositories.RepositoryPool;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WalletMapper implements Mapper {
    private static final RepositoryPool REPOSITORY_POOL = RepositoryPool.getInstance();

    @Override
    public Wallet map(ResultSet resultSet) throws SQLException, PersistenceLayerException {

        int id = resultSet.getInt("id");
        String ownerLogin = resultSet.getString("owner_login");
        int balance = resultSet.getInt("balance");

        Repository<Artifact> artifactRepository = REPOSITORY_POOL.getRepository(Repositories.ARTIFACT);
        SqlCriteria getArtifactsByWalletId = new ArtifactByWalletId(id);

        List<Artifact> artifacts = artifactRepository.query(getArtifactsByWalletId);

        return new Wallet(id, ownerLogin, balance, artifacts);
    }

    public String mapToJson(Wallet wallet) {

        List<Artifact> artifacts = wallet.getArtifactList();
        ArtifactMapper artifactMapper = new ArtifactMapper();

        String artifactsJson = artifactMapper.mapToJson(artifacts);
        artifactsJson = artifactsJson.substring(1, artifactsJson.length()-1);

        return String.format("{\"id\": %d, \"ownerLogin\": \"%s\", \"balance\": %d, %s}",
                wallet.getId(),
                wallet.getOwnerLogin(),
                wallet.getBalance(),
                artifactsJson);
    }

    public String mapToJson(List<Wallet> wallets) {
        StringBuilder json = new StringBuilder();

        json.append("{\"wallets\": [");

        int indexOfLastElement = wallets.size() - 1;
        for (Wallet wallet: wallets) {
            json.append(mapToJson(wallet));

            if (wallets.indexOf(wallet) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");

        return json.toString();
    }
}
