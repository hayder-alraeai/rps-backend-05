package com.hayder.rpsbackend0310;

import java.util.UUID;

public class GameModul {
    private String id;
    private String playerToken;
    private String playerMove;
    private String opponentToken;
    private String opponentMove;
    private String gameStatus;
    private String opponentResult;
    private String playerResult;

    public GameModul(String playerToken) {
        this();
        this.playerToken = playerToken;
    }

    public GameModul() {
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerToken() {
        return playerToken;
    }

    public void setPlayerToken(String playerToken) {
        this.playerToken = playerToken;
    }

    public String getPlayerMove() {
        return playerMove;
    }

    public void setPlayerMove(String playerMove) {
        this.playerMove = playerMove;
    }

    public String getOpponentToken() {
        return opponentToken;
    }

    public void setOpponentToken(String opponentToken) {
        this.opponentToken = opponentToken;
    }

    public String getOpponentMove() {
        return opponentMove;
    }

    public void setOpponentMove(String opponentMove) {
        this.opponentMove = opponentMove;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public String getOpponentResult() {
        return opponentResult;
    }

    public void setOpponentResult(String opponentResult) {
        this.opponentResult = opponentResult;
    }

    public String getPlayerResult() {
        return playerResult;
    }

    public void setPlayerResult(String playerResult) {
        this.playerResult = playerResult;
    }
}
