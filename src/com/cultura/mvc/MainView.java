package com.cultura.mvc;

import com.cultura.eventos.Concierto;
import com.cultura.eventos.Conferencia;
import com.cultura.eventos.Evento;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

    // Controlador de eventos
    private EventoControlador controlador;

    // Elementos de la interfaz gráfica
    private ListView<Evento> vistaListaEventos = new ListView<>();
    private TextField campoNombre = new TextField();
    private TextField campoFecha = new TextField(); // Formato esperado: YYYY-MM-DD
    private TextField campoCapacidad = new TextField();

    @Override
    public void start(Stage escenarioPrincipal) {

        // Inicializar controlador
        controlador = new EventoControlador(); // Asegúrate de tener el controlador adecuado

        // Contenedor principal
        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setPadding(new Insets(10));



        // Botones
        Button botonAgregarConcierto = new Button("Agregar Concierto");
        botonAgregarConcierto.setOnAction(e -> showAddConciertoDialog(escenarioPrincipal, new TextArea()));

        Button botonAgregarConferencia = new Button("Agregar Conferencia");
        botonAgregarConferencia.setOnAction(e -> showAddConferenciaDialog(escenarioPrincipal, new TextArea()));
        
        Button botonActualizar = new Button("Actualizar Evento");
        botonActualizar.setOnAction(e -> System.out.println("actualizar"));

        Button botonEliminar = new Button("Eliminar Evento");
        botonEliminar.setOnAction(e -> System.out.println("eliminar"));

        // Configurar vista de lista de eventos
        /*
        vistaListaEventos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Evento evento, boolean vacio) {
                super.updateItem(evento, vacio);
                setText(vacio ? "" : evento.getNombre() + " - " + evento.getFecha() + " (" + evento.getCapacidadRestante() + ")");
            }
        });
         */
        // Listener para selección en la lista
        /*
        vistaListaEventos.getSelectionModel().selectedItemProperty().addListener((obs, seleccionAnterior, nuevaSeleccion) -> {
            controlador.handleSeleccionEvento(nuevaSeleccion);
        });
         */
        // Diseño del formulario
        GridPane gridEntrada = new GridPane();


        // Contenedor de botones
        HBox contenedorBotones = new HBox(10, botonAgregarConcierto, botonAgregarConferencia, botonActualizar, botonEliminar);

        // Agregar componentes al layout principal
        layoutPrincipal.getChildren().addAll(
                gridEntrada,
                contenedorBotones,
                new Label("Lista de Eventos:"),
                vistaListaEventos
        );

        // Configurar escena
        Scene escena = new Scene(layoutPrincipal, 800, 500);
        escenarioPrincipal.setTitle("Sistema de Gestión de Eventos");
        escenarioPrincipal.setScene(escena);
        escenarioPrincipal.show();

        // Inicializar lista
        // controlador.cargarEventos();
    }

       private void showAddConferenciaDialog(Stage owner, TextArea conferenciaDisplay) {
        Dialog<Evento> dialog = new Dialog<>();
        dialog.setTitle("Agregar Concierto");
        dialog.initOwner(owner);

        // Crear los campos de entrada
        TextField codigoField = new TextField();
        codigoField.setPromptText("Código del concierto");

        TextField tituloField = new TextField();
        tituloField.setPromptText("Título del concierto");

        TextField fechaField = new TextField();
        fechaField.setPromptText("Fecha (YYYY-MM-DD)");

        TextField organizadorField = new TextField();
        organizadorField.setPromptText("Nombre del organizador");

        TextField capacidadField = new TextField();
        capacidadField.setPromptText("Capacidad máxima");

        TextField temaField = new TextField();
        temaField.setPromptText("Tema");

        TextField panelistasField = new TextField();
        panelistasField.setPromptText("Panelistas");

        // Configuración del GridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Código:"), 0, 0);
        grid.add(codigoField, 1, 0);
        grid.add(new Label("Título:"), 0, 1);
        grid.add(tituloField, 1, 1);
        grid.add(new Label("Fecha:"), 0, 2);
        grid.add(fechaField, 1, 2);
        grid.add(new Label("Organizador:"), 0, 3);
        grid.add(organizadorField, 1, 3);
        grid.add(new Label("Capacidad máxima:"), 0, 4);
        grid.add(capacidadField, 1, 4);
        grid.add(new Label("Tema:"), 0, 5);
        grid.add(temaField, 1, 5);
        grid.add(new Label("Panelistas:"), 0, 6);
        grid.add(panelistasField, 1, 6);

        dialog.getDialogPane().setContent(grid);

        // Botones
        ButtonType addButtonType = new ButtonType("Agregar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Validación y procesamiento de datos ingresados
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String codigo = codigoField.getText();
                    String titulo = tituloField.getText();
                    LocalDate fecha = LocalDate.parse(fechaField.getText());
                    String organizador = organizadorField.getText();
                    int capacidad = Integer.parseInt(capacidadField.getText());
                    String tema = temaField.getText();
                    List<String> panelistas = Arrays.asList(panelistasField.getText().split("\\s*,\\s*"));

                    // Crear y devolver el nuevo evento
                    return new Conferencia(codigo, titulo, fecha, organizador, capacidad, tema, panelistas);
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
            }
            return null;
        });

        // Manejar el resultado del diálogo
        
        dialog.showAndWait().ifPresent(evento -> {
            // Aquí puedes agregar lógica para agregar el evento al repositorio
            // y actualizar la interfaz
            
            // controlador.agregarEvento(evento);  // Asegúrate de tener este método en tu controlador
            conferenciaDisplay.setText("Concierto agregado:\n" + evento);
        });
         
    }
    
    
    
    
    
    private void showAddConciertoDialog(Stage owner, TextArea conciertoDisplay) {
        Dialog<Evento> dialog = new Dialog<>();
        dialog.setTitle("Agregar Concierto");
        dialog.initOwner(owner);

        // Crear los campos de entrada
        TextField codigoField = new TextField();
        codigoField.setPromptText("Código del concierto");

        TextField tituloField = new TextField();
        tituloField.setPromptText("Título del concierto");

        TextField fechaField = new TextField();
        fechaField.setPromptText("Fecha (YYYY-MM-DD)");

        TextField organizadorField = new TextField();
        organizadorField.setPromptText("Nombre del organizador");

        TextField capacidadField = new TextField();
        capacidadField.setPromptText("Capacidad máxima");

        TextField artistaPrincipalField = new TextField();
        artistaPrincipalField.setPromptText("Artista principal");

        TextField grupoMusicalField = new TextField();
        grupoMusicalField.setPromptText("Grupo musical");

        // Configuración del GridPane
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        grid.add(new Label("Código:"), 0, 0);
        grid.add(codigoField, 1, 0);
        grid.add(new Label("Título:"), 0, 1);
        grid.add(tituloField, 1, 1);
        grid.add(new Label("Fecha:"), 0, 2);
        grid.add(fechaField, 1, 2);
        grid.add(new Label("Organizador:"), 0, 3);
        grid.add(organizadorField, 1, 3);
        grid.add(new Label("Capacidad máxima:"), 0, 4);
        grid.add(capacidadField, 1, 4);
        grid.add(new Label("Artista principal:"), 0, 5);
        grid.add(artistaPrincipalField, 1, 5);
        grid.add(new Label("Grupo musical:"), 0, 6);
        grid.add(grupoMusicalField, 1, 6);

        dialog.getDialogPane().setContent(grid);

        // Botones
        ButtonType addButtonType = new ButtonType("Agregar", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(addButtonType, ButtonType.CANCEL);

        // Validación y procesamiento de datos ingresados
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == addButtonType) {
                try {
                    String codigo = codigoField.getText();
                    String titulo = tituloField.getText();
                    LocalDate fecha = LocalDate.parse(fechaField.getText());
                    String organizador = organizadorField.getText();
                    int capacidad = Integer.parseInt(capacidadField.getText());
                    String artistaPrincipal = artistaPrincipalField.getText();
                    String grupoMusical = grupoMusicalField.getText();

                    // Crear y devolver el nuevo evento
                    return new Concierto(codigo, titulo, fecha, organizador, capacidad, artistaPrincipal, grupoMusical);
                } catch (Exception e) {
                    System.out.println("ERROR: " + e);
                }
            }
            return null;
        });

        // Manejar el resultado del diálogo
        
        dialog.showAndWait().ifPresent(evento -> {
            // Aquí puedes agregar lógica para agregar el evento al repositorio
            // y actualizar la interfaz
            
            // controlador.agregarEvento(evento);  // Asegúrate de tener este método en tu controlador
            conciertoDisplay.setText("Concierto agregado:\n" + evento);
        });
         
    }

    public static void main(String[] args) {
        launch(args);
    }
}
