package com.ism.core.factory;

import java.util.HashMap;
import java.util.Map;

public class Factory implements RepositoryFactory {
    
    private static Factory instance = null;
    private final Map<Class<?>, Object> repositoryInstances = new HashMap<>();

    private Factory() {}

    public static Factory getInstance() {
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T getInstance(Class<T> repositoryClass) {
        if (!repositoryInstances.containsKey(repositoryClass)) {
            try {
                // Instanciation via réflexion
                T repository = repositoryClass.getDeclaredConstructor().newInstance();
                repositoryInstances.put(repositoryClass, repository);
            } catch (Exception e) {
                throw new RuntimeException("Erreur lors de la création de l'instance pour : " + repositoryClass.getName(), e);
            }
        }
        return (T) repositoryInstances.get(repositoryClass);
    }
}
