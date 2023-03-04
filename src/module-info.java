module ChristopherThomsonGradingCalculator1 {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.base;
	requires java.desktop;
	requires javafx.graphics;
	requires junit;
	
	opens application to javafx.graphics, javafx.fxml;
}
