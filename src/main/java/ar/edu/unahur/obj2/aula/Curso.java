package ar.edu.unahur.obj2.aula;

/**
 * Curso
 */
public class Curso implements Oferta {

    private String descripcion;
    private Double precio;
    private Integer horas;

    public Curso(String descripcion, double precio, int horas) {
        this.descripcion = descripcion;
        setPrecio(precio);
        setHoras(horas);
    }

    public void setHoras(Integer horas2) {
        this.horas = horas2;
    }

    private void setPrecio(Double precio2) {
        if(precio2 <0)
             throw new PrecioNegativoException("No se puede ingresar un valor negatico");
        this.precio= precio2;
    }

    public String getDescripcion() {
            return this.descripcion;
    }

    public Double getPrecio() {
        return this.precio;
    }

    public Integer getHoras() {
        return this.horas;
    }

    @Override
    public Boolean ContieneCurso(String ContieneCurso) {
       return  this.descripcion.equals(ContieneCurso);
    }


    @Override
    public Integer getDescuento() {
        return 0;
    }

}
