package br.edu.ifrn.monitor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.edu.ifrn.monitor.model.Monitor;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {

}
