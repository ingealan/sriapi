package models;

import java.sql.Timestamp;

public class VistaConsultaDetallesModelo {
    private String numeroRuc;
    private String razonSocial;
    private String estadoContribuyenteRuc;
    private String actividadEconomicaPrincipal;
    private String tipoContribuyente;
    private String regimen;
    private String categoria;
    private String obligadoLlevarContabilidad;
    private String agenteRetencion;
    private String contribuyenteEspecial;
    private Timestamp fechaInicioActividades;
    private Timestamp fechaCese;
    private Timestamp fechaReinicioActividades;
    private Timestamp fechaActualizacion;
    private String contribuyenteFantasma;
    private String transaccionesInexistente;
    private String motivoCancelacionSuspension;

    // Constructor y métodos getter/setter

    public VistaConsultaDetallesModelo(String numeroRuc, String razonSocial, String estadoContribuyenteRuc,
                                       String actividadEconomicaPrincipal, String tipoContribuyente, String regimen,
                                       String categoria, String obligadoLlevarContabilidad, String agenteRetencion,
                                       String contribuyenteEspecial, Timestamp fechaInicioActividades, Timestamp fechaCese,
                                       Timestamp fechaReinicioActividades, Timestamp fechaActualizacion,
                                       String contribuyenteFantasma, String transaccionesInexistente,
                                       String motivoCancelacionSuspension) {
        this.numeroRuc = numeroRuc;
        this.razonSocial = razonSocial;
        this.estadoContribuyenteRuc = estadoContribuyenteRuc;
        this.actividadEconomicaPrincipal = actividadEconomicaPrincipal;
        this.tipoContribuyente = tipoContribuyente;
        this.regimen = regimen;
        this.categoria = categoria;
        this.obligadoLlevarContabilidad = obligadoLlevarContabilidad;
        this.agenteRetencion = agenteRetencion;
        this.contribuyenteEspecial = contribuyenteEspecial;
        this.fechaInicioActividades = fechaInicioActividades;
        this.fechaCese = fechaCese;
        this.fechaReinicioActividades = fechaReinicioActividades;
        this.fechaActualizacion = fechaActualizacion;
        this.contribuyenteFantasma = contribuyenteFantasma;
        this.transaccionesInexistente = transaccionesInexistente;
        this.motivoCancelacionSuspension = motivoCancelacionSuspension;
    }

    // Getters and Setters

    public String getNumeroRuc() {
        return numeroRuc;
    }

    public void setNumeroRuc(String numeroRuc) {
        this.numeroRuc = numeroRuc;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getEstadoContribuyenteRuc() {
        return estadoContribuyenteRuc;
    }

    public void setEstadoContribuyenteRuc(String estadoContribuyenteRuc) {
        this.estadoContribuyenteRuc = estadoContribuyenteRuc;
    }

    public String getActividadEconomicaPrincipal() {
        return actividadEconomicaPrincipal;
    }

    public void setActividadEconomicaPrincipal(String actividadEconomicaPrincipal) {
        this.actividadEconomicaPrincipal = actividadEconomicaPrincipal;
    }

    public String getTipoContribuyente() {
        return tipoContribuyente;
    }

    public void setTipoContribuyente(String tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    public String getRegimen() {
        return regimen;
    }

    public void setRegimen(String regimen) {
        this.regimen = regimen;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObligadoLlevarContabilidad() {
        return obligadoLlevarContabilidad;
    }

    public void setObligadoLlevarContabilidad(String obligadoLlevarContabilidad) {
        this.obligadoLlevarContabilidad = obligadoLlevarContabilidad;
    }

    public String getAgenteRetencion() {
        return agenteRetencion;
    }

    public void setAgenteRetencion(String agenteRetencion) {
        this.agenteRetencion = agenteRetencion;
    }

    public String getContribuyenteEspecial() {
        return contribuyenteEspecial;
    }

    public void setContribuyenteEspecial(String contribuyenteEspecial) {
        this.contribuyenteEspecial = contribuyenteEspecial;
    }

    public Timestamp getFechaInicioActividades() {
        return fechaInicioActividades;
    }

    public void setFechaInicioActividades(Timestamp fechaInicioActividades) {
        this.fechaInicioActividades = fechaInicioActividades;
    }

    public Timestamp getFechaCese() {
        return fechaCese;
    }

    public void setFechaCese(Timestamp fechaCese) {
        this.fechaCese = fechaCese;
    }

    public Timestamp getFechaReinicioActividades() {
        return fechaReinicioActividades;
    }

    public void setFechaReinicioActividades(Timestamp fechaReinicioActividades) {
        this.fechaReinicioActividades = fechaReinicioActividades;
    }

    public Timestamp getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Timestamp fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getContribuyenteFantasma() {
        return contribuyenteFantasma;
    }

    public void setContribuyenteFantasma(String contribuyenteFantasma) {
        this.contribuyenteFantasma = contribuyenteFantasma;
    }

    public String getTransaccionesInexistente() {
        return transaccionesInexistente;
    }

    public void setTransaccionesInexistente(String transaccionesInexistente) {
        this.transaccionesInexistente = transaccionesInexistente;
    }

    public String getMotivoCancelacionSuspension() {
        return motivoCancelacionSuspension;
    }

    public void setMotivoCancelacionSuspension(String motivoCancelacionSuspension) {
        this.motivoCancelacionSuspension = motivoCancelacionSuspension;
    }
}
