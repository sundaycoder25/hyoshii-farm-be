package hyoshifarm.repository;

import hyoshifarm.entity.PlcData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlcDataRepository extends JpaRepository<PlcData, Long> {
    @Query(value = "SELECT * FROM plc_data ORDER BY timestamp DESC LIMIT 20", nativeQuery = true)
    List<PlcData> findLast20Records();
    List<PlcData> findTop10ByOrderByTimestampDesc();
    List<PlcData> findByPicId(Integer picId);
}