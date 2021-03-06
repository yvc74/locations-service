package com.logistimo.locations.repository.location;

import com.logistimo.locations.entity.location.SubDistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by kumargaurav on 27/02/17.
 */
public interface SubDistrictRepository extends JpaRepository<SubDistrict,Long> {

    @Query(value = "SELECT * FROM SUBDISTRICT WHERE DISTID= ?1 AND SUBDISNAME = ?2", nativeQuery = true)
    SubDistrict findByName(String distId, String subdistrict);
}
