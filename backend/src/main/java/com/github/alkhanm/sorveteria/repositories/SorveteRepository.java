package com.github.alkhanm.sorveteria.repositories;

import com.github.alkhanm.sorveteria.domain.Sorvete;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SorveteRepository extends JpaRepository<Sorvete, Long> {}
