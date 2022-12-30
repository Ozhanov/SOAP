package com.mirat;

import com.example.soap.file.wsdl.GetFilesResponse;
import com.mirat.soap.client.FilesHashClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("main-stage.fxml")
public class UiController {

    @FXML
    private Label outputLabel;
    @FXML
    private TextField nameTextField;

    private FilesHashClient filesHashClient;

    @Autowired
    public UiController(FilesHashClient filesHashClient) {
        this.filesHashClient = filesHashClient;
    }

    public void loadWeatherForecast(ActionEvent actionEvent) {
        String name = nameTextField.getText();
        GetFilesResponse response = filesHashClient.getFilesHash(name);
        this.outputLabel.setText(response.getFileHash().getName() + "  hashcode = " + response.getFileHash().getHashCode());
    }
}
