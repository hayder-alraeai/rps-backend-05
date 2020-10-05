package com.hayder.rpsbackend0310;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
    @Autowired
    private GameService gameService;

    @GetMapping("auth/token")
    public String getToken(){
        return gameService.getToken();
    }
    @GetMapping("games/start")
    public GameModul start(@RequestHeader("Token") String token){
        return gameService.add(token);
    }
    @GetMapping("games")
    public List<GameModul> getAll(@RequestHeader("Token") String token){
        return gameService.getAll(token);
    }
    @GetMapping("games/{id}")
    public GameModul getOne(@RequestHeader("Token") String token, @PathVariable("id") String id){
        return gameService.getOne(token, id);
    }
    @GetMapping("games/move/{gameId}/{sign}")
    public GameModul move(@RequestHeader("Token") String token,@PathVariable("gameId") String gameId, @PathVariable("sign") String sign){
        return gameService.move(token, gameId, sign);
    }
    @GetMapping("games/join/{gameId}")
    public GameModul join(@RequestHeader("Token") String token, @PathVariable("gameId") String gameId){
        return gameService.join(token, gameId);
    }
    @GetMapping("games/result/{gameId}")
    public ResultResponse getResult(@RequestHeader("Token") String token, @PathVariable("gameId") String gameId){
        return gameService.getResult(token, gameId);
    }
    @GetMapping("auth/validate")
    public ValidationResponse validate(@RequestHeader("Token") String token){
        return gameService.validate(token);
    }
}
