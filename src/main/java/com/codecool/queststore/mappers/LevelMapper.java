package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Level;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LevelMapper implements Mapper<Level> {
    @Override
    public Level map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        int threshold = resultSet.getInt("threshold");

        return new Level(id, name, threshold);
    }

    public String mapToJson(Level level) {
        return String.format("{\"id\": %d, \"name\": \"%s\", \"threshold\": %d}",
                level.getId(),
                level.getName(),
                level.getThreshold());
    }

    public String mapToJson(List<Level> levels) {
        StringBuilder json = new StringBuilder();

        json.append("{\"levels\": [");

        int indexOfLastElement = levels.size() - 1;
        for (Level level : levels) {
            json.append(mapToJson(level));

            if (levels.indexOf(level) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");

        return json.toString();
    }
}
