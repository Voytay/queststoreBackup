package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.Quest;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class QuestMapper implements Mapper {
    @Override
    public Quest map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        int reward = resultSet.getInt("reward");

        return new Quest(id, name, description, reward);
    }

    public String mapToJson(Quest quest) {
        return String.format("{\"id\": %d, \"name\": \"%s\", \"description\": \"%s\", \"reward\": %d}",
                quest.getId(),
                quest.getName(),
                quest.getDescription(),
                quest.getReward());
    }

    public String mapToJson(List<Quest> quests) {
        StringBuilder json = new StringBuilder();

        json.append("{\"quests\": [");

        int indexOfLastElement = quests.size() - 1;
        for (Quest quest: quests) {
            json.append(mapToJson(quest));

            if (quests.indexOf(quest) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");

        return json.toString();
    }
}
