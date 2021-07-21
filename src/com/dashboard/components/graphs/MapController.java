package com.dashboard.components.graphs;

import java.util.List;

import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapCircle;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;

import javafx.fxml.FXML;

public class MapController {
	
	@FXML
	private MapView mapView;
		
	private Coordinate center;
	
	private int defaultZoom = 4;
	
	/*private MapCircle SP;
	private MapCircle MG;
	private MapCircle RJ;
	private MapCircle ES;
	private MapCircle RS;
	private MapCircle PR;
	private MapCircle SC;
	private MapCircle MS;
	private MapCircle MT;
	private MapCircle GO;
	private MapCircle AC;
	private MapCircle AM;
	private MapCircle RO;
	private MapCircle RR;
	private MapCircle PA;
	private MapCircle AP;
	private MapCircle BA;
	private MapCircle TO;
	private MapCircle MR;
	private MapCircle PI;
	private MapCircle CE;
	private MapCircle PE;
	private MapCircle SE;
	private MapCircle RN;
	private MapCircle AL;
	private MapCircle PB;*/
	
	
	public MapController() {
		this.center = new Coordinate(-15.723588679352947, -46.98361582418985);
		/*
		this.SP = new MapCircle(new Coordinate(-21.98761654615617, -48.783849369628065), 500000).setVisible(true);
		this.MG = new MapCircle(new Coordinate(-18.56684774956769, -44.65299022563616), 100000).setVisible(true);
		this.RJ = new MapCircle(new Coordinate(-22.231898938925315, -42.41177941347034), 100000).setVisible(true);
		this.ES = new MapCircle(new Coordinate(-19.480829103722524, -40.56607639168672), 100000).setVisible(true);
		this.PR = new MapCircle(new Coordinate(-24.850298733198386, -51.94791169268568), 100000).setVisible(true);
		this.SC = new MapCircle(new Coordinate(-27.414437951987114, -49.618810260434934), 100000).setVisible(true);
		this.RS = new MapCircle(new Coordinate(-29.767948713277143, -53.61783347429944), 100000).setVisible(true);
		
		this.GO = new MapCircle(new Coordinate(-15.923030148901978, -49.70670088051987), 100000).setVisible(true);
		this.MS = new MapCircle(new Coordinate(-20.018511286253194, -54.892247465530986), 100000).setVisible(true);
		this.MT = new MapCircle(new Coordinate(-13.158382498563341, -55.902989611252906), 100000).setVisible(true);
		this.AC = new MapCircle(new Coordinate(-8.975961305492415, -70.75650444246978), 100000).setVisible(true);
		this.RR = new MapCircle(new Coordinate(2.1926157297721143, -61.440098703636615), 100000).setVisible(true);
		this.RO = new MapCircle(new Coordinate(-10.794182256952674, -63.11002048525037), 100000).setVisible(true);
		this.AM = new MapCircle(new Coordinate(-3.9998849109360304, -63.9889266860997), 100000).setVisible(true);
		
		this.PA = new MapCircle(new Coordinate(-5.007493035041155, -52.563146116836386), 100000).setVisible(true);
		this.AP = new MapCircle(new Coordinate(1.7973484351587585, -51.903966215531014), 100000).setVisible(true);
		this.TO = new MapCircle(new Coordinate(-10.621461924767853, -48.34439618073224), 100000).setVisible(true);
		this.BA = new MapCircle(new Coordinate(-12.129286652702438, -41.2252558752115), 100000).setVisible(true);
		this.CE = new MapCircle(new Coordinate(-4.788569000881139, -39.42349806516903), 100000).setVisible(true);
		this.MR = new MapCircle(new Coordinate(-4.788569000881139, -45.66373209119935), 100000).setVisible(true);
		this.PI = new MapCircle(new Coordinate(-7.497275646259049, -42.98306817860886), 100000).setVisible(true);
		

		this.RN = new MapCircle(new Coordinate(-5.576344712331536, -36.78677946262099), 100000).setVisible(true);
		this.PE = new MapCircle(new Coordinate(-8.54163404704468, -36.567052912408656), 100000).setVisible(true);
		this.AL = new MapCircle(new Coordinate(-9.669794251948831, -35.68814671155931), 100000).setVisible(true);
		this.PB = new MapCircle(new Coordinate(-7.017751461830989, -36.698888842536064), 100000).setVisible(true);
		this.SE = new MapCircle(new Coordinate(-10.794182246042391, -37.1822872530032), 100000).setVisible(true);
		*/
	}
	
	public void initMap(List<MapCircle> dados) {
		Projection projection = Projection.WEB_MERCATOR;
		
		// watch the MapView's initialized property to finish initialization
        mapView.initializedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) {
                afterInit(dados);
            }
        });
		
		mapView.initialize(Configuration.builder()
				.projection(projection)
				.interactive(false)
				.build()
				);
		
	}
	
	private void afterInit(List<MapCircle> dados) {
		System.out.println("Após inicialização");
		mapView.setCenter(center);
		mapView.setZoom(defaultZoom);
		
		for(MapCircle circle : dados) {
			mapView.addMapCircle(circle);
		}
		
		
		/*
		mapView.addMapCircle(this.SP);
		mapView.addMapCircle(this.MG);
		mapView.addMapCircle(this.ES);
		mapView.addMapCircle(this.RJ);
		mapView.addMapCircle(this.PR);
		mapView.addMapCircle(this.SC);
		mapView.addMapCircle(this.RS);
		mapView.addMapCircle(this.GO);
		mapView.addMapCircle(this.MT);
		mapView.addMapCircle(this.MS);
		mapView.addMapCircle(this.AC);
		mapView.addMapCircle(this.RR);
		mapView.addMapCircle(this.AM);
		mapView.addMapCircle(this.RO);
		mapView.addMapCircle(this.PA);
		mapView.addMapCircle(this.AP);
		mapView.addMapCircle(this.TO);
		mapView.addMapCircle(this.MR);
		mapView.addMapCircle(this.CE);
		mapView.addMapCircle(this.PI);
		mapView.addMapCircle(this.RN);
		mapView.addMapCircle(this.AL);
		mapView.addMapCircle(this.PB);
		mapView.addMapCircle(this.PE);
		mapView.addMapCircle(this.SE);
		mapView.addMapCircle(this.BA);
		
		*/
	}
}
