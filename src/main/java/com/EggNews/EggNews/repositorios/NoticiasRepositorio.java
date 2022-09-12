
package com.EggNews.EggNews.repositorios;

import com.EggNews.EggNews.entidades.Noticia;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticiasRepositorio extends JpaRepository<Noticia, Long> {
    
    
    @Query("SELECT n FROM Noticia n WHERE n.titulo = :titulo")
    public Noticia buscarporTitulo(@Param("titulo") String titulo);
    
    @Query("SELECT n FROM Noticia n WHERE n.alta = :alta")
    public List<Noticia> buscarPorFecha (@Param("alta") Date alta);
    
    @Query("SELECT n FROM Noticia n WHERE n.id = :id")
    public Noticia buscarporId(@Param("id") String id);
}
