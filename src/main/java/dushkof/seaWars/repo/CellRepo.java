package dushkof.seaWars.repo;

import dushkof.seaWars.objects.Cell;
import dushkof.seaWars.objects.Field;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepo extends JpaRepository<Cell, Long> {
   Cell findCellById(Long CellId);

}
