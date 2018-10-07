package com.codecool.queststore.services;

import com.codecool.queststore.criteria.AllQuests;
import com.codecool.queststore.criteria.QuestById;
import com.codecool.queststore.entities.Quest;
import com.codecool.queststore.repositories.*;

import java.util.List;

public class QuestManager {
    private static final RepositoryPool REPOSITORY_POOL;

    static {
        REPOSITORY_POOL = RepositoryPool.getInstance();
    }

    public List<Quest> get(int id) throws ServiceLayerException {
        try {
            Repository<Quest> questRepository = (QuestRepository) REPOSITORY_POOL
                    .getRepository(Repositories.QUEST);
            return questRepository.query(new QuestById(id));
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get quest (id: %d): %s", id, e.getMessage()));
        }
    }

    public List<Quest> getAll() throws ServiceLayerException {

        try {

            Repository<Quest> questRepository = (QuestRepository)REPOSITORY_POOL
                    .getRepository(Repositories.QUEST);
            List<Quest> quests = questRepository.query(new AllQuests());

            return quests;

        } catch(PersistenceLayerException e) {
            throw new ServiceLayerException("Can't get quests: " + e.getMessage());
        }
    }

    public void create(String name, String description, int reward) throws ServiceLayerException {

        try {

            Quest quest = new Quest(name, description, reward);
            Repository<Quest> questRepository = (QuestRepository)REPOSITORY_POOL
                    .getRepository(Repositories.QUEST);
            questRepository.add(quest);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't create quest: " + e.getMessage());
        }
    }

    public void edit(int id, String name, String description, int reward) throws ServiceLayerException {

        try {

            Repository<Quest> questRepository = (QuestRepository)REPOSITORY_POOL
                    .getRepository(Repositories.QUEST);
            Quest quest = this.get(id).get(0);

            quest.setName(name);
            quest.setDescription(description);
            quest.setReward(reward);

            questRepository.update(quest);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't edit quest: " + e.getMessage());
        }
    }

    public void delete(int id) throws ServiceLayerException {

        try {

            Repository<Quest> questRepository = (QuestRepository)REPOSITORY_POOL
                    .getRepository(Repositories.QUEST);
            Quest quest = this.get(id).get(0);
            questRepository.delete(quest);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException("Can't delete quest: " + e.getMessage());
        }
    }
}
