package com.example.apirestevents.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DatabaseUpdatedEvent extends ApplicationEvent {

    private final Class resourceClass;
    private final Object resource;

    /**
     * Crea un nuevo evento indicando que un recurso fue actualizado.
     *
     * @param source El objeto en el que se originó el evento (generalmente el publicador del evento).
     * @param resource El recurso actualizado.
     */
    public DatabaseUpdatedEvent(Object source, Object resource) {
        super(source);
        this.resourceClass = resource.getClass();
        this.resource = resource;
    }

}
