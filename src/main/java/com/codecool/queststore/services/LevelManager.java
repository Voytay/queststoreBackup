package com.codecool.queststore.services;

import com.codecool.queststore.criteria.AllLevels;
import com.codecool.queststore.criteria.LevelById;
import com.codecool.queststore.entities.Level;
import com.codecool.queststore.repositories.*;

import java.util.List;

public class LevelManager {
    private static final RepositoryPool repositoryPool;

    static {
        repositoryPool = RepositoryPool.getInstance();
    }

    public List<Level> get(int id) throws ServiceLayerException {
        try {
            Repository<Level> levelRepository = (LevelRepository) repositoryPool
                    .getRepository(Repositories.LEVEL);
            return levelRepository.query(new LevelById(id));
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get level (id: %d): %s", id, e.getMessage()));
        }
    }

    public List<Level> getAll() throws ServiceLayerException {
        try {
            Repository<Level> levelRepository = (LevelRepository) repositoryPool
                    .getRepository(Repositories.LEVEL);
            return levelRepository.query(new AllLevels());
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get all levels: " + e.getMessage()));
        }
    }

    public void create(String name, int threshold) throws ServiceLayerException {
        try {
            Repository<Level> levelRepository = (LevelRepository) repositoryPool
                    .getRepository(Repositories.LEVEL);

            Level level = new Level(name, threshold);

            levelRepository.add(level);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException("Can't create level " + name);
        }
    }

    public void edit(int id, String name, int threshold) throws ServiceLayerException {
        try {
            Repository<Level> levelRepository = (LevelRepository) repositoryPool
                    .getRepository(Repositories.LEVEL);

            Level level = this.get(id).get(0);
            level.setId(id);
            level.setName(name);
            level.setThreshold(threshold);

            levelRepository.update(level);
        } catch (PersistenceLayerException e) {
           throw new ServiceLayerException(String.format("Can't edit level %s: %s", name, e.getMessage()));
        }
    }

    public void remove(int id) throws ServiceLayerException {
        try {
            Repository<Level> levelRepository = (LevelRepository) repositoryPool
                    .getRepository(Repositories.LEVEL);

            Level level = this.get(id).get(0);
            levelRepository.delete(level);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't remove level (id: %d): %s", id, e.getMessage()));
        }
    }
}
