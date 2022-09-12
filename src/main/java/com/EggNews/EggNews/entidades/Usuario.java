
package com.EggNews.EggNews.entidades;

import com.EggNews.EggNews.enums.Rol;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class Usuario {
    
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    
    private String nombreUsuario;
    private String email;
    private String password;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date alta;
    
    @Enumerated(EnumType.STRING)
    private Rol rol;
}
