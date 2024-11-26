package com.cultura.mvc;

import com.cultura.eventos.Concierto;
import com.cultura.eventos.Conferencia;
import com.cultura.eventos.Evento;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainView extends Application {

    private EventoRepositorio repositorio = new EventoRepositorio();

    // Elementos de la interfaz gráfica
    private ListView<Evento> vistaListaEventos = new ListView<>();
    private TextField campoNombre = new TextField();
    private TextField campoFecha = new TextField(); // Formato esperado: YYYY-MM-DD
    private TextField campoCapacidad = new TextField();

    private void refrescarListaEventos() {
        vistaListaEventos.getItems().clear(); // Limpiar la lista actual
        vistaListaEventos.getItems().addAll(repositorio.buscarTodos()); // Agregar todos los eventos desde el repositorio
    }

    @Override
    public void start(Stage escenarioPrincipal) {
        
        // Crear instancias de eventos de ejemplo 
        Evento evento1 = new Concierto("C001", "Concierto de Rock", LocalDate.of(2024, 11, 30), "Empresa X", 100, "Artista Y", "Rock");
        Evento evento2 = new Conferencia("C002", "Conferencia de Tecnología", LocalDate.of(2024, 12, 15), "Tech Corp", 150, "Innovaciones en IA", List.of("Panelista A", "Panelista B"));
        repositorio.guardar(evento1);
        repositorio.guardar(evento2);
       
        refrescarListaEventos();
        // Contenedor principal
        VBox layoutPrincipal = new VBox(10);
        layoutPrincipal.setPadding(new Insets(10));

        // Botones
        Button botonAgregarConcierto = new Button("Agregar Concierto");
        botonAgregarConcierto.setOnAction(e -> showAddConciertoDialog(escenarioPrincipal, new TextArea()));

        Button botonAgregarConferencia = new Button("Agregar Conferencia");
        botonAgregarConferencia.setOnAction(e -> showAddConferenciaDialog(escenarioPrincipal, new TextArea()));

        Button botonActualizar = new Button("Actualizar Evento");
        botonActualizar.setOnAction(e -> showActualizarEventoDialog(escenarioPrincipal));

        Button botonEliminar = new Button("Eliminar Evento");
        botonEliminar.setOnAction(e -> showEliminarEventoDialog(escenarioPrincipal));

        // Configuración de la vista de lista de eventos
        vistaListaEventos.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(Evento evento, boolean vacio) {
                super.updateItem(evento, vacio);
                if (vacio || evento == null) {
                    setText("");
                } else {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Título: ").append(evento.getTitulo()).append("\n");
                    sb.append("Código: ").append(evento.getCodigo()).append("\n");
                    sb.append("Fecha: ").append(evento.getFecha()).append("\n");
                    sb.append("Organizador: ").append(evento.getOrganizador()).append("\n");
                    sb.append("Capacidad Máxima: ").append(evento.getCapacidadMaxima()).append("\n");

                    if (evento instanceof Concierto) {
                        Concierto concierto = (Concierto) evento;
                        sb.append("Artista Principal: ").append(concierto.getArtistaPrincipal()).append("\n");
                        sb.append("Género Musical: ").append(concierto.getGeneroMusical()).append("\n");
                    } else if (evento instanceof Conferencia) {
                        Conferencia conferencia = (Conferencia) evento;
                        sb.append("Tema: ").append(conferencia.getTema()).append("\n");
                        sb.append("Panelistas: ").append(String.join(", ", conferencia.getPanelistas())).append("\n");
                    }

                    setText(sb.toString());
                }
            }
        });

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

        DatePicker fechaPicker = new DatePicker();  // Usar DatePicker para la fecha
        fechaPicker.setPromptText("Fecha");

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
        grid.add(fechaPicker, 1, 2);  // Añadir el DatePicker
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
                    LocalDate fecha = fechaPicker.getValue();
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
            repositorio.guardar(evento);
            refrescarListaEventos();
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

        DatePicker fechaPicker = new DatePicker();  // Usar DatePicker para la fecha
        fechaPicker.setPromptText("Fecha");

        TextField organizadorField = new TextField();
        organizadorField.setPromptText("Nombre del organizador");

        TextField capacidadField = new TextField();
        capacidadField.setPromptText("Capacidad máxima");

        TextField artistaPrincipalField = new TextField();
        artistaPrincipalField.setPromptText("Artista principal");

        TextField generoMusicalField = new TextField();
        generoMusicalField.setPromptText("Grupo musical");

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
        grid.add(fechaPicker, 1, 2);  // Añadir el DatePicker
        grid.add(new Label("Organizador:"), 0, 3);
        grid.add(organizadorField, 1, 3);
        grid.add(new Label("Capacidad máxima:"), 0, 4);
        grid.add(capacidadField, 1, 4);
        grid.add(new Label("Artista principal:"), 0, 5);
        grid.add(artistaPrincipalField, 1, 5);
        grid.add(new Label("Genero musical:"), 0, 6);
        grid.add(generoMusicalField, 1, 6);

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
                    LocalDate fecha = fechaPicker.getValue();  // Obtener la fecha desde el DatePicker
                    String organizador = organizadorField.getText();
                    int capacidad = Integer.parseInt(capacidadField.getText());
                    String artistaPrincipal = artistaPrincipalField.getText();
                    String generoMusical = generoMusicalField.getText();

                    // Crear y devolver el nuevo evento
                    return new Concierto(codigo, titulo, fecha, organizador, capacidad, artistaPrincipal, generoMusical);
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
            repositorio.guardar(evento);
            refrescarListaEventos();
            // controlador.agregarEvento(evento);  // Asegúrate de tener este método en tu controlador
            //conciertoDisplay.setText("Concierto agregado:\n" + evento);
        });
    }

    private void showEliminarEventoDialog(Stage owner) {
        // Crear un cuadro de diálogo
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Eliminar Evento");
        dialog.setHeaderText("Eliminar Evento");
        dialog.setContentText("Por favor, ingresa el código del evento:");

        // Mostrar el diálogo y capturar el resultado
        dialog.showAndWait().ifPresent(codigo -> {
            boolean eliminado = repositorio.eliminar(codigo);
            if (eliminado) {
                refrescarListaEventos();
                mostrarMensaje("Evento eliminado", "El evento con el código " + codigo + " ha sido eliminado.");
            } else {
                mostrarMensaje("Evento no encontrado", "No se encontró ningún evento con el código " + codigo + ".");
            }
        });
    }
    
    private void showActualizarEventoDialog(Stage owner) {
    // Crear un cuadro de diálogo para ingresar el código
    TextInputDialog dialog = new TextInputDialog();
    dialog.setTitle("Actualizar Evento");
    dialog.setHeaderText("Actualizar Evento");
    dialog.setContentText("Por favor, ingresa el código del evento:");

    // Mostrar el diálogo y capturar el resultado
    dialog.showAndWait().ifPresent(codigo -> {
        Optional<Evento> eventoOpt = repositorio.buscarPorCodigo(codigo);
        if (eventoOpt.isPresent()) {
            Evento evento = eventoOpt.get();
            // Crear un formulario para actualizar el evento
            Dialog<Evento> updateDialog = new Dialog<>();
            updateDialog.setTitle("Actualizar Evento");
            updateDialog.initOwner(owner);

            // Crear los campos de entrada con los valores actuales
            TextField tituloField = new TextField(evento.getTitulo());
            DatePicker fechaPicker = new DatePicker(evento.getFecha());
            TextField organizadorField = new TextField(evento.getOrganizador());
            TextField capacidadField = new TextField(String.valueOf(evento.getCapacidadMaxima()));

            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(20, 150, 10, 10));
            grid.add(new Label("Título:"), 0, 0);
            grid.add(tituloField, 1, 0);
            grid.add(new Label("Fecha:"), 0, 1);
            grid.add(fechaPicker, 1, 1);
            grid.add(new Label("Organizador:"), 0, 2);
            grid.add(organizadorField, 1, 2);
            grid.add(new Label("Capacidad máxima:"), 0, 3);
            grid.add(capacidadField, 1, 3);

            if (evento instanceof Concierto) {
                Concierto concierto = (Concierto) evento;
                TextField artistaPrincipalField = new TextField(concierto.getArtistaPrincipal());
                TextField generoMusicalField = new TextField(concierto.getGeneroMusical());
                grid.add(new Label("Artista Principal:"), 0, 4);
                grid.add(artistaPrincipalField, 1, 4);
                grid.add(new Label("Género Musical:"), 0, 5);
                grid.add(generoMusicalField, 1, 5);

                updateDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        try {
                            String titulo = tituloField.getText();
                            LocalDate fecha = fechaPicker.getValue();
                            String organizador = organizadorField.getText();
                            int capacidad = Integer.parseInt(capacidadField.getText());
                            String artistaPrincipal = artistaPrincipalField.getText();
                            String generoMusical = generoMusicalField.getText();

                            return new Concierto(codigo, titulo, fecha, organizador, capacidad, artistaPrincipal, generoMusical);
                        } catch (Exception e) {
                            System.out.println("ERROR: " + e);
                        }
                    }
                    return null;
                });
            } else if (evento instanceof Conferencia) {
                Conferencia conferencia = (Conferencia) evento;
                TextField temaField = new TextField(conferencia.getTema());
                TextField panelistasField = new TextField(String.join(", ", conferencia.getPanelistas()));
                grid.add(new Label("Tema:"), 0, 4);
                grid.add(temaField, 1, 4);
                grid.add(new Label("Panelistas (separados por comas):"), 0, 5);
                grid.add(panelistasField, 1, 5);

                updateDialog.setResultConverter(dialogButton -> {
                    if (dialogButton == ButtonType.OK) {
                        try {
                            String titulo = tituloField.getText();
                            LocalDate fecha = fechaPicker.getValue();
                            String organizador = organizadorField.getText();
                            int capacidad = Integer.parseInt(capacidadField.getText());
                            String tema = temaField.getText();
                            List<String> panelistas = List.of(panelistasField.getText().split(",\\s*"));

                            return new Conferencia(codigo, titulo, fecha, organizador, capacidad, tema, panelistas);
                        } catch (Exception e) {
                            System.out.println("ERROR: " + e);
                        }
                    }
                    return null;
                });
            }

            updateDialog.getDialogPane().setContent(grid);
            updateDialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

            updateDialog.showAndWait().ifPresent(eventoActualizado -> {
                repositorio.actualizar(eventoActualizado);
                refrescarListaEventos();
                mostrarMensaje("Evento actualizado", "El evento con el código " + codigo + " ha sido actualizado.");
            });
        } else {
            mostrarMensaje("Evento no encontrado", "No se encontró ningún evento con el código " + codigo + ".");
        }
    });
}


    private void mostrarMensaje(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
