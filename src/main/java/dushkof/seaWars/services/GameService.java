package dushkof.seaWars.services;

import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface GameService {

    Field createField(String name, Long gameId) throws IOException;

    void startGame(Long gameId);

    String createGame(String name) throws IOException;

    String connectSecondUser(Long id, String name);

    List<Game> foundNewGames();

    String leaveGame(String name, Long gameId);

    Game getGameById(Long gameId);

    void placeShip(Field field, Cell cell);
}
