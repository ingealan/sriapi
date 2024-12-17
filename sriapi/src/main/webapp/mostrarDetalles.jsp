<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="models.VistaConsultaDetallesModelo" %>

<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Consulta de Detalles</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Estilos generales */
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 20px;
    }

    .container {
      max-width: 100%;
      margin: 0 auto;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 8px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    h2 {
      text-align: center;
      margin-bottom: 30px;
      color: #333;
    }

    /* Contenedor para tabla con desplazamiento horizontal */
    .table-wrapper {
      width: 100%;
      overflow-x: auto;
      margin-top: 20px;
    }

    /* Tabla */
    table {
      width: 100%;
      border-collapse: collapse;
      margin-top: 20px;
    }

    th, td {
      padding: 12px 15px;
      text-align: center;
      border-bottom: 1px solid #ddd;
      font-size: 14px;
      white-space: nowrap; /* Evita que el texto se divida en varias líneas */
      text-overflow: ellipsis; /* Muestra puntos suspensivos para texto largo */
      overflow: hidden; /* Oculta el texto que no cabe */
    }

    th {
      background-color: #007bff;
      color: white;
      font-weight: bold;
    }

    tr:nth-child(even) {
      background-color: #f9f9f9;
    }

    tr:hover {
      background-color: #f1f1f1;
    }

    td {
      color: #555;
    }

    td, th {
      word-wrap: break-word;
      overflow-wrap: break-word;
    }

    /* Diseño responsive */
    @media (max-width: 768px) {
      table, thead, tbody, th, td, tr {
        display: block;
      }

      thead {
        position: absolute;
        top: -9999px;
        left: -9999px;
      }

      tr {
        border: 1px solid #ddd;
        margin-bottom: 10px;
      }

      td {
        border: none;
        position: relative;
        padding-left: 50%;
        text-align: left;
      }

      td:before {
        content: attr(data-label);
        position: absolute;
        left: 10px;
        top: 50%;
        transform: translateY(-50%);
        font-weight: bold;
      }
    }

    /* Estilo del botón */
    .btn-back {
      display: block;
      margin: 30px auto;
      background-color: #007bff;
      color: white;
      padding: 10px 20px;
      border: none;
      border-radius: 5px;
      text-align: center;
      font-size: 16px;
      cursor: pointer;
      text-decoration: none;
    }

    .btn-back:hover {
      background-color: #0056b3;
    }
  </style>
</head>
<body>
<div class="container">
  <h2>Detalles de la Vista Consulta</h2>

  <!-- Mostrar mensaje de error si no hay detalles -->
  <%
    String error = (String) request.getAttribute("error");
    if (error != null) {
  %>
  <div class="alert alert-warning"><%= error %></div>
  <%
    }
  %>

  <!-- Tabla de detalles -->
  <div class="table-wrapper">
    <table>
      <thead>
      <tr>
        <th>RUC</th>
        <th>Razón Social</th>
        <th>Estado Contribuyente</th>
        <th>Actividad Económica</th>
        <th>Tipo Contribuyente</th>
        <th>Régimen</th>
        <th>Categoría</th>
        <th>Contabilidad</th>
        <th>Agente Retención</th>
        <th>Contribuyente Especial</th>
        <th>Fecha Inicio</th>
        <th>Fecha Cese</th>
        <th>Fecha Reinicio</th>
        <th>Fecha Actualización</th>
        <th>Contribuyente Fantasma</th>
        <th>Transacciones Inexistentes</th>
        <th>Motivo Cancelación</th>
      </tr>
      </thead>
      <tbody>
      <%
        // Obtener la lista de detalles
        List<VistaConsultaDetallesModelo> detallesList = (List<VistaConsultaDetallesModelo>) request.getAttribute("detallesList");
        if (detallesList != null && !detallesList.isEmpty()) {
          for (VistaConsultaDetallesModelo detalle : detallesList) {
      %>
      <tr>
        <td><%= detalle.getNumeroRuc() %></td>
        <td><%= detalle.getRazonSocial() %></td>
        <td><%= detalle.getEstadoContribuyenteRuc() %></td>
        <td><%= detalle.getActividadEconomicaPrincipal() %></td>
        <td><%= detalle.getTipoContribuyente() %></td>
        <td><%= detalle.getRegimen() %></td>
        <td><%= detalle.getCategoria() %></td>
        <td><%= detalle.getObligadoLlevarContabilidad() %></td>
        <td><%= detalle.getAgenteRetencion() %></td>
        <td><%= detalle.getContribuyenteEspecial() %></td>
        <td><%= detalle.getFechaInicioActividades() %></td>
        <td><%= detalle.getFechaCese() %></td>
        <td><%= detalle.getFechaReinicioActividades() %></td>
        <td><%= detalle.getFechaActualizacion() %></td>
        <td><%= detalle.getContribuyenteFantasma() %></td>
        <td><%= detalle.getTransaccionesInexistente() %></td>
        <td><%= detalle.getMotivoCancelacionSuspension() %></td>
      </tr>
      <%
        }
      } else {
      %>
      <tr>
        <td colspan="17">No hay detalles para mostrar.</td>
      </tr>
      <%
        }
      %>
      </tbody>
    </table>
  </div>

  <!-- Botón para volver a consulta.html -->
  <a href="consulta.html" class="btn-back">Volver a Consulta</a>

</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
