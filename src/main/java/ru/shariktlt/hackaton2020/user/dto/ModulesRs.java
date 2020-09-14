package ru.shariktlt.hackaton2020.user.dto;

import lombok.Getter;

import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ModulesRs implements Serializable {

    @Getter
    private Set<String> availableModules;

    public ModulesRs(List<Modules> availableModules) {
        this.availableModules = availableModules.stream().map(Modules::getId).collect(Collectors.toSet());
    }
}
