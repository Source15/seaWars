package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Field;
import dushkof.seaWars.objects.Game;
import dushkof.seaWars.objects.Ship;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FieldRepo extends JpaRepository<Field, Long> {

    Field findFieldByCells(Long CellId);

    Field findFieldByCellsContains(Cell cell);

    Field findFieldById(Long FieldId);

    Field findFieldByUser(Long UserId);
}
