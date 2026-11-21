package ar.edu.unahur.obj2.aula;
public class CertificacionOficial extends  Adicionales{

    public CertificacionOficial(Oferta oferta) {
        super(oferta);
    }

    @Override  
    public Double getPrecio(){
        return super.getPrecio() + 25000.00;
    }
    @Override 
    public String getDescripcion()
    {
        return super.getDescripcion() + " + Certificación oficial"; 
    }

}
