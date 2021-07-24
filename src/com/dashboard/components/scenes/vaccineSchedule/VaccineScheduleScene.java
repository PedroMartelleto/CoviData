package com.dashboard.components.scenes.vaccineSchedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

/**
 * Covid-related news.
 */
public class VaccineScheduleScene extends AnchorPane {
	@FXML
	private TableView<String> scheduleTable;

	@FXML
	private void initialize() {
		try {
			// TODO: Fix this
			BufferedReader reader = new BufferedReader(new FileReader(new File("com/dashboard/resources/Cronograma_21Julho2021.csv")));
			
			String line = null;
			boolean isFirstRow = false;
			while ((line = reader.readLine()) != null) {
				if (isFirstRow) {
					String[] columnNames = line.split(",");
					
					for (String columnName : columnNames) {
						scheduleTable.getColumns().add(new TableColumn<>(columnName));
					}
				} else {
	//				scheduleTable.getRowFactory().add(line.split(","));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public VaccineScheduleScene() {
		FXMLUtils.loadFXML(this);
	}
}
