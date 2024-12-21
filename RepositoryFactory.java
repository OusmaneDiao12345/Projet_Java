package com.ism.core.factory;

public interface RepositoryFactory {
    <T> T getInstance(Class<T> repositoryClass);
}

