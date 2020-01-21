package com.mglsh.serverV4.repository;

import com.mglsh.serverV4.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Long > {
}
