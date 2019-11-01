import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class FFF extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Label label1 = new Label("Здесь был ");
        label1.setFont(Font.font("Calibri", 40));
        label1.setTextFill(Color.YELLOWGREEN);

        TextField name = new TextField();
        Button addBtn = new Button("Жми");
        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
               label1.setText(label1.getText() + name.getText());
            }
        });

        Button clearBtn = new Button("Очистить");
        clearBtn.setOnAction(event ->{ label1.setText("Здесь был ");} );

        VBox group = new VBox(label1, name, addBtn, clearBtn);
          group.setBackground(new Background(new BackgroundFill(Color.DARKGREEN, CornerRadii.EMPTY, Insets.EMPTY)));
          group.setPadding(new Insets(10));
          addBtn.setMaxWidth(Double.MAX_VALUE);
          clearBtn.setMaxWidth(Double.MAX_VALUE);

          group.setOnMouseClicked(new EventHandler<MouseEvent>() {
              @Override
              public void handle(MouseEvent mouseEvent) {
                  if (mouseEvent.getButton()== MouseButton.PRIMARY && mouseEvent.getClickCount()==2)
                      primaryStage.setTitle(primaryStage.getTitle() + " !");
              }
          });

        Scene scene = new Scene(group, 400, 300);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
