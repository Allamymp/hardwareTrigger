package com.sd.fishapi.repository;


import com.sd.fishapi.model.hardwares.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HardwareRepository extends JpaRepository<Hardware,String> {
}
