package com.erpweb.repositorios.inventario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.inventario.Vehiculo;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {

}
