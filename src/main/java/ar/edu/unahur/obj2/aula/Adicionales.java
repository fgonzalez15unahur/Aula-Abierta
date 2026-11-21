package ar.edu.unahur.obj2.aula;
public abstract class Adicionales implements Oferta {
    protected Oferta oferta;
    public Adicionales(Oferta oferta)
    {
        this.oferta = oferta;
    }
    @Override
    public Double getPrecio() {
        return oferta.getPrecio();
    }

    @Override
    public String getDescripcion() {
        return oferta.getDescripcion();
    }

    @Override
    public Integer getHoras() {
        return oferta.getHoras();
    }

    @Override
    public Boolean ContieneCurso(String ContieneCurso) {
        return oferta.ContieneCurso(ContieneCurso);
    }

    @Override
    public Integer getDescuento() {
        return  oferta.getDescuento();
    }

}
