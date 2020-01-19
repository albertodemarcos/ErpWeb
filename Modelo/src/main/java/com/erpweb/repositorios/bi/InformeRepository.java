package com.erpweb.repositorios.bi;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erpweb.entidades.bi.Informe;

@Repository
public interface InformeRepository extends JpaRepository<Informe, Long> {

}
