package ar.edu.unahur.obj2.aula;

public class BecaInstitucional extends Adicionales{

    public BecaInstitucional(Oferta oferta) {
        super(oferta);
    }
    @Override  
    public Double getPrecio(){
        return super.getPrecio() - super.getPrecio() * 0.2;
    }
    @Override 
    public String getDescripcion()
    {
        return super.getDescripcion() + " + Beca institucional"; 
    }

}
