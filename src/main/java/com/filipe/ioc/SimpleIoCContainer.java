package com.filipe.ioc;

import java.util.HashMap;
import java.util.Map;

//injecao de depencias simples

//cria um mapa para armazenar instâncias de objetos, associando cada instância ao seu tipo (classe).
public class SimpleIoCContainer {
    private final Map<Class<?>, Object> beans = new HashMap<>();

    //Método genérico para registrar um objeto no container.
    //Recebe a classe e a instância, e armazena no mapa.
    public <T> void registerBean(Class<T> clazz, T instance) {
        beans.put(clazz, instance);
    }
    //Método genérico para recuperar uma instância do container.
    //Busca pelo tipo da classe e faz o cast para o tipo correto.
    public <T> T getBean(Class<T> clazz) {
        return clazz.cast(beans.get(clazz));
    }
}
