package br.com.squadjoaquina.errorlogger.service;

import br.com.squadjoaquina.errorlogger.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LevelService {
    private final LevelRepository levelRepository;

    @Autowired
    public LevelService(LevelRepository levelRepository) {
        this.levelRepository = levelRepository;
    }

    //TODO: ADD METHODS FOR HANDLING LEVELS.

}
