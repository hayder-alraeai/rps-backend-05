package com.hayder.rpsbackend0310;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class GameUtils {

    public void checkToken(String token, List<String> tokens){
        if (!tokens.contains(token)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is not valid");
        }
    }

    public void checkIfGameExist(String gameId, List<GameModul> gameRegister) {
        if (!gameRegister.stream().anyMatch(g -> g.getId().equals(gameId))){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game id is not valid");
        }
    }

    public GameModul getGameById(String gameId, List<GameModul> gameRegister) {
        return gameRegister.stream().filter(g -> g.getId().equals(gameId)).findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Game id is not valid"));
    }


    public GameModul calc(GameModul game){
        if (game == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "NO SUCH GAME");
        }

        if (game.getPlayerMove().equals(game.getOpponentMove())){
            game.setGameStatus("NONE");
            game.setPlayerResult("DRAW");
            game.setOpponentResult("DRAW");
        }else if (game.getPlayerMove().equals("SCISSORS") && game.getOpponentMove().equals("PAPER")){
            game.setPlayerResult("WIN");
            game.setOpponentResult("LOSE");
            game.setGameStatus("NONE");
        }else if (game.getPlayerMove().equals("PAPER") && game.getOpponentMove().equals("ROCK")){
            game.setPlayerResult("WIN");
            game.setOpponentResult("LOSE");
            game.setGameStatus("NONE");
        }else if (game.getPlayerMove().equals("ROCK") && game.getOpponentMove().equals("SCISSORS")){
            game.setPlayerResult("WIN");
            game.setOpponentResult("LOSE");
            game.setGameStatus("NONE");
        }else{
            game.setPlayerResult("LOSE");
            game.setOpponentResult("WIN");
            game.setGameStatus("NONE");
        }
        return game;
    }
}
