package com.example.apirestevents.listener;

import com.example.apirestevents.config.LogMarkers;
import com.example.apirestevents.event.DatabaseCreatedEvent;
import com.example.apirestevents.event.DatabaseDeleteEvent;
import com.example.apirestevents.event.DatabaseUpdatedEvent;
import com.example.apirestevents.model.entities.ApplicationLog;
import com.example.apirestevents.repository.ApplicationLogRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
@Component
public class DatabaseEventListener {

    private void saveLog(String severity, String message,String location, String exceptionData, String category) {
        ApplicationLog log = new ApplicationLog();
        log.setSeverity(severity);
        log.setMessage(message);
        log.setLocation(location);
        log.setExceptionData(exceptionData);
        log.setCategory(category);
        logRepository.save(log);
    }

    private static final Logger logger = LogManager.getLogger(DatabaseEventListener.class);
    @Autowired
    private ApplicationLogRepository logRepository;
    @EventListener
    public void onResourceCreated(DatabaseCreatedEvent event) {
        Object resource = event.getResource();
        Long id = null;
        String message = null;
        try {
            // Intenta invocar el método getId() del recurso
            if (resource != null) {
                Method getIdMethod = resource.getClass().getMethod("getId");
                id = (Long) getIdMethod.invoke(resource);
            }
        } catch (Exception e) {
            message = "Error al obtener el ID del recurso";
            logger.error(message, e);
            saveLog("ERROR", message,event.getSource().getClass().getSimpleName(), e.toString(),LogMarkers.DATABASE.getName());
        }
        message = String.format("Recurso creado: Origen=%s Tipo=%s Id=%s",
                event.getSource().getClass().getSimpleName(),
                event.getResourceClass().getSimpleName(),
                id);
        logger.info(LogMarkers.DATABASE, message);
        saveLog("INFO", message,event.getSource().getClass().getSimpleName(), null,LogMarkers.DATABASE.getName());
    }

    @EventListener
    public void onResourceUpdate(DatabaseUpdatedEvent event) {
        Object resource = event.getResource();
        Long id = null;
        String message = null;
        try {
            // Intenta invocar el método getId() del recurso
            if (resource != null) {
                Method getIdMethod = resource.getClass().getMethod("getId");
                id = (Long) getIdMethod.invoke(resource);
            }
        } catch (Exception e) {
            message = "Error al obtener el ID del recurso";
            logger.error(message, e);
            saveLog("ERROR", message, event.getSource().getClass().getSimpleName(), e.toString(),LogMarkers.DATABASE.getName());
        }
        message = String.format("Recurso actualizado: Origen=%s Tipo=%s Id=%s",
                event.getSource().getClass().getSimpleName(),
                event.getResourceClass().getSimpleName(),
                id);
        logger.info(LogMarkers.DATABASE, message);
        saveLog("INFO", message, event.getSource().getClass().getSimpleName(),null,LogMarkers.DATABASE.getName());
    }

    @EventListener
    public void onResourceDelete(DatabaseDeleteEvent event) {
        Object resource = event.getResource();
        Long id = null;
        String message = null;
        try {
            // Intenta invocar el método getId() del recurso
            if (resource != null) {
                Method getIdMethod = resource.getClass().getMethod("getId");
                id = (Long) getIdMethod.invoke(resource);
            }
        } catch (Exception e) {
            message = "Error al obtener el ID del recurso";
            logger.error(message, e);
            saveLog("ERROR", message, event.getSource().getClass().getSimpleName(), e.toString(),LogMarkers.DATABASE.getName());
        }
        message = String.format("Recurso eliminado: Origen=%s Tipo=%s Id=%s",
                event.getSource().getClass().getSimpleName(),
                event.getResourceClass().getSimpleName(),
                id);
        logger.info(LogMarkers.DATABASE, message);
        saveLog("INFO", message, event.getSource().getClass().getSimpleName(), null,LogMarkers.DATABASE.getName());
    }


}
