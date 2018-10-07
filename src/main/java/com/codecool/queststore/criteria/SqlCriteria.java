package com.codecool.queststore.criteria;

import com.codecool.queststore.repositories.PersistenceLayerException;

import java.sql.PreparedStatement;

public interface SqlCriteria {
    PreparedStatement toPreparedStatement() throws PersistenceLayerException;
}
