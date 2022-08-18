
package com.EggNews.EggNews.repositorios;

import com.EggNews.EggNews.entidades.Foto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepositorio extends JpaRepository<Foto, String>{
    
        @Query("SELECT f FROM Foto f WHERE f.id = :idFoto")
    public Foto buscarporId(@Param("idFoto") String idFoto);
    
}
