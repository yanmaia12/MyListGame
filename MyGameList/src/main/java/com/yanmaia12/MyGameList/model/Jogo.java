package com.yanmaia12.MyGameList.model;

public record Jogo(int id, String name, Double rating){
    @Override
    public String toString() {
        return "%s".formatted(name);
    }
}
