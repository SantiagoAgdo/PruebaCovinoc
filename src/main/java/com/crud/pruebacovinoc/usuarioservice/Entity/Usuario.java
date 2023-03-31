package com.crud.pruebacovinoc.usuarioservice.Entity;
import com.crud.pruebacovinoc.computadorservice.entity.Computador;
import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entidad Usuario.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique=true , length=20)
    private String username;
    @Column(length=20 ,nullable = false)
    private String password;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Set<Computador> computadoras = new HashSet<>();

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Usuario(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    public Usuario() {
        // Constructor sin argumentos
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Computador> getComputadoras() {
        return computadoras;
    }

    public void setComputadoras(Set<Computador> computadoras) {
        this.computadoras = computadoras;
        for (Computador computador : computadoras) {
            computador.setUsuario(this);
        }
    }
}
