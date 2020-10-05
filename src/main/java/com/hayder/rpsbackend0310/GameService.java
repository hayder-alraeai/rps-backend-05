package com.hayder.rpsbackend0310;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GameService {
    @Autowired
    private GameUtils gameUtils;
    private List<GameModul> gameRegister = new ArrayList<>();
    private List<String> tokenRegister = new ArrayList<>();

    public String getToken(){
        String token = UUID.randomUUID().toString();
        tokenRegister.add(token);
        return tokenRegister.stream()
                .filter(t -> t.equals(token))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not add token"));
    }
    public List<GameModul> getAll(String token){
        gameUtils.checkToken(token, tokenRegister );
        return gameRegister;
    }
    public GameModul getOne(String token, String gameId){
        gameUtils.checkToken(token, tokenRegister );
        gameUtils.checkIfGameExist(gameId, gameRegister);
        return gameUtils.getGameById(gameId, gameRegister);

    }
    public GameModul add(String token){
        gameUtils.checkToken(token, tokenRegister);
        GameModul gameModul = new GameModul();
        gameModul.setPlayerToken(token);
        gameModul.setGameStatus("OPEN");
        gameRegister.add(gameModul);
        return gameRegister.stream()
                .filter(g -> g.getId().equals(gameModul.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game Could not be started"));
    }


    public GameModul move(String token, String gameId, String sign) {
        gameUtils.checkToken(token, tokenRegister);
        gameUtils.checkIfGameExist(gameId, gameRegister);
        GameModul game = gameUtils.getGameById(gameId, gameRegister);
        if (!game.getGameStatus().equals("ACTIVE")){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game status is still OPEN");
        }
        if (game.getPlayerToken().equals(token))
            game.setPlayerMove(sign);
        else if (game.getOpponentToken().equals(token))
            game.setOpponentMove(sign);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token is not valid in this game");
        if (game.getOpponentMove() != null && game.getPlayerMove() != null)
            game.setGameStatus("NONE");
        if (game.getPlayerMove() != null && game.getOpponentMove() != null)
            gameUtils.calc(game);
        return game;
    }

    public GameModul join(String token, String gameId) {
        gameUtils.checkToken(token, tokenRegister);
        gameUtils.checkIfGameExist(gameId, gameRegister);
        GameModul game = gameUtils.getGameById(gameId, gameRegister);
        if (game.getGameStatus().equals("OPEN")){
            game.setOpponentToken(token);
            game.setGameStatus("ACTIVE");
        }else {throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Game status is not OPEN");}
        return game;
    }

    public ResultResponse getResult(String token, String gameId) {
        gameUtils.checkToken(token, tokenRegister);
        gameUtils.checkIfGameExist(gameId, gameRegister);
        GameModul game = gameUtils.getGameById(gameId, gameRegister);
        if (game.getPlayerMove() == null || game.getOpponentMove() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "All player should make a move to see the result");
        }
        ResultResponse result = new ResultResponse();
        if(game.getPlayerToken().equals(token)){
            result.setResult(game.getPlayerResult());
        }else if (game.getOpponentToken().equals(token)){
            result.setResult(game.getOpponentResult());
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Token cannot be identified!");
        }
        return result;
    }

    public ValidationResponse validate(String token) {
        gameUtils.checkToken(token, tokenRegister);
        return new ValidationResponse("OK");
    }
}
