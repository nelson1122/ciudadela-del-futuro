package com.constructoressas.ciudadeladelfuturo.utils;

public class Constants {
    private Constants() {
    }

    public static final String MATERIAL_CEMENTO = "CEMENTO";
    public static final String MATERIAL_GRAVA = "GRAVA";
    public static final String MATERIAL_ARENA = "ARENA";
    public static final String MATERIAL_MADERA = "MADERA";
    public static final String MATERIAL_ADOBE = "ADOBE";

    public static final String MSG_COORDENADAS_NO_DISPONIBLES = "Las coordenadas no se encuentran disponibles";
    public static final String MSG_CANTIDAD_MATERIAL_NO_SUFICIENTE = "No hay cantidad suficiente del material ";
    public static final String MSG_VALOR_DESCONOCIDO = "Valor desconocido: ";

    public static final Integer ESTADO_SOLICITUD_PENDIENTE = 1;
    public static final Integer ESTADO_SOLICITUD_RECHAZADA = 2;
    public static final Integer ESTADO_SOLICITUD_PROGRAMADA = 3;
    public static final Integer ESTADO_SOLICITUD_EN_PROGRESO = 4;
    public static final Integer ESTADO_SOLICITUD_FINALIZADA = 5;

    public static final String MSG_SOLICITUDES_NO_REGISTRADAS = "No se han registrado solicitudes en el sistema";
    public static final String MSG_REPORTE_GENERADO = "Reporte generado exitosamente";
    public static final String MSG_ERROR_GENERAR_REPORTE = "Ha ocurrido un error al generar el reporte";


    public static final Object[] HEADERS_REPORTE = new String[]{
            "TIPO CONSTRUCCION",
            "X",
            "Y",
            "ESTADO",
            "FECHA ESTADO",
            "MOTIVO"
    };
}
