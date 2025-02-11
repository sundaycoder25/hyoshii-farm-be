package hyoshifarm.repository;

import hyoshifarm.entity.PlcData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PlcDataRepository extends JpaRepository<PlcData, Long> {
    List<PlcData> findTop10ByOrderByTimestampDesc();
    List<PlcData> findByPicId(Integer picId);
}