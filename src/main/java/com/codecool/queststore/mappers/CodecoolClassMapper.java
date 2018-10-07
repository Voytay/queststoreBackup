package com.codecool.queststore.mappers;

import com.codecool.queststore.entities.CodecoolClass;
import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CodecoolClassMapper implements Mapper<CodecoolClass> {
    @Override
    public CodecoolClass map(ResultSet resultSet) throws SQLException, PersistenceLayerException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");

        return new CodecoolClass(id, name);
    }

    public String mapToJson(CodecoolClass codecoolClass) {
        if(codecoolClass != null) {
            return String.format("{\"id\": %d, \"name\": \"%s\"}",
                    codecoolClass.getId(),
                    codecoolClass.getName());
        } else {
            return null;

        }
    }

    public String mapToJson(List<CodecoolClass> codecoolClasses) {
        StringBuilder json = new StringBuilder();

        json.append("{\"classes\": [");

        int indexOfLastElement = codecoolClasses.size() - 1;
        for (CodecoolClass codecoolClass : codecoolClasses) {
            json.append(mapToJson(codecoolClass));

            if (codecoolClasses.indexOf(codecoolClass) != indexOfLastElement) {
                json.append(",");
            }
        }

        json.append("]}");

        return json.toString();
    }
}
