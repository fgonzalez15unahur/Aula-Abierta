package ar.edu.unahur.obj2.aula;

import java.util.ArrayList;
import java.util.List;

/**
 * Pack
 */
public class Pack implements Oferta {


    private Integer descuento;
    private String descripcion;
    private List<Oferta> cursos = new ArrayList<>();

    public Pack(String nombre, Integer descuento) {
        setDescripcion(nombre);
        setDescuento(descuento);
    }
    private void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public  void setDescuento(Integer descuento2) {
        if(descuento2<0 || descuento2>100)
            throw new DescuentoInvalidoException("El descuento no debe ser menor que cero y mayor que 100");
        this.descuento= descuento2;
    }

    public String getDescripcion() {
        return this.descripcion;
    }

    public Integer getDescuento() {
        return this.descuento;
    }

    public Double getPrecio() {
       Double total= this.cursos.stream().mapToDouble(Oferta::getPrecio).sum() ;
       return  total - (total * descuento/100);
    }

    public void AgregarCurso(Curso curso) {
        cursos.add(curso);
    }
    @Override
    public Integer getHoras() {
            return this.cursos.stream().mapToInt(Oferta::getHoras).sum() ;
    }
    @Override
    public Boolean ContieneCurso(String cursoSolicitado) {
         return  this.cursos.stream().anyMatch(curso-> curso.getDescripcion().equals(cursoSolicitado));
    }
    public void Agregar(Oferta cursoMatematica) {
            this.cursos.add(cursoMatematica);
    }

}
