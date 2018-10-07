package com.codecool.queststore.services;

import com.codecool.queststore.criteria.AllCodecoolClass;
import com.codecool.queststore.criteria.CodecoolClassById;
import com.codecool.queststore.entities.CodecoolClass;
import com.codecool.queststore.repositories.*;

import java.util.List;

public class CodecoolClassManager {
    private static final RepositoryPool repositoryPool;

    static {
       repositoryPool = RepositoryPool.getInstance();
    }

    public List<CodecoolClass> get(int id) throws ServiceLayerException {
        try {
            Repository<CodecoolClass> codecoolClassRepository;
            codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                    .getRepository(Repositories.CODECOOL_CLASS);
            return codecoolClassRepository.query(new CodecoolClassById(id));
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get class (id: %d): %s", id,  e.getMessage()));
        }
    }

    public List<CodecoolClass> getAll() throws ServiceLayerException {
        try {
            Repository<CodecoolClass> codecoolClassRepository;
            codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                    .getRepository(Repositories.CODECOOL_CLASS);
            return codecoolClassRepository.query(new AllCodecoolClass());
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't get all classes: %s", e.getMessage()));
        }
    }

    public void create(String name) throws ServiceLayerException {
        try {
            Repository<CodecoolClass> codecoolClassRepository;
            codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                    .getRepository(Repositories.CODECOOL_CLASS);

            CodecoolClass codecoolClass = new CodecoolClass(name);

            codecoolClassRepository.add(codecoolClass);
        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException("Can't create class " + name);
        }
    }

    public void edit(int id, String name) throws ServiceLayerException {
        try {
            Repository<CodecoolClass> codecoolClassRepository;
            codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                    .getRepository(Repositories.CODECOOL_CLASS);

            CodecoolClass codecoolClass = this.get(id).get(0);
            codecoolClass.setId(id);
            codecoolClass.setName(name);
            codecoolClassRepository.update(codecoolClass);

        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't edit class %s: %s", name, e.getMessage()));
        }
    }

    public void remove(int id) throws ServiceLayerException {
        try {
            Repository<CodecoolClass> codecoolClassRepository;
            codecoolClassRepository = (CodecoolClassRepository) repositoryPool
                    .getRepository(Repositories.CODECOOL_CLASS);

            CodecoolClass codecoolClass = this.get(id).get(0);
            codecoolClassRepository.delete(codecoolClass);

        } catch (PersistenceLayerException e) {
            throw new ServiceLayerException(String.format("Can't remove class (id: %d): %s", id, e.getMessage()));
        }
    }
}
