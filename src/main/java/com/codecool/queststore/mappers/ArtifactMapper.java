package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Artifact;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ArtifactMapper implements Mapper {
    @Override
    public Artifact map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int price = resultSet.getInt("price");

        return new Artifact(id, name, description, price);
    }

    public String mapToJson(Artifact artifact) {
        return String.format("{\"id\": %d, \"name\": \"%s\", \"description\": \"%s\", \"price\": %d}",
                artifact.getId(),
                artifact.getName(),
                artifact.getDescription(),
                artifact.getPrice());
    }

    public String mapToJson(List<Artifact> artifacts) {
        StringBuilder json = new StringBuilder();

        json.append("{\"artifacts\": [");

        int indexOfLastElement = artifacts.size() - 1;
        for (Artifact artifact : artifacts) {
            json.append(mapToJson(artifact));

            if (artifacts.indexOf(artifact) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");

        return json.toString();
    }
}
