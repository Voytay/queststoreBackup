package com.codecool.queststore.services;

import com.codecool.queststore.criteria.QuestById;
import com.codecool.queststore.criteria.StudentByLogin;
import com.codecool.queststore.entities.Quest;
import com.codecool.queststore.entities.Student;
import com.codecool.queststore.repositories.*;

public class RewardManager {

    private static final RepositoryPool REPOSITORY_POOL;

    static {
        REPOSITORY_POOL = RepositoryPool.getInstance();
    }

    public void grant(String login, int questId) throws ServiceLayerException {
        try {

            Student student = loadStudentByLogin(login);
            Quest quest = loadQuestById(questId);

            int experience = student.getExperience() + quest.getReward();

            student.setExperience(experience);

        } catch (PersistenceLayerException e) {

            throw new ServiceLayerException(String.format("Codecooler (login: %s) can't be granted : %s", login, e.getMessage()));
        }
    }

    public Student loadStudentByLogin(String login) throws PersistenceLayerException {
        Repository<Student> studentRepository = (StudentRepository)REPOSITORY_POOL.getRepository(Repositories.STUDENT);
        return studentRepository.query(new StudentByLogin(login)).get(0);
    }

    public Quest loadQuestById(int questId) throws PersistenceLayerException {
        Repository<Quest> questRepository = (QuestRepository)REPOSITORY_POOL.getRepository(Repositories.QUEST);
        return questRepository.query(new QuestById(questId)).get(0);
    }
}
