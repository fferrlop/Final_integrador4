package BusquedaPalabrasYContactos;

import java.util.ArrayList;
import java.util.List;

public class Contactos {
    private List<DatosContactos> listaContactos;

    public Contactos() {
        this.listaContactos = new ArrayList<>();
    }

    public void agregarContacto(DatosContactos contacto) {
        listaContactos.add(contacto);
    }

    public void eliminarContacto(DatosContactos contacto) {
        listaContactos.remove(contacto);
    }

    public List<DatosContactos> getListaContactos() {
        return listaContactos;
    }
}