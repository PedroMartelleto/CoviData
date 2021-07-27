package com.dashboard.components.scenes.vaccineSchedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Field;

import com.dashboard.utils.FXMLUtils;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Covid-related news.
 */
public class VaccineScheduleScene extends AnchorPane {
	private static final String VACCINE_SCHEDULE_FILENAME = "Cronograma_21Julho2021.csv";

	@FXML
	private TableView<VaccineScheduleTableRow> scheduleTable;

	@FXML
	private void initialize() {
		try {
			BufferedReader reader = new BufferedReader(
					new FileReader(new File("data/" + VACCINE_SCHEDULE_FILENAME)));
			Field[] columnNicknames = VaccineScheduleTableRow.class.getDeclaredFields();

			String line = null;
			boolean isFirstRow = true;

			// For each line in the csv...
			while ((line = reader.readLine()) != null) {
				if (isFirstRow) {
					String[] columnNames = line.split(",");

					// Appends columns to table view
					for (int i = 0; i < columnNames.length; ++i) {
						TableColumn<VaccineScheduleTableRow, String> col = new TableColumn<>(columnNames[i]);
						col.setCellValueFactory(new PropertyValueFactory<>(columnNicknames[i].getName()));
						scheduleTable.getColumns().add(col);
					}
				} else {
					String[] l = line.split(",");
					if (l.length >= 14) {
						scheduleTable.getItems().add(new VaccineScheduleTableRow(l[0], l[1], l[2], l[3], l[4], l[5], l[6],
								l[7], l[8], l[9], l[10], l[11], l[12], l[13]));
					}
				}
				isFirstRow = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new instance of the vaccine schedule scene.
	 */
	public VaccineScheduleScene() {
		FXMLUtils.loadFXML(this);
	}
}
