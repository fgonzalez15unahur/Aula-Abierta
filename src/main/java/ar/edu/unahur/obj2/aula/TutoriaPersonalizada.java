package ar.edu.unahur.obj2.aula;

public class TutoriaPersonalizada extends Adicionales {

    public TutoriaPersonalizada(Oferta oferta) {
        super(oferta);
        
    }
    @Override  
    public Double getPrecio(){
        return super.getPrecio() + 15000.00;
    }
    @Override 
    public String getDescripcion()
    {
        return super.getDescripcion() + " Tutoria Personalizada"; 
    }
    @Override 
    public  Integer getHoras()
    {
        return super.getHoras() + 10;
    }

}
