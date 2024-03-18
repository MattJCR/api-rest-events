package com.example.apirestevents.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class DatabaseDeleteEvent extends ApplicationEvent {

    private final Class resourceClass;
    private final Object resource;

    /**
     * Crea un nuevo evento indicando que un recurso fue eliminado.
     *
     * @param source El objeto en el que se originó el evento (generalmente el publicador del evento).
     * @param resource El recurso eliminado.
     */
    public DatabaseDeleteEvent(Object source, Object resource) {
        super(source);
        this.resourceClass = resource.getClass();
        this.resource = resource;
    }

}
